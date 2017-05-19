package br.com.grupo9.sistemadereservas.model.DAO;

import java.util.List;

import br.com.grupo9.sistemadereservas.interfaces.DAO;
import br.com.grupo9.sistemadereservas.model.PO.ClientePO;

public class ClienteDAO implements DAO<ClientePO> {

	@Override
	public boolean cadastrar(ClientePO entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ClientePO capturarPorId(ClientePO entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean atualizar(ClientePO entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ClientePO> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean excluir(ClientePO entidade) {
		// TODO Auto-generated method stub
		return false;
	}

}
