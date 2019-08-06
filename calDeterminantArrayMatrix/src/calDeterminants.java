
/*
* $Id: Matrix.java
*
* Description: Calculate the determinant for each matrix in a file.
*              The each matrix will be used by the methods in the matrix class.
*              Methods that are a part of this class are mainly for reading
*              matrices from a file.
*
* Assumptions:
*              Matrices given are always square.
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * calDeterminantArrayMatrix aims to calculate the determinant of each matrix
 * in a file.
 *
 * Requirements:
 * Matrix need to be at most 6 x 6. All matrices smaller than
 * this are acceptable.
 * Must use arrays as the underlying data structure.
 *
 * @version 1.0 2019-07-15
 * @author Earlyn Reinhardt
 *
 */
public class calDeterminants {

    /**
     * Main entry point of the program to calculate determinants of matrices
     * in a file
     *
     * @param args - Holds the argument for the command line. One is needed:
     *             the path of the input file.
     */
    public static void main (String args[]){

        BufferedReader input;
        BufferedWriter output;
        String         size;
        int[][]        matrixToEvaluate;
        int            determinant;
        int            calculationCount;

        Matrix matrixHelper = new Matrix();
        calDeterminants calDeterminantsMachine = new calDeterminants();


        // Open the file that contains our matrices
        if (args.length != 2){

            System.out.println("Include the input and output filename");

            System.out.println("java Lab2 [input file name] [output file " +
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
        size = calDeterminantsMachine.readSize(input);

        calculationCount = 1;

        while (size != null){

            matrixToEvaluate = matrixHelper.createMatrix(size, input);

            matrixHelper.printMatrix(matrixToEvaluate);

            determinant = matrixHelper.determineDeterminant(matrixToEvaluate);

            matrixHelper.printDeterminant(determinant);

            calDeterminantsMachine.writeDeterminant(calculationCount, determinant, output);

            calculationCount++;

            size = calDeterminantsMachine.readSize(input);
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
     * upcoming matrix is 6 x 6.
     *
     *
     * @param input - Buffered input with our original matrix
     * @return A multidimensional array that contains the matrix.
     */
    private String readSize(BufferedReader input){

        String text = "";

        // Read the line that contains the size of nxn matrix
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

}
