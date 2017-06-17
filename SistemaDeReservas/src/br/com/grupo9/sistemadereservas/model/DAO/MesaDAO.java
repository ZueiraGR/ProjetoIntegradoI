package br.com.grupo9.sistemadereservas.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.grupo9.sistemadereservas.interfaces.DAO;
import br.com.grupo9.sistemadereservas.model.PO.MesaPO;
import br.com.grupo9.sistemadereservas.model.Util.PersistenceUtil;

public class MesaDAO implements DAO<MesaPO> {
	private EntityManager manager;

	@Override
	public boolean cadastrar(MesaPO entidade) {
		getManager().getTransaction().begin();
		try{
			getManager().persist(entidade);
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro tentar cadastrar a mesa. Causa:\n");
			e.printStackTrace();
			return false;
		}finally {
			fecharManager();
		}
	}

	@Override
	public MesaPO capturarPorId(MesaPO entidade) {
		try {
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM mesa u ")
				 .append("WHERE u.chave = :chave");
			TypedQuery<MesaPO> typedQuery = getManager().createQuery(query.toString(), MesaPO.class);
			typedQuery.setParameter("chave", entidade.getChave().intValue());
			return (MesaPO) typedQuery.getSingleResult();
		} catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar o usuario pelo login e senha. Causa:\n");
			e.printStackTrace();
			return null;
		}finally {
			fecharManager();
		}
	}

	@Override
	public boolean atualizar(MesaPO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM mesa u")
			 .append("WHERE u.chave = :chave");
		TypedQuery<MesaPO> typedQuery = getManager().createQuery(query.toString(), MesaPO.class);
		typedQuery.setParameter("chave", entidade.getChave().intValue());
		MesaPO mesa = (MesaPO) typedQuery.getSingleResult();
		getManager().getTransaction().begin();
		try {
			if (mesa != null && mesa.getChave() == entidade.getChave()) {
				getManager().merge(entidade);
			}
			getManager().getTransaction().commit();
			return true;
		} catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar alterar o usuario. Causa:\n");
			 e.printStackTrace();
			return false;
		}finally {
			fecharManager();
		}

	}

	@Override
	public List<MesaPO> listar(Integer pagina, Integer qtdRegistros, String filtro) {
		try {
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM mesa u ");
			TypedQuery<MesaPO> typedQuery = getManager().createQuery(query.toString(), MesaPO.class);
			return (List<MesaPO>) typedQuery.setFirstResult(pagina).setMaxResults(qtdRegistros).getResultList();
		} catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar todos os usuarios. Causa:\n");
			e.printStackTrace();
			return null;
		}finally {
			fecharManager();
		}
	}

	@Override
	public boolean excluir(MesaPO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM mesa u ")
			 .append("WHERE u.chave = :chave");
		TypedQuery<MesaPO> typedQuery = getManager().createQuery(query.toString(), MesaPO.class);
		typedQuery.setParameter("chave", entidade.getChave().intValue());
		MesaPO mesa = (MesaPO) typedQuery.getSingleResult();
		getManager().getTransaction().begin();
		try {
			if (mesa != null && mesa.getChave() == entidade.getChave()) {
				getManager().remove(entidade);
			}
			getManager().getTransaction().commit();
			return true;
		} catch (Exception e) {
			getManager().getTransaction().rollback();
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
