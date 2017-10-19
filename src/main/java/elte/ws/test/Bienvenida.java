package elte.ws.test;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

//import net.webservicex.CurrencyConvertor;
//import net.webservicex.CurrencyConvertorSoap;

@Path(value="/bienvenida")
public class Bienvenida {
	@GET
	@Path(value="/saludar")
	public String saludar() {
		return "Bienvenido al curso get";

	}
	
	@PUT
	@Path(value="/m1")
	public String saludar2() {
		return "Bienvenido al curso put";

	}
	@POST
	@Path(value="/m2")
	public String saludar3() {
		return "Bienvenido al curso post";
		
	}

	
//	@GET
//	@POST
////	@Path(value ="/convertirDaE/{valor}")
//	@Path(value ="/convertirDaE")
//	public double convertirDolaresEuros(
//			@QueryParam(value="valor")double dolares){
//		
//		
//CurrencyConvertor curr= new CurrencyConvertor();
//		
//		CurrencyConvertorSoap ser= curr.getCurrencyConvertorSoap();
//
//		
//		double eurDol =  ser.conversionRate(net.webservicex.Currency.USD,net.webservicex.Currency.EUR );
//		
////		System.out.print("1 "+net.webservicex.Currency.EUR.name()+" es "+eurDol+" "+net.webservicex.Currency.USD.name()+"\n");
//		
//		return dolares *eurDol;
//	}
	
	
}
