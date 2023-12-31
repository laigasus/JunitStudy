package ch02;

public class Answer {
   private final int i;
   private final Question question;

   public Answer(Question question, int i) {
      this.question = question;
      this.i = i;
   }

   public String getQuestionText() {
      return question.getText();
   }

   @Override
   public String toString() {
      return String.format("%s %s", question.getText(), question.getAnswerChoice(i));
   }

   public boolean match(Answer otherAnswer) {
      return question.match(i, otherAnswer.i);
   }

}
