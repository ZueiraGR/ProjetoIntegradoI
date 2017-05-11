package br.com.grupo9.sistemadereservas.controle.WebServices;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.grupo9.sistemadereservas.controle.WebServices.Services.ReservaWS;
import br.com.grupo9.sistemadereservas.controle.WebServices.Services.UsuarioWS;



@ApplicationPath("ws")
public class ApplicationConfig extends Application{
	
	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> resources = new HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}
	
	private void addRestResourceClasses(Set<Class<?>> resources){
		resources.add(UsuarioWS.class);
		resources.add(ReservaWS.class);
	}
}
