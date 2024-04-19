package colecciones;

import org.bson.types.ObjectId;

/**
 * Clase POJO que representa un documento de la colección Personas.
 * 
 * @author Diego Valenzuela Parra - 00000247700
 */
public class Persona {
    
    private ObjectId id;
    private String nombre;
    private Integer edad;

    public Persona() {
    }
    
    public Persona(String nombre, Integer edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    
    @Override
    public String toString() {
        return "Nombre: " + this.nombre + ", Edad: " + this.edad;
    }
}
