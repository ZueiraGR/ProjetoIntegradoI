package br.com.grupo9.sistemadereservas.model.BO;

import br.com.grupo9.sistemadereservas.model.DAO.CargoDAO;
import br.com.grupo9.sistemadereservas.model.PO.CargoPO;

public class CargoBO {
	private CargoPO cargoPO;
	private CargoDAO cargoDAO;
	
	public CargoPO getCargoPO() {
		if(this.cargoPO == null){
			this.cargoPO = new CargoPO();
		}
		return cargoPO;
	}
	public void setCargoPO(CargoPO cargoPO) {
		this.cargoPO = cargoPO;
	}
	public CargoDAO getCargoDAO() {
		if(this.cargoDAO == null){
			this.cargoDAO = new CargoDAO();
		}
		return cargoDAO;
	}
	
}
