package Control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;

public  class Sistema {

	
	
	private static String nombre = null;
	private static int id_jugador = 0;
	private static int id_juego = 0;
	private static int id_puntos =0;
	private static int puntos = 0;
	
	

	public static String getNombre() {
		return nombre;
	}

	public static void setNombre(String nombre) {
		Sistema.nombre = nombre;
	}

	public static int getId_jugador() {
		return id_jugador;
	}

	public static void setId_jugador(int id_jugador) {
		Sistema.id_jugador = id_jugador;
	}

	public static int getId_juego() {
		return id_juego;
	}

	public static void setId_juego(int id_juego) {
		Sistema.id_juego = id_juego;
	}

	public static int getPuntos() {
		return puntos;
	}

	public static void setPuntos(int puntos) {
		Sistema.puntos = puntos;
	}

	public static int getId_puntos() {
		return id_puntos;
	}

	public static void setId_puntos(int id_puntos) {
		Sistema.id_puntos = id_puntos;
	}

	

	
	
}
