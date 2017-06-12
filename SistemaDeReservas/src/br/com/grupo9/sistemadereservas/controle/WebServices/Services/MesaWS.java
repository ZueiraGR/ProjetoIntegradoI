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

import br.com.grupo9.sistemadereservas.model.BO.MesaBO;
import br.com.grupo9.sistemadereservas.model.PO.MesaPO;

@RequestScoped
@Path("/mesaws")
@Produces("application/json")
@Consumes("application/json")
public class MesaWS {
	
	private MesaBO mesaBO;

	@POST
	@Path("/cadastrar/")
	public List<String> create(final MesaPO mesa) {
		List<String> retorno = new ArrayList<>();
		getMesaBO().setMesaPO(mesa);		
		if(getMesaBO().cadastrar()){
			retorno.add("sucess");
		}else{
			retorno = getMesaBO().getMensagemErro();
		}
		return retorno;
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the mesapo 
		MesaPO mesapo = null;
		if (mesapo == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(mesapo).build();
	}

	@GET
	public List<MesaPO> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the mesapoes 
		final List<MesaPO> mesapoes = null;
		return mesapoes;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final MesaPO mesapo) {
		//TODO: process the given mesapo 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the mesapo matching by the given id 
		return Response.noContent().build();
	}
	
	private MesaBO getMesaBO(){
		if(this.mesaBO == null){
			this.mesaBO = new MesaBO();
		}
		return this.mesaBO;
	}

}
