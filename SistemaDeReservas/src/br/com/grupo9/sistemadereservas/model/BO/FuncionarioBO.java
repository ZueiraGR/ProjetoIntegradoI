package br.com.grupo9.sistemadereservas.model.BO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.grupo9.sistemadereservas.model.DAO.CargoDAO;
import br.com.grupo9.sistemadereservas.model.DAO.FuncionarioDAO;
import br.com.grupo9.sistemadereservas.model.PO.FuncionarioPO;
import br.com.grupo9.sistemadereservas.model.PO.UsuarioPO;

public class FuncionarioBO extends UsuarioBO {
	private FuncionarioDAO funcionarioDAO;
	private CargoDAO cargoDAO;
	private List<String> mensagemErro;
	
	public boolean cadastrar(){
		getUsuarioPO().setDataCriacao(Calendar.getInstance());
		getUsuarioPO().getFuncionario().setCargo(getCargoDAO().capturarPorId(getUsuarioPO().getFuncionario().getCargo()));
		return getFuncionarioDAO().cadastrarFuncionario(getUsuarioPO());
	}
	
	public void compor(){
//		getUsuarioDAO().
	}
	public List<String> getMensagemErro(){
		this.mensagemErro = new ArrayList<>();
		this.mensagemErro.add("error");
		return this.mensagemErro;
	}

	public boolean altualizar(){
//		getFuncionarioDAO().atualizar(getFuncionarioPO());
		return true;
	}
	
	public boolean deletar(){
		UsuarioPO usuario = getUsuarioDAO().capturarPorId(getUsuarioPO());
		getUsuarioPO().setDataExclusao(Calendar.getInstance());
		return getUsuarioDAO().atualizar(usuario);
	}
	
	public List<FuncionarioPO> listar(Integer pagina, Integer qtdRegistros){
		pagina = pagina*qtdRegistros-qtdRegistros;
		return getFuncionarioDAO().listar(pagina,qtdRegistros);
	}	
	private FuncionarioDAO getFuncionarioDAO() {
		if(this.funcionarioDAO == null){
			this.funcionarioDAO = new FuncionarioDAO();
		}
		return funcionarioDAO;
	}
	private CargoDAO getCargoDAO() {
		if(this.cargoDAO == null){
			this.cargoDAO = new CargoDAO();
		}
		return cargoDAO;
	}
}
