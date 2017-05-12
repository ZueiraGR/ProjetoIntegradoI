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

import br.com.grupo9.sistemadereservas.model.PO.CargoPO;

@RequestScoped
@Path("/cargows")
@Produces("application/json")
@Consumes("application/json")
public class CargoWS {

	@POST
	public Response create(final CargoPO cargopo) {
		//TODO: process the given cargopo 
		//you may want to use the following return statement, assuming that CargoPO#getId() or a similar method 
		//would provide the identifier to retrieve the created CargoPO resource:
		//return Response.created(UriBuilder.fromResource(CargoPOEndpoint.class).path(String.valueOf(cargopo.getId())).build()).build();
		return Response.created(null).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the cargopo 
		CargoPO cargopo = null;
		if (cargopo == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(cargopo).build();
	}

	@GET
	public List<CargoPO> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the cargopoes 
		final List<CargoPO> cargopoes = null;
		return cargopoes;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final CargoPO cargopo) {
		//TODO: process the given cargopo 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the cargopo matching by the given id 
		return Response.noContent().build();
	}

}
