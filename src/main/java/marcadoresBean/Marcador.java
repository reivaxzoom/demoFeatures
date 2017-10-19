package marcadoresBean;

public class Marcador {
	
	private String p1Name;	
	private String p2Name;
	private int player1Score;
	private int player2Score;
	private  String state;
	
	private Marcador() {
		// TODO Auto-generated constructor stub
	}
	
	private static Marcador marcador;
	
	public static synchronized Marcador getInstance( ) {
	      if (marcador == null)
	    	  marcador=new Marcador();
	      return marcador;
	   }
	
	public void scorePlayer1(){
		player1Score = player1Score+1;
		updateState();
	}
	
	public void scorePlayer2(){
		player2Score=player2Score+1;
		updateState();
	}
	
		
	public void updateState(){
		if(player1Score==player2Score){
			setState("empates");
			return;
		}
		if(player1Score>player2Score){
			setState( "ventaja jugador "+p1Name);
		}else{
			setState( "ventaja jugador "+p2Name);
		}
	}
	
	public void limpiarMarcador(){
		p1Name="";
		p2Name="";
		player1Score=0;
		player2Score=0;
	}
	
	public String getP1Name() {
		return p1Name;
	}

	public void setP1Name(String p1Name) {
		this.p1Name = p1Name;
	}

	public String getP2Name() {
		return p2Name;
	}

	public void setP2Name(String p2Name) {
		this.p2Name = p2Name;
	}

	public int getPlayer1Score() {
		return player1Score;
	}

	public void setPlayer1Score(int player1Score) {
		this.player1Score = player1Score;
	}

	public int getPlayer2Score() {
		return player2Score;
	}

	public void setPlayer2Score(int player2Score) {
		this.player2Score = player2Score;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
