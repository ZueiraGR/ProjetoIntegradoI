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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.grupo9.sistemadereservas.model.PO.FuncionarioPO;

@RequestScoped
@Path("/funcionariows")
@Produces("application/json")
@Consumes("application/json")
public class FuncionarioWS {

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
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the funcionariopo 
		FuncionarioPO funcionariopo = null;
		if (funcionariopo == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(funcionariopo).build();
	}

	@GET
	public List<FuncionarioPO> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the funcionariopoes 
		final List<FuncionarioPO> funcionariopoes = null;
		return funcionariopoes;
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

}
