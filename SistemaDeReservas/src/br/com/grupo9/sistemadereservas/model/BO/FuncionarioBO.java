package br.com.grupo9.sistemadereservas.model.BO;

import br.com.grupo9.sistemadereservas.model.DAO.FuncionarioDAO;
import br.com.grupo9.sistemadereservas.model.PO.FuncionarioPO;

public class FuncionarioBO extends UsuarioBO {
	private FuncionarioPO funcionarioPO;
	private FuncionarioDAO funcionarioDAO;
	private CargoBO cargoBO;
	
	public void comporFuncionario(){
		getFuncionarioDAO().compor(getUsusarioPO(), getFuncionarioPO());
	}
	
	public FuncionarioPO getFuncionarioPO() {
		if(this.funcionarioPO == null){
			this.funcionarioPO = new FuncionarioPO();
		}
		return funcionarioPO;
	}
	public void setFuncionarioPO(FuncionarioPO funcionarioPO) {
		this.funcionarioPO = funcionarioPO;
	}
	public FuncionarioDAO getFuncionarioDAO() {
		if(this.funcionarioDAO == null){
			this.funcionarioDAO = new FuncionarioDAO();
		}
		return funcionarioDAO;
	}
	public CargoBO getCargoBO() {
		if(this.cargoBO == null){
			this.cargoBO = new CargoBO();
		}
		return cargoBO;
	}
		
}
