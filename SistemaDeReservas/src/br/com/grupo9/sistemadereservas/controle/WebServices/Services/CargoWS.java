package br.com.grupo9.sistemadereservas.controle.WebServices.Services;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import br.com.grupo9.sistemadereservas.model.BO.CargoBO;
import br.com.grupo9.sistemadereservas.model.PO.CargoPO;

@RequestScoped
@Path("/cargows")
@Produces("application/json")
@Consumes("application/json")
public class CargoWS {
	private CargoBO cargoBO;
	@POST
	@Path("/cadastrar/")
	public List<String> cadastrar(CargoPO cargoPO) {
		getCargoBO().setCargoPO(cargoPO);
		if(getCargoBO().cadastrar()){
			List<String> lista = new ArrayList<>();
			lista.add("sucess");
			return lista;
		}else{
			return getCargoBO().getMensagemErro();
		}
	}

	@GET
	@Path("/capturar/{nome}")
	public List<String> capturar(@PathParam("nome") final String nome) {
		getCargoBO().getCargoPO().setNome(nome);
		List<String> lista = new ArrayList<>();
		if(getCargoBO().capturarCargoPeloNome()){
			lista.add("sucess");
		}else{
			lista.add("error");
		}
		return lista;
	}

	@GET
	@Path("/listar/{pagina:[0-9]*}/{qtdRegistros:[0-9]*}")
	public List<CargoPO> listar(@PathParam("pagina") final Integer pagina, @PathParam("qtdRegistros") final Integer qtdRegistros) {
		return getCargoBO().listar(pagina,qtdRegistros);
	}
	
	@GET
	@Path("/listarTodos/")
	public List<CargoPO> listarTodos() {
		return getCargoBO().listarTodos();
	}

	@POST
	@Path("/alterar/")
	public List<String> alterar(CargoPO cargoPO) {
		getCargoBO().setCargoPO(cargoPO);
		if(getCargoBO().atualizar()){
			List<String> lista = new ArrayList<>();
			lista.add("sucess");
			return lista;
		}else{
			return getCargoBO().getMensagemErro();
		}
	}

	@GET
	@Path("/deletar/{chave:[0-9]*}")
	public List<String> deleteById(@PathParam("chave") Integer chave) {
		getCargoBO().getCargoPO().setChave(chave);
		if(getCargoBO().excluir()){
			List<String> lista = new ArrayList<>();
			lista.add("sucess");
			return lista;
		}else{
			return getCargoBO().getMensagemErro();
		}
	}
	
	private CargoBO getCargoBO(){
		if(this.cargoBO == null){
			this.cargoBO = new CargoBO();
		}
		return this.cargoBO;		
	}

}
