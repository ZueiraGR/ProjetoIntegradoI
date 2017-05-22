package br.com.grupo9.sistemadereservas.controle.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.grupo9.sistemadereservas.controle.Dominio.TipoUsuario;
import br.com.grupo9.sistemadereservas.model.BO.ClienteBO;
import br.com.grupo9.sistemadereservas.model.BO.FuncionarioBO;

@ManagedBean
public class UsuarioBean {
	private ClienteBO clienteBO;
	private FuncionarioBO funcionarioBO;
	private char tipo;
	
	public UsuarioBean(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		this.tipo = (char) session.getAttribute("tipo");
		if(this.tipo == TipoUsuario.CLIENTE.getCodigo()){
			this.clienteBO = (ClienteBO) session.getAttribute("usuario");
		}else if(this.tipo == TipoUsuario.FUNCIONARIO.getCodigo()){
			this.funcionarioBO = (FuncionarioBO) session.getAttribute("usuario");
		}
	}
	
	public String getNome(){
		if(this.tipo == TipoUsuario.CLIENTE.getCodigo()){
			return getClienteBO().getClientePO().getNome();
		}else if(this.tipo == TipoUsuario.FUNCIONARIO.getCodigo()){
			return getFuncionarioBO().getFuncionarioPO().getNome();
		}else{
			return null;
		}
	}

	public ClienteBO getClienteBO() {
		return clienteBO;
	}

	public FuncionarioBO getFuncionarioBO() {
		return funcionarioBO;
	}
	
}
