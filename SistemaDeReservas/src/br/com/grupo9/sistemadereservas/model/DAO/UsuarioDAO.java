package br.com.grupo9.sistemadereservas.model.DAO;

import br.com.grupo9.sistemadereservas.model.PO.UsuarioPO;
import br.com.grupo9.sistemadereservas.model.util.PersistenceUtil;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UsuarioDAO {
	
	private EntityManager manager;
        
        public UsuarioDAO(){
            this.manager = PersistenceUtil.getEntityManager();
        }
	
	public UsuarioPO cadastrarUsuario(UsuarioPO usuarioPO){
		this.manager.getTransaction().begin();
		try{
			this.manager.persist(usuarioPO);
			this.manager.getTransaction().commit();
			return usuarioPO;
		}catch (Exception e) {
			this.manager.getTransaction().rollback();
			System.out.println("\nOcorreu um erro tentar cadastrar o usuario. Causa:\n");
//			e.printStackTrace();
			return null;
		}
	}
	
	public UsuarioPO getUsuarioByLogin(UsuarioPO usuarioPO){
		try{
			return this.manager.find(UsuarioPO.class, usuarioPO);
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao capturar o usuario pelo login. Causa:\n");
//			e.printStackTrace();
			return null;
		}
	}
	
	public UsuarioPO getUsuarioByLoginESenha(UsuarioPO usuarioPO){
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM UsuarioPO u ")
				 .append("WHERE u.login = :login")
				 .append("AND u.senha = :senha");
			TypedQuery<UsuarioPO> typedQuery = this.manager.createQuery(query.toString(),UsuarioPO.class);
				typedQuery.setParameter("login", usuarioPO.getLogin());
				typedQuery.setParameter("senha", usuarioPO.getSenha());
				return (UsuarioPO) typedQuery.getSingleResult();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar o usuario pelo login e senha. Causa:\n");
//			e.printStackTrace();
			return null;
		}
	}
	
	public List<UsuarioPO> getUsuarios(){
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM UsuarioPO u ");
			TypedQuery<UsuarioPO> typedQuery = this.manager.createQuery(query.toString(),UsuarioPO.class);
				return (List<UsuarioPO>) typedQuery.getResultList();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar todos os usuarios. Causa:\n");
//			e.printStackTrace();
			return null;
		}
	}
	
	public boolean altualizarUsuario(UsuarioPO usuarioPO){
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM UsuarioPO u")
			 .append("WHERE u.login = :login");
		TypedQuery<UsuarioPO> typedQuery = this.manager.createQuery(query.toString(),UsuarioPO.class);
			typedQuery.setParameter("login", usuarioPO.getLogin());
			UsuarioPO usuario = (UsuarioPO)typedQuery.getSingleResult();
			this.manager.getTransaction().begin();
		try{
			if(usuario != null && usuario.getLogin().equals(usuarioPO.getLogin())){
				this.manager.merge(usuarioPO);
			}
			this.manager.getTransaction().commit();
			return true;
		}catch (Exception e) {
			this.manager.getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar alterar o usuario. Causa:\n");
//			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deletarUsuario(UsuarioPO usuarioPO){
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM UsuarioPO u ")
			 .append("WHERE u.login = :login");
		TypedQuery<UsuarioPO> typedQuery = this.manager.createQuery(query.toString(),UsuarioPO.class);
			typedQuery.setParameter("login", usuarioPO.getLogin());
			UsuarioPO usuario = (UsuarioPO)typedQuery.getSingleResult();
			this.manager.getTransaction().begin();
		try{
			if(usuario != null && usuario.getLogin().equals(usuarioPO.getLogin())){
				this.manager.remove(usuarioPO);
			}
			this.manager.getTransaction().commit();
			return true;
		}catch (Exception e) {
			this.manager.getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar excluir o usuario. Causa:\n");
//			e.printStackTrace();
			return false;
		}
	}
}
