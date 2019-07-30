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

//TODO: Add in code to take in files.
//TODO: Add inline comments.
//TODO: Create method to print determinant.

public class matrixDeterminants{

    public static void main (String[] args){

        DoublyLinkedList matrix = new DoublyLinkedList();
        DoublyLinkedList minor;

/*
        matrix.addNode(new Node(1, 1, 3));
        matrix.addNode(new Node(1, 2, 5));
        matrix.addNode(new Node(2, 1, 4));
        matrix.addNode(new Node(2, 2, 9));
*/


        matrix.addNode(new Node(1, 1, -1));
        matrix.addNode(new Node(1, 2, -2));
        matrix.addNode(new Node(1, 3, -3));
        matrix.addNode(new Node(2, 1, 4));
        matrix.addNode(new Node(2, 2, -5));
        matrix.addNode(new Node(2, 3, 6));
        matrix.addNode(new Node(3, 1, -7));
        matrix.addNode(new Node(3, 2, 8));
        matrix.addNode(new Node(3, 3, -9));

        matrix.calculateDeterminant(matrix);

    }
}