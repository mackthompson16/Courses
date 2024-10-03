import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author YOUR NAME (you@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

    //////////////////////////////////////////////////////////
    // Do not change the following three fields in any way. //
    //////////////////////////////////////////////////////////

    /** References to the first and last node of the list. */
    Node front;
    Node rear;

    /** The number of nodes in the list. */
    int size;

    /////////////////////////////////////////////////////////
    // Do not change the following constructor in any way. //
    /////////////////////////////////////////////////////////

    /**
     * Instantiates an empty LinkedSet.
     */
    public LinkedSet() {
        front = null;
        rear = null;
        size = 0;
    }


    //////////////////////////////////////////////////
    // Public interface and class-specific methods. //
    //////////////////////////////////////////////////

    ///////////////////////////////////////
    // DO NOT CHANGE THE TOSTRING METHOD //
    ///////////////////////////////////////
    /**
     * Return a string representation of this LinkedSet.
     *
     * @return a string representation of this LinkedSet
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (T element : this) {
            result.append(element + ", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }


    ///////////////////////////////////
    // DO NOT CHANGE THE SIZE METHOD //
    ///////////////////////////////////
    /**
     * Returns the current size of this collection.
     *
     * @return  the number of elements in this collection.
     */
    public int size() {
        return size;
    }

    //////////////////////////////////////
    // DO NOT CHANGE THE ISEMPTY METHOD //
    //////////////////////////////////////
    /**
     * Tests to see if this collection is empty.
     *
     * @return  true if this collection contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Ensures the collection contains the specified element. Neither duplicate
     * nor null values are allowed. This method ensures that the elements in the
     * linked list are maintained in ascending natural order.
     *
     * @param  element  The element whose presence is to be ensured.
     * @return true if collection is changed, false otherwise.
     */
    public boolean contains(T element) {
        Node curr = front;
        for(int i = 0; i < size; i++){
            if(element.equals(curr.element)){
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public boolean add(T element) {
        if(element == null||contains(element)) {return false;}

        if(front   == null){
            front  = new Node(element);
            rear   = front;
            size++;
        } else {

            int lower = element.compareTo(rear.element);
            int upper = -1;

            Node newNode = new Node(element);

            rear.next = newNode;
            Node temp1 = rear;
            rear = newNode;
            newNode.prev = temp1;

            for (int i = 0; i < size; i++) {

                    if (lower > 0 && upper < 0) {

                        size++;
                        return true;

                    } else {

                        T temp = newNode.prev.element;
                        newNode.prev.element = newNode.element;
                        newNode.element = temp;
                        newNode = newNode.prev;
                        newNode.next.prev = newNode;

                        if (newNode.prev == null) {
                            front = newNode;
                            size++;
                            return true;
                        }

                        upper = lower;
                        lower = element.compareTo(newNode.prev.element);


                    }
                }
            }

        return true;
    }




    /**\
     * Ensures the collection does not contain the specified element.
     * If the specified element is present, this method removes it
     * from the collection. This method, consistent with add, ensures
     * that the elements in the linked lists are maintained in ascending
     * natural order.
     *
     * @param   element  The element to be removed.
     * @return  true if collection is changed, false otherwise.
     */
    public boolean remove(T element) {

        if(!validIndex(indexOf(element))){return false;}

        if(size == 1){
            front = null;
            rear = null;
            size--;
            return true;}

        int index = indexOf(element);
        Node curr = getNode(index);

        if(index+1 == size){
            curr = curr.prev;
            rear.prev.next = null;
            rear = rear.prev;

            size--;
            return true;
        }

        for(int i = index; i < size()-2; i ++){

            T temp = curr.next.element;
            curr.next.element = curr.element;
            curr.element = temp;
            curr = curr.next;
            curr.next.prev = curr;

        }
        rear.prev.element = rear.element;
        rear.prev.next = null;
        rear = rear.prev;

        size--;

        return true;
    }

    /**
     * Searches for specified element in this collection.
     *
     * @param   element  The element whose presence in this collection is to be tested.
     * @return  true if this collection contains the specified element, false otherwise.
     */

    private int indexOf(T element){
        Node curr = front;
        for(int i = 0; i < size(); i++){
            if(curr.element.equals(element)){
                return i;
            }
            curr = curr.next;
        }
        return -1;
    }

    private Node getNode(int index){
        Node curr = front;
        for(int i = 0 ; i < index; i++){
            curr = curr.next;
        }
        return curr;
    }
    private boolean validIndex(int index){
        return (index > -1) && (index < size);
    }

    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(Set<T> s) {
        Iterator<T> it = s.iterator();
        while(it.hasNext())
        {
              if(!contains(it.next()))
                 {
                return false;
                 }
        }
        return true;
    }



    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(LinkedSet<T> s) {
        if(size!=s.size()){return false;}
    Node curr = front;

    for(int i =0; i < size; i++)
    {
        if(!s.contains(curr.element))
        {
            return false;
        }

        curr = curr.next;
    }

return true;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(Set<T> s){
    if(s.isEmpty()||equals(s)){return this;}

    Set<T> union = new LinkedSet<T>();

      for(T val: this){
          union.add(val);
      }
      for(T val: s){
          union.add(val);
      }
      return union;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(LinkedSet<T> s){
        if(s.isEmpty()||equals(s)){return this;}

        Set<T> union = new LinkedSet<T>();
        for(T val: this){
            union.add(val);
        }
        for(T val: s){
            union.add(val);
        }
        return union;
    }


    /**
     * Returns a set that is the intersection of this set and the parameter set.
     *
     * @return  a set that contains elements that are in both this set and the parameter set
     */
    public Set<T> intersection(Set<T> s) {
        if(s.isEmpty()){return s;}
if(equals(s)||isEmpty()){return this;}


     LinkedSet<T> intersection = new LinkedSet<T>();


     for(T val:this){
         if(s.contains(val)){
             intersection.add(val);
         }
     }
     return intersection;
    }

    /**
     * Returns a set that is the intersection of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in both
     *            this set and the parameter set
     */
    public Set<T> intersection(LinkedSet<T> s)
    {
        if(equals(s)||isEmpty()){return this;}

LinkedSet<T> intersection = new LinkedSet<T>();

        if(s.isEmpty()){return intersection;}


        for(T val:this){
            if(s.contains(val)){
                intersection.add(val);
            }
        }
        return intersection;
    }


    /**
     * Returns a set that is the complement of this set and the parameter set.
     *
     * @return  a set that contains elements that are in this set but not the parameter set
     */
    public Set<T> complement(Set<T> s) {
        if(s.isEmpty()){return this;}
LinkedSet<T> complement = new LinkedSet<T>();

     for(T val: this){
         complement.add(val);

     }
     for(T val: s){
         complement.remove(val);
     }

return complement;

    }


    /**
     * Returns a set that is the complement of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in this
     *            set but not the parameter set
     */
    public Set<T> complement(LinkedSet<T> s)
    {

        if(s.isEmpty()){return this;}
        LinkedSet<T> complement = new LinkedSet<T>();

        for(T val: this){
            complement.add(val);

        }
        for(T val: s){
            complement.remove(val);
        }
        return complement;

    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in ascending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */

    private class DescendingLinkedSetIterator implements Iterator<T> {
        private Node current = rear;


        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T value = current.element;
            current = current.prev;
            return value;
        }
    }
    private class AscendingLinkedSetIterator implements Iterator<T> {
        private Node current = front;


        public boolean hasNext() {

            return current != null;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T value = current.element;
            current = current.next;

            return value;
        }
    }
    public Iterator<T> iterator() {

        return new AscendingLinkedSetIterator();

    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in descending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */


    public Iterator<T> descendingIterator() {
        return new DescendingLinkedSetIterator();
    }


    /**
     * Returns an iterator over the members of the power set
     * of this LinkedSet. No specific order can be assumed.
     *
     * @return  an iterator over members of the power set
     */

    private class PowerSetIterator implements Iterator<Set<T>>{

        int currIndex = 0;
        private int totalSets;

        public PowerSetIterator(){
            totalSets =(int)Math.pow(2,size());
        }
        public boolean hasNext(){
            return currIndex < totalSets;
        }

        public Set<T> next(){
            Set<T> curr = new LinkedSet<T>();
            if(!hasNext())  {
                throw new NoSuchElementException();
            }

            currIndex++;
            return curr;
        }
    }
    public Iterator<Set<T>> powerSetIterator() {
        return new PowerSetIterator();
    }



    //////////////////////////////
    // Private utility methods. //
    //////////////////////////////

    // Feel free to add as many private methods as you need.

    ////////////////////
    // Nested classes //
    ////////////////////

    //////////////////////////////////////////////
    // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
    //////////////////////////////////////////////

    /**
     * Defines a node class for a doubly-linked list.
     */
    class Node {
        /** the value stored in this node. */
        T element;
        /** a reference to the node after this node. */
        Node next;
        /** a reference to the node before this node. */
        Node prev;

        /**
         * Instantiate an empty node.
         */
        public Node() {
            element = null;
            next = null;
            prev = null;
        }

        /**
         * Instantiate a node that containts element
         * and with no node before or after it.
         */
        public Node(T e) {
            element = e;
            next = null;
            prev = null;
        }
    }

}
