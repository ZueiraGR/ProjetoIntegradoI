package br.com.grupo9.sistemadereservas.model.BO;

import java.util.ArrayList;
import java.util.List;

import br.com.grupo9.sistemadereservas.controle.Dominio.NivelDeAcesso;
import br.com.grupo9.sistemadereservas.model.DAO.CargoDAO;
import br.com.grupo9.sistemadereservas.model.PO.CargoPO;

public class CargoBO {
	private CargoPO cargoPO;
	private CargoDAO cargoDAO;
	private List<String> mensagensDeErro;
	
	private final int CARGOS_ATIVOS = 1;
	private final int CARGOS_EXCLUIDOS = 0;
	
	public CargoBO(){
		this.mensagensDeErro = new ArrayList<>();
	}
	
	public CargoPO capturar(){
		return getCargoDAO().capturarPorId(getCargoPO());
	}
	
	public boolean capturarCargoPeloNome(){
		return getCargoDAO().capturarPorNome(getCargoPO()) != null ? true : false;
	}
	
	public boolean cadastrar(){
		if(isDadosValidosParaCadastro()){
			return getCargoDAO().cadastrar(getCargoPO());
		}else{
			return false;
		}
	}
	public boolean atualizar(){
		getCargoDAO().atualizar(getCargoPO());
		return true;
	}
	public boolean excluir(){
		getCargoDAO().excluir(getCargoPO());
		return true;
	}
	
	public List<CargoPO> listar(Integer pagina, Integer qtdRegistros){
		pagina = pagina*qtdRegistros-qtdRegistros;
		return getCargoDAO().listar(pagina,qtdRegistros, getFiltro(CARGOS_ATIVOS));
	}
	
	public List<CargoPO> listarTodos(){
		return getCargoDAO().listaTodos();
	}
	
	public CargoPO getCargoPO() {
		if(this.cargoPO == null){
			this.cargoPO = new CargoPO();
		}
		return cargoPO;
	}
	public void setCargoPO(CargoPO cargoPO) {
		this.cargoPO = cargoPO;
	}
	private CargoDAO getCargoDAO() {
		if(this.cargoDAO == null){
			this.cargoDAO = new CargoDAO();
		}
		return cargoDAO;
	}
	
	private boolean isDadosValidosParaCadastro(){
		if(getCargoPO().getNome() == null || getCargoPO().getNome().isEmpty()){
			setMensagemErro("O nome do cargo deve ser preenchido.<br/>");
		}
		if(getCargoPO().getNome().length() < 5){
			setMensagemErro("O nome do cargo deve conter no mínimo 5 caracteres.<br/>");
		}
		if(getCargoPO().getNivelAcesso() == null){
			setMensagemErro("O nível de acesso deve ser definido.<br/>");
		}
		if(!isNivelAcessoValido()){
			setMensagemErro("O nível de acesso informado não é válido.<br/>");
		}
		
		if(getMensagemErro().isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isNivelAcessoValido(){
		boolean retorno = false;
		for(char caractere : NivelDeAcesso.getListaNiveisDeAcesso()){
			if(getCargoPO().getNivelAcesso().charValue() == caractere){
				retorno = true;
				break;
			}
		}
		return retorno;
	}
	
	private String getFiltro(int codigo){
		String filtro = "";
		switch (codigo) {
		case CARGOS_ATIVOS: filtro = "WHERE c.dataExclusao IS NULL ORDER BY c.nome ASC";	
			break;
		case CARGOS_EXCLUIDOS: filtro = "WHERE c.dataExclusao IS NOT NULL ORDER BY c.nome ASC";
			break;
		}
		return filtro;
	}
	
	private void setMensagemErro(String mensagem){
		this.mensagensDeErro.add(mensagem);
	}
	public List<String> getMensagemErro(){
		return this.mensagensDeErro;
	}
	
}
