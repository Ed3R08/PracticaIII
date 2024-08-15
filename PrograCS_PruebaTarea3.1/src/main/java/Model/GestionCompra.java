package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class GestionCompra {

    public void registrarCompra(int idFuncionario, List<Producto> productos) {
        String query = "INSERT INTO OrdenCompra (idFuncionario, fechaHora, total) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            double total = productos.stream().mapToDouble(Producto::getPrecio).sum();
            LocalDateTime fechaHora = LocalDateTime.now();

            stmt.setInt(1, idFuncionario);
            stmt.setObject(2, fechaHora);
            stmt.setDouble(3, total);

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idOrden = generatedKeys.getInt(1);
                    registrarProductosDeCompra(idOrden, productos, conn);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void registrarProductosDeCompra(int idOrden, List<Producto> productos, Connection conn) throws SQLException {
        String query = "INSERT INTO OrdenProducto (idOrden, idProducto) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            for (Producto producto : productos) {
                stmt.setInt(1, idOrden);
                stmt.setInt(2, producto.getId());
                stmt.executeUpdate();
            }
        }
    }
}