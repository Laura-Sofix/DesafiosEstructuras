/** Desafio 11: Tablas Hash - Hashing Perfecto
 Pregunta: ¿Que es el hashing perfecto y como asegura que no haya colisiones en una tabla hash? ¿Que condiciones son necesarias para lograr hashing perfecto?
 Caso de Uso: Implementa un sistema que almacene una lista de identificadores unicos de productos en una tienda online. Utiliza hashing perfecto para garantizar que no haya colisiones al almacenar los productos.
 Codigo en Java (Hashing Perfecto - Simplificado): */

class HashTablePerfecto {
    private String[] table; // Arreglo que representa la tabla hash

    public HashTablePerfecto(int size) {
        this.table = new String[size]; // Se inicializa la tabla hash con un tamaño fijo
    }

    public int hash(String key) {
        return key.hashCode() % table.length; // Se usa hashCode y el modulo con el tamaño de la tabla
    }

    public void insert(String key) {
        int index = hash(key); // Se calcula la posicion a insertar usando la funcion hash
        if (table[index] != null) { // Se verifica si ya existe un elemento en la posicion
            System.out.println("Colision detectada, el hash no es perfecto para esta clave."); // Si hay colision, se muestra que no es hashing perfecto
        } else {
            table[index] = key; // Si no hay colision, se inserta la clave en la posicion calculada
        }
    }

    public String search(String key) {
        int index = hash(key); // Se calcula la posicion usando la funcion hash
        return table[index]; // Se retorna el valor almacenado en esa posicion
    }

    public static void main(String[] args) {
        HashTablePerfecto ht = new HashTablePerfecto(10); // Se crea una tabla hash de tamaño 10
        ht.insert("Producto123"); // Se inserta una clave 'Producto123' en la tabla
        ht.insert("Producto456"); // Se inserta otra clave 'Producto456' en la tabla
        System.out.println(ht.search("Producto123")); // Se busca 'Producto123' en la tabla y se imprime
    }
}
