package br.com.grupo9.sistemadereservas.controle.WebServices.Services;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.grupo9.sistemadereservas.model.BO.Configuracoes;
import br.com.grupo9.sistemadereservas.model.BO.ParametrosAtendimentoBO;

@RequestScoped
@Path("/parametrosws")
@Produces("application/json")
@Consumes("application/json")
public class ParametrosAtendimentoWS {
		
	@GET
	@Path("/capturar/")
	public Configuracoes capturarConfiguracoes() {
		return ParametrosAtendimentoBO.getConfig();
	}


	@POST
	@Path("/atualizar/")
	public List<String> atualizarConfiguracoes(final Configuracoes configuracoes) {
		ParametrosAtendimentoBO.setConfig(configuracoes);
		List<String> retorno = new ArrayList<>();
		if(ParametrosAtendimentoBO.salvarConfiguracoes()){
			retorno.add("sucess");
		}else{
			retorno.add("error");
		}
		return retorno;
	}
	

}
