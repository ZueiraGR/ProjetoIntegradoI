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

import br.com.grupo9.sistemadereservas.model.BO.PromocaoBO;
import br.com.grupo9.sistemadereservas.model.PO.PromocaoPO;

@RequestScoped
@Path("/promocaows")
@Produces("application/json")
@Consumes("application/json")
public class PromocaoWS {
	
	private PromocaoBO promocaoBO;
	
	@POST
	@Path("/cadastar/")
	public List<String> cadastrar(final PromocaoPO promocaopo) {
		List<String> retorno = new ArrayList<>();
		getPromocaoBO().setPromocaoPO(promocaopo);
		if(getPromocaoBO().cadastar()){
			retorno.add("sucess");
		}else{
			retorno = getPromocaoBO().getMensagensDeErro();
		}
		return retorno;
	}

	@GET
	@Path("/capturar/{chave:[0-9]*}")
	public PromocaoPO capturar(@PathParam("chave") final Integer chave) {
		PromocaoPO promocaoPO = new PromocaoPO();
		promocaoPO.setChave(chave);
		getPromocaoBO().setPromocaoPO(promocaoPO);
		return getPromocaoBO().capturar();
	}

	@GET
	@Path("/listar/{pagina:[0-9]}/{qtdRegistros:[0-9]}/{opcaoListagem}")
	public List<PromocaoPO> listAll(@PathParam("pagina") Integer pagina, @PathParam("qtdRegistros") Integer qtdRegistros ,@PathParam("opcaoListagem") String opcaoListagem) {
		if(opcaoListagem.equals("T")){
			return getPromocaoBO().listarTodas(pagina, qtdRegistros);
		}else{
			return getPromocaoBO().listarSomentAtivas();
		}
	}

	@POST
	@Path("/atualizar/")
	public List<String> update(final PromocaoPO promocaopo) {
		List<String> retorno = new ArrayList<>();
		getPromocaoBO().setPromocaoPO(promocaopo);
		if(getPromocaoBO().atualizar()){
			retorno.add("sucess");
		}else{
			retorno = getPromocaoBO().getMensagensDeErro();
		}
		return retorno;
	}

	@GET
	@Path("/excluir/{chave:[0-9]*}")
	public List<String> deleteById(@PathParam("chave") final Integer chave) {
		List<String> retorno = new ArrayList<>();
		PromocaoPO promocao = new PromocaoPO();
		promocao.setChave(chave);
		getPromocaoBO().setPromocaoPO(promocao);
		if(getPromocaoBO().excluir()){
			retorno.add("sucess");
		}else{
			retorno = getPromocaoBO().getMensagensDeErro();
		}
		return retorno;
	}
	
	private PromocaoBO getPromocaoBO(){
		if(this.promocaoBO == null){
			this.promocaoBO = new PromocaoBO();
		}
		return this.promocaoBO;
	}

}
