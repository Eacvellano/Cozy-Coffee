/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelo;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author emili
 */

public class Juego implements Guardable {
    // Estado de la partida.
    private int dia;
    private int saldo;
    private int atendidos;
    private int aciertos;
    private int ventasDia;
    private int propinasDia;
    private int bonoPropina;
    private boolean pausado;
    private boolean cerrado;

    private final ArrayList<String> seleccion = new ArrayList<>();
    private Cliente clienteEnPantalla;

    // Catálogo provisional para integrar después el de la persona 3.
    private final ArrayList<Bebida> bebidas = new ArrayList<>();
    private final Random azar = new Random();
    private String estado = "";
    private int ultimoCliente = 0;
    //catalogo de mejoras de la tienda
    private final ArrayList<Mejora> catalogo = new ArrayList<>(); 
    
    public Juego() {
        crearBebida("Café", 12, true, "cafe");
        crearBebida("Café con leche", 16, true, "cafe", "leche");
        crearBebida("Chocolate con leche", 18, true, "chocolate", "leche");
        crearBebida("Café con caramelo", 20, false, "cafe", "caramelo");

        nuevaPartida();
    }

    private void crearBebida(String nombre, int precio,
            boolean disponible, String... ingredientes) {
        Bebida bebida = new Bebida(nombre, precio, disponible);
        for (String ingrediente : ingredientes) {
            bebida.agregarIngredientes(ingrediente);
        }
        bebidas.add(bebida);
    }

    public void nuevaPartida() {
        dia = 1;
        saldo = 0;
        bonoPropina = 0;

        for (Bebida bebida : bebidas) {
            bebida.setDisponible(!bebida.getNombre().equals("Café con caramelo"));
        }
        crearCatalogo();
        reiniciarDia();
        atenderCliente();
    }

    private void reiniciarDia() {
        atendidos = 0;
        aciertos = 0;
        ventasDia = 0;
        propinasDia = 0;
        pausado = false;
        cerrado = false;
        seleccion.clear();
        clienteEnPantalla = null;
        estado = "";
    }

    public void atenderCliente() {
        // No reemplazar un pedido que todavía está pendiente.
        if (pausado || cerrado || clienteEnPantalla != null) {
            return;
        }

        if (atendidos >= 5) {
            estado = "Puedes cerrar el día.";
            return;
        }

        ArrayList<Bebida> disponibles = new ArrayList<>();

        for (Bebida bebida : bebidas) {
            if (bebida.estaDisponible()) {
                disponibles.add(bebida);
            }
        }

        if (disponibles.isEmpty()) {
            estado = "No hay bebidas disponibles.";
            return;
        }

        // Construye la receta antes del diálogo y la conserva para validar la entrega.
        String[] bases = {"cafe", "chocolate", "leche", "agua"};
        String[] extras = {"leche", "azucar", "crema", "vainilla", "canela", "maple"};
        ArrayList<String> receta = new ArrayList<>();
        receta.add(bases[azar.nextInt(bases.length)]);
        ArrayList<String> opciones = new ArrayList<>(java.util.Arrays.asList(extras));
        opciones.removeAll(receta);
         // Con la receta de caramelo comprada, el caramelo puede salir en los pedidos.
         if(bebidaDisponible("Café con caramelo")){
             opciones.add("caramelo");
         }
        java.util.Collections.shuffle(opciones, azar);
        receta.addAll(opciones.subList(0, azar.nextInt(4)));
        Bebida pedido = new Bebida(String.join(" + ", receta), 12 + 3 * (receta.size() - 1), true);
        for (String ingrediente : receta) {
            pedido.agregarIngredientes(ingrediente);
        }

        // El constructor debe coincidir con Cliente según el UML.
        // La imagen se asignará cuando se integren los recursos.

        String[][] clientes = {
            {"Panda", "Friendly Panda Cafe Customer.png"},
            {"Avellano", "Avellano.jpg"}, {"Azul Cielo", "AzulCielo.jpg"},
            {"Filipinas", "Filipinas.png"}, {"Juan Rulfo", "Juan Rulfo.jpg"},
            {"Mildred", "Mildred.jpg"}
        };
        int indice = azar.nextInt(clientes.length - 1);
        if (indice >= ultimoCliente) indice++;
        ultimoCliente = indice;
        clienteEnPantalla = new Cliente(clientes[indice][0],
                "/Vista/imagenes/" + clientes[indice][1], pedido);

        seleccion.clear();
        estado = "Pedido: " + pedido.getNombre() + ".";
    }

    public void agregarIngrediente(String nombre) {
        if (pausado || cerrado || clienteEnPantalla == null || nombre == null) {
            return;
        }

        String ingrediente = nombre.trim().toLowerCase(java.util.Locale.ROOT).replace("é", "e");

        if (ingrediente.isEmpty() || seleccion.contains(ingrediente)) {
            return;
        }
        //el caramelo sigue bloq (RF09)
        if(ingrediente.equals("caramelo") && !bebidaDisponible("Café con caramelo")){
           return; 
        }
        seleccion.add(ingrediente);
    }

