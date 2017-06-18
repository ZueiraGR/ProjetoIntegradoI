package br.com.grupo9.sistemadereservas.model.DAO;

import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.grupo9.sistemadereservas.interfaces.DAO;
import br.com.grupo9.sistemadereservas.model.PO.FuncionarioPO;
import br.com.grupo9.sistemadereservas.model.PO.UsuarioPO;
import br.com.grupo9.sistemadereservas.model.Util.PersistenceUtil;

public class FuncionarioDAO implements DAO<FuncionarioPO> {
	
	private EntityManager manager;
	
	@Override
	public boolean cadastrar(FuncionarioPO entidade) {
		getManager().getTransaction().begin();
		try{
			getManager().persist(entidade);
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro tentar cadastrar o funcionario. Causa:\n");
			e.printStackTrace();
			return false;
		}finally {
			fecharManager();
		}
	}
	public boolean cadastrarFuncionario(UsuarioPO entidade){
		FuncionarioPO funcionario = entidade.getFuncionario();
		getManager().getTransaction().begin();
		try{
			getManager().persist(funcionario);
			if(funcionario.getChave() != null && funcionario.getChave().intValue() > 0){
				entidade.setFuncionario(funcionario);
				try{
					getManager().persist(entidade);
					getManager().getTransaction().commit();
					return true;
				}catch (Exception e) {
					e.printStackTrace();
					getManager().getTransaction().rollback();
					return false;
				}				
			}else{
				getManager().getTransaction().rollback();
				return false;
			}
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro tentar cadastrar o funcionario-usuario. Causa:\n");
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public FuncionarioPO capturarPorId(FuncionarioPO entidade) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM funcionario u ")
				 .append("WHERE u.chave = :chave")
				 .append("AND u.status = :status");
			TypedQuery<FuncionarioPO> typedQuery = getManager().createQuery(query.toString(),FuncionarioPO.class);
				typedQuery.setParameter("chave", entidade.getChave());
				return (FuncionarioPO) typedQuery.getSingleResult();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao capturar o funcionario pela chave . Causa:\n");
			e.printStackTrace();
			return null;
		}finally {
			fecharManager();
		}
	}

	@Override
	public boolean atualizar(FuncionarioPO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
			 .append("FROM funcionario u")
			 .append("WHERE u.nome = :nome");
		TypedQuery<FuncionarioPO> typedQuery = getManager().createQuery(query.toString(),FuncionarioPO.class);
			typedQuery.setParameter("nome", entidade.getNome());
			FuncionarioPO funcionario = (FuncionarioPO)typedQuery.getSingleResult();
			getManager().getTransaction().begin();
		try{
			if(funcionario != null && funcionario.getNome().equals(entidade.getNome())){
				getManager().merge(entidade);
			}
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar alterar o funcionario. Causa:\n");
			e.printStackTrace();
			return false;
		}finally {
			fecharManager();
		}
	}

	@Override
	public List<FuncionarioPO> listar(Integer pagina, Integer qtdRegistros, String filtro) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM funcionario u ");
			TypedQuery<FuncionarioPO> typedQuery = getManager().createQuery(query.toString(),FuncionarioPO.class);
				return (List<FuncionarioPO>) typedQuery.setFirstResult(pagina).setMaxResults(qtdRegistros).getResultList();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar todos os funcionários. Causa:\n");
			e.printStackTrace();
			return null;
		}finally {
			fecharManager();
		}
	}
	
	public List<FuncionarioPO> listar(int pagina,int qtdRegistros) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
				 .append("FROM funcionario u");
			TypedQuery<FuncionarioPO> typedQuery = getManager().createQuery(query.toString(),FuncionarioPO.class);
				return (List<FuncionarioPO>) typedQuery.setFirstResult(pagina).setMaxResults(qtdRegistros).getResultList();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao tentar capturar todos os funcionários com "+qtdRegistros+" da página "+pagina+". Causa:\n");
			e.printStackTrace();
			return null;
		}finally {
			fecharManager();
		}
	}

	@Override
	public boolean excluir(FuncionarioPO entidade) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u ")
		 	 .append("FROM usuario u ")
		 	 .append("WHERE u.funcionario.chave = :chave");
		TypedQuery<UsuarioPO> typedQuery = getManager().createQuery(query.toString(),UsuarioPO.class);
		typedQuery.setParameter("chave", entidade.getChave());
		UsuarioPO usuario = (UsuarioPO)typedQuery.getSingleResult();
		getManager().getTransaction().begin();
		try{
			usuario.setDataExclusao(Calendar.getInstance());
			getManager().merge(usuario);	
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar excluir o funcionário. Causa:\n");
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
