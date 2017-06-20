package br.com.grupo9.sistemadereservas.model.BO;

import java.util.List;

import br.com.grupo9.sistemadereservas.controle.Dominio.StatusUsuario;
import br.com.grupo9.sistemadereservas.model.DAO.UsuarioDAO;
import br.com.grupo9.sistemadereservas.model.PO.UsuarioPO;

/**
 * Classe que conterá as regras de negócio e os metodos relacionados ao usuario
 * @author Marcos
 */

public class UsuarioBO {
	private UsuarioPO usuarioPO;
	private UsuarioDAO usuarioDAO;
	
	
	public UsuarioPO capturarUsuarioValido(){
		UsuarioPO usuarioCapturado = getUsuarioDAO().capturarPorId(getUsuarioPO());
		if(usuarioCapturado != null && usuarioCapturado.getStatus().charValue() == StatusUsuario.ATIVO.getCodigo()){
			if(!getUsuarioPO().getLogin().equals(usuarioCapturado.getLogin())){
				usuarioCapturado = null;
			}
		}
		return usuarioCapturado;
	}
	
	public boolean atualizar(){
		return getUsuarioDAO().atualizar(getUsuarioPO());
	}
	
	public boolean isUsuarioJaExiste(){
		return ((UsuarioDAO) getUsuarioDAO()).isUsuarioJaExiste(getUsuarioPO());
	}
	
	public UsuarioPO getUsuarioPO(){
		if(this.usuarioPO == null){
			this.usuarioPO = new UsuarioPO();
		}
		return this.usuarioPO;
	}
	
	public void setUsuarioPO(UsuarioPO usuarioPO) {
		this.usuarioPO = usuarioPO;
	}

	public UsuarioDAO getUsuarioDAO(){
		if(this.usuarioDAO == null){
			this.usuarioDAO = new UsuarioDAO();
		}
		return this.usuarioDAO;
	}
	
	public List<UsuarioPO> listar(){
		return getUsuarioDAO().listarTodos();
	}
}
