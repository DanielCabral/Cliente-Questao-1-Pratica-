package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Arquivo {
	
public static String lerArquivo(String caminho) {
	FileInputStream arquivo;
	String linha;
	String textoCompleto = "";
	try {
		arquivo = new FileInputStream(caminho);
		InputStreamReader input = new InputStreamReader(arquivo);
		BufferedReader br = new BufferedReader(input);
		do {
			linha = br.readLine();
			if (linha != null) {
				textoCompleto = textoCompleto + " " + linha;
			}
		} while (linha != null);
		// System.out.println(textoCompleto);
		br.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return textoCompleto;
}
public static void gravarArquivo(String caminho,String jsArray) {
	FileOutputStream arquivoEscrever;
	try {
		arquivoEscrever = new FileOutputStream(caminho, false);
		PrintWriter pr = new PrintWriter(arquivoEscrever);
		pr.print(jsArray);
		System.out.println("Arquivo criado");
		pr.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static void gravarArquivoTexto(String caminho,String texto) {
	FileOutputStream arquivoEscrever;
	try {
		arquivoEscrever = new FileOutputStream(caminho);
		PrintWriter pr = new PrintWriter(arquivoEscrever);
		pr.print(texto);
		System.out.println("Arquivo criado");
		pr.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
