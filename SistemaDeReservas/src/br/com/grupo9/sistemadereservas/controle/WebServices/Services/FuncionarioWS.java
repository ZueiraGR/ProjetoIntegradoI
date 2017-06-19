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
	public UsuarioPO capturarPorId(@PathParam("id") final Integer chave) {
		UsuarioPO usuarioPO = new UsuarioPO();
		FuncionarioPO funcionarioPO = new FuncionarioPO();
		funcionarioPO.setChave(chave);
		usuarioPO.setFuncionario(funcionarioPO);
		getFuncionarioBO().setUsuarioPO(usuarioPO);
		return getFuncionarioBO().capturar();
	}
	@GET
	@Path("/listar/{pagina:[0-9]*}/{registros:[0-9]*}")
	public List<FuncionarioPO> listAll(@PathParam("pagina") final int pagina,@PathParam("registros") final int qtdRegistros) {
		return getFuncionarioBO().listar(pagina,qtdRegistros);
	}

	@POST
	@Path("/atualizar/")
	public List<String> atualizar(final FuncionarioPO funcionarioPO) {
		List<String> retorno = new ArrayList<>();
		UsuarioPO usuarioPO = new UsuarioPO();
		usuarioPO.setFuncionario(funcionarioPO);
		if(getFuncionarioBO().altualizar()){
			retorno.add("sucess");
		}else{
		}
		return retorno;
	}

	@GET
	@Path("/excluir/{chave:[0-9]*}")
	public List<String> excluir(@PathParam("chave") Integer chave) {
		List<String> retorno = new ArrayList<>();
		UsuarioPO usuarioPO = new UsuarioPO();
		FuncionarioPO funcionarioPO = new FuncionarioPO();
		funcionarioPO.setChave(chave);
		usuarioPO.setFuncionario(funcionarioPO);
		getFuncionarioBO().setUsuarioPO(usuarioPO);
		if(getFuncionarioBO().excluir()){
			retorno.add("sucess");	
		}else{
			retorno.add("error");
		}
		return retorno;
	}
	
	private FuncionarioBO getFuncionarioBO(){
		if(this.funcionarioBO == null){
			this.funcionarioBO = new FuncionarioBO();
		}
		return this.funcionarioBO;
	}

}
