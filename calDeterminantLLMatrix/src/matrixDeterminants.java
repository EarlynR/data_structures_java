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

//TODO: Fix the determinant method.

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


        matrix.addNode(new Node(1, 1, 3));
        matrix.addNode(new Node(1, 2, 5));
        matrix.addNode(new Node(1, 3, 8));
        matrix.addNode(new Node(2, 1, 4));
        matrix.addNode(new Node(2, 2, 9));
        matrix.addNode(new Node(2, 3, 7));
        matrix.addNode(new Node(3, 1, 2));
        matrix.addNode(new Node(3, 2, -1));
        matrix.addNode(new Node(3, 3, 0));



        System.out.println("Matrix: ");
        matrix.printLinkedList();
        //System.out.println("Matrix size: " + matrix.getSize());
        System.out.println();

        System.out.println("Determinant: " + matrix.calculateDeterminant
                (matrix));
    }
}