package br.com.grupo9.sistemadereservas.controle.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.grupo9.sistemadereservas.controle.Dominio.StatusUsuario;
import br.com.grupo9.sistemadereservas.controle.Util.JsonUtil;
import br.com.grupo9.sistemadereservas.controle.Util.SecurityUtil;
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
		getUsuarioBO().getUsuarioPO().setSenha(SecurityUtil.getHash(getUsuarioBO().getUsuarioPO().getSenha()));
		System.out.println(getUsuarioBO().getUsuarioPO().getSenha());
		setUsuarioCapturado(getUsuarioBO().capturarUsuarioValido());
		if(getUsuarioCapturado() != null){
			setSessao(request.getSession());
			if(getUsuarioCapturado() != null){
				if(getUsuarioCapturado().getSenha().equals(getUsuarioBO().getUsuarioPO().getSenha())){
					getSessao().setAttribute("usuario", getUsuarioCapturado());
					retorno.println("{\"loginValido\":1,\"senhaValida\":1}");
				}else{
					if(getSessao().getAttribute("qtdTentativas") == null){
						getSessao().setAttribute("qtdTentativas", getUsuarioCapturado());
						retorno.println("{\"loginValido\":1,\"senhaValida\":0,\"loginInativou\":0}");
					}else{
						int qtdErro = Integer.parseInt((String) getSessao().getAttribute("qtdTentativas"))+1;
						if(qtdErro >4){
							getUsuarioCapturado().setDataInativacao(Calendar.getInstance());
							getUsuarioCapturado().setStatus(StatusUsuario.INATIVO.getCodigo());
							if(getUsuarioCapturado().getCliente() !=null){
								getUsuarioCapturado().getCliente().setStatus(StatusUsuario.INATIVO.getCodigo());
							}else if(getUsuarioCapturado().getFuncionario() !=null){
								getUsuarioCapturado().getFuncionario().setStatus(StatusUsuario.INATIVO.getCodigo());
							}
							getUsuarioBO().setUsuarioPO(getUsuarioCapturado());
							getUsuarioBO().atualizar();
							retorno.println("{\"loginValido\":1,\"senhaValida\":0,\"loginInativou\":1}");
						}else{
							getSessao().setAttribute("qtdTentativas", qtdErro);
							retorno.println("{\"loginValido\":1,\"senhaValida\":0,\"loginInativou\":0}");
						}
					}
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
}
