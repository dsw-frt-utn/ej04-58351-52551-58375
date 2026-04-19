package domain;

public class VehiculoElectrico extends Vehiculo {
    private double kwhBase;

    public VehiculoElectrico(String patente, Marca marca, String modelo, int anio, double capacidadCarga,
                             Sucursal sucursal, double kwhBase) {
        super(VehiculoTipo.ELECTRICO, patente, marca, modelo, anio, capacidadCarga, sucursal);
        this.kwhBase = 16;
    }

    
    @Override
    public double calcularConsumo(double kilometros) {
    double consumo = (kilometros / 100) * kwhBase;

    if (capacidadCarga > 1200) {
        consumo *= 1.15;
    }

    return consumo;
    }
    
    public double getKwhBase() {
        return kwhBase;
    }
}
