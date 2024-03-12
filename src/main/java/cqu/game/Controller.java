/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqu.game;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.event.ActionEvent;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author 12233612
 */
public class Controller implements Initializable, IView {

    @FXML
    private Button loadBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button play;
    @FXML
    private TextArea txt;

    @FXML
    private Button btnExit;
    @FXML
    private Button displayBtn;
    private Game game;

    /**
     *
     * @param s string needed for null validation
     * @return the result of validation for null
     */
    private boolean validate(String s) {
        // check non empty string or just whitespace 
        // (should never be null but check anyway)
        if ((s == null) || (s == "") ||(s.matches("\\d+"))|| (s.matches("\\s*"))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param q question asked
     * @return
     */
    @Override
    public String ask(String q) {
        String r = q + "\nText is required.";
        String s = "";
        boolean valid = false;
        while (!valid) {
            TextInputDialog tid = new TextInputDialog("");
            tid.setHeaderText(q);
            // Disable the cancel button
            Button cancel = (Button) tid.getDialogPane().lookupButton(ButtonType.CANCEL);
            cancel.setDisable(true);
            // Block execution until the user responds
            tid.showAndWait();
            s = tid.getEditor().getText();
            valid = validate(s);
            if (!valid) {
                q = r;
            }
        }
        // remove leading and/or trailing whitespace
        return s.trim();
    }

    /**
     * to display the message
     *
     * @param s MESSAGE to display
     */
    @Override
    public void display(String s) {

        txt.setText(s);

    }

    public void bind(Game g) {
        game = g;

    }

    /**
     * method to append text
     *
     * @param s string to append
     */

    @Override
    public void append(String s) {
        txt.appendText(s);
    }

    @Override
    public boolean choose(String q) {
        String r = choose(q, "Yes", "No");
        return r.equals("Yes");
    }

    @Override
    public String choose(String q, String a1, String a2) {
        ButtonType b1 = new ButtonType( a1 );
        ButtonType b2 = new ButtonType( a2 );
        Alert alert = new Alert(Alert.AlertType.NONE, q, b1, b2 );
        alert.setTitle( "Choose" );
        // Block execution until the user responds
        java.util.Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == b1 )
            return a1;
        return a2;
    } 


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleLoadButtonAction(ActionEvent event) {
        
        try {
                 game.load("animal.txt");
                
            txt.setText("loading......"+
                      " loaded   successfully");
        } catch (Exception e) {
            e.printStackTrace();
            //append("Error occurred ,please try later");
            txt.setText("Error occured ,try later");
        }
    }

    @FXML
    private void handleSaveButtonAction  (ActionEvent event)  {
         try {
            game.save("animal.txt");
            txt.setText("     "
                    + " your game has been saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            //append("Error occurred ,please try later");
             txt.setText("Error occured ,try later");
        }
       
    }

    @FXML
    private void handleDisplayButtonAction(ActionEvent event) {
        try{
            display(game.display());
            
        }catch(Exception e){
            txt.setText("No things to display ,please only click display after playing or loading");
        }
    }

    @FXML
    private void handlePlayButtonAction(ActionEvent event) {
        game.play();
    }
}
