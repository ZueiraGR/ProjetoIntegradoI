package br.com.grupo9.sistemadereservas.controle.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.grupo9.sistemadereservas.controle.Dominio.TipoUsuario;
import br.com.grupo9.sistemadereservas.controle.Util.JsonUtil;
import br.com.grupo9.sistemadereservas.model.PO.UsuarioPO;
import jdk.nashorn.internal.runtime.JSONFunctions;

@ManagedBean
public class UsuarioBean {
	private UsuarioPO usuario;
	
	public UsuarioBean(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		this.usuario = (UsuarioPO) session.getAttribute("usuario");
	}
	
	public String getUsuarioDaSessao(){
		return JsonUtil.converterObjetoEmJson(getUsuario());
	}
	
	public String getNome(){
		if(TipoUsuario.CLIENTE.equals(getUsuario().getTipo())){
			return getUsuario().getCliente().getNome();
		}else if(TipoUsuario.FUNCIONARIO.equals(getUsuario().getTipo())){
			return getUsuario().getFuncionario().getNome();
		}else{
			return "";
		}
	}
	
	public boolean isUsuarioLogado(){
		return usuario != null ? true : false ; 
	}
	public boolean isFuncionario(){
		boolean retorno = false;
		if(isUsuarioLogado()){
			retorno = TipoUsuario.FUNCIONARIO.equals(getUsuario().getTipo());
		}
		return retorno;
	}

	public UsuarioPO getUsuario() {
		return usuario;
	}
	
}
