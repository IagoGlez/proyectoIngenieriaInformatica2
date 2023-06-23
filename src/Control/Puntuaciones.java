package Control;

public class Puntuaciones {
	String NombreUsuario;
	int Id_usuario;
	int Puntuacion;
	
	public Puntuaciones(String NombreUser,int Id_user, int score) {
		NombreUsuario = NombreUser;
		Id_usuario =Id_user;
		Puntuacion = score;
	}
	
	public String getNombreUsuario() {
		return NombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		NombreUsuario = nombreUsuario;
	}
	public int getId_usuario() {
		return Id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		Id_usuario = id_usuario;
	}
	public int getPuntuacion() {
		return Puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		Puntuacion = puntuacion;
	}
	
	
}
