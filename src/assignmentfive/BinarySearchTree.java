package assignmentfive;

import java.io.*;
import java.util.*;

public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

  protected TreeNode<E> root;
  protected int size = 0;
  
  private java.util.ArrayList<E> list = new java.util.ArrayList<>();
  
  /** Create a default binary tree */
  public BinarySearchTree() { }

  /** Create a binary tree from an array of objects */
  public BinarySearchTree(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      insert(objects[i]);
  }

  /** Returns true if the element is in the tree */
  public boolean search(E e) {
    TreeNode<E> current = root; // Start from the root
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      }//if
      else if (e.compareTo(current.element) > 0) {
        current = current.right;
      }//else if
      else{ // element matches current.element
        return true; // Element is found
      }//else
    }//while
    return false;
  }//search
  
  public boolean search(E e,int[] count) {//overloaded search method to get counter
    TreeNode<E> current = root; // Start from the root
    while (current != null) {
      count[0]++;
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      }//if
      else if (e.compareTo(current.element) > 0) {
        current = current.right;
      }//else if
      else{ // element matches current.element
        return true; // Element is found
      }//else
    }//while
    return false;
  }//search

  /** Insert element o into the binary tree
   * Return true if the element is inserted successfully. 
   *  Uses an iterative algorithm
   */
  public boolean insert(E e) {
    if (root == null)
      root = createNewNode(e); // Create a new root
    else {
      // Locate the parent node
      TreeNode<E> parent = null;
      TreeNode<E> current = root;
      while (current != null)
        if (e.compareTo(current.element) < 0) {
          parent = current;
          current = current.left;
        }
        else if (e.compareTo(current.element) > 0) {
          parent = current;
          current = current.right;
        }
        else
          return false; // Duplicate node not inserted
          
      // Create the new node and attach it to the parent node
      if (e.compareTo(parent.element) < 0)
        parent.left = createNewNode(e);
      else
        parent.right = createNewNode(e);
    }
    size++;
    return true; // Element inserted
  }

  protected TreeNode<E> createNewNode(E e) {
    return new TreeNode<E>(e);
  }

  /** Inorder traversal from the root*/
  public void inorder() {
    inorder(root);
  }

  /** Inorder traversal from a subtree */
  protected void inorder(TreeNode<E> root) {
    if (root == null) return;
    inorder(root.left);
    System.out.print(root.element + " ");
    inorder(root.right);
  }
  
  protected void inorderList(TreeNode<E> root,ArrayList list) {
    if (root == null) return;
    inorderList(root.left,list);
    list.add(root.element);
    inorderList(root.right,list);
  }
   
  /** Postorder traversal from the root */
  public void postorder() {
    postorder(root);
  }

  /** Postorder traversal from a subtree */
  protected void postorder(TreeNode<E> root) {
    if (root == null) return;
    postorder(root.left);
    postorder(root.right);
    System.out.print(root.element + " ");
  }
  
  protected void postorderList(TreeNode<E> root,ArrayList list) {
    if (root == null) return;
    postorderList(root.left,list);
    postorderList(root.right,list);
    list.add(root.element);
  }
  
  /** Preorder traversal from the root */
  public void preorder() {
    preorder(root);
  }

  /** Preorder traversal from a subtree */
  protected void preorder(TreeNode<E> root) {
    if (root == null) return;
    System.out.print(root.element + " ");
    preorder(root.left);
    preorder(root.right);
  }
  
  protected void preorderList(TreeNode<E> root,ArrayList list) {
    if (root == null) return;
    list.add(root.element);
    preorderList(root.left,list);
    preorderList(root.right,list);
  }

  /** Inner class tree node */
  public static class TreeNode<E extends Comparable<E>> {
    E element;
    TreeNode<E> left;
    TreeNode<E> right;

    public TreeNode(E e) {
      element = e;
    }
  }

  /** Get the number of nodes in the tree */
  public int getSize() {
    return size;
  }

  /** Returns the root of the tree */
  public TreeNode getRoot() {
    return root;
  }
   
  /** Returns a path from the root leading to the specified element */
  public ArrayList<E> path(E e){
    java.util.ArrayList<E> list1 = new java.util.ArrayList<>();
    java.util.ArrayList<E> list2 = new java.util.ArrayList<>();
    TreeNode<E> current = root; // Start from the root
    //implement the code here as in search method.
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        list1.add(current.element);
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        list1.add(current.element);
        current = current.right;
      }
      else {// element matches current.element
        list1.add(current.element);   
        return list1; // Element is found return an array of elements
        }//else
    }//while
    return list2; // Return an empty array because the element was not found
  }//path
    
    /* Returns the number of leaf nodes in this tree*/
    public int getNumberOfLeaves(TreeNode<E> node){
        if( node == null ){
            return 0;
        }
        if( node.left == null && node.right == null ) {
            return 1;
        } 
        else {
            return getNumberOfLeaves(node.left) + getNumberOfLeaves(node.right);
        }
        
    }//getNumberOfLeaves
    
    /* Returns an ArrayList containing all elements in preorder of the specified element’s left sub-tree */
    public ArrayList<E> leftSubTree(E e){
        TreeNode<E> current = root;
        list.clear();//clear list before adding new values
        
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else if (e.compareTo(current.element) == 0){ // element matches current.element
                inorderList(current.left,list);
                current = null;
            }//else
        }//while
        return list;
    }//leftSubTree
    
    /* Returns an ArrayList containing all elements in preorder of the specified element’s right sub-tree */
    public ArrayList<E> rightSubTree(E e){
        TreeNode<E> current = root;
        list.clear();//clear list before adding new values
        
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else if (e.compareTo(current.element) == 0){ // element matches current.element
                inorderList(current.right,list);
                current = null;
            }//else
        }//while
        return list;
    }//rightSubTree
    
    /* Returns the inorder predecessor of the specified element */
    public E inorderPredecessor(E e){
        TreeNode<E> current = root;
        list.clear();//clear list before adding values to it
        inorderList(current, list);
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) == e){
                e = list.get(i-1);
            }//if
        }//for
        return e;
    }//for
    
    
  /** Delete an element from the binary tree.
   * Return true if the element is deleted successfully
   * Return false if the element is not in the tree */
  public boolean delete(E e) {
    // Locate the node to be deleted and also locate its parent node
    TreeNode<E> parent = null;
    TreeNode<E> current = root;
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        parent = current;
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        parent = current;
        current = current.right;
      }
      else
        break; // Element is in the tree pointed by current
    }
    if (current == null)
      return false; // Element is not in the tree
    // Case 1: current has no left children
    if (current.left == null) {
      // Connect the parent with the right child of the current node
      if (parent == null) {
         root = current.right;
      }
      else {
        if (e.compareTo(parent.element) < 0)
          parent.left = current.right;
        else
          parent.right = current.right;
      }
    }
    else {
      // Case 2 & 3: The current node has a left child
      // Locate the rightmost node in the left subtree of
      // the current node and also its parent
      TreeNode<E> parentOfRightMost = current;
      TreeNode<E> rightMost = current.left;

      while (rightMost.right != null) {
        parentOfRightMost = rightMost;
        rightMost = rightMost.right; // Keep going to the right
      }
      // Replace the element in current by the element in rightMost
      current.element = rightMost.element;
      
      // Eliminate rightmost node
      if (parentOfRightMost.right == rightMost)
        parentOfRightMost.right = rightMost.left;
      else
        // Special case: parentOfRightMost == current
        parentOfRightMost.left = rightMost.left;
    }
    size--;
    return true; // Element inserted
  }
  
  /** Obtain an iterator. Use inorder. */
  public java.util.Iterator iterator() {
    return inorderIterator();
  }

  /** Obtain an inorder iterator */
  public java.util.Iterator inorderIterator() {
    return new InorderIterator();
  }

  // Inner class InorderIterator
  class InorderIterator implements java.util.Iterator {
    // Store the elements in a list
    private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
    private int current = 0; // Point to the current element in list

    public InorderIterator() {
      inorder(); // Traverse binary tree and store elements in list
    }

    /** Inorder traversal from the root*/
    private void inorder() {
      inorder(root);
    }

    /** Inorder traversal from a subtree */
    private void inorder(TreeNode<E> root) {
      if (root == null)return;
      inorder(root.left);
      list.add(root.element);
      inorder(root.right);
    }

    /** Next element for traversing? */
    public boolean hasNext() {
      if (current < list.size())
        return true;
      return false;
    }

    /** Get the current element and move cursor to the next */
    public Object next() {
      return list.get(current++);
    }

    /** Remove the current element and refresh the list */
    public void remove() {
      delete(list.get(current)); // Delete the current element
      list.clear(); // Clear the list
      inorder(); // Rebuild the list
    }
  }

  /** Remove all elements from the tree */
  public void clear() {
    root = null;
    size = 0;
  }
}
