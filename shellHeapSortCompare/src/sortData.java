/*
* Author: Earlyn Reinhardt
*
* Version: 1.0
*
* Description: Compare the efficiency of the shell sort and the heap sort for
 * different types of datasets.
 *
 * Inputs: 15 files
 * Outputs:
*
*/

//TODO: Check if sorts are equal to make sure it's correct
//TODO: Change return statements for methods that swap elements in place
//TODO: Add code to only save results less than or equal to 50 records
//TODO: Remove extraneous print statements

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * sortData is the entrypoint of this program. This class will read the input
 * file, sort and time it, and save the results into a file.
 */
public class sortData{

    public static void main(String[] args){

        BufferedReader input;         // Allows us to gain contents in file.
        BufferedWriter output;        // Allows us to write results to file.
        File           inputFolder;   // Directory containing datasets.
        File           outputFolder;  // Directory to save output
        File[]         inputFiles;    // Input files;
        String         inputFileName; // Name of file being sorted.
        String         ouputFileName; // Name of file containing results.
        String         tempLine;      // Line in file to read.
        int            tempInt;       // Placeholder to hold value from file

        ArrayList<Integer> arrayInt = new ArrayList<Integer>(); // Array list of ints
                                                        // to sort
        ArrayList<Integer> sortedShellSeq1 = new ArrayList<Integer>(); // Sorted
        // array
        // done by shell sort with sequence 1

        ArrayList<Integer> sortedShellSeq2 = new ArrayList<Integer>(); // Sorted
        // array
        // done by shell sort with sequence 2

        ArrayList<Integer> sortedShellSeq3 = new ArrayList<Integer>(); // Sorted
        // array
        // done by shell sort with sequence 3

        ArrayList<Integer> sortedShellSeq4 = new ArrayList<Integer>(); // Sorted
        // array
        // done by shell sort with sequence 4

        ArrayList<Integer> sortedHeap = new ArrayList<Integer>(); // Sorted array
        // done by heap sort

        Date            startTime;    // Start time for sorting
        Date            endTime;      // End time for sorting
        long            seconds;      // Time difference for sorting.

        sortingAlgorithms sortEngine = new sortingAlgorithms();


        // Open the directory that contains the files to sort
        if (args.length != 2){

            System.out.println("Include the input and output directory.");

            System.out.println("Example: java sortData [input file directory]" +
                               " [output directory]");

        }

        // Get the input and output folder.
        // Reference Link: https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
        try {

            inputFolder = new File(args[0]);

            outputFolder = new File(args[1]);

            // Get the files within input directory
            inputFiles = inputFolder.listFiles();


        } catch (Exception ioe){

            System.err.println(ioe.toString());

            return;

        }

        // Go through each file and sort the data
        for (File file : inputFiles){

            if (file.isFile()){

                try{

                    // Set up a buffered reader for the contents in file
                    input = new BufferedReader(new FileReader(file));

                    // Read the first line.
                    tempLine = input.readLine();

                } catch (Exception ioe){

                    System.err.println(ioe.toString());

                    return;

                }

                // Go through each line of the file.
                // Save results into an array.
                // Sort array.
                // Print results and times.
                // Save results if array has less than 50 elements.
                while (tempLine != null) {

                    try{

                        // Try to convert the string into an integer.
                        tempInt = Integer.parseInt(tempLine);

                        arrayInt.add(tempInt);

                        // Continue to next line.
                        tempLine = input.readLine();

                    } catch (IOException iox){

                        System.err.println(iox.toString());

                        System.exit(2);

                    }
                }

                // Deep copy original array to arrays created for sorting
                // algorithms
                sortedHeap = sortEngine.copyArrayList(arrayInt);
                sortedShellSeq1 = sortEngine.copyArrayList(arrayInt);
                sortedShellSeq2 = sortEngine.copyArrayList(arrayInt);
                sortedShellSeq3 = sortEngine.copyArrayList(arrayInt);
                sortedShellSeq4 = sortEngine.copyArrayList(arrayInt);

                // Sort data with heap sort
                sortedHeap = sortEngine.heapSort(sortedHeap, arrayInt.size());
                System.out.println("Heap Sorted: ");
                System.out.println(Arrays.deepToString(sortedHeap.toArray()));

                // Sort data with shell sort with first sequence
                sortedShellSeq1 = sortEngine.shellSort(sortedShellSeq1, 1);
                System.out.println("Shell Sorted: ");
                System.out.println(Arrays.deepToString(sortedShellSeq1.toArray()));

                // Sort data with shell sort with second sequence
                sortedShellSeq2 = sortEngine.shellSort(sortedShellSeq2, 2);
                System.out.println("Shell Sorted: ");
                System.out.println(Arrays.deepToString
                        (sortedShellSeq2.toArray()));

                // Sort data with shell sort with third sequence
                sortedShellSeq3 = sortEngine.shellSort(sortedShellSeq3, 3);
                System.out.println("Shell Sorted: ");
                System.out.println(Arrays.deepToString
                        (sortedShellSeq3.toArray()));


                // Sort data with shell sort with fourth sequence
                sortedShellSeq4 = sortEngine.shellSort(sortedShellSeq4, 4);
                System.out.println("Shell Sorted: ");
                System.out.println(Arrays.deepToString
                        (sortedShellSeq4.toArray()));

                // Save results if file size is less than 50
                if (arrayInt.size() <= 50){


                }

            }
        }
    }
}

