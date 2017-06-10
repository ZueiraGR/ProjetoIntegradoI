package br.com.grupo9.sistemadereservas.model.BO;

import br.com.grupo9.sistemadereservas.model.DAO.MesaDAO;
import br.com.grupo9.sistemadereservas.model.PO.MesaPO;

public class MesaBO {
	
	private MesaDAO mesaDAO;
	private MesaPO mesaPO;
	
	public boolean cadastrar(){
		return getMesaDAO().cadastrar(getMesaPO());
	}

	private MesaPO getMesaPO() {
		return mesaPO;
	}
	public void setMesaPO(MesaPO mesaPO) {
		this.mesaPO = mesaPO;
	}
	private MesaDAO getMesaDAO() {
		return mesaDAO;
	}

}
