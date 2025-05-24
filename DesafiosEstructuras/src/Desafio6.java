/** Desafio 6: Camino mas Corto con Dijkstra
 Pregunta: Que hace el algoritmo de Dijkstra y como encuentra el camino mas corto entre dos nodos en un grafo ponderado?
 Caso de Uso: Disena un sistema que calcule el camino mas corto entre dos estaciones de metro en una ciudad utilizando el algoritmo de Dijkstra.
 Codigo en Java (Dijkstra): */

import java.util.*; // Se importa la libreria util para usar Map, List, Scanner y la PriorityQueue

class Desafio6 {
    private final Map<String, List<Par6>> adjList = new HashMap<>(); // Se crea una lista de adyacencia para representar el grafo

    public static void main(String[] args) {
        Desafio6 grafo = new Desafio6(); // Se instancia el objeto grafo
        // Se agregan aristas con sus respectivos pesos para pruebas
        grafo.agregarArista("A", "B", 10);
        grafo.agregarArista("B", "C", 5);
        grafo.agregarArista("B", "D", 1);
        grafo.agregarArista("C", "D", 10);
        grafo.agregarArista("A", "D", 1);

        Scanner scanner = new Scanner(System.in); // Se instancia el scanner para la entrada de los datos
        int opcion; // Variable para almacenar la opcion del usuario

        do {
            // Se imprime en consola el menu de opciones para el usuario
            System.out.println("\n--- MENU ---");
            System.out.println("1. Agregar arista");
            System.out.println("2. Calcular caminos minimos (Dijkstra)");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt(); // Se lee la opcion seleccionada
            scanner.nextLine(); // Se limpia el buffer del scanner

            switch (opcion) {
                case 1: // Caso para agregar arista
                    // Se le piden al usuario los datos a ingresar segun lo que el metodo requiera
                    System.out.print("Nodo origen: ");
                    String origen = scanner.nextLine().toUpperCase(); // Se lee el nodo origen

                    System.out.print("Nodo destino: ");
                    String destino = scanner.nextLine().toUpperCase(); // Se lee el nodo destino

                    System.out.print("Peso (distancia): ");
                    int peso = scanner.nextInt(); // Se lee el peso o distancia entre nodos
                    scanner.nextLine();

                    grafo.agregarArista(origen, destino, peso); // Se llama al metodo para agregar la arista
                    System.out.println("Arista agregada: " + origen + " -> " + destino + " con peso " + peso);
                    break;

                case 2: // Caso para calcular el camino minimo
                    System.out.print("Nodo de inicio: ");
                    String inicio = scanner.nextLine().toUpperCase(); // Se lee el nodo de inicio
                    System.out.println("\n--- Distancias minimas desde " + inicio + " ---");
                    grafo.dijkstra(inicio); // Se ejecuta el metodo para el algoritmo de Dijkstra
                    break;

                case 3:
                    System.out.println("Saliendo del programa..."); // Opcion para salir del programa
                    break;

                default:
                    System.out.println("Opcion invalida. Intente de nuevo."); // Validacion en caso de opcion incorrecta
            }

        } while (opcion != 3); // El ciclo se repite hasta que el usuario elija salir

    }

    public void agregarArista(String origen, String destino, int peso) { // Metodo para agregar una arista al grafo
        adjList.putIfAbsent(origen, new ArrayList<>()); // Si no existe el nodo origen, se crea
        adjList.get(origen).add(new Par6(peso, destino)); // Se agrega el nodo destino con su peso a la lista del nodo origen
    }

    public void dijkstra(String inicio) { // Metodo que implementa el algoritmo de Dijkstra
        Map<String, Integer> distancias = new HashMap<>(); // Mapa para almacenar la distancia minima a cada nodo
        PriorityQueue<Par6> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.peso)); // Se crea la cola de prioridad ordenada por peso

        distancias.put(inicio, 0); // Se establece la distancia del nodo de inicio como 0
        pq.add(new Par6(0, inicio)); // Se agrega el nodo de inicio a la cola

        while (!pq.isEmpty()) { // Mientras la cola no este vacia
            Par6 actualPar = pq.poll(); // Se extrae el nodo con menor distancia
            int distancia = actualPar.peso; // Se obtiene la distancia acumulada
            String actual = actualPar.destino; // Se obtiene el nombre del nodo actual

            if (!adjList.containsKey(actual)) continue; // Si el nodo actual no tiene vecinos se continua normal

            for (Par6 vecino : adjList.get(actual)) { // Se recorren los vecinos del nodo actual
                String vecinoNodo = vecino.destino; // Se obtiene el nodo vecino
                int peso = vecino.peso; // Se obtiene el peso hacia el vecino
                int nuevaDistancia = distancia + peso; // Se calcula la nueva distancia acumulada

                // Si la nueva distancia es menor que la almacenada, entonces se actualiza
                if (nuevaDistancia < distancias.getOrDefault(vecinoNodo, Integer.MAX_VALUE)) {
                    distancias.put(vecinoNodo, nuevaDistancia); // Se actualiza la distancia
                    pq.add(new Par6(nuevaDistancia, vecinoNodo)); // Se agrega el vecino a la cola de prioridad
                }
            }
        }

        distancias.forEach((key, value) -> System.out.println(key + ": " + value)); // Se imprime la distancia minima a cada nodo
    }
}

class Par6 { // Clase para almacenar un nodo y su peso
    int peso; // Representa el peso de la arista
    String destino; // Representa el nodo destino

    public Par6(int peso, String destino) { // Constructor para inicializar los atributos de peso y destino
        this.peso = peso; // Se le asigna valor a peso
        this.destino = destino; // Se le asigna valor a destino
    }
}
