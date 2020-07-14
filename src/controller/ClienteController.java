package controller;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import server.StubCliente;

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
	models.Arquivo arquivo = null;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cliente = new StubCliente();
		System.out.println(cliente.stub);
		try {
			arquivosDoServidor = cliente.receberLista();
			listaDeArquivos.getItems().addAll(arquivosDoServidor);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 listaDeArquivos.getSelectionModel().selectedItemProperty().addListener(
		                (observable, oldValue, newValue) -> {
							try {
								selecionarEReceberArquivo(newValue);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						});
	}	
	
	public String selecionarEReceberArquivo(Object  newValue) throws IOException{
		itemSelecionadoNaLista= (String) newValue;
		arquivo = cliente.enviarArquivo(itemSelecionadoNaLista);
		controller.Arquivo.gravarArquivoTexto("src/dados/"+arquivo.getNome(), arquivo.getConteudo());
		
		conteudoArquivo.setText(arquivo.getConteudo());
        return itemSelecionadoNaLista;
     }
	
	@FXML
	public void salvar() throws IOException {
		controller.Arquivo.gravarArquivoTexto("src/dados/"+arquivo.getNome(), conteudoArquivo.getText());
	}
	
	@FXML
	public void enviar() throws IOException, ParseException {
		arquivo.setConteudo(conteudoArquivo.getText());
		
		File file =new File("src/dados/"+arquivo.getNome());
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"); 
		
		String dataDeModificacao = ""+formatter.format(file.lastModified());
		arquivo.setDataDeModificacao(dataDeModificacao);
		cliente.receberArquivo(arquivo);
	}
	
	@FXML
	public void encerrarConexao() throws IOException {
		System.exit(0);
	}
	
}
