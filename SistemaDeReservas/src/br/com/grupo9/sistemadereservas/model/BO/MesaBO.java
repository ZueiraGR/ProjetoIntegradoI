package br.com.grupo9.sistemadereservas.model.BO;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.grupo9.sistemadereservas.model.DAO.MesaDAO;
import br.com.grupo9.sistemadereservas.model.PO.MesaPO;
import br.com.grupo9.sistemadereservas.model.Util.ArquivoUtil;

public class MesaBO {

	private String PATH_IMAGENS;
	private MesaDAO mesaDAO;
	private MesaPO mesaPO;
	private List<String> mensagemErro;
	private String nomeDoArquivo;
	
	public MesaBO(){
		PATH_IMAGENS = getApplicationPath();
		if(PATH_IMAGENS.contains("\\")){
			// SERVIDOR WINDOWS
			PATH_IMAGENS = PATH_IMAGENS.replace("WEB-INF\\classes\\br\\com\\grupo9\\sistemadereservas\\model\\BO", "")+"img\\";
		}else{
			// SERVIDOR BASEADO EM UNIX(LINUX,MAC,etc.)
			PATH_IMAGENS = PATH_IMAGENS.replace("WEB-INF/classes/br/com/grupo9/sistemadereservas/model/BO", "")+"img/";
		}
	}

	public boolean cadastrar() {
		gerarNomeDoArquivo();
		if(salvarImagem()){
			getMesaPO().setImagem(getNomeGeradoDoArquivo());
			if (getMesaDAO().cadastrar(getMesaPO())) {
				return true;
			} else {
				excluirImagem();
				return false;
			}
		}else{
			return false;
		}
	}
	
	public MesaPO capturar(){
		return getMesaDAO().capturarPorId(getMesaPO());
	}
	
	public List<MesaPO> listar(Integer pagina, Integer qtdRegistros){
		pagina = pagina*qtdRegistros-qtdRegistros;
		return getMesaDAO().listar(pagina,qtdRegistros);
	}
	
	public boolean atualizar(){
		boolean retorno = false;
		MesaPO mesa;
		mesa = getMesaDAO().capturarPorId(getMesaPO());
		if(mesa != null){
			if(getMesaPO().getImagem() == null && !getMesaPO().getImagem().isEmpty()){
				setNomeDoArquivo(mesa.getImagem());
				excluirImagem();
				gerarNomeDoArquivo();
				salvarImagem();
				getMesaPO().setImagem(getNomeGeradoDoArquivo());
				retorno =  getMesaDAO().atualizar(getMesaPO());
			}else{
				getMesaPO().setImagem(mesa.getImagem());
				retorno =  getMesaDAO().atualizar(getMesaPO());
			}
		}
		return retorno;
	}
	
	public boolean excluir(){
		MesaPO mesa = getMesaDAO().capturarPorId(getMesaPO());
		setNomeDoArquivo(mesa.getImagem());
		mesa.setDataExclusao(Calendar.getInstance());
		if(excluirImagem()){
			return getMesaDAO().atualizar(mesa);
		}else{
			return false;
		}		
	}

	private boolean salvarImagem() {
		return ArquivoUtil.salvar(getMesaPO().getImagem(), getNomeGeradoDoArquivo(), PATH_IMAGENS);
	}
	
	private String getNomeGeradoDoArquivo(){
		return this.nomeDoArquivo;
	}
	
	private void gerarNomeDoArquivo(){
		Calendar ex = Calendar.getInstance();
		this.nomeDoArquivo= "mesa" + ex.getTimeInMillis() + ".jpg";
	}
	
	private boolean excluirImagem() {
		return ArquivoUtil.excluir(getNomeDoArquivo(), PATH_IMAGENS);
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
	
	public List<String> getMensagemErro(){
		this.mensagemErro = new ArrayList<>();
		this.mensagemErro.add("error");
		return this.mensagemErro;
	}

	public MesaPO getMesaPO() {
		return mesaPO;
	}

	public void setMesaPO(MesaPO mesaPO) {
		this.mesaPO = mesaPO;
	}

	private String getNomeDoArquivo() {
		return nomeDoArquivo;
	}

	private void setNomeDoArquivo(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}

	private MesaDAO getMesaDAO() {
		if(this.mesaDAO == null){
			this.mesaDAO = new MesaDAO();
		}
		return mesaDAO;
	}

}
