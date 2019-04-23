package Parte1;

import java.util.Iterator;
import java.util.PriorityQueue;

import Parte1.Grafo.Nodo;
import Parte1.Grafo.Vertice;

public class dijkstra {

	int distancia[];
	int nodo;
	int vertice;
	boolean marcado[];

	public dijkstra( Grafo grafo, int raiz) {
		this.nodo = grafo.nodo;
		this.vertice = grafo.vertice;
		distancia = new int[vertice];
		marcado = new boolean[vertice];

		for( int i = 0; i< vertice; i ++)
		{
			distancia[i] = Integer.MAX_VALUE;
			distancia[raiz] = 0;
		}

		grafo.n[raiz].setMarcado(true);


		for(int i= 0; i < vertice; i++)
		{

			int inicio = grafo.v[i].raiz;
			int destino = grafo.v[i].destino;
			int peso = grafo.v[i].peso;
			if( !grafo.n[destino].marcado() && distancia[inicio] != Integer.MAX_VALUE && distancia[inicio] + peso < distancia[destino])
			{
				distancia[destino] = distancia[inicio] + peso;
				grafo.n[destino].setMarcado(true);
			}

		}

		grafo.solucion(distancia, nodo);

	}

}
