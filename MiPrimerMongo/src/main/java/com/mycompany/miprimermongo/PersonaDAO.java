package com.mycompany.miprimermongo;

import colecciones.Persona;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Indexes.ascending;
import static com.mongodb.client.model.Sorts.orderBy;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * Clase DAO para modificar documentos de la colección Personas.
 * 
 * @author Diego Valenzuela Parra - 00000247700
 */
public class PersonaDAO {

    private final MongoDatabase dataBase; // Declara una variable para la base de datos MongoDB

    public PersonaDAO() {
        // Configura el proveedor de códecs para trabajar con objetos Java y MongoDB
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());

        // Combina el proveedor de códecs POJO con el registro de códecs predeterminado de MongoDB
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        // Configura la cadena de conexión a la base de datos MongoDB local
        ConnectionString cadenaConexion = new ConnectionString("mongodb://localhost/27017");

        // Configura los ajustes del cliente MongoDB, incluyendo la cadena de conexión y el registro de códecs
        MongoClientSettings clientsSettings = MongoClientSettings.builder()
                .applyConnectionString(cadenaConexion)
                .codecRegistry(codecRegistry)
                .build();

        // Crea un cliente MongoDB utilizando los ajustes configurados
        MongoClient dbServer = MongoClients.create(clientsSettings);

        // Obtiene la base de datos específica ("ClaseBaseDatosAvanzadas") del cliente MongoDB
        this.dataBase = dbServer.getDatabase("mongolito");
    }

    /**
     * Método para obtener a todas las personas de la colección "Personas".
     */
    public void leerPersonas() {
        // Obtiene la colección de MongoDB para el tipo de datos "Persona"
        MongoCollection<Persona> coleccion = dataBase.getCollection("Personas", Persona.class);

        // Se crea una lista para almacenar a las personas encontradas
        List<Persona> listaPersonas = new LinkedList<>();

        // Hacemos la consulta y guardamos los resultados en la lista
        coleccion.find().into(listaPersonas);

        // Iteramos sobre la lista y vamos imprimiendo a cada persona
        int i = 1;
        for (Persona persona : listaPersonas) {
            System.out.println("Persona " + i + " - " + persona);
            i++;
        }
    }

    /**
     * Método para obtener a todas las personas mayores de 18, ordenándolas de
     * menor a mayor según la edad y el nombre.
     */
    public void leerPersonasEdadNombre() {
        // Obtiene la colección de MongoDB para el tipo de datos "Persona"
        MongoCollection<Persona> coleccion = dataBase.getCollection("Personas", Persona.class);

        // Se crea una lista para almacenar a las personas encontradas
        List<Persona> listaPersonas = new LinkedList<>();

        /**
         * Creamos un objeto BSON (Binary JSON) para filtrar a aquellas personas
         * con 18 años o más
         */
        Bson filtro = gte("edad", 18);

        /**
         * Creamos otro objeto BSON, pero ahora para ordenar los resultados de
         * manera ascendente, primero por la edad y luego por el nombre
         */
        Bson orden = orderBy(ascending("edad"), ascending("nombre"));

        // Hacemos la consulta y guardamos los resultados en la lista de personas
        coleccion.find(filtro).sort(orden).into(listaPersonas);

        // Iteramos sobre la lista y vamos imprimiendo a cada persona
        int i = 1;
        for (Persona persona : listaPersonas) {
            System.out.println("Persona " + i + " - " + persona);
            i++;
        }
    }

    /**
     * Método para crear un documento en la base datos usando la clase POJO.
     *
     * @param persona Persona a insertar en la base de datos.
     */
    public void crearDocumento(Persona persona) {
        // Obtiene la colección de MongoDB para el tipo de datos "Persona"
        MongoCollection<Persona> coleccion = dataBase.getCollection("Personas", Persona.class);

        // Mandamos a crear el documento
        coleccion.insertOne(persona);
    }

    /**
     * Método para crear un documento en la base datos sin usar la clase POJO.
     *
     * @param nombre Nombre para asignarle al nuevo documento.
     * @param edad Edad para asignarle al nuevo documento.
     */
    public void crearDocumento(String nombre, Integer edad) {
        // Obtiene la colección de MongoDB con el nombre "Personas"
        MongoCollection<Document> coleccion = dataBase.getCollection("Personas");

        // Creamos un documento, y le añadimos el nombre y la edad del parámetro
        Document persona = new Document();
        persona.append("nombre", nombre)
                .append("edad", edad);

        // Mandamos a crear el documento
        coleccion.insertOne(persona);
    }

    /**
     * Método para actualizar un documento de la base datos, también funciona
     * sin usar la clase POJO.
     */
    public void actualizarDocumento() {
        // Obtiene la colección de MongoDB para el tipo de datos "Persona"
        MongoCollection<Document> coleccion = dataBase.getCollection("Personas");

        // Creamos un filtro para actualizar al documento con un ObjectId específico
        Document filtro = new Document().append("_id", new ObjectId("661726e45f699d153b30cedd"));
        /**
         * Creamos un BSON para indicar los atributos que se van a actualizar
         * mediante los métodos combine y set
         */
        Bson actualizacion = combine(
                set("edad", 18),
                set("nombre", "modificado"));

        // Mandamos a actualizar el documento
        coleccion.updateOne(filtro, actualizacion);
    }

    /**
     * Método para eliminar un documento de la base datos, también funciona sin
     * usar la clase POJO.
     */
    public void eliminarDocumento() {
        // Obtiene la colección de MongoDB para el tipo de datos "Persona"
        MongoCollection<Document> coleccion = dataBase.getCollection("Personas");

        // Creamos un filtro para eliminar al documento con un ObjectId específico
        Document filtro = new Document().append("_id", new ObjectId("6614929d0622f061cf24dbd8"));

        // Mandamos a eliminar el documento
        coleccion.deleteOne(filtro);
    }
}
