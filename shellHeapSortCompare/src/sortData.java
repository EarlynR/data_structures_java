/*
* Author: Earlyn Reinhardt
*
* Version: 1.0
*
* Description: Compare the efficiency of the shell sort and the heap sort for
 * different types of datasets.
 *
 * Inputs: 75 files
 * Outputs: 15 files,
 *
 * Suggested improvements: Make array list into object to include name
*
*/

//TODO: Check if sorts are equal to make sure it's correct
//TODO: Change return statements for methods that swap elements in place
//TODO: Add code to only save results less than or equal to 50 records
//TODO: Remove extraneous print statements
//TODO: Add additional sequence to test

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.time.*;

/**
 * sortData is the entrypoint of this program. This class will read the input
 * file, sort and time it, and save the results into a file.
 */
public class sortData{

    /**
     * Main entry point of the program to sort files.
     *
     * Sorting Algorithms:
     *      1. Simple Insertion Sort
     *      2. Shell Sort (Four Variations)
     *      3. Heap Sort
     *
     * Types of Order:
     *      1. Ascending
     *      2. Descending
     *      3. Random (wih duplicates accounting for 1% of data)
     *
     * @param args - input directory containing files to sort, output
     *             directory to store results.
     */
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

        ArrayList<Integer> sortedInsertion = new ArrayList<Integer>(); //
        // Sorted array done by a simple insertion sort

        Instant         startTime;    // Start time for sorting
        Instant         endTime;      // End time for sorting
        long            timeDiffHeapSort; // Time difference for heapsort.
        long            timeDiffShellSort1; // Time difference for shell sort
        // with first sequence
        long            timeDiffShellSort2; // Time difference for shell sort
        // with second sequence
        long            timeDiffShellSort3; // Time difference for shell sort
        // with third sequence
        long            timeDiffShellSort4; // Time difference for shell sort
        // with fourth sequence
        long            timeDiffInsertion; // Time difference for shell sort
        // with fourth sequence

        sortingAlgorithms sortEngine = new sortingAlgorithms();
        sortData          mainEngine = new sortData();


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
                sortedInsertion = sortEngine.copyArrayList(arrayInt);

                // Sort data with heap sort
                startTime = Instant.now();
                sortEngine.heapSort(sortedHeap, arrayInt.size());
                endTime = Instant.now();
                timeDiffHeapSort = mainEngine.calculateTimeDifference
                        (startTime, endTime);
                System.out.println("Heap Sorted: ");
                System.out.println("Time to Sort: " + timeDiffHeapSort);
                System.out.println(Arrays.deepToString(sortedHeap.toArray()));

                // Sort data with shell sort with first sequence
                startTime = Instant.now();
                sortEngine.shellSort(sortedShellSeq1, 1);
                endTime = Instant.now();
                timeDiffShellSort1 = mainEngine.calculateTimeDifference
                        (startTime, endTime);
                System.out.println("Shell Sorted: ");
                System.out.println("Time to Sort: " + timeDiffShellSort1);
                System.out.println(Arrays.deepToString(sortedShellSeq1.toArray()));

                // Sort data with shell sort with second sequence
                startTime = Instant.now();
                sortEngine.shellSort(sortedShellSeq2, 2);
                endTime = Instant.now();
                timeDiffShellSort2 = mainEngine.calculateTimeDifference
                        (startTime, endTime);
                System.out.println("Shell Sorted: ");
                System.out.println("Time to Sort: " + timeDiffShellSort2);
                System.out.println(Arrays.deepToString
                        (sortedShellSeq2.toArray()));

                // Sort data with shell sort with third sequence
                startTime = Instant.now();
                sortEngine.shellSort(sortedShellSeq3, 3);
                endTime = Instant.now();
                timeDiffShellSort3 = mainEngine.calculateTimeDifference
                        (startTime, endTime);
                System.out.println("Shell Sorted: ");
                System.out.println("Time to Sort: " + timeDiffShellSort3);
                System.out.println(Arrays.deepToString
                        (sortedShellSeq3.toArray()));


