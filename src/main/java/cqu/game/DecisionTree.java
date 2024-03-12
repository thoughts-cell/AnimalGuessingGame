/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.game;

import java.io.BufferedReader;
import java.util.Formatter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author 12233612
 */
public class DecisionTree {

    private Node root;
    private IBehaviour behaviour;

    public DecisionTree() {
    }

    /**
     * to construct a Decision tree with parameter
     *
     * @param behaviour passed in
     */
    public DecisionTree(IBehaviour behaviour) {

        this.behaviour = behaviour;
    }

    /**
     * first execute method to identify the animal
     *
     * @return true if the second execute method return true
     */
    public boolean execute() {

        if (root == null) {
            root = behaviour.emptyTree();
        }
        return execute(root);

    }

    /**
     * method to identify the animal according to the node passed in
     *
     * @param n the node passed in
     * @return boolean value to indicate the player lose or win
     */
    private boolean execute(Node n) {
        // System.out.println("in the second execute ,root has a  " + n.getData());
        if (n.isLeaf()) {
            return (behaviour.processLeafNode(n));
        } else {
            if (behaviour.processNonLeafNode(n)) {
                return execute(n.getLeft());
            } else {
                return execute(n.getRight());
            }
        }

    }

    /**
     * to Generate indented pre-order traversal of tree structure
     *
     * @return string of the node in the tree
     */
    public String display() {
        StringBuilder sb = new StringBuilder();
        display(sb, 0, "root:", root);
        return sb.toString();
    }

    /**
     * to display the questions and animals in the GUI
     *
     * @param sb string builder object passed in
     * @param level for indentation
     * @param n string passed in
     * @param node node
     */
    private void display(StringBuilder sb, int level, String n, Node node) {
        if (node == null) {
            return;
        }
        level = level + 2;
        for (int i = 0; i < level; i++) {
            sb.append("      ");

        }
        sb.append(String.format("%s  %s   %d\n", n, node.getData(), node.getLabel()));
        display(sb, level, "left: ", node.getLeft());
        display(sb, level, "right: ", node.getRight());

    }

    /**
     * method to label the node using in order traversal
     *
     * @param n reference of current node
     * @param count index
     * @return value of next
     */
    private int label(Node n, int count) {
        if (n != null) {

            count = label(n.getLeft(), count);
            n.label = count++;
            count = label(n.getRight(), count);
            return count;

        }
        return count;
    }

    /**
     * method to save the current game
     *
     * @param name the name of file to be saved
     * @throws Exception exception to be thrown
     */
    public void save(String name) throws Exception {

        try ( Formatter f = new Formatter(name)) {
            label(root, 0);
            save(root, f);

            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method to recursively save the data of each node in pre-order
     *
     * @param node the node passed in
     * @param formatter formatter object to write to
     */
    private void save(Node node, Formatter formatter) {

        if (node != null) {

            formatter.format("%d  %s %n", node.label, node.data);
            save(node.getLeft(), formatter);
            save(node.getRight(), formatter);
        }
    }

    /**
     * to reload or rebuild the game from saved file
     *
     * @param fname the name of saved file
     * @throws IOException to be thrown
     */
    public void load(String fname) throws Exception {

        try ( BufferedReader br = Files.newBufferedReader(Path.of(fname))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] subString = line.split(" ", 2);//dived each line into 2 substrings
                if (subString.length == 2) {
                    int label = Integer.parseInt(subString[0]);
                    String data = subString[1];

                    Node t = new Node(data, label);
                    insert(root, t);

                }

            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method to insert node in the tree
     *
     * @param n the current node
     * @param t the node to be inserted
     */
    private void insert(Node n, Node t) {
        if (n == null) {
            root = t;
        } else if (t.label < n.label) {
            if (n.left == null) {
                n.left = t;
            } else 
                insert(n.left, t);      
        } else if (t.label > n.label) {
            if (n.right == null) {
                n.right = t;
            } else 
                insert(n.right, t);
            }
    }
}
