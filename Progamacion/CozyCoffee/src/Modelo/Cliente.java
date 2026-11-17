/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

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
    
    
    
}
