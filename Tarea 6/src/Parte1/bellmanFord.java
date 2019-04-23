package Parte1;

public class bellmanFord{

	int distancia[];
	int nodo;
	int vertice;

	public bellmanFord( Grafo grafo, int raiz)
	{
		this.nodo = grafo.nodo;
		this.vertice = grafo.vertice;
		distancia = new int[vertice];

		for( int i = 0; i < vertice; i++)
		{
			distancia[i] = Integer.MAX_VALUE;
			distancia[raiz] = 0;
		}

		for( int j = 0; j< vertice ; j++)
		{
			int inicio = grafo.v[j].raiz;
			int destino = grafo.v[j].destino;
			int peso = grafo.v[j].peso;
			if( distancia[inicio] != Integer.MAX_VALUE && distancia[inicio] + peso < distancia[destino])
			{
				distancia[destino] = distancia[inicio] + peso;
			}

		}

		grafo.solucion(distancia, nodo);
	}

}
