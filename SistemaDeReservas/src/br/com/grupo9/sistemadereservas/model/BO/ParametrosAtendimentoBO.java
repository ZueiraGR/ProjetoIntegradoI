package br.com.grupo9.sistemadereservas.model.BO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.grupo9.sistemadereservas.model.Util.ArquivoUtil;

public class ParametrosAtendimentoBO {
	private static Configuracoes CONFIG;
	private static String stringXML;
	private static String PATH_ARQUIVO;
	private static final String nomeArquivo = "config-sys.xml";
	
	static{
		PATH_ARQUIVO = new ParametrosAtendimentoBO().getApplicationPath();
		if(PATH_ARQUIVO.contains("\\")){
			// SERVIDOR WINDOWS
			PATH_ARQUIVO = PATH_ARQUIVO.replace("br\\com\\grupo9\\sistemadereservas\\model\\BO", "")+"META-INF\\";
		}else{
			// SERVIDOR BASEADO EM UNIX(LINUX,MAC,etc.)
			PATH_ARQUIVO = PATH_ARQUIVO.replace("br/com/grupo9/sistemadereservas/model/BO", "")+"META-INF/";
		}
		System.out.println(PATH_ARQUIVO);
		if(CONFIG == null){
			abrirConfiguracoes();
		}
	}
	
	private static void abrirConfiguracoes(){
		try {
			FileReader arquivo = new FileReader(ArquivoUtil.abrir(nomeArquivo, PATH_ARQUIVO));
			XStream xStream = new XStream( new DomDriver());
			xStream.alias("Configuracao", Configuracoes.class);
			xStream.alias("ValorDeCobranca", ValorDeCobranca.class);
			xStream.alias("Dia", Dia.class);
			setConfig((Configuracoes) xStream.fromXML(arquivo));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void gerarStringXML(){
		XStream xStream = new XStream( new DomDriver());
		xStream.alias("Configuracao", Configuracoes.class);
		xStream.alias("ValorDeCobranca", ValorDeCobranca.class);
		xStream.alias("Dia", Dia.class);
		setStringXML(xStream.toXML(getConfig()));
	}
	
	public static boolean salvarConfiguracoes(){
		gerarStringXML();
		try {
			PrintWriter escreve = new PrintWriter(ArquivoUtil.abrir(nomeArquivo, PATH_ARQUIVO));
			escreve.print(getStringXML());
			escreve.flush();
			escreve.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private String getApplicationPath() {
		String url = getClass().getResource(getClass().getSimpleName() + ".class").getPath();
		File dir = new File(url).getParentFile();
		String path = null;
		path = dir.getPath();
		try {
			return URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return path.replace("%20", " ");
		}
	}
	
	private static String getStringXML(){
		return stringXML;
	}
	
	private static void setStringXML(String string){
		stringXML = string; 
	}
	
	public static void setConfig(Configuracoes configuracoes){
		CONFIG = configuracoes;
	}
	
	public static Configuracoes getConfig(){
		if(CONFIG == null){
			CONFIG = new Configuracoes();
		}
		return CONFIG;
	}
}
