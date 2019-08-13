/*
*
* Author: Earlyn Reinhardt
*
* Version: 1.0
*
* Description: Create methods that sorts data using the shell sort and the
* heap sort.
*
*
 */

import java.util.*;
import java.lang.Math.*;

/**
 * sortingAlgorithms is a class that allows you to sort data. The two sorting
 * algorithms currently available in this class are the shell sort and the
 * heap sort.
 */
public class sortingAlgorithms{


    /**
     * Constructor
     */
    public sortingAlgorithms(){

    }

    /**
     * Reverse an array in place. Traverse first half of array only since
     * method is switching elements.
     *
     * @param arrayToReverse - a sorted array of ints
     */
    public void reverseArray(int [] arrayToReverse){

        int tempInt; // Placeholder to help with swapping values
        int arraySize = arrayToReverse.length;

        // Go through half of the list and swap values in place
        // 1st element - last element, 2nd element - 2nd to last element, etc.
        for (int i = 0; i < arraySize / 2; i++){

            tempInt = arrayToReverse[i];

            arrayToReverse[i] = arrayToReverse[arraySize - i - 1];

            arrayToReverse[arraySize - i - 1] = tempInt;

        }

    }

    /**
     * Print the elements of array one by one.
     *
     * @param arrayToPrint
     */
    public void printArray(int[] arrayToPrint){

        System.out.println();

        // Go through each element of array and print it.
        for (int i = 0; i < arrayToPrint.length; i++){

            System.out.print(arrayToPrint[i] + " ");
        }

        System.out.println();

    }


    /**
     * In the shell sort choose the starting increment
     * @param arrayToSort
     */
    public int chooseStartingIncrement(ArrayList<Integer> arrayToSort, int[]
            arraySequence){

        int seqStartIndex = 0; // Starting index for the shell short sequence

        // Go through sequence and find the first value that is smaller than
        // the size of the file.
        for (int i = 0; i < arraySequence.length; i++) {

            if (arraySequence[i] < arrayToSort.size()){

                // Number is only bigger than last element
                if (i == arraySequence.length - 1){

                    seqStartIndex = arraySequence.length - 1;

                    return seqStartIndex;
                }
                else {

                    // The starting index
                    seqStartIndex = i + 1;

                }

                return seqStartIndex;
            }
        }

        return seqStartIndex;
    }

    /**
     * Sort the algorithm using insertion sort. Insertion sort iterates though
     * an array, swapping as it goes through.
     *
     * Code Reference: ISBN: 978-1-5418-7704-7
     *
     * @param arrayToSort - unsorted array or a portion of the array.
     * @return arrayList - sorted array or portion of array
     */
    public void insertionSortInterval(ArrayList<Integer>
                                                         arrayToSort,
                                                    int startIndex,
                                                    int gapSize){

        int i = 0;         // Start index
        int j = 0;         // Use to loop through array to find smaller values
        int tempInt = 0;   // Use to temporarily store a value before swapping

        for (i = startIndex + gapSize; i < arrayToSort.size(); i = i + gapSize){

            j = i;

            // Move towards the start of the list if the elements towards
            // the end are smaller than the elements in the front. Swap
            // as you go.
            while (j - gapSize >= startIndex && arrayToSort.get(j) <
                    arrayToSort.get(j -
                    gapSize)) {

                // Swap numbers
                tempInt = arrayToSort.get(j);
                arrayToSort.set(j,  arrayToSort.get(j - gapSize));
                arrayToSort.set(j - gapSize, tempInt);
                j = j - gapSize;
            }
        }
    }

