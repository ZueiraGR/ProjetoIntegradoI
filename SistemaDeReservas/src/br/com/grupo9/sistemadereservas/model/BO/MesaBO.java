package br.com.grupo9.sistemadereservas.model.BO;

import java.util.ArrayList;
import java.util.List;

import br.com.grupo9.sistemadereservas.model.DAO.MesaDAO;
import br.com.grupo9.sistemadereservas.model.PO.MesaPO;

public class MesaBO {
	
	private MesaDAO mesaDAO;
	private MesaPO mesaPO;
	private List<String> mensagemErro;
	
	public boolean cadastrar(){
		return getMesaDAO().cadastrar(getMesaPO());
	}
	
	public List<String> getMensagemErro(){
		this.mensagemErro = new ArrayList<>();
		this.mensagemErro.add("error");
		return this.mensagemErro;
	}
	
	private MesaPO getMesaPO() {
		if(this.mesaPO == null){
			this.mesaPO = new MesaPO();
		}
		return mesaPO;
	}
	public void setMesaPO(MesaPO mesaPO) {
		this.mesaPO = mesaPO;
	}
	private MesaDAO getMesaDAO() {
		if(this.mesaDAO == null){
			this.mesaDAO = new MesaDAO();
		}
		return mesaDAO;
	}

}
