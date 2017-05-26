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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.grupo9.sistemadereservas.model.BO.CargoBO;
import br.com.grupo9.sistemadereservas.model.PO.CargoPO;

@RequestScoped
@Path("/cargows")
@Produces("application/json")
@Consumes("application/json")
public class CargoWS {
	private CargoBO cargoBO;
	@POST
	@Path("/cadastrar")
	public ArrayList<String> cadastrar(final CargoPO cargoPO) {
		getCargoBO().setCargoPO(cargoPO);
		if(getCargoBO().cadastrar()){
			return new ArrayList<>();
		}else{
			return getCargoBO().getMensagemErro();
		}
		
		//TODO testar a funcionalidade
	}

	@GET
	@Path("/capturar/{id:[0-9]*}")
	public CargoPO capturar(@PathParam("id") final Long id) {
		return  null;
	}

	@GET
	@Path("/listar/{pagina:[0-9]*}/{qtdRegistros:[0-9]*}")
	public List<CargoPO> listar(@PathParam("pagina") final Integer pagina, @PathParam("qtdRegistros") final Integer qtdRegistros) {
		return getCargoBO().listar(pagina,qtdRegistros);
	}

	@PUT
	@Path("/update/{id:[0-9]*}")
	public Response update(@PathParam("id") Long id, final CargoPO cargopo) {
		//TODO: process the given cargopo 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/deletar/{id:[0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the cargopo matching by the given id 
		return Response.noContent().build();
	}
	
	private CargoBO getCargoBO(){
		if(this.cargoBO == null){
			this.cargoBO = new CargoBO();
		}
		return this.cargoBO;		
	}

}
