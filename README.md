# ☕ Cozy Coffee

Juego de cafetería desarrollado en Java con Swing y NetBeans. Prepara bebidas combinando ingredientes y atiende los pedidos de distintos clientes. 🐼

## 🎮 Cómo jugar

1. 💬 Lee el pedido del cliente.
2. 🥛 Pulsa los ingredientes de la estantería para agregarlos al vaso.
3. 🧹 Usa **Limpiar** para comenzar de nuevo.
4. ✅ Pulsa **Entregar** para comprobar la preparación.
5. 🌅 Después de atender cinco clientes, pulsa **Entregar** para avanzar al siguiente día.

Los pedidos se generan aleatoriamente con los ingredientes disponibles. Cada cliente solicita entre uno y cuatro ingredientes y la preparación debe coincidir con su pedido.

## ✨ Funciones actuales

- 🎲 Pedidos y clientes aleatorios.
- 👆 Selección de ingredientes mediante imágenes.
- ✅ Validación de las bebidas entregadas.
- 📊 Contadores de día, saldo, aciertos y clientes atendidos.
- 📅 Partidas de siete días con cinco clientes por jornada.
- 💰 Ingresos por ventas y propinas al cerrar el día.
- 🎨 Interfaz ilustrada con fondos, personajes y botones personalizados.

## 🧺 Ingredientes disponibles

☕ Café, 💧 agua, 🥛 leche, 🍫 chocolate, 🧊 azúcar, 🍦 crema, 🌼 vainilla, 🪵 canela y 🍁 maple.

🔒 Matcha y caramelo aparecen bloqueados y están pendientes de integración.

## 🛠️ Tecnologías

- ☕ Java.
- 🖥️ Java Swing.
- 📦 Apache NetBeans.
- 🐙 Git y GitHub.

## 🚀 Ejecutar el proyecto

1. Abre NetBeans.
2. Selecciona **File → Open Project**.
3. Abre la carpeta `Progamacion/CozyCoffee`.
4. Comprueba que el proyecto tenga configurado un JDK compatible.
5. Ejecuta con **F6**.
6. Si se solicita la clase principal, selecciona `Vista.Main`.

## 📂 Organización

El código fuente se encuentra en `Progamacion/CozyCoffee/src`.

| Paquete | Contenido |
| --- | --- |
| `Modelo` | Estado de la partida, clientes, bebidas y clases de mejoras |
| `Vista` | Ventana principal y paneles de la interfaz |
| `Vista/imagenes` | Fondos, personajes, ingredientes y botones |

### 🖼️ Paneles principales

- `VistaCafe` integra la interfaz y actualiza los paneles.
- `PanelSuperior` muestra los indicadores de la partida.
- `PanelIngredientes` permite seleccionar ingredientes.
- `PanelCliente` muestra el personaje y su pedido.
- `PanelAcciones` muestra la preparación y los botones.

## 🚧 Estado del proyecto

Proyecto en desarrollo colaborativo. El ciclo básico de preparación y entrega es jugable. ☕

La tienda de mejoras, el guardado y la carga de partidas continúan pendientes.
