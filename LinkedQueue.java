package dnaAnalyser;
import java.util.Iterator;
/**
 * LinkedQueue represents a linked implementation of a queue.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 08/12/08
 */

public class LinkedQueue<T> implements QueueADT<T>
{
   private int count;
   private LinearNode<T> front, rear;

   /**
    * Creates an empty queue.
    */
   public LinkedQueue() {
      count = 0;
      front = rear = null;
   }

   /**
    * Adds the specified element to the rear of this queue.
    *
    * @param element  the element to be added to the rear of this queue
    */
   public void enqueue (T element) {
      LinearNode<T> node = new LinearNode<T>(element);

      if (isEmpty())
         front = node;
      else
         rear.setNext (node);

      rear = node;
      count++;
   }

   /**
    * Removes the element at the front of this queue and returns a
    * reference to it. Throws an EmptyCollectionException if the
    * queue is empty.
    *
    * @return                           the element at the front of this queue
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T dequeue() throws EmptyCollectionException {
      if (isEmpty())
         throw new EmptyCollectionException ("queue");

      T result = front.getElement();
      front = front.getNext();
      count--;

      if (isEmpty())
         rear = null;

      return result;
   }
   
   /**
    * Returns a reference to the element at the front of this queue.
    * The element is not removed from the queue.  Throws an
    * EmptyCollectionException if the queue is empty.  
    *
    * @return                            a reference to the first element in
    *                                    this queue
    * @throws EmptyCollectionsException  if an empty collection exception occurs
    */
   public T first() throws EmptyCollectionException {
      if (isEmpty())
         throw new EmptyCollectionException ("queue");

      return front.getElement();
   }

   /**
    * Returns true if this queue is empty and false otherwise. 
    *
    * @return  true if this queue is empty and false if otherwise
    */
   public boolean isEmpty() {
	   return count==0;
   }
 
   /**
    * Returns the number of elements currently in this queue.
    *
    * @return  the integer representation of the size of this queue
    */
   public int size() {
	   return count;
   }

   /**
    * Returns a string representation of this queue. 
    *
    * @return  the string representation of this queue
    */
   public String toString() {
       String out = "";
       LinearNode<T> current = front;
       while (current != null) {
          out += (current == front?"":"--") + current.getElement() ;
          current = current.getNext();
       }
       return "LinkedList ["+out+"]";
   }

    public Iterator<T> iterator(){
       return new LQIterator<T>(front);
    }

    private class LQIterator<T> implements Iterator<T> {
       private LinearNode<T> current;

       // this will have unpredictable behaviour if the Linked list is modified before the iterator is completed.
       public LQIterator(LinearNode<T> current){
          this.current = current;
       }
       public boolean hasNext(){
          return current != null;
       }
       public T next(){
          T dataItem = current.getElement();
          current = current.getNext();
          return dataItem;
       }
       public void remove() {
       }
    }
}
