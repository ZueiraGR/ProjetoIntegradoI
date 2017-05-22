package br.com.grupo9.sistemadereservas.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.grupo9.sistemadereservas.interfaces.DAO;
import br.com.grupo9.sistemadereservas.model.PO.MesaPO;
import br.com.grupo9.sistemadereservas.model.Util.PersistenceUtil;

public class MesaDAO implements DAO<MesaPO> {
	private EntityManager manager;

	public MesaDAO() {
		this.manager = PersistenceUtil.getEntityManager();
	}

	@Override
	public boolean cadastrar(MesaPO entidade) {
		this.manager.getTransaction().begin();
		try{
			this.manager.persist(entidade);
			this.manager.getTransaction().commit();
			return true;
		}catch (Exception e) {
			this.manager.getTransaction().rollback();
			System.out.println("\nOcorreu um erro tentar cadastrar o usuario. Causa:\n");
//			e.printStackTrace();
			return false;
		}

	}

	@Override
	public MesaPO capturarPorId(MesaPO entidade) {
		try {
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ").append("FROM MesaPO u ").append("WHERE u.chave = :chave");

			TypedQuery<MesaPO> typedQuery = this.manager.createQuery(query.toString(), MesaPO.class);
			typedQuery.setParameter("chave", entidade.getChave());

			return (MesaPO) typedQuery.getSingleResult();
		} catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar o usuario pelo login e senha. Causa:\n");
			// e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean atualizar(MesaPO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ").append("FROM MesaPO u").append("WHERE u.chave = :chave");
		TypedQuery<MesaPO> typedQuery = this.manager.createQuery(query.toString(), MesaPO.class);
		typedQuery.setParameter("chave", entidade.getChave());
		MesaPO mesa = (MesaPO) typedQuery.getSingleResult();
		this.manager.getTransaction().begin();
		try {
			if (mesa != null && mesa.getChave() == entidade.getChave()) {
				this.manager.merge(entidade);
			}
			this.manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.manager.getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar alterar o usuario. Causa:\n");
			// e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<MesaPO> listar() {
		try {
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ").append("FROM MesaPO u ");
			TypedQuery<MesaPO> typedQuery = this.manager.createQuery(query.toString(), MesaPO.class);
			return (List<MesaPO>) typedQuery.getResultList();
		} catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar todos os usuarios. Causa:\n");
			// e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean excluir(MesaPO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ").append("FROM MesaPO u ").append("WHERE u.chave = :chave");
		TypedQuery<MesaPO> typedQuery = this.manager.createQuery(query.toString(), MesaPO.class);
		typedQuery.setParameter("login", entidade.getChave());
		MesaPO mesa = (MesaPO) typedQuery.getSingleResult();
		this.manager.getTransaction().begin();
		try {
			if (mesa != null && mesa.getChave() == entidade.getChave()) {
				this.manager.remove(entidade);
			}
			this.manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.manager.getTransaction().rollback();
			return false;
		}
	}

}
