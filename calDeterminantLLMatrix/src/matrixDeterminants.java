/*
*
* @author: Earlyn Reinhardt
*
* Objective: Calculate the determinants of each of the matrices in the file.
*
* Matrices are comprised of doubly-linked lists and the determinants are
* calculated with recursion.
*
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This program aims to calculate the determinant of each matrix in a file,
 * using a linked list.
 *
 * Requirements:
 * Matrix need to be at most 6 x 6. All matrices smaller than
 * this are acceptable.
 *
 * @version 1.0 2019-07-30
 * @author Earlyn Reinhardt
 *
 */
public class matrixDeterminants{

    /**
     * Main entry point of the program to calculate determinants of matrices
     * in a file
     *
     * @param args - Holds the argument for the command line. Two are needed:
     *             the path of the input file and the path of the output file.
     */
    public static void main (String[] args){

        BufferedReader   input;               // Allows us to gain contents of
                                              // original file.
        BufferedWriter   output;              // Allows us to write results to
                                              // file.
        String           size;                // Size of matrix in input.
        DoublyLinkedList matrixToEvaluate;    // The matrix that needs the
                                              // determinant.
        int              determinant;         // Determinant of matrix
        int              calculationCount;    // Number of times we calculated
                                              // the determinant.

        matrixDeterminants matrixDeterminant = new matrixDeterminants();


        // Open the file that contains our matrices
        if (args.length != 2){

            System.out.println("Include the input and output filename");

            System.out.println("java matrixDeterminants [input file name] [output file " +
                    "name]");

        }

        // Set up a buffered reader for the file
        try{

            input = new BufferedReader(new FileReader(args[0]));

            output = new BufferedWriter(new FileWriter(args[1]));

        } catch (Exception ioe){

            System.err.println(ioe.toString());

            return;

        }

        // Read the size of the matrices, create a matrices, and calculate
        // determinants, one by one
        size = matrixDeterminant.readSize(input);

        calculationCount = 1;

        while (size != null){

            matrixToEvaluate = matrixDeterminant.createMatrix(size, input);

            // The matrix is square
            if (matrixToEvaluate.checkSize()){

                // Print matrix
                matrixToEvaluate.printLinkedListContents();

                // Calculate determinant
                determinant = matrixToEvaluate.calculateDeterminant(matrixToEvaluate);

                // Print the determinant
                matrixToEvaluate.printDeterminant();

                // Write the determinant to the file
                matrixDeterminant.writeDeterminant(calculationCount, determinant, output);

                // Add 1 to the count
                calculationCount++;

                // Move unto the next matrix by getting the size.
                size = matrixDeterminant.readSize(input);
            }
            // The matrix is not square or complete.
            else {

                System.out.println("This is not a valid matrix. Cannot " +
                        "calculate the determinant.");

                // Move unto the next matrix even if we couldn't calculate
                // the determinant of the matrix
                size = matrixDeterminant.readSize(input);
            }
        }


        // Clean up and return to OS
        try {

            input.close();

            output.close();

        } catch (Exception x) {

            System.err.println(x.toString());

        }

        return;
    }

    /**
     * Read the size of the incoming matrix. The first line will
     * just be a number indicating the size of the square matrix.
     *
     * For example: There is a line with the number 6, which means that the
     * upcoming matrix is 6 x 6. Since the matrix will be saved as a linked
     * list, there will be 36 nodes (6^2).
     *
     *
     * @param input - Buffered input with our original matrix
     * @return A string that determines the size (m) of the matrix (mxm).
     */
    private String readSize(BufferedReader input){

        String text = "";

        // Read the line that contains the size of mxm matrix
        try
        {
            text = input.readLine();
        }
        catch (IOException iox)
        {
            System.err.println(iox.toString());

            System.exit(2);
        }

        return text;
    }

    /**
     * Output the results into a file that looks like this:
     *
     * Matrix 1 has determinant -44.
     * Matrix 2 has determinant 0.
     *
     * @param matrixNth  - The nth matrix (int) where the program calculated
     *                   the
     *                   determinant.
     * @param determinant - The result (int).
     * @param output - The bufferedwriter that will stream the results into a
     *              file.
     */
    private void writeDeterminant(int matrixNth, int determinant,
                                  BufferedWriter
                                          output){

        // Sentence that contains result.
        String result = "Matrix " + matrixNth + " has determinant " +
                determinant;

        // Write results to file with the sentence made before.
        try{

            output.write(result, 0, result.length());

            output.newLine();

        } catch(IOException iox){

            System.err.println(iox.toString());

            System.exit(3);

        }

        return;

    }

    /**
     * Create one matrix with information given by the file.
     *
     * @param size String that is grabbed by the file that determines the
     *             size of the matrix
     * @param input BufferedReader that sends information about the matrices
     * @return
     */
    public DoublyLinkedList createMatrix(String size, BufferedReader input){

        DoublyLinkedList matrix = new DoublyLinkedList();
        int requestedMatrixSize = 0;
        String inputRow;  // The string that contains the row from the file
        Node tempNode;    // To temporary place the data from the file
        int charCounter;  // Use as an index to retrieve the number in the row
        int col;          // Column of node.
        int tempInt;      // Temporary placeholder of int.

        // Check to see if the request size if valid
        try {

            requestedMatrixSize = Integer.parseInt(size);

        }
        catch (NumberFormatException e) {

            System.err.println("Program needs a valid size");

        }

        // Create matrix from reading the input
        if (requestedMatrixSize == 0) {

            return matrix;

        }
        // Create matrix and fill the array.
        else {

            // Read each line of the matrix
            // Then read each character and place each
            // character in the matrix
            for (int row = 1; row <= requestedMatrixSize; row++){

                try{

                    inputRow = input.readLine();

                    // Restart the index to zero
                    charCounter = 0;

                    // Restart the column to one for each row.
                    col = 1;

                    while (charCounter < inputRow.length()){

                        // Negative number in the row
                        if (inputRow.charAt(charCounter) == '-'){

                            tempInt = Character.getNumericValue(inputRow
                                    .charAt(charCounter+1));

                            tempInt = -tempInt;

                            tempNode = new Node(row, col,tempInt);

                            matrix.addNode(tempNode);

                            charCounter += 3;
                            col++;

                        }
                        // Positive number in the row.
                        else if (Character.isDigit(inputRow.charAt
                                (charCounter))){

                            tempInt = Character.getNumericValue(inputRow
                                            .charAt(charCounter));

                            tempNode = new Node(row, col, tempInt);

                            matrix.addNode(tempNode);

                            charCounter += 2;
                            col++;

                        }
                        // Space or any other character (ignored)
                        else {

                            charCounter += 2;

                        }
                    }



                }
                catch (IOException iox)
                {

                    System.err.println(iox.toString());
                    System.exit(2)
                    ;
                }

            }

        }

        return matrix;
    }
}