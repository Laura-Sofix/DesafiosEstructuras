/** Desafio 8: Grafo - Recorrido BFS (Amplitud)
 Pregunta: ¿Que estrategia utiliza BFS para recorrer un grafo y como garantiza que se visitan todos los nodos de la forma mas corta posible? ¿Cuales son las ventajas de BFS respecto a DFS en ciertos problemas?
 Caso de Uso: Imagina un sistema de busqueda en redes sociales, donde cada usuario es un nodo y las relaciones de amistad son aristas. Utiliza BFS para encontrar los amigos mas cercanos de un usuario en un grafo.
 Codigo en Java (BFS):*/

import java.util.*; // Se importa el paquete util para utilizar estructuras como Map, List, Set y Queue

class Desafio8 {
    private Map<String, List<String>> adjList = new HashMap<>(); // Se declara la lista de adyacencia para representar el grafo

    public void agregarArista(String origen, String destino) {
        adjList.putIfAbsent(origen, new ArrayList<>()); // Si el nodo origen no existe, se crea una nueva lista vacia
        adjList.get(origen).add(destino); // Se agrega el nodo destino como adyacente al nodo origen
    }

    public void bfs(String inicio) { // Metodo que implementa el recorrido en amplitud (bfs)
        Set<String> visitados = new HashSet<>(); // Conjunto para registrar nodos visitados
        Queue<String> cola = new LinkedList<>(); // Cola para el orden de visita de los nodos

        cola.add(inicio); // Se agrega el nodo inicial a la cola
        visitados.add(inicio); // Se marca como visitado

        while (!cola.isEmpty()) { // Mientras la cola no este vacia
            String nodo = cola.poll(); // Se extrae el primer nodo en la cola
            System.out.print(nodo + " "); // Se imprime el nodo visitado

            for (String vecino : adjList.getOrDefault(nodo, new ArrayList<>())) { // Se recorren los vecinos del nodo actual
                if (!visitados.contains(vecino)) { // Si el vecino no ha sido visitado
                    cola.add(vecino); // Se agrega a la cola para ser procesado
                    visitados.add(vecino); // Se marca como visitado
                }
            }
        }
    }

    public static void main(String[] args) {
        Desafio8 grafo = new Desafio8(); // Se crea el grafo
        grafo.agregarArista("A", "B"); // Se agregan conexiones entre nodos
        grafo.agregarArista("A", "C");
        grafo.agregarArista("B", "D");
        grafo.bfs("A"); // Se realiza el recorrido BFS comenzando desde el nodo A
    }
}
