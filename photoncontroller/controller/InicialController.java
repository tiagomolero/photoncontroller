package photoncontroller.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class InicialController implements Initializable{

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	Button btnAdiciona = new Button();
	@FXML
	Button btnListar = new Button();
	@FXML
	Button btnSair = new Button();
	@FXML
	Label lblTitulo = new Label();
	
	@FXML
	public void adicionar(ActionEvent event) throws IOException {
		
		Parent cadastro = FXMLLoader.load(getClass().getResource("/fxml/adiciona.fxml"));
		Scene cadastroCene = new Scene(cadastro);
		Stage cadastroTela = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		cadastroTela.setScene(cadastroCene);
		cadastroTela.show();
	}

	@FXML
	public void listar(ActionEvent event) throws IOException{
	
		Parent cadastro = FXMLLoader.load(getClass().getResource("/fxml/lista.fxml"));
		Scene cadastroCene = new Scene(cadastro);
		Stage cadastroTela = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		cadastroTela.setScene(cadastroCene);
		cadastroTela.show();
	}
	
}
