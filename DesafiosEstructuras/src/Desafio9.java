/** Desafio 9: Camino mas Corto con Dijkstra
 Pregunta: ¿Como puede Dijkstra encontrar el camino mas corto en un grafo ponderado? ¿Que estructuras de datos puedes usar para mejorar el rendimiento del algoritmo?
 Caso de Uso: Imagina que eres un planificador de rutas en una ciudad, y necesitas encontrar el camino mas corto entre dos puntos en un grafo donde las aristas tienen diferentes pesos (distancias o tiempos).
 Codigo en Java (Dijkstra): */

import java.util.*; // Se importa la libreria util para usar Map, List y PriorityQueue

class Desafio9 {
    // Mapa de listas de adyacencia que representa el grafo (pero hay un error, se usa int[] en lugar de una clase, lo que dificulta la legibilidad y manejo de datos)
    private Map<String, List<int[]>> adjList = new HashMap<>();

    // Metodo para agregar una arista entre dos nodos con un peso determinado
    public void agregarArista(String origen, String destino, int peso) {
        adjList.putIfAbsent(origen, new ArrayList<>()); // Si el nodo origen no tiene lista, se inicializa
        adjList.get(origen).add(new int[]{peso, destino}); // (error) Se agrega una arista como arreglo de enteros, pero el destino es int
    }

    // Metodo que implementa el algoritmo de Dijkstra para calcular caminos minimos
    public void dijkstra(String inicio) {
        Map<String, Integer> distancias = new HashMap<>(); // Mapa para registrar la distancia minima desde el nodo de inicio
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // (error) Uso de int[] dificulta el manejo claro de datos

        distancias.put(inicio, 0); // Se asigna distancia 0 al nodo inicial
        pq.add(new int[]{0, inicio}); // (error) Se intenta insertar un String en un arreglo de int

        while (!pq.isEmpty()) { // Mientras la cola no este vacia, se continua procesando
            int[] nodo = pq.poll(); // Se obtiene el nodo con menor distancia acumulada
            int distancia = nodo[0]; // Se extrae la distancia
            String actual = nodo[1]; // (error) Se intenta sacar un String desde un int

            for (int[] vecino : adjList.get(actual)) { // Se recorren los vecinos del nodo actual
                String vecinoNodo = vecino[1]; // (error) Se intenta leer el nodo vecino como String, pero es un arreglo de enteros
                int peso = vecino[0]; // Se extrae el peso
                int nuevaDistancia = distancia + peso; // Se calcula la nueva distancia
                if (nuevaDistancia < distancias.getOrDefault(vecinoNodo, Integer.MAX_VALUE)) { // Se compara con la distancia ya registrada
                    distancias.put(vecinoNodo, nuevaDistancia); // Se actualiza la distancia si es menor
                    pq.add(new int[]{nuevaDistancia, vecinoNodo}); // (error) Inserta un arreglo con tipo de dato incorrecto
                }
            }
        }
        distancias.forEach((key, value) -> System.out.println(key + ": " + value)); // Se imprime la distancia minima a cada nodo
    }

    public static void main(String[] args) {
        Desafio9 grafo = new Desafio9(); // Se crea el grafo
        grafo.agregarArista("A", "B", 10); // Se agrega arista de A a B con peso 10
        grafo.agregarArista("B", "C", 5); // Se agrega arista de B a C con peso 5
        grafo.dijkstra("A"); // Se ejecuta el algoritmo desde el nodo A
    }
}

// (Codigo corregido y mejorado en desafio 6)