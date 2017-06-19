package br.com.grupo9.sistemadereservas.model.DAO;

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
		return false;
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
				 .append("WHERE u.chave = :chave");
			TypedQuery<FuncionarioPO> typedQuery = getManager().createQuery(query.toString(),FuncionarioPO.class);
				typedQuery.setParameter("chave", entidade.getChave());
				return (FuncionarioPO) typedQuery.getSingleResult();
		}catch (Exception e) {
			System.out.println("\nOcorreu um erro ao capturar o funcionario pela chave . Causa:\n");
			return null;
		}finally {
			fecharManager();
		}
	}
	
	public UsuarioPO comporUsuarioComChaveDoFuncionario(FuncionarioPO entidade){
		try{
			StringBuilder query = new StringBuilder();
			query.append("SELECT u ")
			 	 .append("FROM usuario u ")
			 	 .append("WHERE u.funcionario.chave = :chave");
			TypedQuery<UsuarioPO> typedQuery = getManager().createQuery(query.toString(),UsuarioPO.class);
			typedQuery.setParameter("chave", entidade.getChave().intValue());
			return (UsuarioPO)typedQuery.getSingleResult();
		}catch (Exception e) {
			System.out.println("Não foi localizado um usuário com chave do funcionário :"+entidade.getChave().intValue()+"\n");
			return null;
		}finally {
			getManager().close();
		}
	}
	
	@Override
	public boolean atualizar(FuncionarioPO entidade) {
		return false;
	}	
	
	public boolean atualizar(UsuarioPO entidade) {
		try{
			getManager().getTransaction().begin();
			getManager().merge(entidade);
			getManager().merge(entidade.getFuncionario());
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar atualizar o funcionário: "+entidade.getFuncionario().getNome()+"\n");
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
				 .append("FROM funcionario u ")
				 .append(filtro);
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
	
	@Override
	public boolean excluir(FuncionarioPO entidade) {
		return false;
	}
	
	public boolean excluir(UsuarioPO entidade){
		try{
			getManager().getTransaction().begin();
			getManager().merge(entidade);
			getManager().merge(entidade.getFuncionario());
			getManager().getTransaction().commit();
			return true;
		}catch (Exception e) {
			getManager().getTransaction().rollback();
			System.out.println("\nOcorreu um erro ao tentar excluir o funcionário: "+entidade.getLogin()+"\n");
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
