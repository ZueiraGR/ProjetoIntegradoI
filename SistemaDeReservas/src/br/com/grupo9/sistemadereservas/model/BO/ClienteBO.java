package br.com.grupo9.sistemadereservas.model.BO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.grupo9.sistemadereservas.controle.Dominio.StatusUsuario;
import br.com.grupo9.sistemadereservas.model.DAO.ClienteDAO;
import br.com.grupo9.sistemadereservas.model.PO.ClientePO;
import br.com.grupo9.sistemadereservas.model.PO.UsuarioPO;

public class ClienteBO extends UsuarioBO {
	private ClientePO clientePO;
	private ClienteDAO clienteDAO;
	private List<String> mensagemErro;
	public boolean cadastrar(){
		getUsuarioPO().setDataCriacao(Calendar.getInstance());
		return getClienteDAO().cadastrarCliente(getUsuarioPO());
	}
	
	public boolean altualizar(){
		UsuarioPO usuario = getClienteDAO().comporUsuarioComChaveDoCliente(getUsuarioPO().getCliente());
		usuario.setCliente(getUsuarioPO().getCliente());
		return getClienteDAO().atualizar(usuario);
	}
	
	public boolean excluir(){
		UsuarioPO usuario = getClienteDAO().comporUsuarioComChaveDoCliente(getUsuarioPO().getCliente());
		if(usuario !=null){
			usuario.setDataExclusao(Calendar.getInstance());
			usuario.getCliente().setStatus(StatusUsuario.EXCLUIDO.getCodigo());
			usuario.setStatus(StatusUsuario.EXCLUIDO.getCodigo());
			return getClienteDAO().excluir(usuario);
		}else{
			return false;
		}
	}
	
	public List<String> getMensagemErro(){
		this.mensagemErro = new ArrayList<>();
		this.mensagemErro.add("error");
		return this.mensagemErro;
	}
	
	public List<ClientePO> listar(Integer pagina, Integer qtdRegistros){
		pagina = pagina*qtdRegistros-qtdRegistros;
		return getClienteDAO().listar(pagina,qtdRegistros,getFiltro());
	}
	
	private String getFiltro(){
		return "WHERE u.status != '"+StatusUsuario.EXCLUIDO.getCodigo()+"'";
	}

	public ClientePO getClientePO() {
		if(this.clientePO == null){
			this.clientePO = new ClientePO();
		}
		return clientePO;
	}
	public void setClientePO(ClientePO clientePO) {
		this.clientePO = clientePO;
	}
	private ClienteDAO getClienteDAO() {
		if(this.clienteDAO == null){
			this.clienteDAO = new ClienteDAO();
		}
		return clienteDAO;
	}
}
