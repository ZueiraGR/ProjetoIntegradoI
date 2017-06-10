package br.com.grupo9.sistemadereservas.model.BO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.grupo9.sistemadereservas.model.DAO.FuncionarioDAO;
import br.com.grupo9.sistemadereservas.model.PO.FuncionarioPO;

public class FuncionarioBO extends UsuarioBO {
	private FuncionarioPO funcionarioPO;
	private FuncionarioDAO funcionarioDAO;
	private List<String> mensagemErro;
	
	public boolean cadastrar(){
		getUsuarioPO().setDataCriacao(Calendar.getInstance());
		return getFuncionarioDAO().cadastrarFuncionario(getUsuarioPO());
	}
	public List<String> getMensagemErro(){
		this.mensagemErro = new ArrayList<>();
		this.mensagemErro.add("error");
		return this.mensagemErro;
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