    /**
     * Sort array with the shell sort algorithm using the Knuth sequence
     * h = h * 3 + 1
     *
     * @param arrayToSort - an unordered array
     * @return an array that is sorted using the shell sort algorithm.
     */
    public ArrayList<Integer> shellSort(ArrayList<Integer> arrayToSort, int option){

        int startingIndexSeq; // Starting index for sequence.
                              // Two increments smaller than file size.
        int[] tempSeq;          // Sliced sequence with proper gap values.

        // Knuth Sequence
        int[] knutSeqOrg = new int[] {1, 4, 13, 40, 121, 364, 1093, 3280,
                9841, 29524};
        reverseArray(knutSeqOrg);


        // Second Sequence
        int[] seqTwo = new int[] {1, 5, 17, 53, 149, 373, 1123, 3371, 10111,
                30341};
        reverseArray(seqTwo);

        // Third Sequence
        int[] seqThree = new int[] {1, 10, 30, 60, 120, 360, 1080, 3240, 9720,
                29160};
        reverseArray(seqThree);

        // Fourth Sequence
        // Catalan Number Sequence, missing first "1" because it is not
        // necessary.
        // https://en.wikipedia.org/wiki/Catalan_number
        int[] seqFour = new int[] {1, 2, 5, 14, 42, 132, 429, 1430, 4862,
                16796, 58786};
        reverseArray(seqFour);

        switch (option){

            // Use first sequence
            case 1:

                System.out.println("Option 1!");

                // Determine starting sequence
                startingIndexSeq = chooseStartingIncrement(arrayToSort,
                    knutSeqOrg);

                // Obtain a sliced array of the sequence array
                // with the right starting increment.
                tempSeq = Arrays.copyOfRange(knutSeqOrg, startingIndexSeq,
                        knutSeqOrg.length);

                // Go through each element in sequence
                for (int seq1GapVal: tempSeq){

                    System.out.println("Gap Value: " + seq1GapVal);

                    for (int i = 0; i < seq1GapVal; i++){

                        insertionSortInterval(arrayToSort, i, seq1GapVal);

                    }
                }

                break;

            // Use second sequence
            case 2:

                System.out.println("Option 2!");

                // Determine starting sequence
                startingIndexSeq = chooseStartingIncrement(arrayToSort,
                        seqTwo);

                // Obtain a sliced array of the sequence array
                // with the right starting increment.
                tempSeq = Arrays.copyOfRange(seqTwo, startingIndexSeq,
                        seqTwo.length);

                // Go through each element in sequence
                for (int seq2GapVal: tempSeq){

                    System.out.println("Gap Value: " + seq2GapVal);

                    for (int i = 0; i < seq2GapVal; i++){

                        insertionSortInterval(arrayToSort, i, seq2GapVal);

                    }
                }

                break;

            // Use third sequence
            case 3:

                System.out.println("Option 3!");

                // Determine starting sequence
                startingIndexSeq = chooseStartingIncrement(arrayToSort,
                        seqThree);

                // Obtain a sliced array of the sequence array
                // with the right starting increment.
                tempSeq = Arrays.copyOfRange(seqThree, startingIndexSeq,
                        seqThree.length);

                // Go through each element in sequence
                for (int seq3GapVal: tempSeq){

                    System.out.println("Gap Value: " + seq3GapVal);

                    for (int i = 0; i < seq3GapVal; i++){

                        insertionSortInterval(arrayToSort, i, seq3GapVal);

                    }
                }

                break;

            // Use fourth sequence
            case 4:

                System.out.println("Option 4!");

                // Determine starting sequence
                startingIndexSeq = chooseStartingIncrement(arrayToSort,
                        seqFour);

                // Obtain a sliced array of the sequence array
                // with the right starting increment.
                tempSeq = Arrays.copyOfRange(seqFour, startingIndexSeq,
                        seqFour.length);

                // Go through each element in sequence
                for (int seq4GapVal: tempSeq){

                    System.out.println("Gap Value: " + seq4GapVal);

                    for (int i = 0; i < seq4GapVal; i++){

                        insertionSortInterval(arrayToSort, i, seq4GapVal);

                    }
                }

                break;
        }

        return arrayToSort;

    }


