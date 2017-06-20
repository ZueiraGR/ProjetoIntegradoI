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
import br.com.grupo9.sistemadereservas.model.BO.ClienteBO;
import br.com.grupo9.sistemadereservas.model.PO.ClientePO;
import br.com.grupo9.sistemadereservas.model.PO.FuncionarioPO;
import br.com.grupo9.sistemadereservas.model.PO.UsuarioPO;

@RequestScoped
@Path("/clientews")
@Produces("application/json")
@Consumes("application/json")
public class ClienteWS {
	
	ClienteBO clienteBO;
	
	@POST
	@Path("/cadastrar/")
	public List<String> create(final UsuarioPO usuario) {
		List<String> retorno = new ArrayList<>();
		getClienteBO().setUsuarioPO(usuario);
		getClienteBO().getUsuarioPO().setSenha(SecurityUtil.getHash(getClienteBO().getUsuarioPO().getSenha()));		
		if(getClienteBO().cadastrar()){
			retorno.add("sucess");
		}else{
			retorno = getClienteBO().getMensagemErro();
		}
		return retorno;
	}

	@GET
	@Path("/capturar/{login}")
	public UsuarioPO findById(@PathParam("login") final String login) {
		getClienteBO().getUsuarioPO().setLogin(login);
		UsuarioPO usuario = getClienteBO().capturarUsuarioValido();
		if(usuario != null){
			return usuario;
		}else{
			return null;
		}
	}

	@GET
	@Path("/listar/{pagina:[0-9]*}/{registros:[0-9]*}")
	public List<ClientePO> listAll(@PathParam("pagina") final int pagina,@PathParam("registros") final int qtdRegistros) {
		return getClienteBO().listar(pagina,qtdRegistros);
	}

	@POST
	@Path("/alterar/")
	public List<String> atualizar(final ClientePO cliente) {
		List<String> retorno = new ArrayList<>();
		UsuarioPO usuarioPO = new UsuarioPO();
		usuarioPO.setCliente(cliente);
		getClienteBO().setUsuarioPO(usuarioPO);
		if(getClienteBO().altualizar()){
			retorno.add("sucess");
		}else{
			retorno.add("fail");
		}
		return retorno;
	}

	@GET
	@Path("/excluir/{chave:[0-9]*}")
	public List<String> excluir(@PathParam("chave") Integer chave) {
		List<String> retorno = new ArrayList<>();
		UsuarioPO usuarioPO = new UsuarioPO();
		ClientePO clientePO = new ClientePO();
		clientePO.setChave(chave);
		usuarioPO.setCliente(clientePO);
		getClienteBO().setUsuarioPO(usuarioPO);
		if(getClienteBO().excluir()){
			retorno.add("sucess");	
		}else{
			retorno.add("error");
		}
		return retorno;
	}
	
	@GET
	@Path("/verificaSeExiste/{login}")
	public List<String> isExisteLoginCadastrado(@PathParam("login") final String login){
		List<String> retorno = new ArrayList<>();
		getClienteBO().getUsuarioPO().setLogin("%"+login+"%");
		if(getClienteBO().isUsuarioJaExiste()){
			retorno.add("true");
		}else{
			retorno.add("false");
		}
		return retorno;
	}
	
	private ClienteBO getClienteBO(){
		if(this.clienteBO == null){
			this.clienteBO = new ClienteBO();
		}
		return this.clienteBO;
	}

}
