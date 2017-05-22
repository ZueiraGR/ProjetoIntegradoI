package br.com.grupo9.sistemadereservas.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.grupo9.sistemadereservas.interfaces.DAO;
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
		this.manager.getTransaction().begin();
		try{
			this.manager.persist(entidade);
			this.manager.getTransaction().commit();
			return true;
		}catch (Exception e) {
			this.manager.getTransaction().rollback();
			System.out.println("\nOcorreu um erro tentar cadastrar o funcionario. Causa:\n");
//			e.printStackTrace();
			return false;
		}
	}

	@Override
	public FuncionarioPO capturarPorId(FuncionarioPO entidade) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM FuncionarioPO u ")
				 .append("WHERE u.chave = :chave");
			TypedQuery<FuncionarioPO> typedQuery = this.manager.createQuery(query.toString(),FuncionarioPO.class);
				typedQuery.setParameter("chave", entidade.getChave());
				return (FuncionarioPO) typedQuery.getSingleResult();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao capturar o funcionario pela chave . Causa:\n");
//			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean atualizar(FuncionarioPO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM FuncionarioPO u")
			 .append("WHERE u.nome = :nome");
		TypedQuery<FuncionarioPO> typedQuery = this.manager.createQuery(query.toString(),FuncionarioPO.class);
			typedQuery.setParameter("nome", entidade.getNome());
			FuncionarioPO funcionario = (FuncionarioPO)typedQuery.getSingleResult();
			this.manager.getTransaction().begin();
		try{
			if(funcionario != null && funcionario.getNome().equals(entidade.getNome())){
				this.manager.merge(entidade);
			}
			this.manager.getTransaction().commit();
			return true;
		}catch (Exception e) {
			this.manager.getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar alterar o funcionario. Causa:\n");
//			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<FuncionarioPO> listar() {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM FuncionarioPO u ");
			TypedQuery<FuncionarioPO> typedQuery = this.manager.createQuery(query.toString(),FuncionarioPO.class);
				return (List<FuncionarioPO>) typedQuery.getResultList();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar todos os funcionários. Causa:\n");
//			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean excluir(FuncionarioPO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM FuncionarioPO u ")
			 .append("WHERE u.nome = :nome");
		TypedQuery<FuncionarioPO> typedQuery = this.manager.createQuery(query.toString(),FuncionarioPO.class);
			typedQuery.setParameter("nome", entidade.getNome());
			FuncionarioPO funcionario = (FuncionarioPO)typedQuery.getSingleResult();
			this.manager.getTransaction().begin();
		try{
			if(funcionario != null && funcionario.getNome().equals(entidade.getNome())){
				this.manager.remove(entidade);
			}
			this.manager.getTransaction().commit();
			return true;
		}catch (Exception e) {
			this.manager.getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar excluir o funcionário. Causa:\n");
//			e.printStackTrace();
			return false;
		}
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
