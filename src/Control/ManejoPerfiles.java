package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Vector;

public class ManejoPerfiles {

	/*
	public void guardar(int id_Jugador) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\Documents\\Universidad\\Proy 1\\github1\\proyectoInform-tica2-main\\MiniRetroBDD.db");

		    System.out.println("Conexión exitosa a la base de datos SQLite.");
	            System.out.print("Conexion correcta guardarJugador");
	            // Realiza operaciones con la base de datos
	           
	            
	            String insertQuery = "UPDATE Usuarios SET ";

	            //Try para el INSERT jugadores.
	            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
	            	statement.setString(1, id_Jugador);
	            	statement.setString(2, nombre);
	                statement.executeUpdate();
	                System.out.println("Nombre insertado correctamente.");
	            }
	 
	            catch(SQLIntegrityConstraintViolationException a) {
	            	System.out.println("Nombre repetido Guardar Jugador");
	            }
	            catch (SQLException e) {
	                e.printStackTrace();
	            }
	            
	            connection.close();
		}
		 catch (SQLException e) {
	         e.printStackTrace();
	     }
		
	}*/
	
	public Vector<String>  crear(String nombre) {
		Connection connection = null;
		Vector<String> v = new Vector <>();
		ResultSet rs= null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:MiniRetroBBDD.db");

		    System.out.println("Conexión exitosa a la base de datos SQLite.");
	            System.out.print("Conexion correcta guardarJugador");
	            // Realiza operaciones con la base de datos
	           
	            String insertQuery1 = "SELECT MAX(id_Usuario) AS nuevaID FROM Usuarios";
	            String insertQuery2 = "INSERT INTO Usuarios (id_Usuario,Nombre) VALUES (?,?)";
	            String insertPuntuacion1 = "SELECT MAX(Id_Puntuacion) AS nuevaPuntuacion FROM Puntuaciones";
	            String insertPuntuacion2 = "INSERT INTO Puntuaciones (Id_Puntuacion,Id_Juego,Id_Usuario, Puntuacion) VALUES (?,?,?,?),(?,?,?,?),(?,?,?,?)";
	            int nuevoID = 0;
	            int idUsuarioMax = 0;
	            int idPuntuacionMax1 = 0;
	            int idPuntuacionMax2 = 0;
	            int idPuntuacionMax3 = 0;
	           
	            try (PreparedStatement statement = connection.prepareStatement(insertQuery1)) {
	                
	            	
	            	rs = statement.executeQuery(); 
	   			 	while(rs.next()) {
	   			 		idUsuarioMax = rs.getInt("nuevaID");
	   			 		idUsuarioMax = idUsuarioMax +1;
	   			 	}
	            }
	 
	            catch(SQLIntegrityConstraintViolationException a) {
	            	System.out.println("Nombre repetido Guardar Jugador");
	            }
	            connection.close();
	            Connection con = null;
	            con = DriverManager.getConnection("jdbc:sqlite:MiniRetroBBDD.db");
	            //Try para el INSERT jugadores.
	            try (PreparedStatement statement = con.prepareStatement(insertQuery2)) {
	            	statement.setString(1, String.valueOf(idUsuarioMax));
	            	statement.setString(2, nombre);
	                statement.executeUpdate();
	                System.out.println("Nombre insertado correctamente.");
	                v.add(String.valueOf(idUsuarioMax));
	                v.add(nombre);
	                
	            }
	            
	            catch(SQLIntegrityConstraintViolationException a) {
	            	System.out.println("Nombre repetido Guardar Jugador");
	            }
	           
	            con.close();
	            Connection con1 = null;
	            con1 = DriverManager.getConnection("jdbc:sqlite:MiniRetroBBDD.db");
	            try (PreparedStatement statement = con1.prepareStatement(insertPuntuacion1)) {
	            	 

	            	rs = statement.executeQuery(); 
	            	while(rs.next()) {
	            		idPuntuacionMax1 = rs.getInt("nuevaPuntuacion");
	            		idPuntuacionMax1 = idPuntuacionMax1 +1;
	            		idPuntuacionMax2 = idPuntuacionMax1 +1;
	            		idPuntuacionMax3 = idPuntuacionMax1 +2;
	            	
            	 	}
            	 }
	            	 
            	catch(SQLIntegrityConstraintViolationException a) {
            		System.out.println("Nombre repetido Guardar Jugador");
            	 }
	            con1.close();
	            Connection con2 = null;
	            con2 = DriverManager.getConnection("jdbc:sqlite:MiniRetroBBDD.db");
	            try (PreparedStatement statement = con2.prepareStatement(insertPuntuacion2)) {
	            	 
	            	statement.setInt(1, idPuntuacionMax1);
	            	statement.setString(2, "1");
	            	statement.setString(3, String.valueOf(idUsuarioMax));
	            	statement.setInt(4, 0);
	            	statement.setInt(5, idPuntuacionMax2);
	            	statement.setString(6, "2");
	            	statement.setString(7, String.valueOf(idUsuarioMax));
	            	statement.setInt(8, 0);
	            	statement.setInt(9, idPuntuacionMax3);
	            	statement.setString(10, "3");
	            	statement.setString(11, String.valueOf(idUsuarioMax));
	            	statement.setInt(12, 0);
	            	statement.executeUpdate(); 
	            	
            	 }
	            	 
            	catch(SQLIntegrityConstraintViolationException a) {
            		System.out.println("Nombre repetido Guardar Jugador");
            	 }
	            con2.close();
		}
		 catch (SQLException e) {
	         e.printStackTrace();
	     }
		return v;
		
	}
	
	public Vector<String> cargar(String idUsuario) {
		Connection connection = null;
		Vector<String> t = new Vector <>();
		
		try {
				connection = DriverManager.getConnection("jdbc:sqlite:MiniRetroBBDD.db");
	
			    System.out.println("Conexión exitosa a la base de datos SQLite.");
	            System.out.print("Conexion correcta guardarJugador");
	            // Realiza operaciones con la base de datos
	            int Id_Usuario = 0;
	            String Nombre = "";
	            
	            String insertQuery = "SELECT Id_Usuario,Nombre FROM Usuarios WHERE Id_Usuario = (?)";

	            //Try para el SELECT de jugadores.Quiero comprobar si el nombre existe en la base de datos.
	            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
	                statement.setString(1, idUsuario);
	                ResultSet r =statement.executeQuery();
	                String resultado = "";
	                while(r.next()) {
	                	Id_Usuario = r.getInt("Id_Usuario");
	                	Nombre = r.getString("Nombre");	                	
                	}
	                
	                t.add(String.valueOf(Id_Usuario));
	                t.add(Nombre);
	                return t;
	                /*if(resultado.equals(idUsuario)) {
	                	 System.out.println("Nombre encontrado."+ Id_Usuario+ " "+ Nombre);
	                	 t.add(idUsuario);
	                	 t.add(resultado);
	                	
	                	 return t;
	                }
	                else {
	                	 return t;
	                }*/
	            }
	 
	            
	            catch (SQLException e) {
	                e.printStackTrace();
	            }
	            connection.close();
	            
		
		}
		 catch (SQLException e) {
	         e.printStackTrace();
	     }
		return t;
		
	}//Fin cargar



}//Fin clase
