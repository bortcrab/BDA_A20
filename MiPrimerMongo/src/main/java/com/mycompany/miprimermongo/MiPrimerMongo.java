package com.mycompany.miprimermongo;

import colecciones.Persona;

/**
 * Clase principal de la asignación 20.
 * 
 * @author Diego Valenzuela Parra - 00000247700
 */
public class MiPrimerMongo {

    public static void main(String[] args) {
        
        PersonaDAO personaDAO = new PersonaDAO();
        
        // PUNTO 1
        System.out.println("1. TODAS LAS PERSONAS DE LA COLECCIÓN.");
        personaDAO.leerPersonas();
        System.out.println("");
        
        
        // PUNTO 2
        System.out.println("2. TODAS LAS PERSONAS DE LA COLECCIÓN CON 18 AÑOS O MÁS\n"
                + "ORDENADAS SEGÚN LA EDAD Y EL NOMBRE.");
        personaDAO.leerPersonasEdadNombre();
        System.out.println("");
        
        
        // PUNTO 3 USANDO LA CLASE POJO
        System.out.println("3. CREANDO UN DOCUMENTO USANDO LA CLASE POJO");
        // Creamos una persona
        Persona persona = new Persona("Héctor", 20);
        // Mandamos a crear el documento a la base de datos con los datos de la
        // nueva persona
        personaDAO.crearDocumento(persona);
        
        // Prueba de que sí se creó
        System.out.println("Lista de personas actualizada:");
        personaDAO.leerPersonas();
        System.out.println("");
        
        
        // PUNTO 3 SIN USAR LA CLASE POJO
        System.out.println("3. CREANDO UN DOCUMENTO SIN USAR LA CLASE POJO");
        // Mandamos a crear el documento con un nombre y edad
        personaDAO.crearDocumento("Ricardo", 22);
        
        // Prueba de que sí se creó
        System.out.println("Lista de personas actualizada:");
        personaDAO.leerPersonas();
        System.out.println("");
        
        
        // PUNTO 4
        System.out.println("4. ACTUALIZANDO UN DOCUMENTO");
        // Mandamos a actualizar un documento
        personaDAO.actualizarDocumento();
        
        // Prueba de que sí se actualizó
        System.out.println("Lista de personas actualizada:");
        personaDAO.leerPersonas();
        System.out.println("");
        
        
        // PUNTO 5
        System.out.println("5. ELIMINANDO UN DOCUMENTO");
        // Mandamos a eliminar un documento
        personaDAO.eliminarDocumento();
        
        // Prueba de que sí se eliminó
        System.out.println("Lista de personas actualizada:");
        personaDAO.leerPersonas();
    }
}
