package Parte3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Grafo {



	class Vertice{
		int raiz, destino, peso;
		
		public Vertice() {
			raiz = destino = peso = 0;
		}
	}

	class Nodo implements Comparable<Nodo>{
		int id;
		boolean marcado;
		List<Vertice> vertices = new ArrayList<>();
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
	
	int vertice, nodo;
	Vertice v[];
	Nodo n[];

	public Grafo(int vertice , int nodo)
	{
		this.vertice = vertice;
		this.nodo = nodo;
		v = new Vertice[vertice];
		n = new Nodo[nodo];
		for ( int i = 0; i < vertice; i++)
		{
			v[i] = new Vertice();
		}
		for( int j = 0; j < nodo; j++)
		{
			n[j] = new Nodo(j);
		}
	}

	void solucion(int dist[], int n) 
	{ 
		System.out.println("Nodo          Costo Mínimo"); 
		for (int i = 0; i < n; ++i) 
			System.out.printf("%d \t\t %d\n", i, dist[i]); 
	} 

	public static void main(String[] args) throws Exception {

		List<String> numeros = new ArrayList<>();
		String numero[];

		try {
			FileReader reader = new FileReader(args[0]);
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

		Grafo grafo = new Grafo((numeros.size()*numeros.size()), numeros.size());

		int n = 0;
		for( int j = 0; j<numeros.size(); j++)
		{
			String parte = numeros.get(j);
			numero = parte.split("	");
			for ( int i = 0; i<numero.length; i++){
				if( j != i && Integer.parseInt(numero[i]) != -1){
					grafo.v[n].raiz= j;
					grafo.v[n].destino = i;
					grafo.v[n].peso = Integer.parseInt(numero[i]);
					n++;
				}
			}

		}

		int matriz[][] = new int[numeros.size()][numeros.size()];
		for( int j = 0; j<numeros.size(); j++)
		{
			String parte = numeros.get(j);
			numero = parte.split("	");
			for ( int i = 0; i<numero.length; i++){
				if(Integer.parseInt(numero[i]) != -1){
					matriz[j][i] = Integer.parseInt(numero[i]);
				}
				
			}

		}
		
		
	
		
	}
	
}
