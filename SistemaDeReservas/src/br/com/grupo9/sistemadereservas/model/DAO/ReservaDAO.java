package br.com.grupo9.sistemadereservas.model.DAO;

import java.util.List;

import br.com.grupo9.sistemadereservas.interfaces.DAO;
import br.com.grupo9.sistemadereservas.model.PO.ReservaPO;

public class ReservaDAO implements DAO<ReservaPO>{

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
	public List<ReservaPO> listar(Integer pagina, Integer qtdRegistros) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean excluir(ReservaPO entidade) {
		// TODO Auto-generated method stub
		return false;
	}

}
