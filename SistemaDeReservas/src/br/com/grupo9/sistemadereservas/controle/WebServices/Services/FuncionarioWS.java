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
import javax.ws.rs.core.Response;

import br.com.grupo9.sistemadereservas.controle.Util.SecurityUtil;
import br.com.grupo9.sistemadereservas.model.BO.FuncionarioBO;
import br.com.grupo9.sistemadereservas.model.PO.FuncionarioPO;
import br.com.grupo9.sistemadereservas.model.PO.UsuarioPO;

@RequestScoped
@Path("/funcionariows")
@Produces("application/json")
@Consumes("application/json")
public class FuncionarioWS {
	
	FuncionarioBO funcionarioBO;
	UsuarioPO usuarioPO;
	
	@POST
	@Path("/cadastrar/")
	public List<String> cadastrar(final UsuarioPO usuario) {
		List<String> retorno = new ArrayList<>();
		getFuncionarioBO().setUsuarioPO(usuario);
		getFuncionarioBO().getUsuarioPO().setSenha(SecurityUtil.getHash(getFuncionarioBO().getUsuarioPO().getSenha()));		
		if(getFuncionarioBO().cadastrar()){
			retorno.add("sucess");
		}else{
			retorno = getFuncionarioBO().getMensagemErro();
		}
		return retorno;
	}

	@GET
	@Path("/capturar/{id:[0-9]*}")
	public FuncionarioPO findById(@PathParam("id") final Integer chave) {
		return null;
	}
	@GET
	@Path("/listar/{pagina:[0-9]*}/{registros:[0-9]*}")
	public List<FuncionarioPO> listAll(@PathParam("pagina") final int pagina,@PathParam("registros") final int qtdRegistros) {
		return getFuncionarioBO().listar(pagina,qtdRegistros);
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final FuncionarioPO funcionariopo) {
		//TODO: process the given funcionariopo 
		return Response.noContent().build();
	}

	@POST
	@Path("/excluir/{id:[0-9][0-9]*}")
	public List<String> excluir(@PathParam("id") final Integer id) {
		UsuarioPO usuarioPO = new UsuarioPO();
		usuarioPO.getFuncionario().setChave(id);
		getFuncionarioBO().setUsuarioPO(usuarioPO);
		getFuncionarioBO().deletar();
		return null;
	}
	
	private FuncionarioBO getFuncionarioBO(){
		if(this.funcionarioBO == null){
			this.funcionarioBO = new FuncionarioBO();
		}
		return this.funcionarioBO;
	}
	private UsuarioPO getUsuarioPO(){
		if(this.usuarioPO == null){
			this.usuarioPO = new UsuarioPO();
		}
		return this.usuarioPO;
	}

}
