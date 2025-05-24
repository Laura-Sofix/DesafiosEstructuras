/** Desafio 3: Grafo - Recorrido DFS (Depth-First Search)
 Pregunta: ¿Que estrategia sigue el DFS al recorrer un grafo? ¿Como maneja los ciclos en los grafos? ¿Como implementas una busqueda en profundidad (DFS) utilizando recursion?
 Caso de Uso: Implementa un algoritmo DFS para recorrer un grafo que representa un sistema de red social, donde los nodos son personas y las aristas son relaciones de amistad.
 Codigo en Java (DFS Recursivo): */

import java.util.*;

class Desafio3 {

    private Map<String, List<String>> adjList = new HashMap<>(); // El 'map' representa el grafo donde se van guardando las listas de amigos de cada persona mediante una lista de adyacencia

    public void agregarArista(String origen, String destino) { // Metodo que agrega una relacion de amistad entre dos personas (conecta una arista entre nodo origen y destino)
        adjList.putIfAbsent(origen, new ArrayList<>()); // Si el nodo origen no esta en el mapa, se agrega como una lista vacia
        adjList.get(origen).add(destino); // Se agrega el amigo (destino) en la lista que se creo de origen
    }

    public void dfs(String inicio) { // Inicia la busqueda DFS
        Set<String> visitados = new HashSet<>(); // Para recordar las personas que ya se visitaron
        dfsHelper(inicio, visitados); // Se llama al metodo dfsHelper para la busqueda
    }

    private void dfsHelper(String persona, Set<String> visitados) { // Hace la busqueda dfs en profundidad
        if (visitados.contains(persona)) return;  // Si ya visitamos a esta persona, se sale para no repetir

        System.out.print(persona + " "); // Se imprime en consola la persona actual
        visitados.add(persona); // Se marca esa persona como visitada

        for (String amigo : adjList.getOrDefault(persona, new ArrayList<>())) { // Mediante el for se busca a todos sus amigos (vecinos) y se visitan uno por uno
            dfsHelper(amigo, visitados); // Se llama recursivamente a la funcion para seguir haciendo el recorrido
        }
    }

    public static void main(String[] args) {
        Desafio3 grafo = new Desafio3(); // Se crea el grafo
        grafo.agregarArista("Juan", "Ana"); // Juan es amigo de Ana (arista de Juan a Ana)
        grafo.agregarArista("Ana", "Pedro"); // Ana es amiga de Pedro (arista de Ana a Pedro)
        grafo.agregarArista("Pedro", "Luis"); // Pedro es amigo de Luis (arista de Pedro a Luis)
        grafo.dfs("Juan"); // Se inicia el recorrido dfs desde Juan
    }
}
