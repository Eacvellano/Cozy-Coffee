/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author emili
 */
public class Cliente {
     private String nombre;
    private String imagen;   // nombre del archivo en recursos, ej. "cliente1.png"
    private Bebida pedido;

    
    //constructor
    public Cliente(String nombre, String imagen, Bebida pedido) {
         if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El cliente necesita un nombre.");
        }
        if (imagen == null || imagen.isBlank()) {
            throw new IllegalArgumentException("El cliente necesita una imagen.");
        }
        if (pedido == null) {
            throw new IllegalArgumentException("El cliente necesita un pedido.");
        }
        this.nombre = nombre;
        this.pedido = pedido;
         this.imagen = imagen;
    }

    
    //sets
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
     public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setPedido(Bebida pedido) {
        this.pedido = pedido;
    }
//getts
    public String getNombre() {
        return nombre;
    }
    
     public String getImagen() {
        return imagen;
    }

    public Bebida getPedido() {
        return pedido;
    }
    
    
    // Texto que aparece en el globo de diálogo (RF05)
    public String getDialogo() {
        return "¡Hola! Me gustaría un " + pedido.getNombre() + ", por favor.";
    }
    
    
    private static final String[][] CLIENTES = {
    {"Avellano", "/imagenes/clientes/ana.png"},
    {"Filipinas", "/imagenes/clientes/luis.png"},
    {"AzulCielo", "/imagenes/clientes/marta.png"},
    {"Juan Rulfo", "/imagenes/clientes/pablo.png"},
    {"Mildred", "/imagenes/clientes/sofia.png"}
};
private static final Random AZAR = new Random();

public static Cliente crearAleatorio(List<Bebida> catalogo) {
    List<Bebida> opciones = new ArrayList<>();
    for (Bebida b : catalogo) {
        if (b.estaDisponible()) {
            opciones.add(b);
        }
    }
    if (opciones.isEmpty()) {
        throw new IllegalArgumentException("No hay bebidas disponibles.");
    }
    String[] datos = CLIENTES[AZAR.nextInt(CLIENTES.length)];
    Bebida pedido = opciones.get(AZAR.nextInt(opciones.size()));
    return new Cliente(datos[0], datos[1], pedido);
}
}
