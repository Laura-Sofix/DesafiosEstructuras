/**Desafio 7: Grafo - Recorrido DFS (Profundidad)
 Pregunta: ¿Cual es la diferencia entre un recorrido DFS y un recorrido BFS? ¿Como implementas una busqueda en profundidad (DFS) sin recurrir a la recursion? ¿En que situaciones prefieres usar DFS?
 Caso de Uso: Imagina que estas creando un sistema para analizar un mapa de una ciudad en el que las intersecciones son nodos y las calles son aristas. Usaras DFS para encontrar todos los caminos posibles entre dos nodos.
 Codigo en Java (DFS Iterativo):*/

import java.util.*; // Se importa el paquete util para usar las estructuras de Map, List, Set y Stack

class Desafio7 {
    private Map<String, List<String>> adjList = new HashMap<>(); // Se declara el grafo como una lista de adyacencia

    public void agregarArista(String origen, String destino) {
        adjList.putIfAbsent(origen, new ArrayList<>()); // Si el nodo origen no existe, se crea una lista vacia
        adjList.get(origen).add(destino); // Se agrega el destino a la lista de adyacencia del origen
    }

    public void dfsIterativo(String inicio) { // Metodo que realiza recorrido en profundidad sin recursion
        Set<String> visitados = new HashSet<>(); // Conjunto para registrar los nodos visitados
        Stack<String> stack = new Stack<>(); // Se crea una pila para controlar el recorrido
        stack.push(inicio); // Se inserta el nodo inicial en la pila

        while (!stack.isEmpty()) { // Mientras la pila no este vacia
            String nodo = stack.pop(); // Se extrae el ultimo nodo insertado
            if (!visitados.contains(nodo)) { // Si el nodo no ha sido visitado
                visitados.add(nodo); // Se marca como visitado
                System.out.print(nodo + " "); // Se imprime el nodo

                // Agregar los vecinos no visitados a la pila
                for (String vecino : adjList.getOrDefault(nodo, new ArrayList<>())) { // Se obtienen los vecinos del nodo actual
                    if (!visitados.contains(vecino)) { // Si el vecino no ha sido visitado
                        stack.push(vecino); // Se agrega a la pila
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Desafio7 grafo = new Desafio7(); // Se crea el grafo
        grafo.agregarArista("A", "B"); // Se agregan aristas entre nodos de origen y destino
        grafo.agregarArista("A", "C");
        grafo.agregarArista("B", "D");
        grafo.dfsIterativo("A"); // Se llama al metodo DFS comenzando desde el nodo A
    }
}
