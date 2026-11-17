/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author emili
 */
public class Bebida {
    // Atributos
    private String nombre; 
    private double precio;
    private boolean disponible;
    ArrayList<String> ingredientes = new ArrayList<>();
    
    // Constructor
    public Bebida(String nombre, double precio, boolean disponible) {
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
    }
    
    // Get
    public String getNombre() {
        return nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public boolean estaDisponible() {
        return disponible;
    }
    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }
    
    // Set
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    public void setIngredientes(ArrayList<String> ingredientes) {
        this.ingredientes = ingredientes;
    }
    
    // Métodos de la clase
    
    // Agregar ingredientes: Añade un ingrediente a la preparación actual, evitando repeticiones.
    public void agregarIngredientes(String nombre){
        ingredientes.add(nombre);
    }
    
    // Es Correcta: Comprueba si los ingredientes seleccionados coinciden con los de la receta, sin importar el orden.
    public boolean esCorrecta(ArrayList<String> seleccion){
        try {
        if (seleccion == null) {
            throw new IllegalArgumentException("La selección no puede ser nula");
        }

        if (ingredientes.size() != seleccion.size()) {
            return false;
        }

        return ingredientes.containsAll(seleccion);

    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
        return false;
    }
    }
    
    // Desbloquear: Verifica si un ingredientes esta disponible.
    public void desbloquear(){
        disponible = true;
    }
}
