package br.com.grupo9.sistemadereservas.model.BO;

import br.com.grupo9.sistemadereservas.model.DAO.UsuarioDAO;
import br.com.grupo9.sistemadereservas.model.PO.UsuarioPO;

/**
 * Classe que conterá as regras de negócio e os metodos relacionados ao usuario
 * @author Marcos
 */

public class UsuarioBO {
	private UsuarioPO usuarioPO;
	private UsuarioDAO usuarioDAO;
	
	public boolean autenticarUsuario(){
		boolean retorno = false;
		
		
		return retorno;
	}

	public UsuarioPO getUsusarioPO(){
		if(this.usuarioPO == null){
			this.usuarioPO = new UsuarioPO();
		}
		return this.usuarioPO;
	}
	
	public UsuarioDAO getUsuarioDAO(){
		if(this.usuarioDAO == null){
			this.usuarioDAO = new UsuarioDAO();
		}
		return this.usuarioDAO;
	}
}
