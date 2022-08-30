package com.example.gamesclient;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class HelloController {
    ClientLogic clientLogic = new ClientLogic();


    @FXML
    private VBox contentBox;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchEntry;


    @FXML
    void initialize() {

        searchButton.setOnAction(actionEvent -> {

            //String temp = clientLogic.predict(searchEntry.getText());
            //System.out.println(temp);
            contentBox.getChildren().removeAll(contentBox.getChildren());

            contentBox.setPadding(new Insets(15, 0, 0, 15));
            contentBox.setSpacing(10);

            try {
                getGamesList(searchEntry.getText(), 5);
            }catch (Exception e){
                System.out.println("Exception");
            }


        });
    }

    HBox drawTitleWidget(String temp){
        Button button = new Button("Read more");
        button.setStyle("-fx-background-color:   #f39c63; -fx-text-fill:  white;");



        Text text = new Text(temp);

        HBox hBox = new HBox(text, button);
        hBox.setStyle("-fx-background-color:    #F2F2F2;");
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setSpacing(25);

        button.setOnAction(actionEvent -> {
            try {
                writeName(temp);
                changeScene(button);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return hBox;
    }

    private void changeScene(Button button) throws Exception{
        Stage stage;
        Parent root;

        stage = (Stage) button.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("details-view.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void getGamesList(String game, int count){
        String gamesList[] = new String[5];
        ClientLogic clientLogic = new ClientLogic();

        for (int i = 0; i < count; i++) {
            String temp = clientLogic.predict(game + "$" + Integer.toString(i));
            //System.out.println(temp);
            //System.out.println("");
            contentBox.getChildren().add(drawTitleWidget(temp));
            gamesList[i] = temp;
        }
    }

    private void writeName(String line){
        try {
            File conf = new File("config.txt");

            if(conf.exists() == false) {
                conf.createNewFile();
            }

            PrintWriter printWriter = new PrintWriter(conf);
            printWriter.write("");
            printWriter.println(line);
            printWriter.close();

        }catch (IOException e){
            System.out.println("Error: " + e);
        }
    }





}