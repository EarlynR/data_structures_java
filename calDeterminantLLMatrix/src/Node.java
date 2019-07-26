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

    private int row;
    private int col;
    private int data;

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
        this.next = null;
        this.prev = null;
    }

    /**
     * Uncovers the data that is hidden in the Node object to let the user
     * know the contents.
     *
     * Print the data that is incorporated into the Node class.
     */
    void printNode() {
        System.out.println(data);
        System.out.print(" ");
    }
}
