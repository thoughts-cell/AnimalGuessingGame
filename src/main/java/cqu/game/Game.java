/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.game;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author 12233612
 */
public class Game {

    private DecisionTree tree;
    private final IView view;

    public Game(IView view) {
        this.view = view;
    }

    public String display() {
        return tree.display();
    }

    public void play() {

        // to play the game using the decision tree’s execute() method 
        help();//display the help message 
        boolean again = true;
        AnimalBehaviour ab = new AnimalBehaviour(view);
        tree = new DecisionTree(ab);
        while (again) {
             boolean r = tree.execute();
            if (r == true) {
                again = view.choose("You  won! Play again?");
            } else if (r == false) {
                again = view.choose("I won! play again?");
            }
        }
     }
    
    /**
     * method to load the game from previous saved one 
     * @param fname the name/ path of file of the game to load 
     * @throws Exception any exception to be thrown 
     */
    public  void load(String fname)throws Exception{
        try {
            System.out.println("Entering load method");
            AnimalBehaviour ab = new AnimalBehaviour(view);
        tree = new DecisionTree(ab);
            tree.load(fname);
            boolean again = true;
             while (again) {
             boolean r = tree.execute();
            if (r == true) {
                again = view.choose("You  won! Play again?");
            } else if (r == false) {
                again = view.choose("I won! play again?");

            }
        }
        } catch(FileNotFoundException e){
            view.display("failed to open file");
            System.err.println("Error while opening file :"+e.getMessage());
                   
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void help() {
        view.display("Think of an animal.If my tree is \n"
                + "non-empty.I will ask some yes/no\n"
                + "questions to try to determin what \n"
                + "it is. ");
    }

    
    /**
     * method to save current game in game object
     * @param fname the name of file to be saved 
     * @throws Exception 
     */
    public void save(String fname) throws Exception {
        try {
            tree.save(fname);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