    /**
     * Sort array with the heap sort algorithm with custom sequence.
     *
     * @param arrayToSort - an unordered array
     * @return an array that is sorted with the heap sort algorithm
     */
    public ArrayList<Integer> heapSort(ArrayList<Integer> arrayToSort,
                                       int arraySize){

        int tempInt; // Store number for swapping


        // Heapify the array
        for (int i = arraySize / 2 - 1; i >= 0; i--) {

            arrayToSort = heapifyRecursive(i, arrayToSort, arraySize);

        }

        // Sort the array by switching places
        for (int i = arraySize - 1; i > 0; i--) {

            tempInt = arrayToSort.get(0);
            arrayToSort.set(0, arrayToSort.get(i));
            arrayToSort.set(i, tempInt);

            arrayToSort = heapify(0, arrayToSort, i);

        }

        return arrayToSort;

    }

    /**
     * Recursive Heapify
     *
     * Make the array follow a max heap structure so that
     * no child is larger than the parents.
     *
     * @param arrayToSort - an array of integers
     * @return
     */
    public ArrayList<Integer> heapifyRecursive(int nodeIndex,
                                      ArrayList<Integer> arrayToSort,
                                      int arraySize
                                      ){

        int childIndex = 2 * nodeIndex + 1;  // Index of child
        int parentValue = arrayToSort.get(nodeIndex); // Value of the
                                                      // parent node
        int tempValue;                       // Value of the temporary child;


        // Start from the bottom
        // Check the parents
        // If the child nodes are larger than the parents, switch.
        while (childIndex < arraySize){

            int maxValue = parentValue;  // Assumed to be the largest
            int maxIndex = -1;

            // Find the maximum node to determine if it follows max heap rules
            // There are only two children
            // Prevent nullpoint exception
            for (int i = 0;  i < 2 && i + childIndex < arraySize; i++){

                if (arrayToSort.get(i + childIndex) > maxValue) {

                    maxValue = arrayToSort.get(i + childIndex);

                    maxIndex = i + childIndex;

                }
            }

            // Order follows max heap rules
            // Base case
            if (maxValue == parentValue) {

                return arrayToSort;
            }
            // Switch the child and parent nodes
            // Recursive case
            else {

                // Get value of parent that is lower than child
                tempValue = arrayToSort.get(nodeIndex);

                // Set the value of the parent the child's value
                arrayToSort.set(nodeIndex, arrayToSort.get(maxIndex));

                // Give the child the parent's lower value.
                arrayToSort.set(maxIndex, tempValue);

                // Continue process again.
                nodeIndex = maxIndex;

                childIndex = 2 * nodeIndex + 1;

                return heapify(nodeIndex, arrayToSort, arraySize);

            }
        }
        return arrayToSort;
    }

    /**
     * Iterative Heapify Algorithm
     *
     * Modify the contents of the array so it follows a max heap structure
     * where no child is larger than the parent.
     *
     * @param arrayToSort - an array that does not follow max heap structure.
     *                   Could be sorted, in reverse order, or not
     * @return ArrayList that follows max heap rules.
     */
    public ArrayList<Integer> heapifyIterative(ArrayList<Integer> arrayToSort){

        int parentIndex; // Parent index
        int tempInt;     // Placeholder to hold integer for swapping

        // Go through each node from index 1 since an index of 0 is the roor.
        for (int childIndex = 1; childIndex < arrayToSort.size(); childIndex++){

            parentIndex = floor((childIndex - 1) / 2);

            // Check the child (index i) against parent (index floor((i - 1)/2))
            // If child node is greater than the parent node, swap places
            if (arrayToSort.get(childIndex) > arrayToSort.get(parentIndex)){

                // Get value of the child index
                tempInt = arrayToSort.get(childIndex);

                // Set the value of the child index to the parent index
                arrayToSort.set(childIndex, arrayToSort.get(parentIndex));

                // Set the value of the parent index to the child index
                arrayToSort.set(parentIndex, tempInt);

            }
        }

        return arrayToSort;
    }
}