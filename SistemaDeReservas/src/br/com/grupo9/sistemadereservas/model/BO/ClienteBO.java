package br.com.grupo9.sistemadereservas.model.BO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.grupo9.sistemadereservas.model.DAO.ClienteDAO;
import br.com.grupo9.sistemadereservas.model.PO.ClientePO;

public class ClienteBO extends UsuarioBO {
	private ClientePO clientePO;
	private ClienteDAO clienteDAO;
	private List<String> mensagemErro;
	public boolean cadastrar(){
		getUsuarioPO().setDataCriacao(Calendar.getInstance());
		return getClienteDAO().cadastrarCliente(getUsuarioPO());
	}
	public List<String> getMensagemErro(){
		this.mensagemErro = new ArrayList<>();
		this.mensagemErro.add("error");
		return this.mensagemErro;
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
