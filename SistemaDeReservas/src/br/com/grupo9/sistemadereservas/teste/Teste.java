package br.com.grupo9.sistemadereservas.teste;

//import java.util.Calendar;
//import java.util.GregorianCalendar;
import java.util.List;

import br.com.grupo9.sistemadereservas.model.DAO.UsuarioDAO;
import br.com.grupo9.sistemadereservas.model.PO.UsuarioPO;

public class Teste {
    public static void main(String[] args) {
    	/*CADASTRANDO USUARIO*/
//    	Calendar data = new GregorianCalendar();
//    	UsuarioPO usuarioPO = new UsuarioPO();
//    	usuarioPO.setLogin("marcos.teste7");
//    	usuarioPO.setSenha("123456");
//    	usuarioPO.setStatus('A');
//    	usuarioPO.setTipo('C');
//    	usuarioPO.setDataCriacao(data);
//    	
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
//    	UsuarioPO usuarioCadastrado = usuarioDAO.cadastrarUsuario(usuarioPO);
//    	/*FIM CADASTRANDO USUARIO*/
//    	
//    	System.out.println("login: " + usuarioCadastrado.getLogin()+"\n\n");
//    	
//    	/*LISTANDO USUARIOS*/
    	List<UsuarioPO> lista = usuarioDAO.getUsuarios();
    	
    	for(UsuarioPO usuario : lista){
    		System.out.println(usuario.getLogin()+" - "+usuario.getSenha()+" - "+usuario.getStatus()+" - "+usuario.getTipo()+" - "+usuario.getDataCriacao().getTimeInMillis()+"\n");
    	}
    	/*FIM LISTANDO USUARIOS*/
    }
}
