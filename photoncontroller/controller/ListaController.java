package photoncontroller.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import photoncontroller.dao.PessoaDao;
import photoncontroller.entity.Pessoa;
import photoncontroller.tabela.pessoaTabela;

public class ListaController implements Initializable {

	@FXML
	private Label lblTitulo = new Label();
	@FXML
	private Button btnSalvar = new Button();
	@FXML
	private Button btnEditar = new Button();
	@FXML
	private Button btnSair = new Button();
	@FXML
	private Button btnCancelar = new Button();
	@FXML
	private Button btnExcluir = new Button();
	@FXML
	private TextField tfNome = new TextField();
	@FXML
	private TextField tfIdade = new TextField();
	
	@FXML
	private TableView<pessoaTabela> tvTabela;
	@FXML
	private TableColumn<pessoaTabela, String> tcNome;
	@FXML
	private TableColumn<pessoaTabela, Integer> tcIdade;
	
	
	private PessoaDao pessoaDao = new PessoaDao();
	
	private List<Pessoa> pessoaList = pessoaDao.listPessoa();
	
	
	private ObservableList<pessoaTabela> listPessoaTabela = FXCollections.observableArrayList();
	
	
	
	public void initialize(URL location, ResourceBundle resources) {
		listarPessoas();
		
	}
	
	@FXML
	public void excluir() {
		
		pessoaTabela pessoa = tvTabela.getSelectionModel().getSelectedItem();
		int id = pessoa.getId();
		
		listPessoaTabela.remove(pessoa);
		
		pessoaDao.removePessoa(id);
		
		pessoaList = pessoaDao.listPessoa();
		
		listarPessoas();
	}
	
	@FXML
	public void editar() {
		
		pessoaTabela pessoaTabela = tvTabela.getSelectionModel().getSelectedItem();
		
		Pessoa pessoaUpdate = new Pessoa(pessoaTabela);
		
		pessoaUpdate.setNome(tfNome.getText());
		pessoaUpdate.setIdade(Integer.parseInt(tfIdade.getText()));
		
		pessoaDao.updatePessoa(pessoaUpdate);
		
		pessoaList = pessoaDao.listPessoa();
		
		listarPessoas();
		
	}
	
	
	
	public void listarPessoas() {
		
		if (!listPessoaTabela.isEmpty()) {
			listPessoaTabela.clear();
			System.out.println("Limpou Tabela");
		}
		
		for(Pessoa pessoa : pessoaList) {
			
			pessoaTabela p = new pessoaTabela(pessoa.getId(), pessoa.getNome(), pessoa.getIdade());
			listPessoaTabela.add(p);
		}
		
		tcNome.setCellValueFactory(new PropertyValueFactory<pessoaTabela, String>("Nome"));
		tcIdade.setCellValueFactory(new PropertyValueFactory<pessoaTabela, Integer>("Idade"));
		
		tvTabela.setItems(listPessoaTabela);
		
	}
	
	
	public void sair(ActionEvent event) throws IOException  {
	
		Parent cadastro = FXMLLoader.load(getClass().getResource("/fxml/inicial.fxml"));
		Scene cadastroCena = new Scene(cadastro);
		Stage cadastroTela = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		cadastroTela.setScene(cadastroCena);
		cadastroTela.show();
		
	}
	
	
	

}
