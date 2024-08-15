
package progracs.pruebatarea3;

import Model.GestionFuncionario;
import Vista.LoginFrame;
import javax.swing.SwingUtilities;

public class Panaderia {
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame(new GestionFuncionario()).setVisible(true);
            }
        });
    }
}

