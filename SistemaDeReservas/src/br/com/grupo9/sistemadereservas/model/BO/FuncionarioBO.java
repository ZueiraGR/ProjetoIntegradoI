package br.com.grupo9.sistemadereservas.model.BO;

import java.util.List;

import br.com.grupo9.sistemadereservas.model.DAO.FuncionarioDAO;
import br.com.grupo9.sistemadereservas.model.PO.FuncionarioPO;

public class FuncionarioBO extends UsuarioBO {
	private FuncionarioPO funcionarioPO;
	private FuncionarioDAO funcionarioDAO;
	
	public void cadastrar(){
		getFuncionarioDAO().cadastrar(getFuncionarioPO());
	}
	
	public boolean altualizar(){
		getFuncionarioDAO().atualizar(getFuncionarioPO());
		return true;
	}
	
	public boolean deletar(){
		getFuncionarioDAO().excluir(getFuncionarioPO());
		return true;
	}
	
	public List<FuncionarioPO> listar(Integer pagina, Integer qtdRegistros){
		pagina = pagina*qtdRegistros-qtdRegistros;
		return getFuncionarioDAO().listar(pagina,qtdRegistros);
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
	private FuncionarioDAO getFuncionarioDAO() {
		if(this.funcionarioDAO == null){
			this.funcionarioDAO = new FuncionarioDAO();
		}
		return funcionarioDAO;
	}
		
}
