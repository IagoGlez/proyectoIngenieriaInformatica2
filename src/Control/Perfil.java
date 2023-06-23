package Control;

public class Perfil {
	String nombre = " ";
	int [][] partidas = new int[2][3];//0:Papel y tijera 1:Buscaminas 3:Tres en raya. [0]: numero de victorias.
	
	
	public Perfil ( String n) {
		nombre=n;
		
		
	}


	public String getNombre() {
		return nombre;
	}


	public int[][] getPartidas() {
		return partidas;
	}
	
}
