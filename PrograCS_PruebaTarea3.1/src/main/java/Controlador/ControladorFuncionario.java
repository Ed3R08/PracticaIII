
package Controlador;

import Model.Funcionario;
import Model.GestionFuncionario;


public class ControladorFuncionario {
    
    private GestionFuncionario gestionFuncionario;

    public ControladorFuncionario() {
        gestionFuncionario = new GestionFuncionario();
    }

    public Funcionario autenticarFuncionario(String username, String password) {
        return gestionFuncionario.autenticarFuncionario(username, password);
    }
}

