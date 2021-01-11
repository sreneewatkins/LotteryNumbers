package com.tts;

public class InputInterpretation {

    public static boolean checkContinue(char userShorterAns) {
        //TODO: insert ternary operation OR switch case to return boolean
        //This method is probably overkill. just wanted to test my ability to exe a switch
        //change method to return String and uncomment all of the msg's for testing purposes
        //above testing comment goes with the old switch located in src/notes.txt
        boolean flag;
        switch (userShorterAns) {
            case 'y', 'Y' -> flag = true;
            case 'n', 'N' -> flag = false;
            default -> {
                flag = false;
                System.out.println("Incorrect Input: Please start over");
            }
        }
        return flag;
    }//end checkAnswer

    public static int playAgainCode(char userAns) {
        //codes: 0 quit, 1 regenerate, 2 play again
        int code;
        switch (userAns) {
            case 'A', 'a' -> { code = 1; }
            case 'B', 'b' -> { code = 2; LotteryNumbers.questionnaire(); }
            case 'C', 'c' -> { code = 0; Main.exit(); }
            default -> {
                System.out.println("Incorrect Input: You should have entered a valid letter choice. Bye Bye");
                code = 0;
                Main.exit();
            }
        }
//        System.out.println(" answer code: " + code);
        return code;

    }//end playAgainCode

}//end class
