package br.com.grupo9.sistemadereservas.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.grupo9.sistemadereservas.interfaces.DAO;
import br.com.grupo9.sistemadereservas.model.PO.ReservaPO;
import br.com.grupo9.sistemadereservas.model.Util.PersistenceUtil;

public class ReservaDAO implements DAO<ReservaPO>{
	
	private EntityManager manager;
	
	@Override
	public boolean cadastrar(ReservaPO entidade) {
		try {
			getManager().getTransaction().begin();
			getManager().persist(entidade);
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			return false;
		} finally {
			getManager().close();
		}
	}

	@Override
	public ReservaPO capturarPorId(ReservaPO entidade) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT r ")
				 .append("FROM reserva r ")
				 .append("WHERE r.chave = :chave");
			TypedQuery<ReservaPO> typedQuery = getManager().createQuery(query.toString(),ReservaPO.class);
				typedQuery.setParameter("chave", entidade.getChave().intValue());
				return (ReservaPO) typedQuery.getSingleResult();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao capturar o promoção pela chave. Causa:\n");
			e.printStackTrace();
			return null;
		}finally {
			fecharManager();
		}
	}

	@Override
	public boolean atualizar(ReservaPO entidade) {
		try {
			getManager().getTransaction().begin();
			getManager().merge(entidade);
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			return false;
		} finally {
			getManager().close();
		}
	}

	@Override
	public List<ReservaPO> listar(Integer pagina, Integer qtdRegistros, String filtro) {
		return null;
	}
	
	public List<ReservaPO> listar(int chaveReserva) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT r ")
				 .append("FROM reserva r ");
			TypedQuery<ReservaPO> typedQuery = getManager().createQuery(query.toString(),ReservaPO.class);
				return (List<ReservaPO>) typedQuery.getResultList();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar todos as promoções. Causa:\n");
			e.printStackTrace();
			return null;
		}finally {
			fecharManager();
		}
	}

	@Override
	public boolean excluir(ReservaPO entidade) {
		try {
			getManager().getTransaction().begin();
			getManager().merge(entidade);
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			return false;
		} finally {
			getManager().close();
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
