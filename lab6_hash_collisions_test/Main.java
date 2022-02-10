package AlgoLab6;
/*
 * COMP 3760, Winter (Spring) 2021, Lab 6
 *
 * Eric Dong, A01170099
 * Testing out number of collisions using 3 different hashing algorithms.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final int ASCII_VALUE_TO_REMOVE = 64;

    
    /** Hashes string.
     * @param string String to hash.
     * @param N Constant to modulo result.
     * @return int Hashed result.
     */
    public static int H1(String string, int N) {
        int h = 0;

        for (char stringChar: string.toCharArray()) {
            h += (stringChar - ASCII_VALUE_TO_REMOVE);
        }
        return h % N;
    }

    
    /** Hashes string.
     * @param string String to hash.
     * @param N Constant to modulo result.
     * @return int Hashed result.
     */
    public static int H2(String string, int N) {
        double h = 0;
        double i = 0;
        for (char stringChar: string.toCharArray()) {
            h += (stringChar - ASCII_VALUE_TO_REMOVE) * Math.pow(26, i);
            i++;
        }
        return (int) (h % N);
    }

    
    /** Hashes string.
     * @param string String to hash.
     * @param N Constant to modulo result.
     * @return int Hashed result.
     */
    public static int H3(String string, int N) {
        double h = 0;
        double i = 0;
        for (char stringChar: string.toCharArray()) {
            // changed to prime number to reduce collisions.
            h += (stringChar - ASCII_VALUE_TO_REMOVE) * Math.pow(31, i);
            i++;
        }
        return (int) (h % N);
    }
    
    
    /** 
     * @param names Names to hash.
     * @param hashSize Size of hash.
     * @return int Number of collisions.
     */
    public static int countCollisions(String[] names, int hashSize) {
        String[] hashMap = new String[hashSize];
        Arrays.fill(hashMap, "null");
        int numOfCollisions = 0;

        for (String name : names) {
            int hash = H3(name, hashSize);
            int temp = hash;
            while (!hashMap[hash].equals("null") && !hashMap[hash].equals(name)) {
                hash = (hash + 1) % hashSize;
            }
            if (hash != temp) {
                numOfCollisions += 1;
            }
            hashMap[hash] = name;
        }
        return numOfCollisions;
    }

    
    /** Drives program.
     * @param args Not used.
     * @throws FileNotFoundException Throws exception if file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        String INPUT_FILE_NAME_37 = "37_names.txt";
        String INPUT_FILE_NAME_333 = "333_names.txt";
        String INPUT_FILE_NAME_5163 = "5163_names.txt";
        String[] names = read_input_file(INPUT_FILE_NAME_37);

        System.out.println(countCollisions(names, 37));
        System.out.println(countCollisions(names, 74));
        System.out.println(countCollisions(names, 185));
        System.out.println(countCollisions(names, 370));

//        System.out.println(countCollisions(names, 333));
//        System.out.println(countCollisions(names, 666));
//        System.out.println(countCollisions(names, 1665));
//        System.out.println(countCollisions(names, 3330));

//        System.out.println(countCollisions(names, 5163));
//        System.out.println(countCollisions(names, 10326));
//        System.out.println(countCollisions(names, 25815));
//        System.out.println(countCollisions(names, 51630));
    }

    
    /** 
     * @param fname File to read.
     * @return String[] Array read from file.
     * @throws FileNotFoundException Throws exception if the file is not found.
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
