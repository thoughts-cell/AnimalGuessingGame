
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.game;

/**
 *
 * @author 12233612
 */
public class AnimalBehaviour implements IBehaviour {

    private IView view;

    /**
     * to construct an Animal behavior object
     *
     * @param view the view interface
     */
    public AnimalBehaviour(IView view) {
        this.view = view;
    }

    /**
     * method to add a node to the empty tree by asking user for the animal name
     *
     * @return a new node when there is no node
     */
    @Override
    public Node emptyTree() {
        String ask = view.ask("What is your animal?");
        Node n = new Node(ask);
        return n;

    }

    /**
     * method to process non leaf Node
     *
     * @param n the node passed in
     * @return true if user chose yes
     */
    @Override
    public boolean processNonLeafNode(Node n) {

        return view.choose(n.getData());
    }

    /**
     * to process leaf node (the node contains a question)
     *
     * @param n the node parameter passed in
     * @return  true or false depend on user`s choice
     */
    @Override
    public boolean processLeafNode(Node n) {
        String q = n.getQuestion();
        if (view.choose(q) == true) {
            return false;           //player lost the game -> execute return false
        } else {
            String newAnimal = view.ask("you win! what is your animal");

            String newQuestion = view.ask("Provide a yes/no question that distinguishes between " + newAnimal + "     and    " + n.getData()
                    + ". Yes = " + newAnimal + "; no = " + n.getData());
            n.extend(newQuestion, newAnimal, n.getData());
            return true;
        }

    }

}
