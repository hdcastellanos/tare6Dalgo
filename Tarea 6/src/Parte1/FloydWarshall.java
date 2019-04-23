package Parte1;

public class FloydWarshall{

	int matriz[][];

	public FloydWarshall( int matriz[][]) {
		this.matriz = matriz;


		for(  int i =0 ; i < matriz.length; i++)
		{
			for(int j = 0; j< matriz.length; j++)
			{
				for( int k = 0; k<matriz.length; k++)
				{
					matriz[j][k] = Math.min(matriz[j][k], matriz[j][i] + matriz[i][k]);
				}
			}
		}

		printSolution(matriz);
	}

	void printSolution(int dist[][]) 
	{ 
		System.out.println("Costos mínimos"); 
		for (int i=0; i< matriz.length; ++i) 
		{ 
			for (int j=0; j<matriz.length; ++j) 
			{ 
				System.out.print(dist[i][j]+ "\t"); 
			} 
			System.out.println(); 
		} 
	} 
}
