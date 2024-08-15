
package Controlador;

import Model.GestionProducto;
import Model.Producto;
import java.util.List;

public class ControladorProducto {

    private GestionProducto gestionProducto;

    public ControladorProducto() {
        gestionProducto = new GestionProducto();
    }

    public List<Producto> obtenerTodosLosProductos() {
        return gestionProducto.obtenerTodosLosProductos();
    }
}