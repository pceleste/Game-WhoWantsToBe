package org.academiadecodigo.argicultores;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;


public class Server {

    /**
     * Default port to run the server on
     */
    public final static int DEFAULT_PORT = 8080;

    /**
     * Synchronized List of worker threads, locked by itself
     */

    ServerWorker serverWorker;
    boolean isFirst = false;
    int answerIndex = 0;
    boolean isfinishC1 = false;
    boolean isfinishC2 = false;
    ServerSocket serverSocket;
    Socket clientSocket;
    int scoreP1 = 0;
    int scoreP2 = 0;
    int connectionCount;
    boolean isStartedC1 = false;
    boolean isStartedC2 = false;

    public Server() {

        int port = DEFAULT_PORT;

        try {
            start(port);

        } catch (NumberFormatException ex) {

            System.exit(1);
        }
    }


    public void start(int port) {

        System.out.println("DEBUG: Server instance is : " + this);

        connectionCount = 0;

        try {

            // Bind to local port
            System.out.println("Binding to port " + port + ", please wait  ...");
            serverSocket = new ServerSocket(port);
            System.out.println("Server started: " + serverSocket);

            while (true) {

                // Block waiting for client connections

                clientSocket = serverSocket.accept();

                System.out.println("Client accepted: " + clientSocket);

                try {

                    // Create a new Server Worker
                    connectionCount++;
                    String name = "Player-" + connectionCount;
                    serverWorker = new ServerWorker(name, clientSocket);

                    // Serve the client connection with a new Thread
                    Thread thread = new Thread(serverWorker);
                    thread.setName(name);
                    thread.start();

                } catch (IOException ex) {
                    System.out.println("Error receiving client connection: " + ex.getMessage());
                }

            }

        } catch (IOException e) {
            System.out.println("Unable to start server on port " + port);
        }

    }


    private class ServerWorker implements Runnable {

        // Immutable state, no need to lock
        final private String name;
        final private Socket clientSocket;
        final private BufferedReader in;
        final private BufferedWriter out;
        PrintStream out1;
        private int score;
        boolean endgame = false;
        Prompt prompt = null;


