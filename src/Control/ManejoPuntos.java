package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ManejoPuntos {
	
	public int conseguirID(String idUsuario,String idJuego) {
		int id =0;
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:MiniRetroBBDD.db");

		    System.out.println("Conexión exitosa a la base de datos SQLite.");
	          System.out.print("Conexion correcta tienen");
	          
	          String Query = "SELECT id_Puntuacion FROM Puntuaciones WHERE id_usuario= (?) AND id_juego=(?)";
	          
	          try(PreparedStatement statement = connection.prepareStatement(Query)){
	        	  statement.setString(1, idUsuario);
	              statement.setString(2, idJuego);
	              
	              ResultSet resultado = statement.executeQuery();
	              id=resultado.getInt("id_puntuacion");
	              return id;
	              
	          }
	          catch(SQLException e){
	        	  
	          }
	          connection.close();
		}
		catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	
		
	return id;	
	}
	public int cargarRecord( int idPuntuacion/*, String idJuego,String idUsuario*/) {

		
		Connection connection = null;
		int puntos =0;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:MiniRetroBBDD.db");

		    System.out.println("Conexión exitosa a la base de datos SQLite.");
	           System.out.print("Conexion correcta tienen");
	           // Realiza operaciones con la base de datos
	          
	           
	           String Query = "SELECT Puntuacion FROM Puntuaciones WHERE Id_Puntuacion=(?)";

	           //Try para el SELECT de jugadores.Quiero comprobar si el nombre existe en la base de datos.
	           try (PreparedStatement statement = connection.prepareStatement(Query)) {
	               statement.setString(1,String.valueOf(idPuntuacion) );
	               
	               ResultSet r =statement.executeQuery();
	               while(r.next()) {
	                	puntos = r.getInt("Puntuacion");              
                	}
	               
	           }

	           
	           catch (SQLException e) {
	               e.printStackTrace();
	           }
	          
	           connection.close();
		}
		 catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		return puntos;
		
	}

	public void updatePuntos(int id_Puntuacion, int a) {
		System.out.println("aaaaaaaaaaaaaa");
		System.out.println(a);
		System.out.println(id_Puntuacion);
		System.out.println("aaaaaaaaaaaaaa");
		Connection connection = null;
		try {
			  connection = DriverManager.getConnection("jdbc:sqlite:MiniRetroBBDD.db");

		      System.out.println("Conexión exitosa a la base de datos SQLite.");
	          System.out.print("Conexion correcta tienen");
	          
	          String Query = "UPDATE Puntuaciones SET Puntuacion=(?) WHERE Id_Puntuacion= (?) ";
	          
	          try(PreparedStatement statement = connection.prepareStatement(Query)){
	        	  statement.setString(1, String.valueOf(a));
	              statement.setString(2, String.valueOf(id_Puntuacion));
	              System.out.println("bbbbbbbbbbbbbbbbbb");
	              statement.executeUpdate();
	          }
	          catch(SQLException e){
	        	  
	          }
	          //connection.close();
		}
		catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	
	
	
	public Vector<Pole> listaPuntos(String juego, int id_puntuacion) {
		Connection connection = null;
		Vector<Pole> vp = new Vector<>();
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:MiniRetroBBDD.db");

		    System.out.println("Conexión exitosa a la base de datos SQLite.");
	          System.out.print("Conexion correcta lista de puntos");
	          
	          String Query = "SELECT * FROM Puntuaciones WHERE Id_Juego=(?) LIMIT 10 ";
	          
	          try(PreparedStatement statement = connection.prepareStatement(Query)){
	        	  statement.setString(1, String.valueOf(juego));
	        	  ResultSet r =statement.executeQuery();
	        	  
	        	  while(r.next()) {
	        		  Pole p = new Pole();
	        		  p.nombre=r.getNString("id_Usuario");
	        		  p.puntos=r.getInt("Puntuacion");
	        		  p.juego=r.getString("id_Juego");//Dependiendo de como lo representemos esto igual no es necesario.
	        		  vp.add(p);
	        		  
	        	  }
	        	  return vp;
	          
	          }
	          catch(SQLException e){
	        	  
	          }
	          connection.close();
		}
		catch (SQLException e) {
	        e.printStackTrace();
	    }
		return vp;
		
	}
	


}//Fin de la clase

