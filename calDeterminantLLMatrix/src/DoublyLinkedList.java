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


/**
 * Create a doubly linked list
 */
public class DoublyLinkedList {

    private int size = 0;
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
     * Get the size of the list
     *
     * @return int -  contains the number of nodes in a list.
     */
    public int getSize(){

        return size;
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

        size++;
    }

    /**
     * Print linked list
     */
    public void printLinkedList() {
        if (isEmpty() == true) {

            System.out.println("The matrix is empty.");

        } else {

            Node curr = head;

            while (curr != null){
                curr.printNode();
                curr = curr.next;
            }
        }
    }

}
