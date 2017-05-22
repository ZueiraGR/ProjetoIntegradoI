package br.com.grupo9.sistemadereservas.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.grupo9.sistemadereservas.interfaces.DAO;
import br.com.grupo9.sistemadereservas.model.PO.ClientePO;
import br.com.grupo9.sistemadereservas.model.PO.UsuarioPO;
import br.com.grupo9.sistemadereservas.model.Util.PersistenceUtil;

public class ClienteDAO implements DAO<ClientePO> {

	private EntityManager manager;

	public ClienteDAO() {
		this.manager = PersistenceUtil.getEntityManager();
	}

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

	public ClientePO compor(UsuarioPO entidade, ClientePO clientePO) {
		try {
			StringBuilder query = new StringBuilder();
			query.append("SELECT p ")
				.append("FROM ClientePO p ")
				.append("WHERE chave = :chave");
			TypedQuery<ClientePO> typedQuery = this.manager.createQuery(query.toString(), ClientePO.class);
			typedQuery.setParameter("chave", entidade.getChaveCliente().intValue());
			return (ClientePO) typedQuery.getSingleResult();
		} catch (Exception e) {
			System.out.println("\nOcorreu um erro ao capturar o usuario pelo login. Causa:\n");
			e.printStackTrace();
			return null;
		}
	}

}
