/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Vista;

import Modelo.Juego;
import javax.swing.SwingUtilities;

/**
 *
 * @author emili
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            Juego juego = new Juego();
            //pruebra se puede borrar
            for (int i = 0; i < 5; i++) {
                juego.atenderCliente(); juego.agregarIngrediente("cafe"); juego.entregar(); 
            }
        juego.cerrarDia();
            
            VistaCafe ventana = new VistaCafe(juego);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
            
            //prueba, se puede borrar
            
        });
    }
}
