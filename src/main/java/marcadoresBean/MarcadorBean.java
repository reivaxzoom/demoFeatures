package marcadoresBean;

import javax.ejb.Remove;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.GET;
import javax.ws.rs.Path;	

@ManagedBean
@ApplicationScoped	
@Path("/marcador")
public class MarcadorBean {

	private Marcador mb;
	private String nombrep1;
	private String nombrep2;
		
	public MarcadorBean() {
		mb= Marcador.getInstance();
		
	}
	
	public String startGame(){
		mb.setP1Name(nombrep1);
		mb.setP2Name(nombrep2);
		System.out.println(nombrep1+" "+nombrep2);	
		return "success";
	}
	
	
	@GET
	@Path(value="/p1")
	public String scorePlayer1(){
		mb.scorePlayer1(); 
		return "score p1 "+mb.getP1Name()+" "+mb.getPlayer1Score();
	}
	
	@GET
	@Path(value="/p2")
	public String scorePlayer2(){
		mb.scorePlayer2();
		return "score p2 "+mb.getP2Name()+" "+mb.getPlayer2Score();
	}

	
	public String finish() {
		mb.limpiarMarcador();
		System.out.println("Cleaned");
		return "start";
	}
	
	public Marcador getMb() {
		return mb;
	}

	public void setMb(Marcador mb) {
		this.mb = mb;
	}

	public String getNombrep1() {
		return nombrep1;
	}

	public void setNombrep1(String nombrep1) {
		this.nombrep1 = nombrep1;
	}

	public String getNombrep2() {
		return nombrep2;
	}

	public void setNombrep2(String nombrep2) {
		this.nombrep2 = nombrep2;
	}
	
	
	
}
