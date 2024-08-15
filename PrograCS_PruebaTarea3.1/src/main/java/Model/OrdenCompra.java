
package Model;

import java.time.LocalDateTime;
import java.util.List;

public class OrdenCompra {
    
    private int id;
    private int idFuncionario;
    private LocalDateTime fechaHora;
    private List<Producto> productos;
    private double total;

    public OrdenCompra(int id, int idFuncionario, LocalDateTime fechaHora, List<Producto> productos, double total) {
        this.id = id;
        this.idFuncionario = idFuncionario;
        this.fechaHora = fechaHora;
        this.productos = productos;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
