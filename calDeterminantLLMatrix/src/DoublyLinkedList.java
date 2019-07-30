/*
* @author: Earlyn Reinhardt
*
* Description: Stacks can use two data structures, a linked list and an array.
* This stack uses a linked list, implementing the StackInterface.
*
*
*
*/
import java.lang.NullPointerException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.*;


/**
 * Create a doubly linked list
 */
public class DoublyLinkedList {

    private int numNodes = 0;
    private int sizeMatrix = 0;
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
     * Retrieve the size of the linked list.
     *
     * @return - int that says what size is the linked list.
     */
    public int getSize(){

        sizeMatrix = (int) Math.sqrt(numNodes);

        return sizeMatrix;
    }

    /**
     * Get the head node of the stack. The first who entered.
     *
     * @return Node - Node that is the head of the list.
     */
    public Node getHead(){
        return head;
    }

    /**
     * Get the tail node of the list.
     *
     * @return Node - Node that is the tail of the list.
     */
    public Node getTail(){
        return tail;
    }

    /**
     * @return boolean - tells the user whether or not there are any nodes in
     * the linked list.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Add new node to list
     *
     * @param newNode - Node containing part of the infomration of the matrix
     */
    public void addNode(Node newNode) {

        if (isEmpty()) {

            head = newNode;
            tail = newNode;
        }
        else {

            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        numNodes++;
    }

    /**
     * Print linked list
     */
    public void printLinkedListContents() {

        if (isEmpty() == true) {

            System.out.println("The matrix is empty.");

        } else {

            Node curr = head;

            int row = head.row;

            while (curr != null){

                if (row == curr.row){

                    curr.printNodeDataOnly();
                    curr = curr.next;
                }

                else {

                    System.out.println(); // Print new line indicating new row.
                    curr.printNodeDataOnly();
                    row = curr.row;       // Set row to the new, subsequent row.
                    curr = curr.next;
                }
            }
        }
    }

    /**
     * Print the determinant
     */
    public void printDeterminant(){

        System.out.println("Determinant: " + determinant);
    }

    /**
     * Print all information about each node in LinkedList
     *
     */
    public void printLinkedListContentsDetailed(){

        if (isEmpty() == true) {

            System.out.println("The matrix is empty.");

        } else {

            Node curr = head;

            int row = head.row;

            while (curr != null){

                if (row == curr.row){

                    curr.printNodeInfo();
                    curr = curr.next;
                }

                else {

                    System.out.println(); // Print new line indicating new row.
                    curr.printNodeInfo();
                    row = curr.row;       // Set row to the new, subsequent row.
                    curr = curr.next;
                }
            }
        }
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

            int newRow = 1;
            int newCol = 1;
            Node curr;

            curr = head;
            int prevRow = curr.row;

            while (curr != null){

                if (prevRow != curr.row){

                    prevRow = curr.row;
                    newRow++;
                    newCol = 1;
                    curr.row = newRow;
                    curr.col = newCol;
                    newCol++;

                } else {

                    curr.row = newRow;
                    curr.col = newCol;
                    newCol++;

                }

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
        Node nodeToAdd;

        Node curr = matrix.getHead();


        while (curr != null){


            if (curr.row != row && curr.col != col){

                nodeToAdd = new Node(curr);
                minor.addNode(nodeToAdd);

            }

            curr = curr.next;
        }

        minor.reOrderLinkedList();

        return minor;
    }

    /**
     * Calculate the determinant of the matrix
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


                System.out.println("Col: " + curr.col);
                System.out.println("Minor Size: " + minor.getSize());
                minor.printLinkedListContents();
                System.out.println();
                curr.printNodeData();
                curr.printNodePower();
                System.out.println();

                // Calculate the determinant
               //  Recursive solution

                determinant = determinant + (curr.power * curr.data *
                        calculateDeterminant(minor));

                curr = curr.next;
                System.out.println("Determinant: " + determinant);


            }
        }

        return determinant;
    }


}
