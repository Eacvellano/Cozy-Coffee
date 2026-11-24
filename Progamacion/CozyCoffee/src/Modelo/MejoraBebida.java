/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author emili
 */
public class MejoraBebida extends Mejora{

    private String nombreBebida;

    public MejoraBebida(String nombreBebida, int precio, String nombre) {
        super(nombre, precio);
        this.nombreBebida = nombreBebida;
    }

    

   

    @Override
    //subclase desbloquea bebida
    public void aplicar(Juego j) {
        j.desbloquearBebida(nombreBebida);
    }

    @Override
    
    public String getEfecto() {
     return "desbloquear la bebida: " + nombreBebida; 
    }
    
    
}
