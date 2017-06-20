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
		try{
			getManager().getTransaction().begin();
			getManager().persist(entidade);
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro tentar cadastrar o promocao. Causa:\n");
			e.printStackTrace();
			return false;
		}finally {
			fecharManager();
		}
	}

	@Override
	public PromocaoPO capturarPorId(PromocaoPO entidade) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM promocao u ")
				 .append("WHERE u.chave = :chave");
			TypedQuery<PromocaoPO> typedQuery = getManager().createQuery(query.toString(),PromocaoPO.class);
				typedQuery.setParameter("chave", entidade.getChave().intValue());
				return (PromocaoPO) typedQuery.getSingleResult();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao capturar o promoção pela chave. Causa:\n");
			e.printStackTrace();
			return null;
		}finally {
			fecharManager();
		}
	}

	@Override
	public boolean atualizar(PromocaoPO entidade) {
		try{
			getManager().getTransaction().begin();
			getManager().merge(entidade);
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar alterar o promoção. Causa:\n");
			e.printStackTrace();
			return false;
		}finally {
			fecharManager();
		}
	}

	@Override
	public List<PromocaoPO> listar(Integer pagina, Integer qtdRegistros, String filtro) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM promocao u ");
			TypedQuery<PromocaoPO> typedQuery = getManager().createQuery(query.toString(),PromocaoPO.class);
				return (List<PromocaoPO>) typedQuery.setFirstResult(pagina).setMaxResults(qtdRegistros).getResultList();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar todos as promoções. Causa:\n");
			e.printStackTrace();
			return null;
		}finally {
			fecharManager();
		}
	}
	
	public List<PromocaoPO> listar(String filtro) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM promocao u ")
				 .append(filtro);
			TypedQuery<PromocaoPO> typedQuery = getManager().createQuery(query.toString(),PromocaoPO.class);
				return (List<PromocaoPO>) typedQuery.getResultList();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar todos as promoções. Causa:\n");
			e.printStackTrace();
			return null;
		}finally {
			fecharManager();
		}
	}

	@Override
	public boolean excluir(PromocaoPO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM promocao u ")
			 .append("WHERE u.chave = :chave");
		TypedQuery<PromocaoPO> typedQuery = getManager().createQuery(query.toString(),PromocaoPO.class);
			typedQuery.setParameter("chave", entidade.getChave());
			PromocaoPO promocao = (PromocaoPO)typedQuery.getSingleResult();
			getManager().getTransaction().begin();
		try{
			if(promocao != null && promocao.getChave() == (entidade.getChave())){
				getManager().remove(entidade);
			}
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar excluir o promo��o. Causa:\n");
			e.printStackTrace();
			return false;
		}finally {
			fecharManager();
		}
	}
	
	@Override
	public void fecharManager() {
		if(this.manager.isOpen()){
			this.manager.close();
		}				
	}
	
	private EntityManager getManager(){
		if(this.manager == null){
			this.manager = PersistenceUtil.getEntityManager();
		}else if(!this.manager.isOpen()){
			this.manager = PersistenceUtil.getEntityManager();
		}
		return this.manager;
	}

}
