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
import br.com.grupo9.sistemadereservas.model.BO.MesaBO;
import br.com.grupo9.sistemadereservas.model.PO.MesaPO;

@RequestScoped
@Path("/mesaws")
@Produces("application/json")
@Consumes("application/json")
public class MesaWS {
	
	private MesaBO mesaBO;

	@POST
	@Path("/cadastrar")
	public List<String> create(final MesaPO mesa){
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
	@Path("/capturar/{chave:[0-9]*}")
	public MesaPO findById(@PathParam("chave") final Integer chave) {
		MesaPO mesaPO = new MesaPO();
		mesaPO.setChave(chave);
		getMesaBO().setMesaPO(mesaPO);
		return getMesaBO().capturar();
	}

	@GET
	@Path("/listar/{pagina:[0-9]*}/{registros:[0-9]*}")
	public List<MesaPO> listar(@PathParam("pagina") final int pagina,@PathParam("registros") final int qtdRegistros) {
		return getMesaBO().listar(pagina, qtdRegistros);
	}
	
	@GET
	@Path("/listarTodas/")
	public List<MesaPO> listarTodas() {
		return getMesaBO().listar();
	}

	@POST
	@Path("/atualizar/")
	public List<String> update(final MesaPO mesaPO) {
		getMesaBO().setMesaPO(mesaPO);
		getMesaBO().atualizar();
		//TODO IMPLEMENTAR MENSAGENS DE ERRO
		return null;
	}

	@GET
	@Path("/excluir/{chave:[0-9]*}")
	public List<String> excluir(@PathParam("chave") final Integer chave) {
		MesaPO mesaPO = new MesaPO();
		mesaPO.setChave(chave);
		getMesaBO().setMesaPO(mesaPO);
		getMesaBO().excluir();
		//TODO IMPLEMENTAR MENSAGENS DE ERRO
		return null;
	}
	
	private MesaBO getMesaBO(){
		if(this.mesaBO == null){
			this.mesaBO = new MesaBO();
		}
		return this.mesaBO;
	}

}
