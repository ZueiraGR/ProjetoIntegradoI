package br.com.grupo9.sistemadereservas.model.BO;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.grupo9.sistemadereservas.model.DAO.PromocaoDAO;
import br.com.grupo9.sistemadereservas.model.PO.PromocaoPO;
import br.com.grupo9.sistemadereservas.model.Util.ArquivoUtil;

public class PromocaoBO {
	
	private String PATH_IMAGENS;
	private PromocaoDAO promocaoDAO;
	private PromocaoPO promocaoPO;
	private List<String> mensagensDeErro;
	private String nomeDoArquivo;
	
	private final int APENAS_PROMOCOES_ATIVAS = 0;
	private final int TODAS_AS_PROMOCOES = 1;
	
	public PromocaoBO(){
		PATH_IMAGENS = getApplicationPath();
		if(PATH_IMAGENS.contains("\\")){
			// SERVIDOR WINDOWS
			PATH_IMAGENS = PATH_IMAGENS.replace("WEB-INF\\classes\\br\\com\\grupo9\\sistemadereservas\\model\\BO", "")+"img\\";
		}else{
			// SERVIDOR BASEADO EM UNIX(LINUX,MAC,etc.)
			PATH_IMAGENS = PATH_IMAGENS.replace("WEB-INF/classes/br/com/grupo9/sistemadereservas/model/BO", "")+"img/";
		}
	}
	
	public boolean cadastar(){
		if(isDadosValidosParaCadastro()){
			gerarNomeDoArquivo();
			if(salvarImagem()){
				getPromocaoPO().setImagem(getNomeDoArquivo());
				return getPromocaoDAO().cadastrar(getPromocaoPO());
			}else{
				return false;
			}			
		}else{
			return false;
		}
	}
	public PromocaoPO capturar(){
		return getPromocaoDAO().capturarPorId(getPromocaoPO());
	}
	
	public List<PromocaoPO> listarTodas(Integer pagina, Integer qtdRegistros){
		pagina = pagina*qtdRegistros-qtdRegistros;
		return getPromocaoDAO().listar(pagina,qtdRegistros,getFiltro(TODAS_AS_PROMOCOES));
	}
	public List<PromocaoPO> listarSomentAtivas(){
		return getPromocaoDAO().listar(getFiltro(APENAS_PROMOCOES_ATIVAS));
	}
	
	public boolean atualizar(){
		boolean retorno = false;
		PromocaoPO promocao;
		promocao = getPromocaoDAO().capturarPorId(getPromocaoPO());
		if(promocao != null){
			if(getPromocaoPO().getImagem() != null && !getPromocaoPO().getImagem().isEmpty()){
				setNomeDoArquivo(promocao.getImagem());
				excluirImagem();
				gerarNomeDoArquivo();
				salvarImagem();
				getPromocaoPO().setImagem(getNomeDoArquivo());
				retorno =  getPromocaoDAO().atualizar(getPromocaoPO());
			}else{
				getPromocaoPO().setImagem(promocao.getImagem());
				retorno =  getPromocaoDAO().atualizar(getPromocaoPO());
			}
		}
		return retorno;
	}
	
	public boolean excluir(){
		PromocaoPO promocao = getPromocaoDAO().capturarPorId(getPromocaoPO());
		setNomeDoArquivo(promocao.getImagem());
		promocao.setFim(Calendar.getInstance());
		if(excluirImagem()){
			return getPromocaoDAO().atualizar(promocao);
		}else{
			return false;
		}		
	}
	
	public boolean isDadosValidosParaCadastro(){
		boolean retorno = true ;
		
		if(getPromocaoPO().getTitulo() == null || getPromocaoPO().getTitulo() == "" || getPromocaoPO().getTitulo().length() < 5){
			retorno = false;
			getMensagensDeErro().add("O campo título é obrigatório e deve conter no mínimo 5 caracteres.");
		}
		if(getPromocaoPO().getDescricao() == null || getPromocaoPO().getDescricao() == "" || getPromocaoPO().getDescricao().length() < 10){
			retorno = false;
		}
		if(getPromocaoPO().getInformacao() == null || getPromocaoPO().getInformacao() == "" || getPromocaoPO().getInformacao().length() < 25){
			retorno = false;
		}
		if(getPromocaoPO().getImagem() == null || getPromocaoPO().getImagem() == "" || getPromocaoPO().getImagem().length() < 30){
			retorno = false;
		}
		if(getPromocaoPO().getInicio() == null || getPromocaoPO().getInicio().getTimeInMillis() <= 0){
			retorno = false;
		}
		if(getPromocaoPO().getFim() == null || getPromocaoPO().getFim().getTimeInMillis() <= 0){
			retorno = false;
		}
		
		return retorno;
	}
	
	private boolean salvarImagem() {
		return ArquivoUtil.salvar(getPromocaoPO().getImagem(), getNomeDoArquivo(), PATH_IMAGENS);
	}
	
	private boolean excluirImagem() {
		return ArquivoUtil.excluir(getNomeDoArquivo(), PATH_IMAGENS);
	}
	
	private void gerarNomeDoArquivo(){
		Calendar ex = Calendar.getInstance();
		this.nomeDoArquivo= "promocao" + ex.getTimeInMillis() + ".jpg";
	}
	
	private String getFiltro(int codigo){
		if(codigo == APENAS_PROMOCOES_ATIVAS){
			return "";
		}else if(codigo == TODAS_AS_PROMOCOES){
			return "";
		}else{
			return "";
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
	
	public PromocaoPO getPromocaoPO() {
		return promocaoPO;
	}
	public void setPromocaoPO(PromocaoPO promocaoPO) {
		this.promocaoPO = promocaoPO;
	}
	
	private String getNomeDoArquivo() {
		return nomeDoArquivo;
	}

	private void setNomeDoArquivo(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}
	
	private PromocaoDAO getPromocaoDAO() {
		if(this.promocaoDAO == null){
			this.promocaoDAO = new PromocaoDAO();
		}
		return promocaoDAO;
	}
	
	public List<String> getMensagensDeErro(){
		if(this.mensagensDeErro == null){
			this.mensagensDeErro = new ArrayList<>();
		}
		return this.mensagensDeErro;
	}
}
