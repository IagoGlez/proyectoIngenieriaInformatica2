package Control;

import java.util.Comparator;

public class ComparadorP implements Comparator<Perfil>{
	int p= 0;

	public int compare(Perfil p1, Perfil p2) {
		return p2.partidas[0][p] - p1.partidas[0][p];
	}
	
	
	
	
}//Fin comparador1

