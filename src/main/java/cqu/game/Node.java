/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.game;

/**
 *
 * @author 12233612
 */

public class Node {

     protected String data;
     protected int label;
     protected  Node left;
     protected  Node right;

     /**
      * to construct a node object with data parameter 
      * @param data 
      */
    public Node(String data) {
        this.data = data;
    }

    /**
     * construct a node object with string and label 
     * @param data data in the node 
     * @param label to label the sequence of traversal 
     */
    public Node(String data, int label) {
        this.data = data;
        this.label = label;
    }
    
     public int getLabel() {
        return label;
    }

 /**
  * to construct a Node  3 parameters
  * @param data name of the animal or the question to be asked 
  * @param left  left sub tree
  * @param right  reference of the right sub tree
  */
    public Node(String data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**
     * method to tell if a node is a leaf node 
     * @return true if it is a leaf node
     */
    public boolean isLeaf() {

        return left == null && right == null;
    }

    /**
     * to construct the question to ask to the player
     *
     * @return the question string
     */
    public String getQuestion() {
        if (isLeaf()) {
            return String.format("Is your anmimal a(n) %s ?", data);
        } else {
            return null;
        }

    }
    public int getLable(){
        return  label;
    }
    
    public String getData(){
        return data;
    }
    public Node getLeft(){
        return left;
    }
    public Node getRight(){
        return right;
    }
    
    /**
     * 
     * @param d the data in the node  (root node)
     * @param leftAnimal  left child node   
     * @param rightAnimal right child node
     */

    public void extend(String d, String leftAnimal, String rightAnimal) {
        data = d;
        this.left = new Node(leftAnimal);
        this.right = new Node(rightAnimal);

    }
    
    
}
