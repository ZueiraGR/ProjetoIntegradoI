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

import br.com.grupo9.sistemadereservas.model.DAO.UsuarioDAO;
import br.com.grupo9.sistemadereservas.model.PO.UsuarioPO;

@RequestScoped
@Path("/usuariows")
@Produces("application/json")
@Consumes("application/json")
public class UsuarioWS {

	@POST
	@Path("/cadastrar")
	public Response create(final UsuarioPO usuariopo) {
		//TODO: process the given usuariopo 
		//you may want to use the following return statement, assuming that UsuarioPO#getId() or a similar method 
		//would provide the identifier to retrieve the created UsuarioPO resource:
		//return Response.created(UriBuilder.fromResource(UsuarioWS.class).path(String.valueOf(usuariopo.getId())).build()).build();
		return Response.created(null).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		UsuarioPO usuariopo = null;
		if (usuariopo == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(usuariopo).build();
	}

	@GET
	@Path("/listar/{pagina:[0-9]*}/{qtdRegistros:[0-9]*}")
	public List<UsuarioPO> listAll(@PathParam("pagina") final Integer pagina, @PathParam("qtdRegistros") final Integer qtdRegistros) {
		final List<UsuarioPO> usuariopoes = new UsuarioDAO().listar(pagina,qtdRegistros);
		return usuariopoes;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final UsuarioPO usuariopo) {
		//TODO: process the given usuariopo 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the usuariopo matching by the given id 
		return Response.noContent().build();
	}

}
