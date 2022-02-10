package algoLab5;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * COMP 3760, Winter (Spring) 2021, Lab 5, set D
 *
 * Eric Dong, A01170099
 * Find smallest missing number after sorting an array filled with integers from a given file.
 */
public class findThePasscode {

	
	/** Drives the function.
	 * @param args not used.
	 * @throws IOException io throws IO exception when the program fails to open.
	 */
	public static void main(String[] args) throws IOException {
		
		//
		// Set the input data file name here
		//
		//length_2_N_44.txt
		//length_3_N_429.txt
		//length_4_N_0930.txt
		//length_4_N_8589.txt
		//length_5_N_27100.txt
		String fn = "length_5_N_27100.txt";

		//
		// Call helper function to read the input file
		//
		Integer[] data = read_array(fn);
		
		//
		// We need to know the length of the "strings" in this data file (for output 
		// purposes later), but we read and saved them as integers, so the best way to 
		// determine this now is to loop over the list, find the largest item, and 
		// assume that all of the items are as long as that one.
		//
		Integer max = 0;
		for (int i=0; i<data.length; i++) {
			if (data[i] > max) {
				max = data[i];
			}
		}
		int length_of_passcodes = String.valueOf(max).length();
		System.out.println("length of passcodes is " + length_of_passcodes);
		
		//
		// Print out the list of input data, for no particular reason
		//
//		for (int i=0; i<data.length; i++) {
//			//
//			// Format the numbers with the correct number of leading zeroes as
//			// needed according to the length of the passcodes being processed.
//			//
//			String fmt = "%0" + length_of_passcodes + "d";
//			System.out.println(String.format(fmt, data[i]));
//		}
//		System.out.println(data.length + " data items in the file");
		
		//
		//---------------------------------------------------------------
		// PUT YOUR MAIN PROGRAM LOGIC HERE
		//  1) Call your function to sort the input list
		//  2) Call your function to find the smallest missing number
		//  3) Print out the smallest missing number
		//---------------------------------------------------------------
		//
		merge_sort(data);
//		System.out.println(Arrays.toString(data));
		int missingNumber = smallestMissingInt(data);
		String fmt = "%0" + length_of_passcodes + "d";
		System.out.println(String.format(fmt, missingNumber));
	}

	
	/** Sorts a integer array using merge sort.
	 * @param arrayA Integer array to sort.
	 */
	// merge sort implementation
	public static void merge_sort(Integer[] arrayA) {
		int arrayALength = arrayA.length;

		// left side of array A
		Integer[] arrayB;

		// right side of array B
		Integer[] arrayC;

		// if the array has more than 1 item
		if (arrayALength > 1) {

			// get the middle
			int midpoint = (arrayALength / 2);

			// put the beginning to middle of array A in arrayB
			arrayB = Arrays.copyOfRange(arrayA, 0, midpoint);

			// put the middle to end of array A in arrayC
			arrayC = Arrays.copyOfRange(arrayA, midpoint, arrayALength);

			// recursively call function until all elements have been split
			merge_sort(arrayB);
			merge_sort(arrayC);

			// combine partial solutions
			merge(arrayA, arrayB, arrayC);
		}
	}

	
	/** Helps merge_sort by merging two arrays into one in order.
	 * @param arrayA Array to merge into.
	 * @param arrayB Array to sort and merge into array A.
	 * @param arrayC Array to sort and merge into array A.
	 */
	// merge two arrays into 1 sorted array.
	public static void merge(Integer[] arrayA, Integer[] arrayB, Integer[] arrayC) {
		// initialize some integers.
		int i = 0, j = 0, k = 0;

		// get the length of the two arrays
		int p = arrayB.length;
		int q = arrayC.length;

		// combine arrays into arrayA
		while (i < p && j < q) {

			// add elements in array B and C into A in order of lowest to highest
			if (arrayB[i] <= arrayC[j]) {
				arrayA[k] = arrayB[i];
				i++;
			}
			else {
				arrayA[k] = arrayC[j];
				j++;
			}
			k++;
		}

		// add arrayC to arrayA if arrayC was not touched in above merge
		if (i == p) {
			while(j < q) {
				// add remaining in arrayC to array A
				arrayA[k] = arrayC[j];
				j++;
				k++;
			}
		}
		// add arrayB to arrayA if arrayB was not touched in above merge
		else {
			// add remaining in arrayB to array A
			while (i < p) {
				arrayA[k] = arrayB[i];
				i++;
				k++;
			}
		}
	}

	
	/** Finds smallest missing integer in an integer array.
	 * @param array Integer array.
	 * @return int Missing integer.
	 */
	// find smallest missing integer in a sorted array of integers.
	public static int smallestMissingInt(Integer[] array) {

		// get the length
		int arrayLength = array.length - 1;

		// since there are no negative numbers, if there is only 1 or less numbers in array, then 0 is the missing num.
		if (1 > arrayLength) {
			return 0;
		}

		// if there are only 2 numbers (left) in array.
		if (arrayLength == 1) {

			// check if second number is the next integer of the first number. If it is, then last number + 1 is missing
			if (array[0] + 1 == array[arrayLength]) {
				return array[arrayLength] + 1;
			}

			// otherwise first number + 1 is the missing number.
			return array[0] + 1;
		}

		// get the midpoint of the array of numbers
		int midpoint = arrayLength / 2;

		// calculate the correct value of the middle of the array by adding the number at the beginning of the array to
		// the number of digits from first to middle.
		int midpointValue = array[0] + midpoint;

		// if the value at the middle isnt correct, the missing number is in the left half of the array.
		if (midpointValue == array[midpoint]) {
			return smallestMissingInt(Arrays.copyOfRange(array, midpoint, arrayLength + 1));
		}

		// otherwise the missing number is at the right half
		else {
			return smallestMissingInt(Arrays.copyOf(array, midpoint + 1));
		}
	}

	
	/** Reads array from file.
	 * @param filename File name.
	 * @return Integer[] Integer array read from file.
	 * @throws IOException Throws exception when fail read fails.
	 */
	public static Integer[] read_array(String filename) throws IOException {
		//
		// Reads the input file given by "filename", assumed to contain a list of
		// integer numbers. Stores the numbers into an array and returns the
		// array.
		//
		File file = new File(filename);
		Scanner sc = new Scanner(file);

		//
		// Read the items initially into an ArrayList (for dynamic growth)
		//
		ArrayList<Integer> input_list = new ArrayList<Integer>();		
		while (sc.hasNext()) {			
			int n = sc.nextInt();
			input_list.add(n);
		}

		//
		// Copy the ArrayList to an Integer[] array of the proper size
		//
        Integer[] arr = new Integer[input_list.size()]; 
        arr = input_list.toArray(arr); 
		return arr;
	}
}
