package br.com.grupo9.sistemadereservas.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.grupo9.sistemadereservas.interfaces.DAO;
import br.com.grupo9.sistemadereservas.model.PO.ReservaPO;
import br.com.grupo9.sistemadereservas.model.Util.PersistenceUtil;

public class ReservaDAO implements DAO<ReservaPO>{
	
	private EntityManager manager;
	
	@Override
	public boolean cadastrar(ReservaPO entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ReservaPO capturarPorId(ReservaPO entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean atualizar(ReservaPO entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ReservaPO> listar(Integer pagina, Integer qtdRegistros, String filtro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean excluir(ReservaPO entidade) {
		// TODO Auto-generated method stub
		return false;
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
