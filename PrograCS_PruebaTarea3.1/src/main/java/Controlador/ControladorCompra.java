package Controlador;

import Model.GestionCompra;
import Model.GestionProducto;
import Model.Producto;
import java.util.List;

public class ControladorCompra {
    private GestionCompra gestionCompra;
    private GestionProducto gestionProducto;

    public ControladorCompra(GestionCompra gestionCompra, GestionProducto gestionProducto) {
        this.gestionCompra = gestionCompra;
        this.gestionProducto = gestionProducto;
    }

    public void crearCompra(int idFuncionario, List<Integer> idsProductos) {
        List<Producto> productos = gestionProducto.obtenerProductosPorIds(idsProductos);
        gestionCompra.registrarCompra(idFuncionario, productos);
    }
}

