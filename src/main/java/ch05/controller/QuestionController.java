package ch05.controller;

import ch05.domain.BooleanQuestion;
import ch05.domain.PercentileQuestion;
import ch05.domain.Persistable;
import ch05.domain.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.Clock;
import java.util.List;
import java.util.function.Consumer;

public class QuestionController {
    private Clock clock = Clock.systemUTC();
    // ...

    private static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("default");
    }

    public Question find(Integer id) {
        return em().find(Question.class, id);
    }

    public List<Question> getAll() {
        return em()
                .createQuery("select q from Question q", Question.class)
                .getResultList();
    }

    public List<Question> findWithMatchingText(String text) {
        String query =
                "select q from Question q where q.text like '%" + text + "%'";
        //noinspection SqlSourceToSinkFlow
        return em().createQuery(query, Question.class).getResultList();
    }

    @SuppressWarnings("UnusedReturnValue")
    public int addPercentileQuestion(String text, String[] answerChoices) {
        return persist(new PercentileQuestion(text, answerChoices));
    }

    public int addBooleanQuestion(String text) {
        return persist(new BooleanQuestion(text));
    }

    void setClock(Clock clock) {
        this.clock = clock;
    }
    // ...

    void deleteAll() {
        executeInTransaction(
                (em) -> em.createNativeQuery("delete from Question where true")
                        .executeUpdate());
    }

    private void executeInTransaction(Consumer<EntityManager> func) {
        EntityManager em = em();
        EntityTransaction transaction = em.getTransaction();
        try (em) {
            transaction.begin();
            func.accept(em);
            transaction.commit();
        } catch (Throwable t) {
            System.err.println(t.getStackTrace()[0]);
            transaction.rollback();
        }
    }

    private int persist(Persistable object) {
        object.setCreateTimestamp(clock.instant());
        executeInTransaction((em) -> em.persist(object));
        return object.getId();
    }

    private EntityManager em() {
        return getEntityManagerFactory().createEntityManager();
    }
}
