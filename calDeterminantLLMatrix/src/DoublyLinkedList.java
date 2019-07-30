/*
* @author: Earlyn Reinhardt
*
* Description: Stacks can use two data structures, a linked list and an array.
* This stack uses a linked list, implementing the StackInterface.
*
*
*
*/

import java.lang.*;

/**
 * Create a doubly linked list with headers.
 *
 * There are not dummy header or tail nodes.
 */
public class DoublyLinkedList {

    private int numNodes = 0;
    private double sizeMatrix = 0;
    private int determinant;
    private Node head = null;
    private Node tail = null;

    /**
     * Constructor
     *
     * No parameters, so the size is 0.
     */
    public DoublyLinkedList() {
    }

    /**
     * Retrieve the size of the linked list. The size is equivalent to the m
     * in the mxm matrix.
     *
     * @return - int that says what size is the linked list.
     */
    public int getSize(){

        return (int) sizeMatrix;
    }

    /**
     * Set the size of the matrix.
     *
     * In linear algebra, a square matrix is size m x m (m^2 = nodes (n)).
     * M^2 is the size of the linked list but we need size m. So, we take the
     * square root.
     *
     */
    public void setSize(){

        sizeMatrix = Math.sqrt(numNodes);

    }

    /**
     * Get the head node of the stack. The first that is entered.
     *
     * @return Node - Node that is the head of the list.
     */
    public Node getHead(){

        return head;
    }

    /**
     * Get the tail node of the list. This is the last node in the linked
     * list. The tail node is only the first node if there is one node in the
     * linked list.
     *
     * @return Node - Node that is the tail of the list.
     */
    public Node getTail(){

        return tail;
    }

    /**
     * Check to see if the linked list is empty. Head should be null. Tail
     * will automatically be null as well.
     *
     * @return boolean - tells the user whether or not there are any nodes in
     * the linked list.
     */
    public boolean isEmpty() {

        return head == null && tail == null;
    }

    /**
     * Check the size of the "matrix". Since the matrix is
     * size (m x m), the linked list size if m^2 = nodes (n).
     *
     * If we take the square root, we should have n. If it's a decimal, then
     * the size is m.xx, which means that the matrix is not square (complete).
     *
     * @return
     */
    public boolean checkSize(){

        if (sizeMatrix % 1 == 0){

            return true;

        }
        else {

            return false;
        }

    }

    /**
     * Add new node to list. This will change the tail node and provide the
     * correct link for the new node.
     *
     * @param newNode - Node containing part of the information of the matrix
     */
    public void addNode(Node newNode) {

        if (isEmpty()) {

            // Add a new node which will be the head and tail at the same time.
            head = newNode;
            tail = newNode;
        }
        else {

            // Add a new node to an unempty linked list.
            // There will be a new tail so the old tail will have a new link.
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        // Increase number of nodes.
        numNodes++;

        // Set the size (n in the n X n matrix)
        setSize();
    }

    /**
     * Print linked list contents
     *
     * Use this if you only want the data attribute of the nodes.
     */
    public void printLinkedListContents() {

        if (isEmpty() == true) {

            System.out.println("The matrix is empty.");

        } else {

            Node curr = head;

            int row = head.row;

            // Iterate through the linked list, print each node one by one.
            while (curr != null){

                if (row == curr.row){

                    curr.printNodeDataOnly();
                    curr = curr.next;
                }

                else {

                    // Print new line indicating new row.
                    System.out.println();
                    curr.printNodeDataOnly();

                    // Set row to the new, subsequent row.
                    row = curr.row;
                    curr = curr.next;
                }
            }

            System.out.println();
        }
    }

    /**
     * Print all information about each node in LinkedList
     *
     * Use this if you want details information about the nodes.
     *
     */
    public void printLinkedListContentsDetailed(){

        if (isEmpty() == true) {

            System.out.println("The matrix is empty.");

        } else {

            Node curr = head;

            int row = head.row;

            // Iterate through the linked list, print each node one by one.
            while (curr != null){

                if (row == curr.row){

                    curr.printNodeInfo();
                    curr = curr.next;
                }

                else {

                    // Print new line indicating new row.
                    System.out.println();
                    curr.printNodeInfo();

                    // Set row to the new, subsequent row.
                    row = curr.row;
                    curr = curr.next;
                }
            }
        }
    }

    /**
     * Print the determinant for user. Only applicable for square matrices.
     */
    public void printDeterminant(){

        System.out.println("Determinant: " + determinant);

    }

    /**
     * Reorder each of the nodes with a new column and row, starting with row
     * = 1 and column = 1. Also, recalculate the power with the node's new
     * location.
     */
    public void reOrderLinkedList(){

        if (isEmpty()){

            return;
        }
        else{

            // Reorder the "matrix", starting with location [1, 1]
            int newRow = 1;
            int newCol = 1;
            Node curr;

            curr = head;

            // Use to check whether or not the row
            // has change
            int prevRow = curr.row;

            while (curr != null){

                // Row has changed, need to start a "new" row.
                // Restart the column as one since the row is empty.
                if (prevRow != curr.row){

                    prevRow = curr.row;
                    newRow++;
                    newCol = 1;
                    curr.row = newRow;
                    curr.col = newCol;
                    newCol++;

                }
                // The node belongs in the same row as the previous one.
                // Add it in a new column.
                else {

                    curr.row = newRow;
                    curr.col = newCol;
                    newCol++;

                }

                // Reset the power
                curr.setPower();
                curr = curr.next;

            }

        }

    }

    /**
     * Extract the minor for the determinant. All nodes will have the same
     * data as the major matrix, but the rows and columns will follow the
     * order of the minor matrix.
     *
     * @param row
     * @param col
     * @return
     */
    public DoublyLinkedList extractMinor(int row, int col, DoublyLinkedList
            matrix){


        DoublyLinkedList minor = new DoublyLinkedList();
        Node tempNode;

        Node curr = matrix.getHead();

        // Iterate through the original linked list
        // Grab only what is needed.
        while (curr != null){

            // Grab the node if it doesn't belong to the
            // column or row of the main node in the determinant formula
            // Node can't be part of the same row and column.
            if (curr.row != row && curr.col != col){

                tempNode = new Node(curr);
                minor.addNode(tempNode);

            }

            curr = curr.next;
        }

        // Reorder their location since nodes are now
        // a part of a new matrix.
        minor.reOrderLinkedList();

        return minor;
    }

    /**
     * Calculate the determinant of the matrix. This will use the power, the
     * data value, and the minor. This method is recursive.
     *
     * @param matrix - a matrix made up of a doubly linked list.
     * @return int that is the determinant.
     */
    public int calculateDeterminant(DoublyLinkedList matrix){

        determinant = 0;

        Node curr = matrix.getHead();

        // Base Case
        if (matrix.getSize() == 1) {

            determinant = curr.data;

        }
        else { // Recursive Case

            for (int col = 1; col <= matrix.getSize(); col++) {

                // Get the minor (cofactor) for the recursive part
                DoublyLinkedList minor = extractMinor(curr.row, curr.col, matrix);

                // Calculate the determinant
                //  Recursive solution
                determinant = determinant + (curr.power * curr.data *
                        calculateDeterminant(minor));

                curr = curr.next;
            }
        }

        return determinant;
    }

}
