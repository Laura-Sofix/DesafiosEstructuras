/** Desafio 5: Arbol de Expansion Minima con Prim
 Pregunta: ¿Como se utiliza el algoritmo de Prim para encontrar el arbol de expansion minima? ¿Que tipo de estructura de datos es mas eficiente para implementar este algoritmo?
 Caso de Uso: Imagina que eres un ingeniero que necesita conectar ciudades con la menor cantidad de cables posibles. Usa el algoritmo de Prim para encontrar la red mas economica.
 Codigo en Java (Prim): */

import java.util.*;

class Desafio5 {
    private Map<Integer, List<int[]>> adjList = new HashMap<>(); // El 'map' representa el grafo donde cada ciudad tiene una lista de ciudades conectadas y el costo del cable (peso)

    public void agregarArista(int origen, int destino, int peso) { // Metodo que agrega una conexion entre dos ciudades con su respectivo costo
        adjList.putIfAbsent(origen, new ArrayList<>()); // Si la ciudad de origen no esta registrada, se agrega con una lista vacia
        adjList.get(origen).add(new int[]{destino, peso}); // Se agrega el destino y el costo como un arreglo a la lista del origen
    }

    public void prim(int inicio) { // Se inicia el algoritmo de Prim desde una ciudad especifica
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // Se usa una cola de prioridad para elegir la conexion que tenga menor costo
        Set<Integer> visitados = new HashSet<>(); // Conjunto para las ciudades que ya fueron conectadas
        pq.add(new int[]{inicio, 0}); // Se agrega la ciudad inicial con costo 0

        while (!pq.isEmpty()) { // Mientras haya conexiones disponibles se saca la conexion con menor costo
            int[] arista = pq.poll();
            int nodo = arista[0], peso = arista[1]; // Se obtiene la ciudad destino y el costo

            if (visitados.contains(nodo)) continue; // Si ya se conecto esa ciudad, se salta

            visitados.add(nodo); // Se marca la ciudad como conectada
            System.out.println("Nodo: " + nodo + ", Peso: " + peso); // se imprime la ciudad y el costo

            for (int[] vecino : adjList.getOrDefault(nodo, new ArrayList<>())) { // Se recorren las ciudades vecinas conectadas
                if (!visitados.contains(vecino[0])) { // Si esa ciudad vecina no ha sido conectada, se agrega a la cola de prioridad
                    pq.add(vecino);
                }
            }
        }
    }

    public static void main(String[] args) {
        Desafio5 grafo = new Desafio5(); // Se crea el grafo

        // Se agregan las conexiones entre ciudades con su respectivo costo
        grafo.agregarArista(0, 1, 10); // De la ciudad 0 a la 1 es un costo de 10
        grafo.agregarArista(0, 2, 6); // De la ciudad 0 a la 2 es un costo de 6
        grafo.agregarArista(1, 2, 5); // De la ciudad 1 a la 2 es un costo de 5

        grafo.prim(0); // Se inicia el algoritmo de Prim iniciando desde la ciudad 0
    }
}
