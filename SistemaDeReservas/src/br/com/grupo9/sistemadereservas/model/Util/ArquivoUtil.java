package br.com.grupo9.sistemadereservas.model.Util;

import java.io.File;
import java.io.FileOutputStream;

public class ArquivoUtil {
	
	public static boolean salvar(String arquivoBase64, String nomeDoArquivo, String path){
		try {
			String base64ArquivoSemPropert = arquivoBase64.split(",")[1];
			byte[] arquivoBytes = org.jboss.resteasy.util.Base64.decode(base64ArquivoSemPropert);
			File arquivo = new File(path + nomeDoArquivo);
			FileOutputStream salvar = new FileOutputStream(arquivo);
			salvar.write(arquivoBytes);
			salvar.flush();
			salvar.close();
			System.out.println("Arquivo salvado: " + nomeDoArquivo);
			System.out.println("Local: " + path);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean excluir(String nomeDoArquivo, String path){
		File arquivo = new File(path + nomeDoArquivo);
		if (arquivo.exists()) {
			arquivo.delete();
			System.out.println("Excluindo arquivo: "+nomeDoArquivo);
			return true;
		}else{
			return false;
		}
	}
	
	public static File abrir(String nomeDoArquivo, String path){
		File arquivo = new File(path + nomeDoArquivo);
		if (arquivo.exists()) {
			return arquivo;
		}else{
			return null;
		}
	}
}
