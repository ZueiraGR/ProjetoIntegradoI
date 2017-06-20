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

import br.com.grupo9.sistemadereservas.model.BO.ReservaBO;
import br.com.grupo9.sistemadereservas.model.PO.ReservaPO;

@RequestScoped
@Path("/reservaws")
@Produces("application/json")
@Consumes("application/json")
public class ReservaWS {
	
	private ReservaBO reservaBO;

	@POST
	@Path("/cadastrar/")
	public List<String> cadastrar(final ReservaPO reservapo) {
		getReservaBO().setReservaPO(reservapo);
		List<String> retorno = new ArrayList<>();
		if(getReservaBO().cadastrar()){
			retorno.add("sucess");
		}else{
			retorno.add("error");
		}
		return retorno;
	}

	@GET
	@Path("/capturar/{chave:[0-9]*}")
	public ReservaPO capturar(@PathParam("chave") final Integer chave) {
		ReservaPO reserva = new ReservaPO();
		reserva.setChave(chave);
		getReservaBO().setReservaPO(reserva);
		return getReservaBO().capturar();
	}

	@GET
	@Path("/listar/{pagina:[0-9]*}/{qtdRegistros:[0-9]*}")
	public List<ReservaPO> listar(@PathParam("pagina") Integer pagina, @PathParam("qtdRegistros") Integer qtdRegistros) {
		return getReservaBO().listar(pagina, qtdRegistros);
	}

	@PUT
	@Path("/atualizar/")
	public List<String> atualizar( final ReservaPO reservapo) {
		getReservaBO().setReservaPO(reservapo);
		List<String> retorno = new ArrayList<>();
		if(getReservaBO().atualizar()){
			retorno.add("sucess");
		}else{
			retorno.add("error");
		}
		return retorno;
	}

	@DELETE
	@Path("/excluir/{chave:[0-9]*}")
	public List<String> excluir(@PathParam("chave") final Integer chave) {
		ReservaPO reservapo = new ReservaPO();
		reservapo.setChave(chave);
		getReservaBO().setReservaPO(reservapo);
		List<String> retorno = new ArrayList<>();
		if(getReservaBO().excluir()){
			retorno.add("sucess");
		}else{
			retorno.add("error");
		}
		return retorno;
	}
	
	private ReservaBO getReservaBO(){
		if(this.reservaBO != null){
			this.reservaBO = new ReservaBO();
		}
		return this.reservaBO;
	}

}
