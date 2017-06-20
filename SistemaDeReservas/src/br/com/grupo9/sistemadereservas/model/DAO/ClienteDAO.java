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

	@Override
	public boolean cadastrar(ClientePO entidade) {
		getManager().getTransaction().begin();
		try{
			getManager().persist(entidade);
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro tentar cadastrar o cliente. Causa:\n");
			e.printStackTrace();
			return false;
		}finally {
			fecharManager();
		}
	}
	public boolean cadastrarCliente(UsuarioPO entidade){
		ClientePO cliente = entidade.getCliente();
		getManager().getTransaction().begin();
		try{
			getManager().persist(cliente);
			if(cliente.getChave() != null && cliente.getChave().intValue() > 0){
				entidade.setCliente(cliente);
				getManager().persist(entidade);
				getManager().getTransaction().commit();
				return true;
			}else{
				getManager().getTransaction().rollback();
				return false;
			}
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro tentar cadastrar o cliente-usuario. Causa:\n");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public UsuarioPO comporUsuarioComChaveDoCliente(ClientePO entidade){
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
			 	 .append("FROM usuario u ")
			 	 .append("WHERE u.cliente.chave = :chave");
			TypedQuery<UsuarioPO> typedQuery = getManager().createQuery(query.toString(),UsuarioPO.class);
			typedQuery.setParameter("chave", entidade.getChave());
			return (UsuarioPO)typedQuery.getSingleResult();
		}catch (Exception e) {
			System.out.println("Não foi localizado um usuário com chave do cliente :"+entidade.getChave()+"\n");
			return null;
		}finally {
			getManager().close();
		}
	}

	@Override
	public ClientePO capturarPorId(ClientePO entidade) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM cliente u ")
				 .append("WHERE u.chave = :chave");
			TypedQuery<ClientePO> typedQuery = getManager().createQuery(query.toString(),ClientePO.class);
				typedQuery.setParameter("chave", entidade.getChave().intValue());
				return (ClientePO) typedQuery.getSingleResult();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao capturar o cliente pela chave. Causa:\n");
			e.printStackTrace();
			return null;
		}finally {
			fecharManager();
		}
	}

	@Override
	public boolean atualizar(ClientePO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM ClientePO u")
			 .append("WHERE u.nome = :nome");
		TypedQuery<ClientePO> typedQuery = getManager().createQuery(query.toString(),ClientePO.class);
			typedQuery.setParameter("nome", entidade.getNome());
			ClientePO cliente = (ClientePO)typedQuery.getSingleResult();
			getManager().getTransaction().begin();
		try{
			if(cliente != null && cliente.getNome().equals(entidade.getNome())){
				getManager().merge(entidade);
			}
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar alterar o cliente. Causa:\n");
			e.printStackTrace();
			return false;
		}finally {
			fecharManager();
		}
	}
	
	public boolean atualizar(UsuarioPO entidade) {
		try{
			getManager().getTransaction().begin();
			getManager().merge(entidade);
			getManager().merge(entidade.getCliente());
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar atualizar o cliente: "+entidade.getCliente().getNome()+"\n");
			return false;
		}finally {
			fecharManager();
		}
	}

	@Override
	public List<ClientePO> listar(Integer pagina, Integer qtdRegistros, String filtro) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM cliente u ")
				 .append(filtro);
			TypedQuery<ClientePO> typedQuery = getManager().createQuery(query.toString(),ClientePO.class);
				return (List<ClientePO>) typedQuery.setFirstResult(pagina).setMaxResults(qtdRegistros).getResultList();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar todos os cliente. Causa:\n");
			e.printStackTrace();
			return null;
		}finally {
			fecharManager();
		}
	}
	
	@Override
	public boolean excluir(ClientePO entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean excluir(UsuarioPO entidade){
		try{
			getManager().getTransaction().begin();
			getManager().merge(entidade);
			getManager().merge(entidade.getCliente());
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar excluir o cliente: "+entidade.getLogin()+"\n");
			return false;
		}finally {
			fecharManager();
		}
	}
	
	public UsuarioPO loginById(UsuarioPO entidade) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM usuario u ")
				 .append("WHERE u.cliente.chave = :chave");
			TypedQuery<UsuarioPO> typedQuery = getManager().createQuery(query.toString(),UsuarioPO.class);
			typedQuery.setParameter("chave", entidade.getCliente().getChave());
			return (UsuarioPO)typedQuery.getSingleResult();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar todos os cliente. Causa:\n");
			e.printStackTrace();
			return null;
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
