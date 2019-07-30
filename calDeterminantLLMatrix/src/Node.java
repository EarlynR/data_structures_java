/*
* @author: Earlyn Reinhardt
*
* Create a Node class that will be the element of the doubly linked list stack.
*
*/

import java.io.FileWriter;
import java.io.IOException;

/**
 * Node class for a doubly linked list composed Matrix.
 */
public class Node {

    public int row;   // Row location in the "matrix"
    public int col;   // Column location in the "matrix"
    public int data;  // Actual value within location of the "matrix"
    public int power; // (-1) ^ (row + col)

    public Node next; // Next node
    public Node prev; // Previous node

    /**
     * Create a node with the row, col, data, previous Node, and next Node.
     **
     * @param row - int that tells us the row of the matrix
     * @param col - int that tells us the column in the matrix
     * @param data - int that tells us the actual value
     */
    Node(int row, int col, int data) {

        this.row = row;
        this.col = col;
        this.data = data;
        setPower();
        this.next = null;
        this.prev = null;

    }

    /**
     * Make a new node out of an existing node.
     * Essentially, this method creates a copy
     *
     * @param existingNode - A node with information
     */
    Node(Node existingNode){

        this.row = existingNode.row;
        this.col = existingNode.col;
        this.data = existingNode.data;
        setPower();
        this.next = null;
        this.prev = null;
    }

    /**
     * Calculate the power for the determinant formula:
     * (-1) ^ (row + col)
     *
     * @return int - result of the above calculation
     */
    public void setPower(){

        this.power = 1;

        int exponent = row + col;
        int base = -1;

        // Calculate the power for the
        // part of the determinant formula: -1 ^ (row + col)
        while (exponent >= 1){

            this.power *= base  ;
            exponent--;

        }
    }

    /**
     * Print the node data only without additional text.
     *
     * Use this to print contents of matrix.
     */
    void printNodeDataOnly(){
        System.out.print(data + " ");
    }

    /**
     * Uncovers the data that is hidden in the Node object to let the user
     * know the contents.
     *
     * Print the data that is incorporated into the Node class.
     */
    void printNodeData() {
        System.out.println("Data: " + data);
    }

    /**
     * Print the row of the Node. Shows us which row the node resides in the
     * matrix.
     */
    void printNodeRow(){
        System.out.println("Row: " + row);
    }

    /**
     * Print the column of the Node.
     * Shows us which column the node is in the matrix.
     */
    void printNodeCol(){
        System.out.println("Column: " + col);
    }

    /**
     * Print the power of the Node. Helpful for troubleshooting.
     */
    public void printNodePower(){

        System.out.println("Power: " + power);

    }

    /**
     * Print all information about the Node for user
     */
    void printNodeInfo(){
        printNodeData();
        printNodeRow();
        printNodeCol();
        printNodePower();
    }

}
