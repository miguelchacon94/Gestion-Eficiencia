import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Main {
	/**
	 * 
	 * @param datos
	 * @param n
	 */
	public static void aniadirArchivo(long[] datos, String nombre) {
		FileWriter flwriter = null;
		try {//además de la ruta del archivo recibe un parámetro de tipo boolean, que le indican que se va añadir más registros 
			flwriter = new FileWriter(nombre + ".txt", true);
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			for (int i = 0;i<datos.length;i++) {
				//escribe los datos en el archivo
				bfwriter.write(datos[i]+";"+ "\n");
			}
			bfwriter.close();
			System.out.println("Archivo modificado satisfactoriamente..");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param datos
	 * @param dato
	 * @return
	 */
	public static int busquedaBinaria( int [] datos, int dato) {
		 int inicio = 0;
		 int fin = datos.length - 1;
		 int pos;
		 while (inicio <= fin) {
		     pos = (inicio+fin) / 2;
		     if ( datos[pos] == dato )
		       return pos;
		     else if ( datos[pos] < dato ) {
		  inicio = pos+1;
		     } else {
		  fin = pos-1;
		     }
		 }
		 return -1;
	  }
		/**
		 * 
		 * @param datos
		 * @param dato
		 * @return
		 */
	public static int busquedaSecuencial(int [] datos, int dato){
		int inicio = 0;
		 int fin = datos.length - 1;
		 int pos=-1;
		 for (int i = 0; i<datos.length;i++){
			 if (dato==datos[i]){
				 pos=i;
			 }
		 }
		 
		 
		 
		 return pos;
	}
	
	/**
	 * 
	 * @param A
	 * @param izq
	 * @param der
	 */
	public static void quicksort(int[] A, int izq, int der) {

		  int pivote=A[izq]; // tomamos primer elemento como pivote
		  int i=izq; // i realiza la búsqueda de izquierda a derecha
		  int j=der; // j realiza la búsqueda de derecha a izquierda
		  int aux;
		 
		  while(i<j){            // mientras no se crucen las búsquedas
		     while(A[i]<=pivote && i<j) i++; // busca elemento mayor que pivote
		     while(A[j]>pivote) j--;         // busca elemento menor que pivote
		     if (i<j) {                      // si no se han cruzado                      
		         aux= A[i];                  // los intercambia
		         A[i]=A[j];
		         A[j]=aux;
		     }
		   }
		   A[izq]=A[j]; // se coloca el pivote en su lugar de forma que tendremos
		   A[j]=pivote; // los menores a su izquierda y los mayores a su derecha
		   if(izq<j-1)
		      quicksort(A,izq,j-1); // ordenamos subarray izquierdo
		   if(j+1 <der)
		      quicksort(A,j+1,der); // ordenamos subarray derecho
		}
	
	/**
	 * Metodo deordenacion por 
	 * Inicialmente izq sera cero y der el tamaño del array menos uno
	 * @param A
	 * @param izq posicion donde iniciamos la ordenacion
	 * @param der posicion en la que terminamos
	 */
	public static void mergesort(int[] A,int izq, int der){
	    if (izq<der){
	            int m=(izq+der)/2;
	            mergesort(A,izq, m);
	            mergesort(A,m+1, der);
	            merge(A,izq, m, der);
	    }
	}
	/**
	 * 
	 * @param A
	 * @param izq
	 * @param m
	 * @param der
	 */
	public static void merge(int[] A,int izq, int m, int der){
		   int i;
		   int j;
		   int k;
		   int [] B = new int[A.length]; //array auxiliar
		   for (i=izq; i<=der; i++){ //copia ambas mitades en el array auxiliar
		             B[i]=A[i];
		   }
		             i=izq; 
		             j=m+1;
		             k=izq;
		             while (i<=m && j<=der) //copia el siguiente elemento más grande
		             if (B[i]<=B[j]){
		                     A[k++]=B[i++];
		             } else{
		                     A[k++]=B[j++];
		             }
		             while (i<=m){ //copia los elementos que quedan de la
		                           A[k++]=B[i++]; //primera mitad (si los hay)
		             }
		 }
	
	public static void main(String[] args) throws IOException {
		int m=500;//numero de mediciones que hace
		int n=20000;//tamaño de los arrays
		int o=100000;
		long[] Diferencia1 =new long[m];
		long[] Diferencia2 =new long[m];
		long[] Diferencia3 =new long[m];
		long[] Diferencia4 =new long[m];
		int [] A = new int [n];
		int [] B = new int [n];
		System.out.println("Calculando");
		for(int j=0;j<m;j++){
		for(int i = 0; i<A.length; i++){
			A[i]=B[i]=(int) (Math.random()*o);
			//System.out.println("posicion "+i+" valor: " + A[i]);
		}
		long dif1;
		long dif2;
		long time_start, time_end;
		long time_start2, time_end2;
		long time_start3, time_end3;
		long time_start4, time_end4;
		time_start = System.nanoTime();
		
		mergesort(A, 0, A.length-1);
		time_end = System.nanoTime();
		time_start2 = System.nanoTime();
		quicksort( B, 0, B.length-1);
		time_end2 = System.nanoTime();
		//System.out.println("the task has taken "+ ( time_end - time_start ) +" milliseconds");
		dif1=time_end - time_start;
		
		Diferencia1[j]=dif1;
		//Diferencias de tiempos
		dif2=time_end2 - time_start2;
		Diferencia2[j]=dif2;
		
		time_start3 = System.nanoTime();
		int a = busquedaBinaria(A, (int) (Math.random()*o));
		time_end3 = System.nanoTime();
		Diferencia3[j]=time_end3-time_start3;
		
		time_start4 = System.nanoTime();
		int b=busquedaSecuencial(B, (int) (Math.random()*o) );
		time_end4 = System.nanoTime();
		Diferencia4[j]=time_end4-time_start4;
		
		
		//System.out.println("the mergesort has taken "+ ( time_end - time_start ) +" milliseconds");
		//System.out.println("the quicksort has taken "+ ( time_end2 - time_start2 ) +" nanoseconds");
		//System.out.println("the busqueda binaria has taken "+ ( time_end3 - time_start3 ) +" nanoseconds");
		//System.out.println("the Busqueda secuencial has taken "+ ( time_end4 - time_start4 ) +" nanoseconds");
		}
		aniadirArchivo(Diferencia1, "mergesort");
		aniadirArchivo(Diferencia2, "quicksort");
		aniadirArchivo(Diferencia3, "Busqueda_Binaria");
		aniadirArchivo(Diferencia4, "Busqueda_Secuencial");
		
		
	}

}
