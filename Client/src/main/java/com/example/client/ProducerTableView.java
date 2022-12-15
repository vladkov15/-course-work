package com.example.client;

import com.example.client.Models.Producer;
import com.example.client.Models.ProducerPost;
import com.example.client.Models.Product;
import com.example.client.Models.ProductPost;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProducerTableView {
    private Stage stage;
    private Scene scene;
    private Parent root;
    ObservableList<Producer> producers = FXCollections.observableArrayList();

    @FXML
    private Button Add;

    @FXML
    private Button Delete;

    @FXML
    private TableColumn<Producer, String> Name;

    @FXML
    private TableColumn<Producer, String> Place;

    @FXML
    private Button ProductButton;

    @FXML
    private Button Refresh;

    @FXML
    private Button Search;

    @FXML
    private TableView<Producer> Table = new TableView<>(producers);

    @FXML
    private Button Update;

    @FXML
    private TextField name;

    @FXML
    private TextField place;

    @FXML
    void ProductBtnClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("product-table-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnAddClick(ActionEvent event) throws Exception {
        ProducerPost producer = new ProducerPost(name.getText(),place.getText());
        SendPost(producer);
    }

    @FXML
    void btnClickDelete(ActionEvent event) throws Exception {
        int id = Table.getSelectionModel().getSelectedItem().id;
        SendDelete(id);
    }

    @FXML
    void btnRefreshClick(ActionEvent event) throws Exception {
        SendGet();
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Place.setCellValueFactory(new PropertyValueFactory<>("place"));
        Table.setItems(producers);
    }

    @FXML
    void btnSearchClick(ActionEvent event) {

    }

    @FXML
    void btnUpdateClick(ActionEvent event) throws Exception {
        int id = Table.getSelectionModel().getSelectedItem().id;
        ProducerPost producer = new ProducerPost(name.getText(), place.getText());
        SendPut(producer, id);
    }

    public void SendPost(ProducerPost producer) throws Exception {
        URL url = new URL("http://localhost:3307/producers");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(new Gson().toJson(producer).getBytes());
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

    public void SendDelete(int id) throws Exception{
        URL url = new URL("http://localhost:3307/producers/"+id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Type", "application/json");
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
    public void SendGet() throws Exception{
        URL url = new URL("http://localhost:3307/producers");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        InputStream is = con.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            response.append(line);
        }
        rd.close();
        System.out.println(response);

        final GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();

        Producer[] myResponse = gson.fromJson(String.valueOf(response), Producer[].class);
        producers.addAll(myResponse);
    }

    public void SendPut(ProducerPost producer, int id) throws Exception {
        URL url = new URL("http://localhost:3307/producers/"+id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(new Gson().toJson(producer).getBytes());
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

}
