package br.com.grupo9.sistemadereservas.model.BO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.List;

import br.com.grupo9.sistemadereservas.model.DAO.MesaDAO;
import br.com.grupo9.sistemadereservas.model.PO.FuncionarioPO;
import br.com.grupo9.sistemadereservas.model.PO.MesaPO;
import br.com.grupo9.sistemadereservas.model.Util.ArquivoUtil;

public class MesaBO {

	private String PATH;
	private MesaDAO mesaDAO;
	private MesaPO mesaPO;
	private List<String> mensagemErro;
	
	public MesaBO(){
		PATH = getApplicationPath().replace("WEB-INF\\classes\\br\\com\\grupo9\\sistemadereservas\\model\\BO", "")+"img\\";
	}

	public boolean cadastrar() {
		try {
			String nomeDoArquivo = salvarImagem(getMesaPO().getImagem());
			if(nomeDoArquivo != null && !nomeDoArquivo.isEmpty()){
				getMesaPO().setImagem(nomeDoArquivo);
				System.out.println(nomeDoArquivo);
				if (getMesaDAO().cadastrar(getMesaPO())) {
					return true;
				} else {
					excluirImagem(getMesaPO().getImagem());
					return false;
				}
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		//TODO implementar atualizacao da mesa
		return false;
	}
	
	public boolean excluir(){
		//TODO implementar exclusao da mesa
		return false;
	}

	private String salvarImagem(String imagemBase64) {
		Calendar ex = Calendar.getInstance();
		String nomeDoArquivo = "mesa" + ex.getTimeInMillis() + ".jpg";
		if(ArquivoUtil.salvar(imagemBase64, nomeDoArquivo, PATH)){
			return nomeDoArquivo;
		}else{
			return null;
		}
	}
	
	private void excluirImagem(String imagem) {
		ArquivoUtil.excluir(imagem, PATH);		
	}

	public String getApplicationPath() {
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

	private MesaDAO getMesaDAO() {
		if(this.mesaDAO == null){
			this.mesaDAO = new MesaDAO();
		}
		return mesaDAO;
	}

}
