package br.com.grupo9.sistemadereservas.model.BO;

import java.util.Calendar;
import java.util.List;

import br.com.grupo9.sistemadereservas.model.DAO.ReservaDAO;
import br.com.grupo9.sistemadereservas.model.PO.ReservaPO;

public class ReservaBO {
	
	private ReservaDAO reservaDAO;
	private ReservaPO reservaPO;
	
	public boolean cadastrar(){
		return getReservaDAO().cadastrar(getReservaPO());
	}
	
	public ReservaPO capturar(){
		return getReservaDAO().capturarPorId(getReservaPO());
	}
	
	public boolean atualizar(){
		ReservaPO reserva = getReservaDAO().capturarPorId(getReservaPO());
		if(reserva != null){
			return getReservaDAO().atualizar(getReservaPO());
		}else{
			return false;
		}
	}
	
	public boolean excluir(){
		ReservaPO reserva = getReservaDAO().capturarPorId(getReservaPO());
		if(reserva != null){
			reserva.setFim(Calendar.getInstance());
			return getReservaDAO().excluir(getReservaPO());
		}else{
			return false;
		}
	}
	
	public List<ReservaPO> listar(Integer pagina, Integer qtdRegistros){
		return getReservaDAO().listar(pagina, qtdRegistros, getFiltro());
	}
	
	public String getFiltro(){
		return "";
	}
	public ReservaPO getReservaPO() {
		return reservaPO;
	}
	public void setReservaPO(ReservaPO reservaPO) {
		this.reservaPO = reservaPO;
	}
	private ReservaDAO getReservaDAO() {
		if(this.reservaDAO == null){
			this.reservaDAO = new ReservaDAO();
		}
		return reservaDAO;
	}
	
	
}
