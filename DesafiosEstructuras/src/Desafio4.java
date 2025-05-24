/** Desafio 4: Grafo - Recorrido BFS (Breadth-First Search)
 Pregunta: ¿Que diferencia hay entre el DFS y el BFS? ¿Como implementas una busqueda en anchura (BFS) utilizando una cola?
 Caso de Uso: Crea un algoritmo BFS para recorrer el mapa de una ciudad, donde los nodos son las intersecciones y las aristas son las calles. Utiliza BFS para encontrar la ruta mas corta entre dos puntos.
 Codigo en Java (BFS): */

import java.util.*;

class Desafio4 {

    private Map<String, List<String>> adjList = new HashMap<>(); // El 'map' representa el grafo donde se guardan las calles conectadas a cada interseccion mediante una lista de adyacencia

    public void agregarArista(String origen, String destino) { // Metodo que agrega una calle (arista) entre dos intersecciones
        adjList.putIfAbsent(origen, new ArrayList<>()); // Si el nodo origen no esta en el mapa, se agrega como una lista vacia
        adjList.get(origen).add(destino); // Se agrega el destino a la lista del origen
    }

    public void bfs(String inicio) { // Inicia la busqueda bfs
        Set<String> visitados = new HashSet<>(); // Para recordar las intersecciones que ya se visitaron
        Queue<String> cola = new LinkedList<>(); // Se usa una cola para guardar el orden de las intersecciones que faltan por recorrer

        cola.add(inicio); // Se mete el nodo inicio en la cola
        visitados.add(inicio); // Se marca como visitada

        while (!cola.isEmpty()) { // Mientras la cola no este vacia
            String nodo = cola.poll(); // Se saca la siguiente interseccion de la cola
            System.out.print(nodo + " "); // Se imprime en consola la interseccion actual

            for (String vecino : adjList.getOrDefault(nodo, new ArrayList<>())) { // Mediante el for se revisan las intersecciones vecinas (calles conectadas)
                if (!visitados.contains(vecino)) { // Si esa interseccion aun no fue visitada
                    cola.add(vecino); // Se agrega a la cola para visitarla despues segun el turno
                    visitados.add(vecino); // Se marca como visitada para no repetir
                }
            }
        }
    }

    public static void main(String[] args) {
        Desafio4 grafo = new Desafio4(); // Se crea el grafo

        // Se agregan calles (conexiones entre intersecciones)
        grafo.agregarArista("A", "B"); //Calle de A a B
        grafo.agregarArista("A", "C"); //Calle de A a C
        grafo.agregarArista("B", "D"); //Calle de B a D

        grafo.bfs("A"); // Se inicia el recorrido bfs desde la interseccion A
    }
}
