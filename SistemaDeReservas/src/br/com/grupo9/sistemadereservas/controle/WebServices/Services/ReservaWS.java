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

import br.com.grupo9.sistemadereservas.model.PO.ReservaPO;

@RequestScoped
@Path("/reservaws")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ReservaWS {

	@POST
	public Response create(final ReservaPO reservapo) {
		//TODO: process the given reservapo 
		//you may want to use the following return statement, assuming that ReservaPO#getId() or a similar method 
		//would provide the identifier to retrieve the created ReservaPO resource:
		//return Response.created(UriBuilder.fromResource(ReservaWS.class).path(String.valueOf(reservapo.getId())).build()).build();
		return Response.created(null).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the reservapo 
		ReservaPO reservapo = null;
		if (reservapo == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(reservapo).build();
	}

	@GET
	public List<ReservaPO> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the reservapoes 
		final List<ReservaPO> reservapoes = null;
		return reservapoes;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final ReservaPO reservapo) {
		//TODO: process the given reservapo 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the reservapo matching by the given id 
		return Response.noContent().build();
	}

}
