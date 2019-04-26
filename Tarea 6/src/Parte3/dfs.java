package Parte3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import sun.misc.Queue;



public class dfs {
	;
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
		for (int count = 0; count<args.length;count++){
		
			Stack<Integer> camino = new Stack<>();
			Queue<Node> nodes1 = new Queue<>();

			List<String> numeros = new ArrayList<>();
			List<Node> nodos = new ArrayList<>(); 



			try {
				// agrega todos los nodos

				FileReader reader = new FileReader(args[count]);
				BufferedReader in = new BufferedReader(reader);
				String line = in.readLine();
				for( int i = 0; line != null; i++)
				{
					numeros.add(line);
					Node nodo = new Node(i); 
					nodos.add(nodo);
					line = in.readLine();
				}
				System.out.println("DFS para el grafo de "+numeros.size()+" "+"nodos");
				
				
				// a�adir a la matriz
				int matriz[][] = new int [numeros.size()][numeros.size()];
				for (int i = 0; i < numeros.size() ; i++) {
					String parte = numeros.get(i);
					String [] num = parte.split("	");


					for (int j = 0; j < numeros.size() ; j++) {

						matriz [i][j] = Integer.parseInt(num[j]);
					}
				}

				// A�adir vecinos 
				for (int i=0;i<numeros.size();i++){
					Node actual = nodos.get(i);
					for (int j = 0 ; j<numeros.size(); j++){
						if (matriz[i][j] != -1 && matriz[i][j] != 0 ) actual.addVecino(nodos.get(j));
					}
				}

				camino = new Stack<>();
				boolean ciclo = false;
				
				for (Node actual : nodos){
					if (ciclo == false ){
						
						boolean[] recStack = new boolean[numeros.size()];
						if (actual.darMarca1() == false) {
							ciclo = dfs(actual,recStack,nodes1,camino);
						}
					}
					
				}
				if (ciclo){
					System.out.println("El grafo de"+ " "+ numeros.size()+" "+ "nodos tiene ciclos" ); 
				}
				else {

					System.out.println("Orden topologico para el grafo de  "+ numeros.size()+" "+ "nodos:"+ camino);
				}
			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public static boolean  dfs( Node pNodeA,boolean[] pRec, Queue<Node> pNode,Stack<Integer>pCamino  )throws Exception  {

		
		
		pNode.enqueue(pNodeA);
		
		
		if (pRec[pNodeA.getId()])return true;
		if (pNodeA.marcado1){
			return false;
		}
		pCamino.push(pNodeA.getId());
		
		
		
		pNodeA.marcar1();
		pRec[pNodeA.getId()] = true;

		for (Node vecino: pNodeA.getVecinos()){
	
			if (dfs(vecino,pRec,pNode,pCamino)) {
				return true;
			}

		}
		pRec[pNodeA.getId()] = false;

		return false;

	}




}
