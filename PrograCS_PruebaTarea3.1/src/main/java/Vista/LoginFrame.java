package Vista;

import Model.Funcionario;
import Model.GestionFuncionario;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel mainPanel;

    private GestionFuncionario gestionFuncionario;

    public LoginFrame(GestionFuncionario gestionFuncionario) {
        this.gestionFuncionario = gestionFuncionario;
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200); // Establece el tamaño de la ventana

        mainPanel = new JPanel(); // Asegúrate de inicializar mainPanel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        textFieldUsername = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");

        mainPanel.add(new JLabel("Username:"));
        mainPanel.add(textFieldUsername);
        mainPanel.add(new JLabel("Contraseña:"));
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);

        setContentPane(mainPanel); // Establece el contentPane aquí
        setLocationRelativeTo(null);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textFieldUsername.getText();
                String password = new String(passwordField.getPassword());
                Funcionario funcionario = gestionFuncionario.autenticarFuncionario(username, password);
                if (funcionario != null) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login exitoso");
                    new OrdenFrame(funcionario).setVisible(true);
                    dispose();  // Cierra la ventana de login
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Credenciales incorrectas");
                }
            }
        });
    }
}

    