package br.com.grupo9.sistemadereservas.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.grupo9.sistemadereservas.interfaces.DAO;
import br.com.grupo9.sistemadereservas.model.PO.UsuarioPO;
import br.com.grupo9.sistemadereservas.model.Util.PersistenceUtil;

public class UsuarioDAO implements DAO<UsuarioPO> {
	
	private EntityManager manager;
	
	public UsuarioPO getUsuarioByLoginESenha(UsuarioPO usuarioPO){
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM UsuarioPO u ")
				 .append("WHERE u.login = :login")
				 .append("AND u.senha = :senha");
			TypedQuery<UsuarioPO> typedQuery = getManager().createQuery(query.toString(),UsuarioPO.class);
				typedQuery.setParameter("login", usuarioPO.getLogin());
				typedQuery.setParameter("senha", usuarioPO.getSenha());
				return (UsuarioPO) typedQuery.getSingleResult();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar o usuario pelo login e senha. Causa:\n");
			e.printStackTrace();
			return null;
		}finally {
			fecharManager();
		}	
	}
	
	@Override
	public boolean cadastrar(UsuarioPO entidade) {
		getManager().getTransaction().begin();
		try{
			getManager().persist(entidade);
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro tentar cadastrar o usuario. Causa:\n");
			e.printStackTrace();
			return false;
		}finally {
			fecharManager();
		}		
	}

	@Override
	public UsuarioPO capturarPorId(UsuarioPO entidade) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM usuario u ")
				 .append("WHERE u.login = :login");
			TypedQuery<UsuarioPO> typedQuery = getManager().createQuery(query.toString(),UsuarioPO.class);
				typedQuery.setParameter("login", entidade.getLogin());
				return (UsuarioPO) typedQuery.getSingleResult();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao capturar o usuario pelo login. Causa:\n");
			e.printStackTrace();
			return null;
		}finally {
			fecharManager();
		}	
	}
	
	@Override
	public boolean atualizar(UsuarioPO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM usuario u")
			 .append("WHERE u.login = :login");
		TypedQuery<UsuarioPO> typedQuery = getManager().createQuery(query.toString(),UsuarioPO.class);
			typedQuery.setParameter("login", entidade.getLogin());
			UsuarioPO usuario = (UsuarioPO)typedQuery.getSingleResult();
			
		try{
			if(usuario != null && usuario.getLogin().equals(entidade.getLogin())){
				getManager().getTransaction().begin();
				getManager().merge(entidade);
				getManager().getTransaction().commit();
				return true;
			}else{
				return false;
			}
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar alterar o usuario. Causa:\n");
			e.printStackTrace();
			return false;
		}finally {
			fecharManager();
		}	
	}

	@Override
	public List<UsuarioPO> listar(Integer pagina, Integer qtdRegistros) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM usuario u ");
			TypedQuery<UsuarioPO> typedQuery = getManager().createQuery(query.toString(),UsuarioPO.class);
				return (List<UsuarioPO>) typedQuery.setFirstResult(pagina).setMaxResults(qtdRegistros).getResultList();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar todos os usuarios. Causa:\n");
			e.printStackTrace();
			return null;
		}finally {
			fecharManager();
		}	
	}

	@Override
	public boolean excluir(UsuarioPO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM usuario u ")
			 .append("WHERE u.login = :login");
		TypedQuery<UsuarioPO> typedQuery = getManager().createQuery(query.toString(),UsuarioPO.class);
			typedQuery.setParameter("login", entidade.getLogin());
			UsuarioPO usuario = (UsuarioPO)typedQuery.getSingleResult();
			getManager().getTransaction().begin();
		try{
			if(usuario != null && usuario.getLogin().equals(entidade.getLogin())){
				getManager().remove(entidade);
			}
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar excluir o usuario. Causa:\n");
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
