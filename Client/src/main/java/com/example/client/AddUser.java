package com.example.client;

import com.example.client.Models.UserPost;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddUser {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label label;

    @FXML
    private Label login;

    @FXML
    private TextField loginInput;

    @FXML
    private Label password;

    @FXML
    private TextField passwordInput;

    @FXML
    private Button submitButton;

    @FXML
    void clickBtnSubmit(ActionEvent event) throws IOException {
        UserPost user = new UserPost(loginInput.getText(),passwordInput.getText());
        SendPost(user);
        switchToTable(event);
    }

    public void SendPost(UserPost user) throws IOException {
        URL url = new URL("http://localhost:3307/users");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(new Gson().toJson(user).getBytes());
        os.flush();
        os.close();
        InputStream is = con.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            response.append(line);
        }
        rd.close();
        System.out.println(response);
    }

    public void switchToTable(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("product-table-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
