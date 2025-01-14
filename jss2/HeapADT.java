//*******************************************************************
//  HeapADT.java		Authors:  Lewis and Chase
//
//  Defines the interface to a Heap.
//*******************************************************************
package jss2;

public interface HeapADT<T> extends BinaryTreeADT<T> 
{
   /** Adds the specified object to this heap. */
   public void addElement (T obj);
   
   /** Removes and returns a reference to the element with the 
       lowest value in this heap. */
   public T removeMin();
   
   /** Removes the element with the lowest value in this heap. */
   public T findMin();
}
