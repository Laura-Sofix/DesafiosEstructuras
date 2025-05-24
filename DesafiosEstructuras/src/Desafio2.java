/** Desafio 2: Tablas Hash - Direccionamiento Abierto
 Pregunta: ¿Como implementarias la resolucion de colisiones por direccionamiento abierto en una tabla hash? ¿Que estrategias usarias para manejar las colisiones, como la sonda lineal o doble hash?
 Caso de Uso: Diseniar una tabla hash que almacene numeros de identificacion de usuarios y sus nombres. Si hay una colision, utiliza direccionamiento abierto con sonda lineal para encontrar un espacio vacio en la tabla.
 Codigo en Java (Direccionamiento Abierto con Sonda Lineal): */

class Desafio2 {

    private String[] table; // Arreglo para la tabla hash
    private int size; // Tamaño de la tabla hash

    public Desafio2(int size) { // Constructor que inicializa la tabla con un tamaño
        this.size = size; // Se asigna el tamaño recibido
        table = new String[size]; // Se crea el arreglo de ese tamaño
    }

    private int hash(int key) { // Metodo hash que calcula la posicion a partir de la clave
        return key % size; // Usa el modulo para obtener el indice dentro del rango del tamaño
    }

    public void insert(int key, String value) { // Metodo para insertar una clave con su valor
        int index = hash(key); // Calcula la posicion inicial usando la funcion hash
        while (table[index] != null) { // Si la posicion esta ocupada, hay colision
            index = (index + 1) % size;  // Se busca la siguiente posicion libre
        }
        table[index] = "key" + key + ":" + value; // Guarda el valor junto con la clave como identificador
    }

    public String search(int key) { // Metodo para buscar un valor usando su clave
        int index = hash(key); // Calcula la posicion inicial
        while (table[index] != null) { // Mientras haya un valor en esa posicion
            if (table[index].startsWith("key" + key + ":")) { // valida si esa entrada corresponde a la clave buscada
                return table[index].split(":")[1]; // Si coincide, se devuelve el valor
            }
            index = (index + 1) % size; // Si no coincide, se sigue buscando
        }
        return null; // Si no se encuentra el valor, retorna null
    }

    public static void main(String[] args) {
        Desafio2 hashTable = new Desafio2(10); // Crea la tabla hash de tamaño 10
        hashTable.insert(101, "Juan"); // Inserta un usuario Juan con clave 101
        hashTable.insert(102, "Ana"); // Inserta un usuario Ana con clave 102
        System.out.println(hashTable.search(101));  // Busca e imprime "Juan"
    }
}
