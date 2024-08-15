
package Vista;

import Controlador.ControladorCompra;
import Model.Funcionario;
import Model.GestionCompra;
import Model.GestionProducto;
import Model.Producto;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OrdenFrame extends JFrame {
    
    private JPanel mainPanel;
    private JComboBox<String> comboBoxProductos;
    private JButton agregarProductoButton;
    private JButton registrarOrdenButton;
    private JTextArea textAreaOrdenes;
    private Funcionario funcionario;
    private GestionProducto gestionProducto;
    private ControladorCompra controladorCompra;
    private List<Integer> productosSeleccionados;

    public OrdenFrame(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.gestionProducto = new GestionProducto();
        this.controladorCompra = new ControladorCompra(new GestionCompra(), gestionProducto);
        this.productosSeleccionados = new ArrayList<>();
        setTitle("Ordenes de Compra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300); // Establece el tamaño de la ventana

        mainPanel = new JPanel(); // Asegúrate de inicializar mainPanel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        comboBoxProductos = new JComboBox<>();
        agregarProductoButton = new JButton("Agregar Producto");
        registrarOrdenButton = new JButton("Registrar Orden");
        textAreaOrdenes = new JTextArea(10, 30);
        textAreaOrdenes.setEditable(false);

        mainPanel.add(new JLabel("Seleccione Producto:"));
        mainPanel.add(comboBoxProductos);
        mainPanel.add(agregarProductoButton);
        mainPanel.add(new JLabel("Orden Actual:"));
        mainPanel.add(new JScrollPane(textAreaOrdenes));
        mainPanel.add(registrarOrdenButton);

        setContentPane(mainPanel); // Establece el contentPane aquí
        setLocationRelativeTo(null);

        cargarProductos();

        agregarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBoxProductos.getSelectedIndex();
                if (selectedIndex >= 0) {
                    productosSeleccionados.add(selectedIndex + 1);  // +1 porque los IDs empiezan en 1
                    textAreaOrdenes.append(comboBoxProductos.getSelectedItem() + "\n");
                }
            }
        });

        registrarOrdenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!productosSeleccionados.isEmpty()) {
                    controladorCompra.crearCompra(funcionario.getId(), productosSeleccionados);
                    JOptionPane.showMessageDialog(OrdenFrame.this, "Orden registrada con éxito");
                    productosSeleccionados.clear();
                    textAreaOrdenes.setText("");
                } else {
                    JOptionPane.showMessageDialog(OrdenFrame.this, "Seleccione al menos un producto");
                }
            }
        });
    }

    private void cargarProductos() {
        List<Producto> productos = gestionProducto.obtenerTodosLosProductos();
        for (Producto producto : productos) {
            comboBoxProductos.addItem(producto.getNombre() + " - $" + producto.getPrecio());
        }
    }
}
