package models;

import java.io.Serializable;

public class Arquivo implements Serializable {
	private static final long serialVersionUID = 1L;
    
	   private String nome;
	   private String conteudo;
	   private String dataDeModificacao;
	   public String getNome() {
	             return nome;
	   }
	   public void setNome(String nome) {
	             this.nome = nome;
	   }
	   public String  getConteudo() {
	             return conteudo;
	   }
	   public void setConteudo(String conteudo) {
	             this.conteudo = conteudo;
	   }
	public String getDataDeModificacao() {
		return dataDeModificacao;
	}
	public void setDataDeModificacao(String dataDeModificacao) {
		this.dataDeModificacao = dataDeModificacao;
	}  
	   
}
