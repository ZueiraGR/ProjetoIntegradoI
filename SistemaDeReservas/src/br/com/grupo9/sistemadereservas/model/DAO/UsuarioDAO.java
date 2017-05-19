package br.com.grupo9.sistemadereservas.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.grupo9.sistemadereservas.interfaces.DAO;
import br.com.grupo9.sistemadereservas.model.PO.UsuarioPO;
import br.com.grupo9.sistemadereservas.model.Util.PersistenceUtil;

public class UsuarioDAO implements DAO<UsuarioPO> {
	
	private EntityManager manager;
        
    public UsuarioDAO(){
        this.manager = PersistenceUtil.getEntityManager();
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
	
	@Override
	public boolean cadastrar(UsuarioPO entidade) {
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
	public UsuarioPO capturarPorId(UsuarioPO entidade) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM UsuarioPO u ")
				 .append("WHERE u.login = :login");
			TypedQuery<UsuarioPO> typedQuery = this.manager.createQuery(query.toString(),UsuarioPO.class);
				typedQuery.setParameter("login", entidade.getLogin());
				return (UsuarioPO) typedQuery.getSingleResult();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao capturar o usuario pelo login. Causa:\n");
//			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean atualizar(UsuarioPO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM UsuarioPO u")
			 .append("WHERE u.login = :login");
		TypedQuery<UsuarioPO> typedQuery = this.manager.createQuery(query.toString(),UsuarioPO.class);
			typedQuery.setParameter("login", entidade.getLogin());
			UsuarioPO usuario = (UsuarioPO)typedQuery.getSingleResult();
			this.manager.getTransaction().begin();
		try{
			if(usuario != null && usuario.getLogin().equals(entidade.getLogin())){
				this.manager.merge(entidade);
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

	@Override
	public List<UsuarioPO> listar() {
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

	@Override
	public boolean excluir(UsuarioPO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM UsuarioPO u ")
			 .append("WHERE u.login = :login");
		TypedQuery<UsuarioPO> typedQuery = this.manager.createQuery(query.toString(),UsuarioPO.class);
			typedQuery.setParameter("login", entidade.getLogin());
			UsuarioPO usuario = (UsuarioPO)typedQuery.getSingleResult();
			this.manager.getTransaction().begin();
		try{
			if(usuario != null && usuario.getLogin().equals(entidade.getLogin())){
				this.manager.remove(entidade);
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
