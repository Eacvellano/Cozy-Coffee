/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author emili
 */
public class MejoraPropina extends Mejora{
    private int incremento;
    public MejoraPropina(String nombre, int precio, int incremento) {
        super(nombre, precio);
        this.incremento = incremento;
    }

   

    @Override
    //suma al abono de propina
    public void aplicar(Juego j) {
        j.agregarBono(incremento);
    }

    @Override
    public String getEfecto() {
        return "+" + incremento + "de propina por pedido correcto";
    }
}