                // Sort data with shell sort with fourth sequence
                startTime = Instant.now();
                sortEngine.shellSort(sortedShellSeq4, 4);
                endTime = Instant.now();
                timeDiffShellSort4 = mainEngine.calculateTimeDifference
                        (startTime, endTime);
                System.out.println("Shell Sorted: ");
                System.out.println("Time to Sort: " + timeDiffShellSort4);
                System.out.println(Arrays.deepToString
                        (sortedShellSeq4.toArray()));


                // Sort data with simple insertion sort
                startTime = Instant.now();
                sortEngine.simpleInsertionSort(sortedInsertion);
                endTime = Instant.now();
                timeDiffInsertion = mainEngine.calculateTimeDifference
                        (startTime, endTime);
                System.out.println("Insertion Sorted: ");
                System.out.println("Time to Sort: " + timeDiffInsertion);
                System.out.println(Arrays.deepToString
                        (sortedInsertion.toArray()));

                inputFileName = file.getName();

                // Save results if file size is less than 50
                if (arrayInt.size() <= 50){

                    mainEngine.writeResults(sortedHeap, "HeapSort",
                            timeDiffHeapSort,
                            inputFileName,
                            args[1]);

                    mainEngine.writeResults(sortedShellSeq1,
                            "ShellSortSeq1",
                            timeDiffShellSort1,
                            inputFileName,
                            args[1]);

                    mainEngine.writeResults(sortedShellSeq2,
                            "ShellSortSeq2",
                            timeDiffShellSort2,
                            inputFileName,
                            args[1]);

                    mainEngine.writeResults(sortedShellSeq3,
                            "ShellSortSeq3",
                            timeDiffShellSort3,
                            inputFileName,
                            args[1]);

                    mainEngine.writeResults(sortedShellSeq4,
                            "ShellSortSeq4",
                            timeDiffShellSort4,
                            inputFileName,
                            args[1]);

                }

            }
        }
    }

    /**
     * Write results to a file in the folder that's specific
     * @param sortedArray
     * @param nameSortingAlgorithm
     * @param output
     */
    public void writeResults(ArrayList<Integer> sortedArray,
                        String nameSortingAlgorithm,
                        long timeToSort,
                        String fileName,
                        String outFolder
                        ){

        int size = sortedArray.size();
        String message = "Sorted" + nameSortingAlgorithm + " for size " +
                size + ". Time to sort: " + timeToSort;
        String outputFileName = fileName + "sorted"  + nameSortingAlgorithm +
                "Size" +
                size + "" +
                ".txt";
        BufferedWriter output = null;

        try {

            FileWriter fstream1 = new FileWriter(outFolder + outputFileName);

            output = new BufferedWriter(fstream1);

            output.write(message, 0, message.length());

            for (int i = 0; i < size; i++) {

                output.newLine();

                output.write(String.valueOf(sortedArray.get(i)));

            }

        } catch (Exception ioe){

            System.err.println(ioe.toString());

            return;

        } finally { // Close file to write results.

            try {

                if (output != null){

                    output.close();

                }
                else {

                    System.out.println("Buffered Writer was not initialized.");

                }
            } catch (IOException iox){

                System.err.println(iox.toString());

                System.exit(2);
            }


        }


    }

    /**
     * Method to subtract two dates. Used to determine how long it takes to
     * sort algorithm
     *
     * Reference: https://stackoverflow
     * .com/questions/52672161/java-time-difference-outputs-negative-value-and-wrong-values
     *
     * @param dateStart - time before sorting began
     * @param dateEnd - time when sorting ended
     * @return
     */
    public long calculateTimeDifference(Instant date1, Instant date2){

        long d = Duration.between( date1 , date2 ).toMillis();
        System.out.println("Date 1: " + date1.getNano());
        System.out.println("Date 2: " + date2.getNano());
        System.out.println("Duration: " + d);

        return  d;
    }
}

