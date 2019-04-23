package Parte2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import sun.misc.Queue;


public class bfs {


	static class Node
	{
		int id;
		boolean marcado;
		List<Node> vecinos;

		Node(int pId)
		{
			this.id=pId;
			this.vecinos = new ArrayList<>();

		}
		public void addVecino(Node pVecino)
		{
			this.vecinos.add(pVecino);
		}
		public List<Node> getNeighbours() {
			return vecinos;
		}
		public void setNeighbours(List<Node> neighbours) {
			this.vecinos = neighbours;
		}
		public int getId () {
			return this.id;
		}
	}


		static String RUTA = "./data/NoDirected.txt";

		public static void main (String args[]) throws Exception {
             Queue<Node> nodes = new Queue<>();
			List<String> numeros = new ArrayList<>();
            List<Node> nodos = new ArrayList<>(); 

			try {
				FileReader reader = new FileReader(RUTA);
				BufferedReader in = new BufferedReader(reader);
				String line = in.readLine();
				for( int i = 0; line != null; i++)
				{
					numeros.add(line);
					Node nodo = new Node(i); 
					nodos.add(nodo);
					line = in.readLine();
				}

				int matriz[][] = new int [numeros.size()][numeros.size()];
				for (int i = 0; i < numeros.size() ; i++) {
					String parte = numeros.get(i);
					String [] num = parte.split(" ");


					for (int j = 0; j < numeros.size() ; j++) {

						matriz [i][j] = Integer.parseInt(num[j]);
					}
				}
				
				

				
				



			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}

	}







