package br.com.grupo9.sistemadereservas.model.BO;

import br.com.grupo9.sistemadereservas.model.DAO.ClienteDAO;
import br.com.grupo9.sistemadereservas.model.PO.ClientePO;

public class ClienteBO extends UsuarioBO {
	private ClientePO clientePO;
	private ClienteDAO clienteDAO;

	public ClientePO getClientePO() {
		if(this.clientePO == null){
			this.clientePO = new ClientePO();
		}
		return clientePO;
	}
	public void setClientePO(ClientePO clientePO) {
		this.clientePO = clientePO;
	}
	public ClienteDAO getClienteDAO() {
		if(this.clienteDAO == null){
			this.clienteDAO = new ClienteDAO();
		}
		return clienteDAO;
	}
}
