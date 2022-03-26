package org.academiadecodigo.argicultores;

public enum QandA {

    Q1(1, "What are the four pillars of OOP?",
            "Abstraction, Encapsulation, Inheritance, Polymorphism.",
            "Inheritance, Composition, Classes, Abstraction.",
            "Composition, Abstraction, Encapsulation, Methods.",
            "Classes, Methods, Properties, Constructors.",
            "Abstraction, Encapsulation, Inheritance, Polymorphism."),

    Q2(2, "What is the definition of Garbage Collector?",
            "It is the process by which Java programs perform automatic memory management.",
            "It is the process responsible for cleaning up unused variables and methods.",
            "It is the process responsible for recycling the threads.",
            "None of the cases mentioned",
            "It is the process by which Java programs perform automatic memory management."),

    Q3(3, "What is an Exception?",
            "An abnormal event that occurs during the execution of a program that interrupts the flow of the program.",
            "It is an event used to fix bugs in the program.",
            "A Java interface for error handling.",
            "An event that occurs exceptionally when the program encounters an infinite loop.",
            "An abnormal event that occurs during the execution of a program that interrupts the flow of the program."),

    Q4(4, "What is the difference between list and map",
            "None, because map is also a list.",
            "The list is an ordered sequence of values, while the map is a key-value relationship, and the value must be unique.",
            "The list is an ordered sequence of values, while the map is a sequence of key-value relationships, where the key must be unique.",
            "Both implement the Collection interface, but the List is an iterable sequence and the Map is not.",
            "The list is an ordered sequence of values, while the map is a sequence of key-value relationships, where the key must be unique."),

    Q5(5, "Is it possible to have a private method in an interface?",
            "Yes, the interface only supports private methods.",
            "No, the interface only supports public and default methods",
            "No, all interface methods are automatically static.",
            "Yes, but private methods in interfaces must have a body.",
            "Yes, but private methods in interfaces must have a body."),

    Q6(6, "How many constructors can we have in a class?",
            "There is no limit to the number of constructors.",
            "65535 constructors.",
            "Maximum 5 constructors.",
            "At a maximum equal to the number of properties in the class.",
            "65535 constructors."),

    Q7(7, "Which of these is one of the differences between an interface and an abstract class?",
            "The abstract class can be implemented, whereas the interface can be extended.",
            "A class can only use one interface, but can use multiple abstract classes.",
            "The abstract class is used to implement the core identity of class and the interface is used to implement peripheral abilities of class.",
            "The performance of an interface is fast and the abstract class of interface is slow because it requires time to search actual method in the corresponding class.",
            "The abstract class is used to implement the core identity of class and the interface is used to implement peripheral abilities of class."),

    Q8(8, "What is Polymorphism?",
            "Allows a method to return different types depending on its current state.",
            "Is to create several different methods with the same name, but with different signatures, each with its own implementation.",
            "Feature that allows for the decision of which method to execute to be delayed to runtime.",
            "It is a feature of java that allows a variable to receive different types during a run",
            "Feature that allows for the decision of which method to execute to be delayed to runtime."),

    Q9(9, "What is Overriding?",
            "It is a method that forces you to use the behavior of the inherited class.",
            "It is a feature of Java that serves to redirect the code of the parent class.",
            "It is to replace in the parent class a method that was inherited.",
            "It is to define new behavior for a method that already exists.",
            "Is to define new behavior for a method that already exists."),

    Q10(10, "What is the difference between == and .equals() method in Java?",
            ".equals returns a boolean and == returns an integer.",
            "== is for assigning values to variables, while .equals() compares these values.",
            ".equals() is a method, and == is the operator. Both are used for comparison.",
            "== compares the value stored in heap, while .equals() compares the value stored in memory.",
            ".equals() is a method, and == is the operator. Both are used for comparison.");

    private int numQuestion;
    private String question;
    private String[] answer = new String[4];
    private String correctAnswer;


    QandA(int numQuestion, String question, String answer1 , String answer2, String answer3, String answer4, String correctAnswer){
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
