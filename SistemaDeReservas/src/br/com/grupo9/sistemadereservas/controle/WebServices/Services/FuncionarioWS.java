package br.com.grupo9.sistemadereservas.controle.WebServices.Services;

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
import javax.ws.rs.core.Response;

import br.com.grupo9.sistemadereservas.model.BO.FuncionarioBO;
import br.com.grupo9.sistemadereservas.model.PO.FuncionarioPO;

@RequestScoped
@Path("/funcionariows")
@Produces("application/json")
@Consumes("application/json")
public class FuncionarioWS {
	
	FuncionarioBO funcionarioBO;
	
	@POST
	public Response create(final FuncionarioPO funcionariopo) {
		//TODO: process the given funcionariopo 
		//you may want to use the following return statement, assuming that FuncionarioPO#getId() or a similar method 
		//would provide the identifier to retrieve the created FuncionarioPO resource:
		//return Response.created(UriBuilder.fromResource(FuncionarioWS.class).path(String.valueOf(funcionariopo.getId())).build()).build();
		return Response.created(null).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public FuncionarioPO findById(@PathParam("id") final Long id) {
		//TODO: retrieve the funcionariopo 
		FuncionarioPO funcionariopo = null;
		return funcionariopo;
	}
	@GET
	@Path("/listar/{pagina:[0-9]*}/{registros:[0-9]*}")
	public List<FuncionarioPO> listAll(@PathParam("pagina") final int pagina,@PathParam("registros") final int qtdRegistros) {
		return getFuncionarioBO().listar(pagina,qtdRegistros);
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final FuncionarioPO funcionariopo) {
		//TODO: process the given funcionariopo 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the funcionariopo matching by the given id 
		return Response.noContent().build();
	}
	
	private FuncionarioBO getFuncionarioBO(){
		if(this.funcionarioBO == null){
			this.funcionarioBO = new FuncionarioBO();
		}
		return this.funcionarioBO;
	}

}
