package app;

import java.util.InvalidPropertiesFormatException;
import views.ListarVehiculosView;
import views.MenuPrincipal;

public class Program {
    public static void main(String[] args) throws IllegalArgumentException, InvalidPropertiesFormatException {
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
    }
}
