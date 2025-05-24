/** Desafio 10: Arbol de Expansion Minima con Prim
 Pregunta: ¿Como se puede construir un arbol de expansion minima utilizando el algoritmo de Prim, si las conexiones estan dirigidas?
 Caso de Uso: Supongamos que eres un ingeniero de redes encargado de conectar nodos (por ejemplo, estaciones o servidores) de forma eficiente minimizando los costos de conexion. Este algoritmo ayuda a construir esa red inicial economica.
 Codigo en Java (Prim): */

import java.util.*; // Se importa la libreria util

class Desafio10 {
    private Map<Integer, List<int[]>> adjList = new HashMap<>(); // El 'map' representa el grafo donde cada nodo tiene una lista de aristas que lo conectan a otros nodos con su respectivo peso

    public void agregarArista(int origen, int destino, int peso) { // Metodo que agrega una conexion desde un nodo de origen a un nodo de destino con su peso
        adjList.putIfAbsent(origen, new ArrayList<>()); // Si el nodo de origen no esta en el grafo, se agrega con una lista vacia
        adjList.get(origen).add(new int[]{destino, peso}); // Se agrega el destino y el costo como un arreglo a la lista del origen
    }

    public void prim(int inicio) { // Metodo que implementa el algoritmo de Prim iniciando desde un nodo inicio
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // La cola de prioridad prioriza la arista de menor peso
        Set<Integer> visitados = new HashSet<>(); // Conjunto que almacena los nodos que ya han sido conectados al arbol de expansion minima
        pq.add(new int[]{inicio, 0}); // Se inserta el nodo inicial con peso de cero

        while (!pq.isEmpty()) { // Se ejecuta mientras existan aristas
            int[] arista = pq.poll(); // Se saca la arista con el menor peso
            int nodo = arista[0], peso = arista[1]; // Se obtiene el nodo destino de la arista y su peso

            if (visitados.contains(nodo)) continue; // Si el nodo ya fue visitado, se continua

            visitados.add(nodo); // Se marca el nodo como visitado
            System.out.println("Nodo: " + nodo + ", Peso: " + peso); // Se imprime el nodo agregado y el costo de su conexion

            for (int[] vecino : adjList.getOrDefault(nodo, new ArrayList<>())) { // Se recorren todas las aristas del nodo actual
                if (!visitados.contains(vecino[0])) { // Si el nodo vecino aun no ha sido visitado
                    pq.add(vecino); // Se agrega la arista a la cola
                }
            }
        }
    }

    public static void main(String[] args) {
        Desafio10 grafo = new Desafio10(); // Se crea el grafo

        // Se agregan las aristas entre nodos con el costo
        grafo.agregarArista(0, 1, 10); // Conexion de nodo 0 a nodo 1 con un costo de 10
        grafo.agregarArista(0, 2, 6); // Conexion de nodo 0 a nodo 2 con un costo de 6
        grafo.agregarArista(1, 2, 5); // Conexion de nodo 1 a nodo 2 con un costo de 5

        grafo.prim(0); // Se ejecuta el algoritmo de Prim desde el nodo 0
    }
}
