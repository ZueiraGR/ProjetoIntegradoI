package br.com.grupo9.sistemadereservas.teste;

/**
 * Testando o Hibernate 4.1.5.SP1
 * @author Marcos
 */
import br.com.grupo9.sistemadereservas.model.Usuario;
import br.com.grupo9.sistemadereservas.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class Teste {
    public static void main(String[] args) {
        Session sessao = HibernateUtil.getSession();
        
        List<Usuario> usuarios = sessao.createCriteria(Usuario.class).list();
        
        for(Usuario usuario : usuarios){
            System.out.println(usuario.getLogin()+" - "+usuario.getTipo()+" - "+usuario.getStatus()+" - "+usuario.getDataCriacao().toString());
        }
        
        sessao.close();
    }
}
