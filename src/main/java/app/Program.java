package app;

import java.util.InvalidPropertiesFormatException;
import views.ListarVehiculosView;

public class Program {
    public static void main(String[] args) throws IllegalArgumentException, InvalidPropertiesFormatException {
        ListarVehiculosView view = new ListarVehiculosView();
        view.setVisible(true);
    }
}
