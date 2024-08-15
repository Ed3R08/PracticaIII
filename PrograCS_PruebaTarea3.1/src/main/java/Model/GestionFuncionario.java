
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionFuncionario {

    public Funcionario autenticarFuncionario(String username, String password) {
        Funcionario funcionario = null;
        String query = "SELECT * FROM Funcionario WHERE username = ? AND password = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                funcionario = new Funcionario(id, nombre, apellido, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionario;
    }
}