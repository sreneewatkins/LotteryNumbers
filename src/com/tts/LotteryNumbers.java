package com.tts;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class LotteryNumbers {

    public static String questionnaire() {
        Scanner qInput = new Scanner(System.in);
        Scanner secScan = new Scanner(System.in);

        System.out.println("\nDo you have a red car? (y/n)");
        String redCar = qInput.next();
        System.out.println("\nWhat is the name of your favorite pet?");
        String favPet = secScan.nextLine();
        System.out.printf("\nHow old is %s?\n", favPet);
        int age = qInput.nextInt();
        System.out.println("\nWhat is your lucky number?");
        int luckyN = qInput.nextInt();
        //TODO: catch errors ie when input is not a #
        System.out.println("\nDo you have a favorite athlete? (y/n)");
        String favQrtr = qInput.next();
        int jerseyN = 0;
        if (favQrtr.charAt(0) == 'y' || favQrtr.charAt(0) == 'Y') {
            System.out.println("\nWhat is their jersey number?");
            jerseyN = qInput.nextInt();
        }
        System.out.println("\nWhat is the two-digit model year of your car?");
        int carYr = qInput.nextInt();
        System.out.println("\nWhat is the FIRST name of your favorite actor?");
        String favActor = qInput.next();
        int number1;
        do {
            System.out.println("\nEnter a number between 1 and 50");
            number1 = qInput.nextInt();
        } while ( number1 < 1 || number1 > 50);
        int number2;
        do {
            System.out.println("\nEnter a number between 1 and 65");
            number2 = qInput.nextInt();
        } while ( number2 < 1 || number2 > 65);

        String[] answers = new String[]{
                redCar, favPet, (String.valueOf(age)), (String.valueOf(luckyN)),
                favQrtr, (String.valueOf(jerseyN)), (String.valueOf(carYr)),
                favActor, (String.valueOf(number1)) , (String.valueOf(number2))
        };
        System.out.println("Your answers are:");
        for (String eachAns : answers) {
            System.out.print(eachAns + ", ");
        }

        calculateNumbers(answers);

        //codes: 0 quit, 1 regenerate, 2 play again
        int answerCode;
        do {
            System.out.println("""
                    Would you like to:
                    A) regenerate your lottery numbers based on your current answers OR
                    B) play again OR
                    C) quit
                    Please select A B or C""");
            char answer = qInput.next().charAt(0);
            answerCode = InputInterpretation.playAgainCode(answer);
            if (answerCode == 1) {
                calculateNumbers(answers);
            }
        } while ( answerCode != 0 );

        return "\nThank you for playing";
    }

    public static void calculateNumbers(String[] answers) {
        //this method will output 5 random #'s btw 1 and 65 and a magic ball btw 1 and 75
        //we need 3 random integers. declare constants for the max vals of ea set of #'s
        //check for out of bound # and fix by subtracting maxnum 65 or 75
//        System.out.println("\nentered calculateNumbers method");
        int maxMagicNum = 75;
        int rando1 = (int)(Math.random()*(10))+1;
        int rando2 = (int)(Math.random()*(10))+1;
        int rando3 = (int)(Math.random()*(10))+1;
        System.out.printf("randoms: %s, %s, %s\n", rando1, rando2, rando3);

        //generate "magic ball" first. Lucky #(4th in array) multiplied by one of random #
//        System.out.printf("Lucky # %s * rando1 %s\n", Integer.parseInt(answers[3]), rando1);
        int magicBall = (Integer.parseInt(answers[3])) * rando1;
        while (magicBall > maxMagicNum) {
//            System.out.println("fixing magicBall number " + magicBall);
            magicBall -= maxMagicNum;
            if (magicBall == 0) { magicBall += 1; }
        }
        System.out.println("Magic Ball: " + magicBall + "\n");

        //generate 8 other lottery numbers
        //find the 3rd letter of favePet (2nd in arr)and convert char to int
        //this will fail if the pet name has fewer than 3 letters :(
        int lotto1 = answers[1].charAt(0);

        //use 2 digit model yr (7th in arr) of car and + lucky# (4th in arr)
        int lotto2 = (Integer.parseInt(answers[6])) + (Integer.parseInt(answers[3]));

        //use rando btw 1 and 50 (9th in arr) and - rando2
        int lotto3 = (Integer.parseInt(answers[8])) - rando2;

        //convert first letter of faveactor (8th in arr) to int * rando3
        int lotto4 = answers[7].charAt(0) * rando3;

        //convert last letter of faveactor (8th in arr) to int
        int lotto5 = answers[7].charAt(answers[7].length() - 1);

        //use age of favePet (3rd in arr) + car model yr (7th in arr)
        int lotto6 = Integer.parseInt(answers[2]) + Integer.parseInt(answers[6]);

        //fave athlete# (6th in arr) + pet age (3rd in arr) + lucky# (4th in arr)
        int lotto7 = Integer.parseInt(answers[5]) + Integer.parseInt(answers[2])
                + Integer.parseInt(answers[3]);

        //user picks number between 1 and 65 (10th in arr)
        int lotto8 = Integer.parseInt(answers[9]);

        //add numbers to array
        int[] lottoNumbers = new int[] {
                lotto1, lotto2, lotto3, lotto4, lotto5, lotto6, lotto7, lotto8
        };

        //print lotto# list
        System.out.println("Your possible lotto numbers:");
        for (int eachNum : lottoNumbers) {
            System.out.print(eachNum + ", ");
        }
        System.out.println();

        //check for duplicates and fix range
        //streamlined by passing lotto#'s arr to fn that checks and fixes
        int[] fixedLottNums = fixNumbers(lottoNumbers);

        //print lotto#'s after fix
        System.out.println("Your possible lotto numbers fixed:");
        for (int eachNum : fixedLottNums) {
            System.out.print(eachNum + ", ");
        }
        System.out.println();

        //display 5 Lottery numbers: # and Magic ball: #
        //create a Random class object
        Random randIndex = new Random();
        //create a HashSet to get only unique elements (no dups)
        Set<Integer> winningIndices = new LinkedHashSet<Integer>();
        //generate random #s w/Random class nextInt and add them to the set
        while (winningIndices.size() < 5) {
            winningIndices.add(randIndex.nextInt(8));
        }
        System.out.println("Winning Indices: " + winningIndices);
        System.out.print("\nLottery numbers: ");
        //print the lotto# from lottoNumbers arr that corresponds to random indexes generated above
        for ( int num : winningIndices ) {
            System.out.print(lottoNumbers[num] + ", ");
        }
        System.out.println("Magic Ball: " + magicBall);

    }//end calculateNumbers

    public static int[] fixNumbers(int [] lotteryNums) {
        //check for duplicates and fix the range in one swoop
        //may need/effecient to pass array of numbers
        int maxLottNum = 65;

        for (int i = 0; i < lotteryNums.length; i++) {
            while (lotteryNums[i] > maxLottNum) {
                lotteryNums[i] -= maxLottNum;
            }
            while (lotteryNums[i] < 1 ) {
                lotteryNums[i] += maxLottNum;
            }
            for (int j= 0; j < lotteryNums.length; j++) {
                if (i != j) {
                    if (lotteryNums[i] == lotteryNums[j]){
//                        System.out.print(lotteryNums[j]);
                        lotteryNums[j] += 1;
//                        System.out.println(" is now " + lotteryNums[j]);
                    }
                }
            }
        }

        return lotteryNums;
    }


}//end LotteryNumbers
