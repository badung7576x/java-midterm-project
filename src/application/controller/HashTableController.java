package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import datastructure.hashtable.HashNode;
import datastructure.hashtable.HashTable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class HashTableController implements Initializable{

	@FXML
	private GridPane gridpane;
	
	@FXML
	private TextField input;
	@FXML
	private TextField value;
	
	
	private HashTable<String, String> hashtable = new HashTable<String, String>();
	
	public void add() throws IOException {
		String keyStr = input.getText();
		String valStr = value.getText();
		if(!keyStr.isEmpty() && !valStr.isEmpty()) {
			hashtable.add(keyStr, valStr);
			System.out.println("The node added!");
		}
		this.clearInput();
		this.generateHashTable();
	}
	
	public void remove() throws IOException {
		String keyStr = input.getText();
		if(!keyStr.isEmpty()) {
			hashtable.remove(keyStr);
			System.out.println("The node remove!");
		}
		this.clearInput();
		this.generateHashTable();
	}
	
	public void clear() throws IOException {
		System.out.println("clear element");
		this.clearInput();
		hashtable.clear();
		this.generateHashTable();
	}
	
	public void find() throws IOException {
		String keyStr = input.getText();
		if(!keyStr.isEmpty() && hashtable.get(keyStr)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thông báo !");
			alert.setHeaderText(null);
			alert.setContentText("The hashtable has node!");
			alert.showAndWait();
			System.out.println("The hashtable has node!");
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thông báo !");
			alert.setHeaderText(null);
			alert.setContentText("The hashtable hasn't node!");
			alert.showAndWait();
			System.out.println("The hashtable hasn't node!");
		}
		this.clearInput();
	}
	
	private void generateHashTable() {
		gridpane.getChildren().clear();
		ArrayList<HashNode<String, String>> buckets = hashtable.getBucket();
		if(hashtable.isEmpty()) return;
		int row = 0;
		int col = 0;
		for(HashNode<String, String> xHashNode : buckets) {
			Label xLabel = new Label(String.valueOf(row));
			gridpane.add(xLabel, 0, row);
			if(xHashNode == null) {
				row += 1;
				continue;
			}
			HashNode<String, String> iNode = xHashNode;
			col = 1;
			while(iNode != null) {
				Button xButton = new Button(iNode.key + " | " + iNode.value);
				xButton.setStyle("-fx-background-color: #abccba;-fx-border-color:gray");
				gridpane.add(xButton, col, row);
				iNode = iNode.next;
				col += 1;
			}
			row += 1;
			
		}
		System.out.println("Generate hashtable!");
	}
	
	private void clearInput() {
		input.clear();
		value.clear();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
