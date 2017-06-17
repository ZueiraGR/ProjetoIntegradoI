package br.com.grupo9.sistemadereservas.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.grupo9.sistemadereservas.interfaces.DAO;
import br.com.grupo9.sistemadereservas.model.PO.ParametrosAtendimentoPO;
import br.com.grupo9.sistemadereservas.model.Util.PersistenceUtil;

public class ParametrosAtendimentoDAO implements DAO<ParametrosAtendimentoPO> {
	
	private EntityManager manager;
	
	@Override
	public boolean cadastrar(ParametrosAtendimentoPO entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ParametrosAtendimentoPO capturarPorId(ParametrosAtendimentoPO entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean atualizar(ParametrosAtendimentoPO entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ParametrosAtendimentoPO> listar(Integer pagina, Integer qtdRegistros, String filtro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean excluir(ParametrosAtendimentoPO entidade) {
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
