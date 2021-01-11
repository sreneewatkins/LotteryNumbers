package com.tts;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //create a simple java project that prints "Hello World"
        //this will verify your environment, settings, and ability to
        //run a project on the command line
        System.out.println("Hola Mundo");

        //here we can call the static methods defined in the AsciiChars class
        AsciiChars.printNumbers();
        AsciiChars.printLowerCase();
        AsciiChars.printUpperCase();

        //interact with the user using the scanner class
        Scanner input = new Scanner(System.in);
        System.out.println("\nPlease enter your name:");
        //what is the prbolem with .next() and .nextline()
        String userName = input.nextLine();
        System.out.println("Hello " + userName);

        //ask if user wishes to continue to interactive portion
        //if yes, continue, otherwise quit with a msg
        //The checkAnswer method and shortening of the string to a char
        //could be handled more easily with an if else. I was just trying
        //something different and learning all kinds of things.
        System.out.println("\nWould you like to continue to the " +
                "interactive portion of this project? (y/n)");
        String userAnswer = input.next();
//        System.out.println("userAnswer: " + userAnswer);

        char shorterAnswer = userAnswer.charAt(0);
//        System.out.println("shorterAnswer: " + shorterAnswer);

        boolean keepGoing = InputInterpretation.checkContinue(shorterAnswer);

        //return a string that says thanks for participating or see you next time
        String msgToUser = (keepGoing ? LotteryNumbers.questionnaire() : exit() );
        System.out.println(msgToUser);

    }//end main

    public static String exit() {
//        System.out.println("\nentered exit method");
        return "\nTry again later";
    }

}//end Main
