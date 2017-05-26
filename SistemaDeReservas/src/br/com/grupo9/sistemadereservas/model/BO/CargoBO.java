package br.com.grupo9.sistemadereservas.model.BO;

import java.util.ArrayList;
import java.util.List;

import br.com.grupo9.sistemadereservas.controle.Dominio.NivelDeAcesso;
import br.com.grupo9.sistemadereservas.model.DAO.CargoDAO;
import br.com.grupo9.sistemadereservas.model.PO.CargoPO;

public class CargoBO {
	private CargoPO cargoPO;
	private CargoDAO cargoDAO;
	private ArrayList<String> mensagensDeErro;
	
	public boolean cadastrar(){
		if(isDadosValidosParaCadastro()){
			return getCargoDAO().cadastrar(getCargoPO());
		}else{
			return false;
		}
	}
	public List<CargoPO> listar(Integer pagina, Integer qtdRegistros){
		return getCargoDAO().listar();
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
			setMensagemErro("O nome do cargo deve ser preenchido.");
		}
		if(getCargoPO().getNome().length() < 5){
			setMensagemErro("O nome do cargo deve conter mais de 5 caracteres.");
		}
		if(getCargoPO().getNivelAcesso() == null){
			setMensagemErro("O n�vel de acesso deve ser definido.");
		}
		if(!isNivelAcessoValido()){
			setMensagemErro("O n�vel de acesso informado n�o � v�lido.");
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
	
	private void setMensagemErro(String mensagem){
		this.mensagensDeErro.add(mensagem);
	}
	public ArrayList<String> getMensagemErro(){
		return this.mensagensDeErro;
	}
	
}
