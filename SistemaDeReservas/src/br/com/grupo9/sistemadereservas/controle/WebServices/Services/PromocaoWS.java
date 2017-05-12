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

import br.com.grupo9.sistemadereservas.model.PO.PromocaoPO;

@RequestScoped
@Path("/promocaows")
@Produces("application/json")
@Consumes("application/json")
public class PromocaoWS {

	@POST
	public Response create(final PromocaoPO promocaopo) {
		//TODO: process the given promocaopo 
		//you may want to use the following return statement, assuming that PromocaoPO#getId() or a similar method 
		//would provide the identifier to retrieve the created PromocaoPO resource:
		//return Response.created(UriBuilder.fromResource(PromocaoWS.class).path(String.valueOf(promocaopo.getId())).build()).build();
		return Response.created(null).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the promocaopo 
		PromocaoPO promocaopo = null;
		if (promocaopo == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(promocaopo).build();
	}

	@GET
	public List<PromocaoPO> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the promocaopoes 
		final List<PromocaoPO> promocaopoes = null;
		return promocaopoes;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final PromocaoPO promocaopo) {
		//TODO: process the given promocaopo 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the promocaopo matching by the given id 
		return Response.noContent().build();
	}

}
