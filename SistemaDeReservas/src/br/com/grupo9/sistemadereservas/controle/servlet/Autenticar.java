package br.com.grupo9.sistemadereservas.controle.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.grupo9.sistemadereservas.controle.Dominio.TipoUsuario;
import br.com.grupo9.sistemadereservas.controle.Util.JsonUtil;
import br.com.grupo9.sistemadereservas.controle.Util.SecurityUtil;
import br.com.grupo9.sistemadereservas.model.BO.ClienteBO;
import br.com.grupo9.sistemadereservas.model.BO.FuncionarioBO;
import br.com.grupo9.sistemadereservas.model.BO.UsuarioBO;
import br.com.grupo9.sistemadereservas.model.PO.UsuarioPO;

/**
 * Servlet implementation class Autenticacao
 */
@WebServlet("/autenticar.do")
public class Autenticar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioBO usuarioBO;
	private UsuarioPO usuarioCapturado;
	private ClienteBO clienteBO;
	private FuncionarioBO funcionarioBO;
    private HttpSession sessao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Autenticar() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter retorno = response.getWriter();
		getUsuarioBO().setUsuarioPO(JsonUtil.converterJsonEmUsuario(request));
		getUsuarioBO().getUsusarioPO().setSenha(SecurityUtil.getHash(getUsuarioBO().getUsusarioPO().getSenha()));
		System.out.println(getUsuarioBO().getUsusarioPO().getSenha());
		if(getUsuarioBO().capturarUsuarioValido() != null){
			setUsuarioCapturado(getUsuarioBO().capturarUsuarioValido());
			setSessao(request.getSession(true));
			if(getUsuarioCapturado() != null){
				if(getUsuarioCapturado().getSenha().equals(getUsuarioBO().getUsusarioPO().getSenha())){
					if(getUsuarioCapturado().getTipo() == TipoUsuario.CLIENTE.getCodigo()){
						getClienteBO().setUsuarioPO(getUsuarioCapturado());
						getClienteBO().comporCliente();
						getSessao().setAttribute("usuario", getClienteBO());
						getSessao().setAttribute("tipo", getUsuarioCapturado().getTipo());
						System.out.println("\n\n\n\nautenticou!\n\n\n\n");
					}else if(getUsuarioCapturado().getTipo() == TipoUsuario.FUNCIONARIO.getCodigo()){
						getFuncionarioBO().setUsuarioPO(getUsuarioCapturado());
						getFuncionarioBO().comporFuncionario();
						getSessao().setAttribute("usuario", getFuncionarioBO());
						getSessao().setAttribute("tipo", getUsuarioCapturado().getTipo());
					}
					retorno.println("{\"loginValido\":1,\"senhaValida\":1}");
				}else{
					retorno.println("{\"loginValido\":1,\"senhaValida\":0}");
				}
			}else{
				retorno.println("{\"loginValido\":0,\"senhaValida\":0}");
			}
		}else{
			retorno.println("{\"loginValido\":0,\"senhaValida\":0}");
		}
	}
	
	private UsuarioBO getUsuarioBO(){
		if(this.usuarioBO == null){
			this.usuarioBO = new UsuarioBO();
		}
		return this.usuarioBO;
	}
	
	private UsuarioPO getUsuarioCapturado(){
		return this.usuarioCapturado;
	}
	
	private void setUsuarioCapturado(UsuarioPO usuarioPO){
		this.usuarioCapturado = usuarioPO;
	}
	
	private void setSessao(HttpSession session){
		this.sessao = session;
	}
	private HttpSession getSessao(){
		return this.sessao;
	}

	public ClienteBO getClienteBO() {
		if(this.clienteBO == null){
			this.clienteBO = new ClienteBO();
		}
		return clienteBO;
	}

	public FuncionarioBO getFuncionarioBO() {
		if(this.funcionarioBO == null){
			this.funcionarioBO = new FuncionarioBO();
		}
		return funcionarioBO;
	}
}