    public void limpiar() {
        if (!pausado && !cerrado) {
            seleccion.clear();
        }
    }

    public boolean entregar() {
        if (pausado || cerrado || clienteEnPantalla == null) {
            return false;
        }

        if (seleccion.isEmpty()) {
            estado = "Agrega ingredientes antes de entregar.";
            return false;
        }

        Bebida pedido = clienteEnPantalla.getPedido();
        double precio = pedido.getPrecio();

        // Bebida usa double, pero Juego maneja monedas enteras según el UML.
        if (!Double.isFinite(precio) || precio < 0 || precio > Integer.MAX_VALUE || precio != Math.rint(precio)) {
            estado = "El precio debe ser un número entero válido.";
            return false;
        }

        boolean correcta = pedido.esCorrecta(seleccion);

        if (correcta) {
            aciertos++;
            ventasDia += (int) precio;
            propinasDia += 2 + bonoPropina;
            estado = "Pedido correcto.";
        } else {
            estado = "La preparación no coincide con el pedido.";
        }

        atendidos++;
        seleccion.clear();

        // Retirar el pedido evita contabilizarlo dos veces.
        clienteEnPantalla = null;

        if (atendidos == 5) {
            estado += " Puedes cerrar el día.";
        }

        return correcta;
    }

    public int cerrarDia() {
        if (pausado || cerrado || atendidos != 5) {
            return 0;
        }

        // Sueldo, ventas y propinas se pagan una sola vez.
        int pago = 10 + ventasDia + propinasDia;
        saldo += pago;
        cerrado = true;
        estado = "Día cerrado. Ingreso: $" + pago + ".";
        return pago;
    }

    public void siguienteDia() {
        if (pausado || !cerrado || dia >= 7) {
            return;
        }

        dia++;
        reiniciarDia();
        atenderCliente();
    }

    public void pausar(boolean estado) {
        pausado = estado;
    }

    public void agregarBono(int cantidad) {
        if (cantidad > 0) {
            bonoPropina += cantidad;
        }
    }

    public void desbloquearBebida(String nombre) {
        for (Bebida bebida : bebidas) {
            if (bebida.getNombre().equalsIgnoreCase(nombre)) {
                bebida.desbloquear();
                return;
            }
        }
    }

    // Funciones del UML pendientes para otra etapa.
    public boolean comprarMejora(Mejora m) {
        if(m == null || !catalogo.contains(m)){
            estado = "Mejora no válida";
            return false;
        }
        if(!cerrado || dia >= 7 || pausado){
            estado = "La tienda solo esta disponible al cerrar los dias 1 a 6.";
            return false;
        }
        if(m.estaComprada()){
            estado = "Esa mejora ya fue comprada.";
            return false;
        }
        if(saldo < m.getPrecio()){
            estado = "Saldo insuficiente.";
            return false;
        }
        saldo -= m.getPrecio();
        m.aplicar(this);
        m.marcaComprada();
        estado = "Compraste: " + m.getNombre() + ".";
        //guardar (); y descomentar caudno B terminedaurdar() (RF22)
        return true;
    }

    public void guardar() {
        estado = "El guardado aún no está disponible.";
    }

    public void cargar() {
        estado = "La carga aún no está disponible.";
    }

    public String getEstado() {
        return estado;
    }

    // Consultas utilizadas por los paneles.
    public int getDia() {
        return dia;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getAtendidos() {
        return atendidos;
    }

    public int getAciertos() {
        return aciertos;
    }

    public int getVentasDia() {
        return ventasDia;
    }

    public int getPropinasDia() {
        return propinasDia;
    }

    public boolean estaPausado() {
        return pausado;
    }

    public boolean estaCerrado() {
        return cerrado;
    }

    public Cliente getClienteEnPantalla() {
        return clienteEnPantalla;
    }

    public ArrayList<String> getSeleccion() {
        // Devuelve una copia para proteger la selección original.
        return new ArrayList<>(seleccion);
    }


private void crearCatalogo() {
    catalogo.clear(); // al crear una partida nueva también se reinician las compras
    catalogo.add(new MejoraPropina("Frasco de propinas", 30, 1));
    catalogo.add(new MejoraPropina("Propina generosa", 60, 2));
    catalogo.add(new MejoraBebida("Receta de caramelo", 40, "Café con caramelo"));
}
public boolean bebidaDisponible(String nombre) {
    for (Bebida bebida : bebidas) {
        if (bebida.getNombre().equalsIgnoreCase(nombre)) {
            return bebida.estaDisponible();
        }
    }
    return false;
}
}
