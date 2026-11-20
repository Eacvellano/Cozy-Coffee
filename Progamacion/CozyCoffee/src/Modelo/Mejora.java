/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author emili
 */
public abstract class Mejora {
    //Atributos de la clase
    private String nombre;
    private int precio;
    private boolean comprada;

    //Constructor
    public Mejora(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.comprada = false;
    }

    //Método obtener nombre
    public String getNombre(){
        return nombre;
    }

    //Método obtener precio
    public int getPrecio(){
        return precio;
    }

    //Método para saber si esta comprado el artículo o no
    public boolean estaComprada(){
        return comprada;
    }

    //Método para marcar si el artículo esta comprado.
    public void marcaComprada(){
        comprada = true;
    }

    //Abtracto: cada subclase define su efecto.
    public abstract void aplicar(Juego j);
}
