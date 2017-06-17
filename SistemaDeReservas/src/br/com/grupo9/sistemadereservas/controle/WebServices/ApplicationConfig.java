package br.com.grupo9.sistemadereservas.controle.WebServices;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.grupo9.sistemadereservas.controle.WebServices.Services.CargoWS;
import br.com.grupo9.sistemadereservas.controle.WebServices.Services.ClienteWS;
import br.com.grupo9.sistemadereservas.controle.WebServices.Services.FuncionarioWS;
import br.com.grupo9.sistemadereservas.controle.WebServices.Services.MesaWS;
import br.com.grupo9.sistemadereservas.controle.WebServices.Services.ParametrosAtendimentoWS;
import br.com.grupo9.sistemadereservas.controle.WebServices.Services.PromocaoWS;
import br.com.grupo9.sistemadereservas.controle.WebServices.Services.ReservaWS;



@ApplicationPath("ws")
public class ApplicationConfig extends Application{
	
	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> resources = new HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}
	
	private void addRestResourceClasses(Set<Class<?>> resources){
		resources.add(CargoWS.class);
		resources.add(ClienteWS.class);
		resources.add(FuncionarioWS.class);
		resources.add(MesaWS.class);
		resources.add(ParametrosAtendimentoWS.class);
		resources.add(PromocaoWS.class);
		resources.add(ReservaWS.class);
	}
}
