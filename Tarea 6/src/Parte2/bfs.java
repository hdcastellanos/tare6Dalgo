package Parte2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import sun.misc.Queue;


public class bfs {

	private static List<List> ans = new ArrayList<>();

	static class Node
	{
		int id;
		boolean marcado1;
		boolean marcado2;

		List<Node> vecinos;

		Node(int pId)
		{
			this.id=pId;
			this.vecinos = new ArrayList<>();
			marcado1 = false;


		}
		public boolean darMarca1(){
			return marcado1;
		}



		public void marcar1(){
			marcado1 = true;
		}

		public boolean darMarca2(){
			return marcado2;
		}



		public void marcar2(){
			marcado2 = true;
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

		Queue<Node> nodes1 = new Queue<>();

		List<String> numeros = new ArrayList<>();
		List<Node> nodos = new ArrayList<>(); 


		try {
			// agrega todos los nodos
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
			// añadir a la matriz
			int matriz[][] = new int [numeros.size()][numeros.size()];
			for (int i = 0; i < numeros.size() ; i++) {
				String parte = numeros.get(i);
				String [] num = parte.split(" ");


				for (int j = 0; j < numeros.size() ; j++) {

					matriz [i][j] = Integer.parseInt(num[j]);
				}
			}

			// Añadir vecinos 
			for (int i=0;i<numeros.size();i++){
				Node actual = nodos.get(i);
				for (int j = 0 ; j<numeros.size(); j++){
					if (matriz[i][j] == 1) actual.addVecino(nodos.get(j));
				}
			}

			// bfs
			for (Node actual : nodos){
				if (actual.darMarca1()==false){
					List<Integer> camino = new ArrayList<>();
					bfs(nodes1,actual,camino);
					ans.add(camino);
				}
			}

			System.out.println(ans);







		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}



	/**
	 * Hace un recorrido bfs sobre el nodo que llega como parametro, lo agrega a la cola y hace bfs sobre sus vecinos. al finalizar se elimina de la cola 
	 * y se agrega al camino 
	 * @param pNode cola donde se van a gregar los nods por los que vaya pasando 
	 * @param pNodeA Nodo sobre el cual se va a realizar el recorrido bfs
	 * @param pCamino camino al cual se debe agregar el nodo cuando se elimine de la cola 
	 * @throws Exception si no se puede eliminar el elemento correctamente de la pila 
	 */
	public static void  bfs(Queue<Node> pNode, Node pNodeA, List<Integer> pCamino  ) throws Exception {


		if (pNodeA.darMarca1() == false){
			pNodeA.marcar1();
			pNode.enqueue(pNodeA);



			for (Node vecino: pNodeA.getNeighbours()){
				if (vecino.darMarca1() == false)
					bfs(pNode,vecino,pCamino);

			}
			pCamino.add(pNodeA.getId());
			if (pNode.isEmpty()==false)
				pNode.dequeue().marcar2();
		}



	}








}














