package Parte1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Grafo {

	static String RUTA5 = "./data/distances5.txt";
	static String RUTA100 = "./data/distances100.txt";
	static String RUTA1000 = "./data/distances1000.txt";
	static boolean fin = false;
	static Scanner sc = new Scanner(System.in);

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

	static void menu()
	{
		System.out.println("a. Para el grafo de distancia 5 oprimir el número 1");
		System.out.println("b. Para el grafo de distancia 100 oprimir el número 2");
		System.out.println("c. Para el grafo de distancia 1000 oprimir el número 3");
		System.out.println("d. Para finalizar oprimir el número 4");
	}

	static void loadData( String ruta) throws IOException
	{
		List<String> numeros = new ArrayList<>();
		String numero[];

		try {
			FileReader reader = new FileReader(ruta);
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

		bellmanFord algoritmo = new bellmanFord(grafo, 0);

		dijkstra algoritmo2 = new dijkstra(grafo, 0);

		FloydWarshall algoritmo3 = new FloydWarshall(matriz);
	}


	public static void main(String[] args) throws Exception {

		while(!fin){
			menu();
			String option = sc.nextLine();//Numero ingresado desde consola por el usuario
			switch(option){
			case "1":

				loadData(RUTA5);

				break;
			case "2":

				loadData(RUTA100);

				break;

			case "3":

				loadData(RUTA1000);
				break;
				
			case "4":
				fin = true;

			}
		}

	}
}
