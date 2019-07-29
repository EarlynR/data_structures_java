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

    public int row;
    public int col;
    public int data;
    public int power;

    public Node next;
    public Node prev;

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
        this.power = calculatePower();
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
        this.power = calculatePower();
        this.next = null;
        this.prev = null;
    }

    /**
     * Print the node data only without additional text
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
     * Print the row of the Node
     */
    void printNodeRow(){
        System.out.println("Row: " + row);
    }

    /**
     * Print the column of the Node
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
    }

    /**
     * Calculate the power for the determinant formula:
     * (-1) ^ (row + col)
     *
     * @return int - result of the above calculation
     */
    public int calculatePower(){

        int exponent = row + col;
        int base = -1;
        int power = 1;

        while (exponent >= 1){

            power *= base  ;
            exponent--;

        }

        return power;
    }



}
