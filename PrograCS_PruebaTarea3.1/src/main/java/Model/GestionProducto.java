
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionProducto {

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM Producto";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                productos.add(new Producto(id, nombre, precio));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    public List<Producto> obtenerProductosPorIds(List<Integer> ids) {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM Producto WHERE id IN (" +
                ids.stream().map(id -> "?").reduce((a, b) -> a + ", " + b).orElse("") + ")";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i = 0; i < ids.size(); i++) {
                stmt.setInt(i + 1, ids.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                productos.add(new Producto(id, nombre, precio));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }
}
