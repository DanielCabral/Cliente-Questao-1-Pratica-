package controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class ClienteController implements Initializable {
	@FXML
	ImageView imagem;
	
	@FXML
	javafx.scene.control.ListView<String> listaDeArquivos;

	@FXML
	TextArea conteudoArquivo;
	
	public ObservableList<String> obList;
	
	String itemSelecionadoNaLista;
	Socket socket=null;
	ArrayList<String> arquivosDoServidor =new ArrayList<String>();
	
	StubCliente cliente;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cliente = new StubCliente();
		try {
			arquivosDoServidor = cliente.receberLista();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 listaDeArquivos.getSelectionModel().selectedItemProperty().addListener(
		                (observable, oldValue, newValue) -> {
							try {
								selecionarItemTableViewItemDeOrcamento(newValue);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						});
	}	
	
	public String selecionarItemTableViewItemDeOrcamento(Object  newValue) throws IOException{
		itemSelecionadoNaLista= (String) newValue;
		PrintStream saida = new PrintStream(this.socket.getOutputStream());	
		saida.println(itemSelecionadoNaLista);
        return itemSelecionadoNaLista;
     }
	
	@FXML
	public void encerrarConexao() throws IOException {
		PrintStream saida = new PrintStream(this.socket.getOutputStream());	
		saida.println("fim");
		escuta.stop();
		System.exit(0);
	}
	
}
