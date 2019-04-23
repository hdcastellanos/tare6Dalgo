package Parte2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class bfs {

	
	class Nodo implements Comparable<Nodo>{
		int id;
		boolean marcado;
		List<Nodo> nodos = new ArrayList<>();
		public Nodo(int id) {
			this.id = id;
		}
		
		public boolean marcado()
		{
			return marcado;
		}
		
		public void setMarcado(boolean marcado)
		{
			this.marcado = marcado;
		}

		@Override
		public int compareTo(Nodo o) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
	
	static String RUTA = "./data/NoDirected.txt";
	
	public static void main (String args[]) throws Exception {
		
		List<String> numeros = new ArrayList<>();
		

		try {
			FileReader reader = new FileReader(RUTA);
			BufferedReader in = new BufferedReader(reader);
			String line = in.readLine();
			for( int i = 0; line != null; i++)
			{
				numeros.add(line);
				line = in.readLine();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int matriz[][] = new int [numeros.size()][numeros.size()];
		
		
		
		
	}
	
}
