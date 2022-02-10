import java.util.*;

/*
 * COMP 3760, Winter (Spring) 2021, Lab 4
 *
 * Eric Dong, A01170099
 * Find all perms of 0 1 2 for a given length, and find all for a given length without repeated adjacent digits.
 */

public class algoLab4 {
    static String red = "0";
    static String white = "1";
    static String green = "2";
    static ArrayList<String> colors = new ArrayList<>(Arrays.asList(red, white, green)); // can be put in function.

    
    /**  Generates permutations for an array list string.
     * @param size case see line 10-14
     * @return ArrayList<String> permutations to calculate for
     */
    public static ArrayList<String> generatePerms(int size) {

        // if its not base case
        if (size > 1) {

            // generate perms of size - 1 for colors
            ArrayList<String> smallerPerms = generatePerms(size - 1);

            // empty list to put newly generated strings
            ArrayList<String> allPerms = new ArrayList<>();

            // each color will need to be added to the end in each of the size - 1 perms
            for (String color : colors) {

                // color will be added for each smaller perm then put into allPerms
                for (String permutation : smallerPerms) {

                    // append current color to end of old smaller perm string
                    String newPermutation = permutation + color;

                    // put into allPerms
                    allPerms.add(newPermutation);
                }
            }

            // return all generated perms
            return allPerms;
        }

        // return arraylist containing "0", "1", "2" as base case, could be put into function, wanted to do less typing
        return colors;
    }

    
    /** Generates permutations with no repeating adjacent digits.
     * @param size case see line 10-14.
     * @return ArrayList<String> permutations to calculate for.
     */
    public static ArrayList<String> generatePermsNoRepeatAdjDigits(int size) {

        // if its not base case
        if (size > 1) {

            // generate perms of size - 1 for colors
            ArrayList<String> smallerPerms = generatePermsNoRepeatAdjDigits(size - 1);

            // empty list to put newly generated strings
            ArrayList<String> allPerms = new ArrayList<>();

            // each color except the color added last will need to be added to the end in each of the size - 1 perms
            for (String color : colors) {

                // valid color will be added for each smaller perm then put into allPerms
                for (String permutation : smallerPerms) {

                    // check if the last color is the current color
                    if (!color.equals(permutation.substring(permutation.length() - 1))) {

                        // append current color to end of old smaller perm string
                        String newPermutation = permutation + color;

                        // put into allPerms
                        allPerms.add(newPermutation);
                    }
                }
            }

            // return all generated perms
            return allPerms;
        }

        // return arraylist containing "0", "1", "2" as base case, could be put into function, wanted to do less typing
        return colors;
    }

    
    /**  Drives program.
     * @param args not used.
     */
    public static void main(String[] args) {
        System.out.println("Generating all patterns of a given length:");

        for (int i = 1; i != 11; i++) {
            System.out.println("Length " + i + " produces " + generatePerms(i).size() + " patterns.");
        }

        System.out.println("\nGenerating patterns without double-digits:");

        for (int i = 1; i != 11; i++) {
            System.out.println("Length " + i + " produces " + generatePermsNoRepeatAdjDigits(i).size() + " patterns.");
        }
    }
}
