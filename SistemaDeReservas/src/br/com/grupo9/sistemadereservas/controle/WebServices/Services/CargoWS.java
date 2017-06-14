package br.com.grupo9.sistemadereservas.controle.WebServices.Services;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import br.com.grupo9.sistemadereservas.model.BO.CargoBO;
import br.com.grupo9.sistemadereservas.model.PO.CargoPO;

@RequestScoped
@Path("/cargows")
@Produces("application/json")
@Consumes("application/json")
public class CargoWS {
	private CargoBO cargoBO;
	@POST
	@Path("/cadastrar/")
	public List<String> cadastrar(CargoPO cargoPO) {
		getCargoBO().setCargoPO(cargoPO);
		if(getCargoBO().cadastrar()){
			List<String> lista = new ArrayList<>();
			lista.add("sucess");
			return lista;
		}else{
			return getCargoBO().getMensagemErro();
		}
	}

	@GET
	@Path("/capturar/{id:[0-9]*}")
	public CargoPO capturar(@PathParam("id") final Long id) {
		return  getCargoBO().capturar();
	}

	@GET
	@Path("/listar/{pagina:[0-9]*}/{qtdRegistros:[0-9]*}")
	public List<CargoPO> listar(@PathParam("pagina") final Integer pagina, @PathParam("qtdRegistros") final Integer qtdRegistros) {
		return getCargoBO().listar(pagina,qtdRegistros);
	}
	
	@GET
	@Path("/listarTodos/")
	public List<CargoPO> listarTodos() {
		return getCargoBO().listarTodos();
	}

	@POST
	@Path("/alterar/")
	public String alterar(CargoPO cargoPO) {
		getCargoBO().setCargoPO(cargoPO);
		if(getCargoBO().atualizar()){
			return "sucess";
		}else{
			return "error";
		}
	}

	@GET
	@Path("/deletar/{id:[0-9]*}")
	public String deleteById(@PathParam("id") Integer id) {
		getCargoBO().getCargoPO().setChave(id);
		if(getCargoBO().excluir()){
			return "sucess";
		}else{
			return "error";
		}
	}
	
	private CargoBO getCargoBO(){
		if(this.cargoBO == null){
			this.cargoBO = new CargoBO();
		}
		return this.cargoBO;		
	}

}
