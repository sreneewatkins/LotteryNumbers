package com.tts;

public class AsciiChars {
    //first step in project is to create a separate class
    // that will print valid characters to the user. this is a
    //simple utility class that's called from the main()
    //since the following are static methods they can be called
    //from within the main() using, ex: AsciiChars.printNumbers();

    public static void printNumbers() {
        //dec: 48-57 => 0-9
        for (int i = 48; i <= 57; i++) {
            System.out.print((char)i);
        }
        System.out.println();
    }

    public static void printLowerCase() {
        //dec: 97-122 => a-z
        for (int i = 97; i <= 122; i++) {
            System.out.print((char)i);
        }
        System.out.println();
    }

    public static void printUpperCase() {
        //dec: 65-90 => A-Z
        for (int i = 65; i <= 90; i++) {
            System.out.print((char)i);
        }
        System.out.println();
    }

}//end AsciiChars

