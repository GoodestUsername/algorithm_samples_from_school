import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/*
 * COMP 3760, Winter (Spring) 2021, Lab 3
 *
 * Eric Dong, A01170099
 * Find the highest alpha and zeta scores in a text file of names.
 */

public class maxAlphaZetaInFile {
    private static final int ASCII_VALUE_TO_REMOVE = 64;

    
    /** Runs the program.
     * @param args not used.
     * @throws FileNotFoundException Throws exception if the file to open does not exist.
     */
    public static void main(String[] args) throws FileNotFoundException {

        /*
         * CHANGE THE INPUT FILE NAME HERE:
         */
        String INPUT_FILE_NAME = "14_names.txt";
        String[] names = read_input_file(INPUT_FILE_NAME);


        /* 
         * ASSIGNMENT PART 3: Code for your main-body processing starts here.
         * REPLACE THIS WITH YOUR OWN CODE:
         */
        long numOfNames = names.length;

        // dont run if there are no names
        if (numOfNames != 0 ) {

            String nameOfHighestAlpha = "";
            String nameOfHighestZeta = "";

            int highest_alpha = 0;
            long highest_zeta = 0;

            // loop through every name in file to look for name and values of the highest alpha and zeta scores.
            for (String name : names) { // for each name in the list of names

                int current_alpha = alpha_score(name); // get the alpha score the current name
                long current_zeta = zeta_score(name); // get the zeta score the current name

                if (current_alpha > highest_alpha) {  // if the current is greater than the old highest alpha score
                    highest_alpha = current_alpha; // set the highest alpha to the current alpha
                    nameOfHighestAlpha = name; // set the name to the current name
                }

                if (current_zeta > highest_zeta) {  // if the current is greater than the old highest zeta score
                    highest_zeta = current_zeta; // set the highest alpha to the current zeta
                    nameOfHighestZeta = name; // set the name to the current name
                }
            }

            // output file parsed and number of names parsed
            System.out.println("Input file is " + INPUT_FILE_NAME);
            System.out.println(numOfNames + " names processed");

            // output results of highest alpha and zeta
            System.out.println("Best alpha_score is " + nameOfHighestAlpha + " with " + highest_alpha);
            System.out.println("Best zeta_score is " + nameOfHighestZeta + " with " + highest_zeta);
        }

        // if there are no names, print msg telling usr the file did not contain valid names.
        else {
            System.out.println("File is does not contain valid names.");
        }


//        System.out.println(Arrays.toString(names));
//        System.out.println(alpha_score(names[4]));
//        System.out.println(zeta_score(names[5]));
//        System.out.println("Hello, world");
//        System.out.println("JUSTIN alpha score is " + alpha_score("JUSTIN"));
//        System.out.println("JUSTIN zeta score is " + zeta_score("JUSTIN"));

    }

    
    /** Calculates alpha score.
     * @param name string to calculate alpha score for.
     * @return long alpha score calculated.
     */
    public static int alpha_score(String name) {
        int alpha_score = 0;

        for (char nameChar: name.toCharArray()) { // for each character in the name
            alpha_score += nameChar - ASCII_VALUE_TO_REMOVE; // calculate the sum of the current char with alpha_score.
        }
        return alpha_score;
    }

    
    /** Calculates zeta score.
     * @param name string to calculate zeta score for.
     * @return long zeta score calculated.
     */
    public static long zeta_score(String name) {
        int zeta_score = 1;

        for (char nameChar: name.toCharArray()) { // for each character in the name
            zeta_score *= (nameChar - ASCII_VALUE_TO_REMOVE); // calculate product of the current char with zeta_score.
        }
        return zeta_score;
    }

    
    /** Reads the input file and return an array of strings
     * containing the names to be processed.
     * @param fname string file name.
     * @return String[] string list of names.
     * @throws FileNotFoundException Exception thrown if the file is not found.
     */
    public static String[] read_input_file(String fname) throws FileNotFoundException {
        File file = new File(fname);
        Scanner sc = new Scanner(file);
        String str = sc.nextLine();
        sc.close();
        String[] names = str.split(",");
        return names;
    }

}
