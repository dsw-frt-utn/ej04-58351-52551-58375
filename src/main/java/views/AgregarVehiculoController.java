package views;
import domain.*;
import java.util.ArrayList;
import data.Persistencia;
import javax.swing.JOptionPane;

public class AgregarVehiculoController {
    
    public static AgregarVehiculoView ventana = new AgregarVehiculoView();
    public static void mostrar(){ ventana.setVisible(true); }
    public static void ocultar(){ ventana.setVisible(false); }
    
    public static void registro(){
        String patente = ventana.getjTextField1().getText().replaceAll("\\s+", "").toUpperCase();
        String marcaNombre = ventana.getjTextField2().getText().trim();
        String vehiculo = ventana.getjTextField3().getText().trim();
        
        String tipo = (String) ventana.getjComboBox1().getSelectedItem();
        String sucursal = ventana.getjTextField5().getText().trim();
        String marcaPais = ventana.getjTextField6().getText().trim();
        
        String anioTexto = ventana.getjTextField7().getText().replaceAll("\\s+", "");
        String litrosExtraTexto = ventana.getjTextField8().getText().replaceAll("\\s+", "");
        
        String capCargaTexto = ventana.getCampoCapCarga().getText().replaceAll("\\s+", "");
        String kmLitroTexto = ventana.getCampoKmlitro().getText().replaceAll("\\s+", "");
        String kmRecorrerTexto = ventana.getCampoKmRecorrer().getText().replaceAll("\\s+", "");
        
        ArrayList<String> errores = new ArrayList<>();
        
        //LISTA DE POSIBLES ERRORES
        patente = ventana.getjTextField1().getText();

        if (patente == null || patente.isBlank()) {
            errores.add("Patente vacía");
            } else {
                patente = patente.trim().toUpperCase();
        if (!patente.matches("^[A-Z]{3}\\d{3}$|^[A-Z]{2}\\d{3}[A-Z]{2}$")) {
            errores.add("Patente inválida (ABC123 o AB123CD)");
            }
        }
        if (marcaNombre == null || marcaNombre.isBlank()) {
            errores.add("Marca vacía");
        }
        if (vehiculo == null || vehiculo.isBlank()) {
            errores.add("Modelo vacío");
        }
        if (sucursal == null || sucursal.isBlank()) {
            errores.add("Sucursal inválida");
        }
        if (marcaPais == null || marcaPais.isBlank()) {
            errores.add("País inválido");
        }
        if (!anioTexto.matches("\\d{4}")) {
            errores.add("Año inválido");
        }
        if (!capCargaTexto.matches("\\d+([.,]\\d+)?")) {
            errores.add("Capacidad inválida");
        }
        if (!kmRecorrerTexto.matches("\\d+([.,]\\d+)?")) {
            errores.add("Km inválidos");
        }
        if (tipo.equals("COMBUSTIBLE")) {
            if (!litrosExtraTexto.matches("\\d+([.,]\\d+)?")) {
                errores.add("Litros extra inválidos");
                }
        if (!kmLitroTexto.matches("\\d+([.,]\\d+)?")) {
            errores.add("Km/Litro inválido");
            }
        }
        
        if (!errores.isEmpty()) {
            String mensajeError = "Por favor, corregí los siguientes errores:\n\n" + String.join("\n", errores);
            JOptionPane.showMessageDialog(ventana, mensajeError, "Datos incompletos o incorrectos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //FIN DE LISTA DE ERRORES
        
        int anio = Integer.parseInt(anioTexto);
        double capCarga = Double.parseDouble(capCargaTexto.replace(",", "."));
        double kmRecorrer = Double.parseDouble(kmRecorrerTexto.replace(",", "."));
        
        double litrosExtra = 0;
        double kmLitro = 0;

            if (tipo.equals("COMBUSTIBLE")) {
            litrosExtra = Double.parseDouble(litrosExtraTexto.replace(",", "."));
            kmLitro = Double.parseDouble(kmLitroTexto.replace(",", "."));
        }

        Marca marca = new Marca(marcaNombre, marcaPais);
        Sucursal objSucursal = new Sucursal(sucursal, "", "", null);
        Vehiculo nuevoVehiculo;
        
        if (tipo.equals("ELECTRICO")) {
             nuevoVehiculo = new VehiculoElectrico(patente, marca, vehiculo, anio, capCarga, objSucursal, litrosExtra);
        } else {
             nuevoVehiculo = new VehiculoCombustible(patente, marca, vehiculo, anio, capCarga, objSucursal, kmLitro, litrosExtra);
        }
        Persistencia.getVehiculos().add(nuevoVehiculo);
        
        ventana.agregarFilaTabla(patente, marcaNombre, vehiculo, tipo, String.valueOf(anio), marcaPais, sucursal, 
                                 String.valueOf(capCarga), String.valueOf(kmLitro), String.valueOf(litrosExtra), String.valueOf(kmRecorrer));
        
        String mensajeExito = "¡El vehículo fue agregado!";
        if (tipo.equals("ELECTRICO")) {
            mensajeExito += "\n\n(Nota: Como está marcada la opción ELECTRICO,\nlos campos Km/Litro y Litros Extra se guardaron automáticamente como 0)";
        }
        
        JOptionPane.showMessageDialog(ventana, mensajeExito, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiarFormulario();
    }
    
   private static void limpiarFormulario() {
        ventana.getjTextField1().setText("");
        ventana.getjTextField2().setText("");
        ventana.getjTextField3().setText("");
        ventana.getjComboBox1().setSelectedIndex(0);
        ventana.getjTextField5().setText("");
        ventana.getjTextField6().setText("");
        ventana.getjTextField7().setText("");
        ventana.getjTextField8().setText("");
        
        ventana.getCampoCapCarga().setText("");
        ventana.getCampoKmlitro().setText("");
        ventana.getCampoKmRecorrer().setText("");
        
        ventana.getjTextField1().requestFocus();
    }
}
