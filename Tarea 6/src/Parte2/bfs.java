package Parte2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import sun.misc.Queue;

/**
 * 
 * @author hd.castellanos & a.trujilloa1
 *
 */
public class bfs {
	/**
	 * Lista de los componentes conectados del grafo
	 */
	private static List<Stack> ans = new ArrayList<>();
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
		 * Lista de los vecinos del nodo
		 */
		List<Node> vecinos;

		/**
		 * Crea un nuevo nodo con el id dado por parametro
		 * @param pId id que se le va a saignar al nuevo nodo
		 */
		Node(int pId)
		{
			this.id=pId;
			this.vecinos = new ArrayList<>();
			marcado1 = false;


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

	}



	public static void main (String args[]) throws Exception {

		Queue<Node> nodes1 = new Queue<>();

		List<String> numeros = new ArrayList<>();
		List<Node> nodos = new ArrayList<>(); 


		try {
			// agrega todos los nodos
			FileReader reader = new FileReader(args[0]);
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
				if (!actual.darMarca1()){
					Stack<Integer> camino = new Stack<>();
					bfs(nodes1,actual,camino);
					ans.add(camino);
				}
			}

			System.out.println("los nodos conectados son: "+ans);







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
	public static void  bfs(Queue<Node> pNode, Node pNodeA, Stack<Integer> pCamino  ) throws Exception {


		if (!pNodeA.darMarca1()){
			pNodeA.marcar1();
			pNode.enqueue(pNodeA);
			pCamino.push(pNodeA.getId());



			for (Node vecino: pNodeA.getVecinos()){
				if (!vecino.darMarca1())
					bfs(pNode,vecino,pCamino);

			}
			
			if (!pNode.isEmpty())
				pNode.dequeue().marcar2();
		}

	}

}














