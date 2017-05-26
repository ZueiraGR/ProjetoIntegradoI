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
		this.manager.getTransaction().begin();
		try{
			this.manager.persist(entidade);
			this.manager.getTransaction().commit();
			return true;
		}catch (Exception e) {
			this.manager.getTransaction().rollback();
			System.out.println("\nOcorreu um erro tentar cadastrar o cliente. Causa:\n");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ClientePO capturarPorId(ClientePO entidade) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM ClientePO u ")
				 .append("WHERE u.chave = :chave");
			TypedQuery<ClientePO> typedQuery = this.manager.createQuery(query.toString(),ClientePO.class);
				typedQuery.setParameter("chave", entidade.getChave().intValue());
				return (ClientePO) typedQuery.getSingleResult();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao capturar o cliente pela chave. Causa:\n");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean atualizar(ClientePO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM ClientePO u")
			 .append("WHERE u.nome = :nome");
		TypedQuery<ClientePO> typedQuery = this.manager.createQuery(query.toString(),ClientePO.class);
			typedQuery.setParameter("nome", entidade.getNome());
			ClientePO cliente = (ClientePO)typedQuery.getSingleResult();
			this.manager.getTransaction().begin();
		try{
			if(cliente != null && cliente.getNome().equals(entidade.getNome())){
				this.manager.merge(entidade);
			}
			this.manager.getTransaction().commit();
			return true;
		}catch (Exception e) {
			this.manager.getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar alterar o cliente. Causa:\n");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ClientePO> listar(Integer pagina, Integer qtdRegistros) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM ClientePO u ");
			TypedQuery<ClientePO> typedQuery = this.manager.createQuery(query.toString(),ClientePO.class);
				return (List<ClientePO>) typedQuery.setFirstResult(pagina).setMaxResults(qtdRegistros).getResultList();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar todos os cliente. Causa:\n");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean excluir(ClientePO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM ClientePO u ")
			 .append("WHERE u.nome = :nome");
		TypedQuery<ClientePO> typedQuery = this.manager.createQuery(query.toString(),ClientePO.class);
			typedQuery.setParameter("nome", entidade.getNome());
			ClientePO cliente = (ClientePO)typedQuery.getSingleResult();
			this.manager.getTransaction().begin();
		try{
			if(cliente != null && cliente.getNome().equals(entidade.getNome())){
				this.manager.remove(entidade);
			}
			this.manager.getTransaction().commit();
			return true;
		}catch (Exception e) {
			this.manager.getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar excluir o cliente. Causa:\n");
//			e.printStackTrace();
			return false;
		}
	}

	public ClientePO compor(UsuarioPO entidade) {
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