        private ServerWorker(String name, Socket clientSocket) throws IOException {

            this.name = name;
            this.clientSocket = clientSocket;
            out1 = new PrintStream(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        }

        public String getName() {
            return name;
        }


        @Override
        public void run() {

            if(connectionCount > 2){
                try {
                    out1.write("There are no more slots available.".getBytes(StandardCharsets.UTF_8));
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            System.out.println("Player " + name + " entered");


            try {
                out1.write(menu().getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (!clientSocket.isClosed()) {

                if (this.name.equals("Player-1") && answerIndex == 0) {
                    out1.write(MenuChoice());
                    isStartedC2 = true;
                    isStartedC1 = true;
                }
                if (!isStartedC1 || !isStartedC2) {
                    continue;
                }


                if (endgame) {
                    if (this.name.equals("Player-1")) {
                        scoreP1 = score;
                    }
                    if (this.name.equals("Player-2")) {
                        scoreP2 = score;
                    }
                    if (!isfinishC1 || !isfinishC2) {
                        continue;
                    }

                    try {
                        String finalMessage = "Congratulations you finished with a score of " + score + " out of 10.\n";
                        out1.write(finalMessage.getBytes(StandardCharsets.UTF_8));
                        if(scoreP1 > scoreP2){
                            if(this.name.equals("Player-1")){
                                out1.write("YOU WIN!".getBytes(StandardCharsets.UTF_8));
                            } else {
                                out1.write("YOU LOSE!".getBytes(StandardCharsets.UTF_8));
                            }
                        } else if(scoreP1 < scoreP2){
                            if(this.name.equals("Player-2")){
                                out1.write("YOU WIN!".getBytes(StandardCharsets.UTF_8));
                            } else {
                                out1.write("YOU LOSE!".getBytes(StandardCharsets.UTF_8));
                            }
                        }else {
                            out1.write("DRAW!".getBytes(StandardCharsets.UTF_8));
                        }
                        clientSocket.close();
                        serverSocket.close();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                send();
            }
        }


        public int MenuChoice() {

            try {
                prompt = new Prompt(clientSocket.getInputStream(), out1);
                String[] options = {"...a Programmer", "...a MagnificOx"};
                MenuInputScanner scanner = new MenuInputScanner(options);
                scanner.setMessage("Choose a version: ");
                answerIndex = prompt.getUserInput(scanner);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return answerIndex;
        }

        private void send() {
            switch (answerIndex) {

                case 1:
                    try {
                        out1.write("Welcome to the Who Wants to be a Programmer Quiz\n".getBytes(StandardCharsets.UTF_8));
                        prompt = new Prompt(clientSocket.getInputStream(), out1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    LinkedList<QandA> questions = new LinkedList<>();

                    questions.add(QandA.Q1);
                    questions.add(QandA.Q2);
                    questions.add(QandA.Q3);
                    questions.add(QandA.Q4);
                    questions.add(QandA.Q5);
                    questions.add(QandA.Q6);
                    questions.add(QandA.Q7);
                    questions.add(QandA.Q8);
                    questions.add(QandA.Q9);
                    questions.add(QandA.Q10);

                    int count = 0;
                    while(count < 10) {
                        MenuInputScanner scanner = new MenuInputScanner(questions.get(count).getAnswer());

                        scanner.setMessage(questions.get(count).getQuestion());
                        int answerIndex = prompt.getUserInput(scanner);
                        try {
                            if (questions.get(count).getAnswer()[answerIndex - 1].equals(questions.get(count).getCorrectAnswer())) {
                                score++;
                                String message = "Correct Answer! Your score is currently: " + score + "\n";
                                out1.write(message.getBytes(StandardCharsets.UTF_8));
                                count++;
                            } else {
                                out1.write("Wrong answer Dumbass!\n".getBytes(StandardCharsets.UTF_8));
                                count++;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    endgame = true;
                    break;
                case 2:
                    try {
                        out1.write("Welcome to the Who Wants to be a Magnific0x Quiz\n".getBytes(StandardCharsets.UTF_8));
                        prompt = new Prompt(clientSocket.getInputStream(), out1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    LinkedList<QandM> questionsM = new LinkedList<>();
                    questionsM.add(QandM.Q1);
                    questionsM.add(QandM.Q2);
                    questionsM.add(QandM.Q3);
                    questionsM.add(QandM.Q4);
                    questionsM.add(QandM.Q5);
                    questionsM.add(QandM.Q6);
                    questionsM.add(QandM.Q7);
                    questionsM.add(QandM.Q8);
                    questionsM.add(QandM.Q9);
                    questionsM.add(QandM.Q10);

                    int countM = 0;
                    while(countM < 10) {
                        MenuInputScanner scanner = new MenuInputScanner(questionsM.get(countM).getAnswer());

                        scanner.setMessage(questionsM.get(countM).getQuestion());
                        int answerIndex = prompt.getUserInput(scanner);
                        try {
                            if (questionsM.get(countM).getAnswer()[answerIndex - 1].equals(questionsM.get(countM).getCorrectAnswer())) {
                                score++;
                                String message = "Correct Answer! Your score is currently: " + score + "\n";
                                out1.write(message.getBytes(StandardCharsets.UTF_8));
                                countM++;
                            } else {
                                out1.write("Wrong answer Dumbass!\n".getBytes(StandardCharsets.UTF_8));
                                countM++;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    endgame = true;
                    break;

            }
            if (this.name.equals("Player-1")) {
                isfinishC1 = true;
            }
            if (this.name.equals("Player-2")) {
                isfinishC2 = true;
            }
        }

    }


    public String menu() {
        String str = "\n" +
                " __          ___                                 _         _          _             ___  \n" +
                " \\ \\        / / |                               | |       | |        | |           |__ \\ \n" +
                "  \\ \\  /\\  / /| |__   ___   __      ____ _ _ __ | |_ ___  | |_ ___   | |__   ___      ) |\n" +
                "   \\ \\/  \\/ / | '_ \\ / _ \\  \\ \\ /\\ / / _` | '_ \\| __/ __| | __/ _ \\  | '_ \\ / _ \\    / / \n" +
                "    \\  /\\  /  | | | | (_) |  \\ V  V / (_| | | | | |_\\__ \\ | || (_) | | |_) |  __/_ _|_|  \n" +
                "     \\/  \\/   |_| |_|\\___/    \\_/\\_/ \\__,_|_| |_|\\__|___/  \\__\\___/  |_.__/ \\___(_|_|_)  \n" +
                "                                                                                         \n" +
                "                                                                                         \n";
        return str;
    }

}


