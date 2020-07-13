package server;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import models.Arquivo;

//Criando a interface (Skeleton) do servidor
public interface InterfaceDoServidor extends Remote{

	public ArrayList<String> receberLista() throws RemoteException;
	public Arquivo enviarArquivo(String nome) throws RemoteException;
}
