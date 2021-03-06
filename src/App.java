// JOHN MUEMA MICHAEL
// DATE 03/07/2021
// COSC 2436
// DESCRIPTION generic linked list
/**
 * Your job in this assignment is to create a generic linked list. The linked
 * list should have the ability to perform the following actions:
 * 
 * check if the list is empty check the size of the list add data to the list
 * add data to a specific (valid) location in the list remove an item from the
 * list based on data value outputs the contents of the list Demonstrate your
 * functions using Doubles, Integers, and Strings.
 * 
 * Sample Output:
 * 
 * The size of the list is: 3 The members of my list: 3.3 4.5 5.34 The size of
 * the list is: 3 The members of my list: 3 4 54 The size of the list is: 3 The
 * members of my list: Al Carol Bob
 */
public class App {
    
    static class GenericLinkedList1<T>{
        /**
         * The Node class stores a list element and a reference to the next node.
         */

        private class Node {
            T value;
            Node next;

            /**
             * Constructor.
             * 
             * @param val The element to store in the node.
             * @param n   The reference to the successor node.
             */

            Node(T val, Node n) {
                value = val;
                next = n;
            }

            /**
             * Constructor.
             * 
             * @param val The element to store in the node.
             */

            Node(T val) {
                // Call the other (sister) constructor.
                this(val, null);
            }
        }

        private Node first; // list head
        private Node last; // last element in list

        /**
         * Constructor.
         */

        public  GenericLinkedList1() {
            first = null;
            last = null;
        }

        /**
         * The isEmpty method checks to see if the list is empty.
         * 
         * @return true if list is empty, false otherwise.
         */

        public boolean isEmpty() {
            return first == null;
        }

        /**
         * The size method returns the length of the list.
         * 
         * @return The number of elements in the list.
         */

        public int size() {
            int count = 0;
            Node p = first;
            while (p != null) {
                // There is an element at p
                count++;
                p = p.next;
            }
            return count;
        }

        /**
         * The add method adds an element to the end of the list.
         * 
         * @param e The value to add to the end of the list.
         */

        public void add(T e) {
            if (isEmpty()) {
                first = new Node(e);
                last = first;
            } else {
                // Add to end of existing list
                last.next = new Node(e);
                last = last.next;
            }
        }

        /**
         * The add method adds an element at a position.
         * 
         * @param e     The element to add to the list.
         * @param index The position at which to add the element.
         * @exception IndexOutOfBoundsException When index is out of bounds.
         */

        public void add(int index, T e) {
            if (index < 0 || index > size()) {
                String message = String.valueOf(index);
                throw new IndexOutOfBoundsException(message);
            }

            // Index is at least 0
            if (index == 0) {
                // New element goes at beginning
                first = new Node(e, first);
                if (last == null)
                    last = first;
                return;
            }

            // Set a reference pred to point to the node that
            // will be the predecessor of the new node
            Node pred = first;
            for (int k = 1; k <= index - 1; k++) {
                pred = pred.next;
            }

            // Splice in a node containing the new element
            pred.next = new Node(e, pred.next);

            // Is there a new last element ?
            if (pred.next.next == null)
                last = pred.next;
        }

        /**
         * The toString method computes the string representation of the list.
         * 
         * @return The string form of the list.
         */

        public String toString() {
            StringBuilder strBuilder = new StringBuilder();

            // Use p to walk down the linked list
            Node p = first;
            while (p != null) {
                strBuilder.append(p.value + "\n");
                p = p.next;
            }
            return strBuilder.toString();
        }

        /**
         * The remove method removes the element at an index.
         * 
         * @param index The index of the element to remove.
         * @return The element removed.
         * @exception IndexOutOfBoundsException When index is out of bounds.
         */

        public String remove(int index) {
            if (index < 0 || index >= size()) {
                String message = String.valueOf(index);
                throw new IndexOutOfBoundsException(message);
            }

            String element; // The element to return
            if (index == 0) {
                // Removal of first item in the list
                element = (String) first.value;
                first = first.next;
                if (first == null)
                    last = null;
            } else {
                // To remove an element other than the first,
                // find the predecessor of the element to
                // be removed.
                Node pred = first;

                // Move pred forward index - 1 times
                for (int k = 1; k <= index - 1; k++)
                    pred = pred.next;

                // Store the value to return
                element = (String) pred.next.value;

                // Route link around the node to be removed
                pred.next = pred.next.next;

                // Check if pred is now last
                if (pred.next == null)
                    last = pred;
            }
            return element;
        }

        /**
         * The remove method removes an element.
         * 
         * @param element The element to remove.
         * @return true if the remove succeeded, false otherwise.
         */

        public boolean remove(T element) {
            if (isEmpty())
                return false;

            if (element.equals(first.value)) {
                // Removal of first item in the list
                first = first.next;
                if (first == null)
                    last = null;
                return true;
            }

            // Find the predecessor of the element to remove
            Node pred = first;
            while (pred.next != null && !pred.next.value.equals(element)) {
                pred = pred.next;
            }

            // pred.next == null OR pred.next.value is element
            if (pred.next == null)
                return false;

            // pred.next.value is element
            pred.next = pred.next.next;

            // Check if pred is now last
            if (pred.next == null)
                last = pred;

            return true;
        }

        public static void main(String[] args) {
            // create a new list ll
          GenericLinkedList1<String> ll = new GenericLinkedList1<String>();
           ll.add("Amy");
            ll.add("Bob");
            ll.add(0, "Al");
            ll.add(2, "Beth");
            ll.add(4, "Carol");
            System.out.println("The members of the list are: "+"\n"+ll);

           System.out.println("the size of our list is : " + ll.size());
            // remove carol
            ll.remove(4);
            //print the size of list after removing from list
           System.out.println("The size of the list after removing carol is :" + "" + ll.size() + "\n");
            // replace carol with subcaro
           ll.add(4, "subcaro");
           System.out.println("after replacing caro  with subcaro the size becomes :" + " " + ll.size() + "\n" + ll);

            /*adding a generic list which accepts doubles and a and stores the with a given index
             * @my list
             */
            GenericLinkedList1<Double> MyList = new GenericLinkedList1<Double>();
            MyList.add(0, 2.76);
            MyList.add(1, 16.7);
            MyList.add(18.8);
             System.out.println("The size of myList is :"+MyList.size() +"\n"+
             "The memnbers are : "+"\n"+ MyList);
             // calling method to remove item at position 1
             MyList.remove(2.76);

             System.out.println("The size after removing item on position 1 is :"+""+MyList.size()+"\n"+MyList);
             /*creat a new integer list  with name
             @myInts*/
             GenericLinkedList1<Integer> myInts = new GenericLinkedList1<Integer>();
             myInts.add(2);
             myInts.add(5);
             myInts.add(0, 7);
             System.out.println("The size my generic list MyInts is :"+ myInts.size()+"\n" + myInts);


        }
    }
}
