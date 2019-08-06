/*
* $Id: Matrix.java
*
* This class includes all matrix operations, as well helper functions like
* calculating the power and printing the matrix.
*
* The main function, determineDeterminant, uses recursion to calculate the
* determinant. CalculatePower also uses recursion. Remaining functions
* performs iteratively.
*
*/
import java.io.BufferedReader;
import java.io.IOException;

/**
 * A matrix class with methods to use on a 2D array (matrix)
 *
 * @version 1.0 2019-07-16
 * @author Earlyn Reinhardt
 */
public class Matrix {

    /**
     *
     * @param size String that is grabbed by the file that determines the
     *             size of the matrix
     * @param input BufferedReader that sends information about the matrices
     * @return
     */
    public int[][] createMatrix(String size, BufferedReader input){

        int matrixSize;
        String row;
        int[][] matrix;
        int stringCounter;

        try {

            matrixSize = Integer.parseInt(size);

        }
        catch (NumberFormatException e) {

            System.err.println("Program needs a valid size");

            matrixSize = 0;

        }

        // Create matrix from reading the input
        if (matrixSize == 0) {

            matrix = new int[0][0];

        }
        // Create matrix and fill the array.
        else {

            matrix = new int[matrixSize][matrixSize];

            // Read each line of the matrix
            // Then read each character and place each
            // character in the matrix

            for (int i = 0; i < matrixSize; i++){

                try{

                    row = input.readLine();


                    stringCounter = 0;

                    for (int j = 0; j < matrixSize; j++){

                        if (row.charAt(stringCounter) == '-'){

                            matrix[i][j] = Character.getNumericValue(row.charAt(stringCounter+1));

                            matrix[i][j] = -matrix[i][j];

                            stringCounter += 3;

                        }

                        else if (Character.isDigit(row.charAt(stringCounter))){

                            matrix[i][j] = Character.getNumericValue(row.charAt(stringCounter));

                            stringCounter += 2;

                        }
                        else {

                            stringCounter += 2;

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

    /**
     * Print the matrix out, line by line
     */
    public void printMatrix(int[][] matrix) {


        System.out.print("Matrix: ");

        // Print the results one row at a time.
        for (int i = 0; i < matrix.length; i++) {

            System.out.println();

            for (int j = 0; j < matrix.length; j++) {

                System.out.print(matrix[i][j] + " ");
            }
        }

        System.out.println();
    }

    /**
     * Print the determinant for the user.
     *
     * @param determinant - int that describes the determinant.
     *
     */
    public void printDeterminant(int determinant){

        // Print the determinant to console for user.
        System.out.println("Determinant: " + determinant);
    }

    /**
     * Determine the determinant of the square matrix recursively
     *
     * @return - int value of the determinant
     */
    public int determineDeterminant(int[][] matrix){

        int determinant = 0;

        // Base Case
        if (matrix.length == 1) {

            determinant = matrix[0][0];

        } else { // Recursive Case

            for (int col = 0; col < matrix.length; col++) {

                int power;
                int matrixValue;
                int[][] minor;

                // Calculate the first parts of the determinant
                power = calculatePower(-1, col);

                matrixValue = matrix[0][col];

                // Get the minor (cofactor) for the recursive part
                minor = extractMinor(col, matrix);

                // Calculate the determinant
                // Recursive solution
                determinant =  determinant + (power *
                                              matrixValue *
                                             determineDeterminant(minor));

            }
        }

        return determinant;
    }

    /**
     * Calculate a portion of the determinant recursively: power(-1, i + j)
     *
     *
     * @param base - int that denotes the base
     * @param exponent - int that denotes the power (exponent)
     * @return int that is the power.
     *
     * Example: 2^3 = 8. The 2 is the base and the three is the exponent.
     */
    private int calculatePower(int base, int exponent){

        // Base Case
        if (exponent == 0){

            return 1;
        }
        else { // Recursive case
            return base * (calculatePower(base, exponent-1));
        }
    }

    /**
     * Extract the minor of a matrix, recursive.
     *
     * @return a smaller matrix of n-1 X n-1 size.
     */
    private int[][] extractMinor(int col, int[][] matrix){

        // Create minor that is n-1 x n-1 size
        int [][] minor = new int[matrix.length-1][matrix.length-1];

        int [] colsToGrab = new int [minor.length];

        // Grab the columns that we need to extract
        for (int i = 0;  i < matrix.length; i++){

            if (i != col & i < col){

                colsToGrab[i] = i;

            }
            else if (i != col & i > col){

                colsToGrab[i-1] = i;

            }
        }

        // Grab the rows and the right columns for new matrix

        for (int j = 0; j < minor.length; j++){

            for (int k = 0; k < minor.length; k++){

                minor[j][k] = matrix[j+1][colsToGrab[k]];

            }
        }

        return minor;
    }

}
