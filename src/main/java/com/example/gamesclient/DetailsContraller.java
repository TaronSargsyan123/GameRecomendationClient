package com.example.gamesclient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DetailsContraller {
    @FXML
    private Button backButton;

    @FXML
    private ListView<String> infoList;

    @FXML
    private Text nameText;

    @FXML
    private Text summaryText;

    @FXML
    void initialize() throws IOException {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'details-view.fxml'.";
        backButton.setOnAction(actionEvent -> {
            try {
                backToMainScene(backButton);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        getInfo(readName());



    }

    private void backToMainScene(Button button) throws Exception{
        Stage stage;
        Parent root;

        stage = (Stage) button.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void setName(String temp){
        nameText.setText(temp);
    }


    private void setInfo(String userScore, String metaScore, String platform, String date){
        infoList.getItems().add("Platform - " + platform);
        infoList.getItems().add("User score - " + userScore);
        infoList.getItems().add("Meta score - " + metaScore);
        infoList.getItems().add("Release date - " + date);

    }

    private String readName() throws IOException {
        BufferedReader reader = null;
        String line = "none";
        try {
            reader = new BufferedReader(new FileReader("config.txt"));
            line = reader.readLine();
            System.out.println(line);
        }catch (IOException e){
            System.out.println("Error: ");
        }finally {
            reader.close();
        }
        return line;
    }

    public void getInfo(String game) throws IOException {

        ClientLogic clientLogic = new ClientLogic();
        String[] temp;
        String data = clientLogic.predict("$" + game);
        temp = data.split("\\$");

        String platform = temp[0];
        String date = temp[1];
        String userScore = temp[2];
        String metaScore = temp[3];

        setName(readName());

        setInfo(userScore, metaScore, platform, date);

    }

}
