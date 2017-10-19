package elte.ws.test;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import elte.ws.test.Persona;
import org.json.simple.JSONObject;


@Path("/servicioPersonas")
public class ServicioPersonas {

	@Path("/buscar")
	@GET
	@Produces("application/xml")
	public Persona recuperar(){
		Persona p;
		p= new Persona();
		p.setId(1);
		
		p.setNombre("Xavier");
		return p;
	}
	@Path("/buscar2")
	@GET
	@Produces("application/json")
	public Persona recuperar2(){
		Persona p;
		p= new Persona();
		p.setId(1);
		
		p.setNombre("Xavier");
		return p;
	}
	@Path("/buscar3")
	@GET
	@Produces("text/plain")
	public Persona recuperar3(){
		Persona p;
		p= new Persona();
		p.setId(1);
		
		p.setNombre("Xavier");
		return p;
	}
	@Path("/buscar4")
	@GET
	//El cliente decide como va a recibir el objeto con
	//el parametro en el header Accept y value el application/xml o
	//application/json
	@Produces({"application/xml", "application/json"})
	public Persona recuperar4(){
		Persona p;
		p= new Persona();
		p.setId(1);
		
		p.setNombre("Xavier");
		return p;
	}
	@Path("/buscar5")
	@GET
	@Produces("text/html")
	public Persona recuperar5(){
		Persona p;
		p= new Persona();
		p.setId(1);
		
		p.setNombre("Xavier");
		return p;
	}
	
	
	@Path("/insertar")
	@POST
	@Consumes("application/xml")
	public void insertar(Persona p){
		System.out.println("nombre application/xml "+p.getNombre());
	}
	@Path("/insertar2")
	@POST
	@Consumes("application/json")
	public void insertar2(Persona p){
		System.out.println("nombre application/json "+p.getNombre());
		
	}
	@Path("/insertar3")
	@POST
	//Cliente decide en el formato que envia el objeto
	@Consumes({"application/xml", "application/json"})
	public void insertar3(Persona p){
		System.out.println("nombre  "+p.getNombre());
	}
	
	@Path("/insertar4")
	  @POST
	  @Produces(MediaType.APPLICATION_JSON)
	  @Consumes(MediaType.APPLICATION_JSON)
	  public JSONObject sayPlainTextHello(JSONObject inputJsonObj) throws Exception {

		//sample send
		//{"name": "Diana","pass": "hello"}
		
		System.out.println(inputJsonObj.keySet());
	    String name = (String) inputJsonObj.get("name");
	    String pass = (String) inputJsonObj.get("pass");
	    JSONObject outputJsonObj = new JSONObject();
	    outputJsonObj.put("Saludo", "Hola demonia");
	    outputJsonObj.put("Parametros", name+" "+pass);
	    outputJsonObj.put("Estado", "Grunon");

	    return outputJsonObj;
	  }
	
	
//	@Path("/buscar6")
//	@GET
//	@Produces("text/html")
//	public Persona recuperar6(){
//		Persona p;
//		p= new Persona();
//		p.setId(1);
//		
//		p.setNombre("Xavier");
//		return p;
//	}
}
