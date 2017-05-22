package br.com.grupo9.sistemadereservas.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.grupo9.sistemadereservas.interfaces.DAO;
import br.com.grupo9.sistemadereservas.model.PO.PromocaoPO;
import br.com.grupo9.sistemadereservas.model.Util.PersistenceUtil;

public class PromocaoDAO implements DAO<PromocaoPO> {
	private EntityManager manager;
	public PromocaoDAO() {
		this.manager = PersistenceUtil.getEntityManager();
	}
	@Override
	public boolean cadastrar(PromocaoPO entidade) {
		this.manager.getTransaction().begin();
		try{
			this.manager.persist(entidade);
			this.manager.getTransaction().commit();
			return true;
		}catch (Exception e) {
			this.manager.getTransaction().rollback();
			System.out.println("\nOcorreu um erro tentar cadastrar o promocao. Causa:\n");
//			e.printStackTrace();
			return false;
		}
	}

	@Override
	public PromocaoPO capturarPorId(PromocaoPO entidade) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM PromocaoPO u ")
				 .append("WHERE u.chave = :chave");
			TypedQuery<PromocaoPO> typedQuery = this.manager.createQuery(query.toString(),PromocaoPO.class);
				typedQuery.setParameter("chave", entidade.getChave());
				return (PromocaoPO) typedQuery.getSingleResult();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao capturar o promoção pela chave. Causa:\n");
//			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean atualizar(PromocaoPO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM PromocaoPO u")
			 .append("WHERE u.chave = :chave");
		TypedQuery<PromocaoPO> typedQuery = this.manager.createQuery(query.toString(),PromocaoPO.class);
			typedQuery.setParameter("chave", entidade.getChave());
			PromocaoPO promocao = (PromocaoPO)typedQuery.getSingleResult();
			this.manager.getTransaction().begin();
		try{
			if(promocao != null && promocao.getChave() == (entidade.getChave())){
				this.manager.merge(entidade);
			}
			this.manager.getTransaction().commit();
			return true;
		}catch (Exception e) {
			this.manager.getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar alterar o promoção. Causa:\n");
//			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<PromocaoPO> listar() {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM PromocaoPO u ");
			TypedQuery<PromocaoPO> typedQuery = this.manager.createQuery(query.toString(),PromocaoPO.class);
				return (List<PromocaoPO>) typedQuery.getResultList();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar todos os promoções. Causa:\n");
//			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean excluir(PromocaoPO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM PromocaoPO u ")
			 .append("WHERE u.chave = :chave");
		TypedQuery<PromocaoPO> typedQuery = this.manager.createQuery(query.toString(),PromocaoPO.class);
			typedQuery.setParameter("chave", entidade.getChave());
			PromocaoPO promocao = (PromocaoPO)typedQuery.getSingleResult();
			this.manager.getTransaction().begin();
		try{
			if(promocao != null && promocao.getChave() == (entidade.getChave())){
				this.manager.remove(entidade);
			}
			this.manager.getTransaction().commit();
			return true;
		}catch (Exception e) {
			this.manager.getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar excluir o promoção. Causa:\n");
//			e.printStackTrace();
			return false;
		}
	}

}
