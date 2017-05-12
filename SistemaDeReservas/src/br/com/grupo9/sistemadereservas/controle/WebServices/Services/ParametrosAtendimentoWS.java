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

import br.com.grupo9.sistemadereservas.model.PO.ParametrosAtendimentoPO;

@RequestScoped
@Path("/parametrosws")
@Produces("application/json")
@Consumes("application/json")
public class ParametrosAtendimentoWS {

	@POST
	public Response create(final ParametrosAtendimentoPO parametrosatendimentopo) {
		//TODO: process the given parametrosatendimentopo 
		//you may want to use the following return statement, assuming that ParametrosAtendimentoPO#getId() or a similar method 
		//would provide the identifier to retrieve the created ParametrosAtendimentoPO resource:
		//return Response.created(UriBuilder.fromResource(ParametrosAtendimentoWS.class).path(String.valueOf(parametrosatendimentopo.getId())).build()).build();
		return Response.created(null).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the parametrosatendimentopo 
		ParametrosAtendimentoPO parametrosatendimentopo = null;
		if (parametrosatendimentopo == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(parametrosatendimentopo).build();
	}

	@GET
	public List<ParametrosAtendimentoPO> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the parametrosatendimentopoes 
		final List<ParametrosAtendimentoPO> parametrosatendimentopoes = null;
		return parametrosatendimentopoes;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final ParametrosAtendimentoPO parametrosatendimentopo) {
		//TODO: process the given parametrosatendimentopo 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the parametrosatendimentopo matching by the given id 
		return Response.noContent().build();
	}

}
