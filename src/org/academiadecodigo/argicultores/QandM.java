package org.academiadecodigo.argicultores;

public enum QandM {

    Q1(1, "In what year was Valentine born?",
            "2020",
            "2001",
            "Ontem",
            "2002",
            "2002"),

    Q2(2, "Which class is Uncle Rolo's favorite?",
            "Shellmurais",
            "Loopeytunes",
            "Magnific0x",
            "Rapunshells",
            "Magnific0x"),

    Q3(3, "How many Gustavo's clones are there?",
            "1",
            "2",
            "3",
            "4",
            "2"),

    Q4(4, "What is the responsibility of Thread of detail?",
            "Ask the students to arrange the Puffs.",
            "Making sure that code cadets are satisfied.",
            "Ask code cadets to lay down under the desk and close their eyes",
            "Boicotar.",
            "Making sure that code cadets are satisfied."),

    Q5(5, "What is the probability that Elder will be late?",
            "10%",
            "50%",
            "25%",
            "Depends on the day before",
            "Depends on the day before"),

    Q6(6, "A basketball is used for...",
            "Playing basketball.",
            "Making Raquel worried.",
            "Destroy Mac’s",
            "Playing drinking games",
            "Making Raquel worried."),

    Q7(7, "Simão...",
            "loves to present Summerizers.",
            "is from Vila do Conde.",
            "is 35 years old.",
            "a guy who misses opportunities.",
            "a guy who misses opportunities."),

    Q8(8, "What does Yevheniy drink for breakfast?",
            "Vodka",
            "Water",
            "Gustavo’s milk",
            "Jolas",
            "Vodka"),

    Q9(9, "Whats is the effect of the 'Cachaça de Jambú'?",
               "Removing all the cables from Mac's",
               "Headache",
               "Make the mouth numb",
               "Programming like crazy!",
            "Make the mouth numb"),

    Q10(10, "Uncle Rolo is:",
            "Playboy from Cascais.",
            "Gustavo's lost father",
            "Thread of detail",
            "Vim seller",
            "Playboy from Cascais.");


    private int numQuestion;
    private String question;
    private String[] answer = new String[4];
    private String correctAnswer;


    QandM(int numQuestion, String question, String answer1 , String answer2, String answer3, String answer4, String correctAnswer){
        this.numQuestion = numQuestion;
        this.question = question;
        this.correctAnswer = correctAnswer;
        answer[0] = answer1;
        answer[1] = answer2;
        answer[2] = answer3;
        answer[3] = answer4;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswer() {
        return answer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }



}
