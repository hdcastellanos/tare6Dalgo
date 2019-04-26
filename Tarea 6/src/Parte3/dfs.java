package Parte3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import sun.misc.Queue;



public class dfs {
	private static Stack<Integer> camino;
	/**
	 * Clase nodo 
	 *
	 */
	static class Node
	{
		/**
		 * id del nodo
		 */

		int id;

		/**
		 * Atributo que indica si el nodo ha sido agregado a la cola
		 */
		boolean marcado1;

		/**
		 * Atributo que indica si el nodo ha sido eliminado de la cola
		 */
		boolean marcado2;

		/**
		 * Lista de los vecinos a los que llega el nodo
		 */
		List<Node> vecinos;


		Node predecesor;

		/**
		 * Crea un nuevo nodo con el id dado por parametro
		 * @param pId id que se le va a saignar al nuevo nodo
		 */
		Node(int pId)
		{
			this.id=pId;
			this.vecinos = new ArrayList<>();
			marcado1 = false;
			marcado2 = false;


		}
		/**
		 * @return el valor de la marca para saber si ya fue agregado a la cola
		 */
		public boolean darMarca1(){
			return marcado1;
		}


		/**
		 * Marca el valor de agregado a la cola como verdadero
		 */
		public void marcar1(){
			marcado1 = true;
		}

		/**
		 * @return el valor de la marca para saber si ya fue eliminado de la cola 
		 */
		public boolean darMarca2(){
			return marcado2;
		}


		/**
		 * Marca el valor de elimado de la cola como verdadero
		 */
		public void marcar2(){
			marcado2 = true;
		}

		/**
		 * Agrega un vecino a la lista de vecinos del nodo
		 * @param pVecino vecino a agregar
		 */
		public void addVecino(Node pVecino)
		{
			this.vecinos.add(pVecino);
		}
		/**
		 * @return la lista de vecinos del nodo
		 */
		public List<Node> getVecinos() {
			return vecinos;
		}

		/**
		 * @return el id del nodo
		 */
		public int getId () {
			return this.id;
		}

		public void desmaarcar() {
			marcado1 = false;
		}

		public void restart () {
			for (Node actual: vecinos ) {
				actual.desmaarcar();
			}
		}

	}

	public static void main (String args[]) throws Exception {

		Queue<Node> nodes1 = new Queue<>();

		List<String> numeros = new ArrayList<>();
		List<Node> nodos = new ArrayList<>(); 


		try {
			// agrega todos los nodos

			FileReader reader = new FileReader(args[1]);
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
				String [] num = parte.split("	");


				for (int j = 0; j < numeros.size() ; j++) {

					matriz [i][j] = Integer.parseInt(num[j]);
				}
			}

			// Añadir vecinos 
			for (int i=0;i<numeros.size();i++){
				Node actual = nodos.get(i);
				for (int j = 0 ; j<numeros.size(); j++){
					if (matriz[i][j] != -1 && matriz[i][j] != 0 ) actual.addVecino(nodos.get(j));
				}
			}

			camino = new Stack<>();
			for (Node actual : nodos){
				boolean[] recStack = new boolean[numeros.size()];
				if (actual.darMarca1() == false) {
					dfs(nodes1,actual,camino,recStack);
				}
			}

			System.out.println("Orden topologico para el grafo " + camino);

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void  dfs(Queue<Node> pNode, Node pNodeA, Stack<Integer> pOrder, boolean[] pRec  ) throws Exception {

		pNodeA.marcar1();
		pRec[pNodeA.getId()] = true;
		pNode.enqueue(pNodeA);
		pOrder.push(pNodeA.getId());


		for (Node vecino: pNodeA.getVecinos()){
			if (pRec[vecino.getId()] == true) {
				throw new Exception("tiene ciclos"); 
			}
			if (vecino.darMarca1() == false) {
				dfs(pNode,vecino,pOrder,pRec);
			}

		}
		pRec[pNodeA.getId()] = false;


		pNode.dequeue().marcar2();


	}




}
