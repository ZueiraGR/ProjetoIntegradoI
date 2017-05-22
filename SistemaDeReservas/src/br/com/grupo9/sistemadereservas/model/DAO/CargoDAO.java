package br.com.grupo9.sistemadereservas.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.grupo9.sistemadereservas.interfaces.DAO;
import br.com.grupo9.sistemadereservas.model.PO.CargoPO;
import br.com.grupo9.sistemadereservas.model.Util.PersistenceUtil;

public class CargoDAO implements DAO<CargoPO> {
	private EntityManager manager;

	public CargoDAO() {
		this.manager = PersistenceUtil.getEntityManager();
	}

	@Override
	public boolean cadastrar(CargoPO entidade) {
		this.manager.getTransaction().begin();
		try {
			this.manager.persist(entidade);
			this.manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.manager.getTransaction().rollback();
			// e.printStackTrace();
			return false;
		}
	}

	@Override
	public CargoPO capturarPorId(CargoPO entidade) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM CargoPO u ")
				 .append("WHERE u.chave = :chave");
			TypedQuery<CargoPO> typedQuery = this.manager.createQuery(query.toString(),CargoPO.class);
				typedQuery.setParameter("chave", entidade.getChave());
				return (CargoPO) typedQuery.getSingleResult();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao capturar o cargo pela chave. Causa:\n");
//			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean atualizar(CargoPO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM CargoPO u")
			 .append("WHERE u.nome = :nome");
		TypedQuery<CargoPO> typedQuery = this.manager.createQuery(query.toString(),CargoPO.class);
			typedQuery.setParameter("nome", entidade.getNome());
			CargoPO cargo = (CargoPO)typedQuery.getSingleResult();
			this.manager.getTransaction().begin();
		try{
			if(cargo != null && cargo.getNome().equals(entidade.getNome())){
				this.manager.merge(entidade);
			}
			this.manager.getTransaction().commit();
			return true;
		}catch (Exception e) {
			this.manager.getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar alterar o cargo. Causa:\n");
//			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CargoPO> listar() {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM CargoPO u ");
			TypedQuery<CargoPO> typedQuery = this.manager.createQuery(query.toString(),CargoPO.class);
				return (List<CargoPO>) typedQuery.getResultList();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar todos os cargos. Causa:\n");
//			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean excluir(CargoPO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM CargoPO u ")
			 .append("WHERE u.nome = :nome");
		TypedQuery<CargoPO> typedQuery = this.manager.createQuery(query.toString(),CargoPO.class);
			typedQuery.setParameter("nome", entidade.getNome());
			CargoPO cargo = (CargoPO)typedQuery.getSingleResult();
			this.manager.getTransaction().begin();
		try{
			if(cargo != null && cargo.getNome().equals(entidade.getNome())){
				this.manager.remove(entidade);
			}
			this.manager.getTransaction().commit();
			return true;
		}catch (Exception e) {
			this.manager.getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar excluir o cargo. Causa:\n");
//			e.printStackTrace();
			return false;
		}
	}

}
