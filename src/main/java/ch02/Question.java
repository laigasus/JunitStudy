package ch02;

public abstract class Question {
   private final String text;
   private final String[] answerChoices;

   public Question(int ignoredId, String text, String[] answerChoices) {
      this.text = text;
      this.answerChoices = answerChoices;
   }

   public String getText() {
      return text;
   }
   
   public String getAnswerChoice(int i) {
      return answerChoices[i];
   }

   abstract public boolean match(int expected, int actual);

}
