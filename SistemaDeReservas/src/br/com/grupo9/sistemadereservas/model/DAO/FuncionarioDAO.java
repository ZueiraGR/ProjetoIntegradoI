package br.com.grupo9.sistemadereservas.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.grupo9.sistemadereservas.interfaces.DAO;
import br.com.grupo9.sistemadereservas.model.PO.ClientePO;
import br.com.grupo9.sistemadereservas.model.PO.FuncionarioPO;
import br.com.grupo9.sistemadereservas.model.PO.UsuarioPO;
import br.com.grupo9.sistemadereservas.model.Util.PersistenceUtil;

public class FuncionarioDAO implements DAO<FuncionarioPO> {
	
	private EntityManager manager;
	
	public FuncionarioDAO() {
		this.manager = PersistenceUtil.getEntityManager();
	}

	@Override
	public boolean cadastrar(FuncionarioPO entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FuncionarioPO capturarPorId(FuncionarioPO entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean atualizar(FuncionarioPO entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<FuncionarioPO> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean excluir(FuncionarioPO entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	public void compor(UsuarioPO usuarioPO, FuncionarioPO entidade) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT f ")
				 .append("FROM FuncionarioPO f ")
				 .append("WHERE chave = :chave");
			TypedQuery<FuncionarioPO> typedQuery = this.manager.createQuery(query.toString(),FuncionarioPO.class);
				typedQuery.setParameter("chave", usuarioPO.getChaveFuncionario().intValue());
				entidade = (FuncionarioPO) typedQuery.getSingleResult();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao compor funcionario. Causa:\n");
			e.printStackTrace();
		}
	}
}
