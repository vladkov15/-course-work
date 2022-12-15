package com.example.client;

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

public class ProductTableView {
    ObservableList<Product> products = FXCollections.observableArrayList();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button Refresh;

    @FXML
    private Button Add;

    @FXML
    private TextField amount;

    @FXML
    private TextField decription;

    @FXML
    private Button Delete;

    @FXML
    private Button Producer;


    @FXML
    private TableColumn<Product, String> Defect;
    @FXML
    private TableColumn<Product, String> Description;

    @FXML
    private TableColumn<Product, String> Name;

    @FXML
    private TableColumn<Product, String> Amount;

    @FXML
    private TableColumn<Product, String> FirstDate;

    @FXML
    private TableColumn<Product, String> SecondDate;

    @FXML
    private TextField firstDate;

    @FXML
    private TextField name;

    @FXML
    private Button Search;

    @FXML
    private TextField secondDate;


    @FXML
    private TableView<Product> Table = new TableView<Product>(products);

    @FXML
    private Button Update;

    @FXML
    private TextField defect;

    @FXML
    void btnAddClick(ActionEvent event) throws Exception {
        ProductPost product = new ProductPost(name.getText(),Integer.valueOf(amount.getText()),
                firstDate.getText(),secondDate.getText(),decription.getText(), defect.getText());
        SendPost(product);
    }

    @FXML
    void btnClickDelete(ActionEvent event) throws Exception {
       int id = Table.getSelectionModel().getSelectedItem().id;
       SendDelete(id);
    }

    @FXML
    void btnSearchClick(ActionEvent event) {

    }

    @FXML
    void ClickOnProducersBtn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("producer-table-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnUpdateClick(ActionEvent event) throws Exception {
        int id = Table.getSelectionModel().getSelectedItem().id;
        ProductPost product = new ProductPost(name.getText(),Integer.valueOf(amount.getText()),
                firstDate.getText(),secondDate.getText(),decription.getText(), defect.getText());
        SendPut(product, id);
    }



    @FXML
    void btnRefreshClick(ActionEvent event) throws Exception {
        SendGet();
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        FirstDate.setCellValueFactory(new PropertyValueFactory<>("date_of_create"));
        SecondDate.setCellValueFactory(new PropertyValueFactory<>("expiration_date"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Defect.setCellValueFactory(new PropertyValueFactory<>("defect"));
        Table.setItems(products);

    }

    public void SendDelete(int id) throws Exception{
        URL url = new URL("http://localhost:3307/products/"+Integer.toString(id));
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

    public void SendPost(ProductPost product) throws Exception {
        URL url = new URL("http://localhost:3307/products");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(new Gson().toJson(product).getBytes());
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

    public void SendGet() throws Exception{
        URL url = new URL("http://localhost:3307/products");
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

        Product[] myResponse = gson.fromJson(String.valueOf(response), Product[].class);
        products.addAll(myResponse);
        }

    public void SendPut(ProductPost product, int id) throws Exception {
        URL url = new URL("http://localhost:3307/products/"+id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(new Gson().toJson(product).getBytes());
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
