
package entidades;

import java.time.LocalDate;

public class Reporte {
    private int idReporte;
    private String nombre;
    private LocalDate fecha;

    public Reporte(int idReporte, String nombre, LocalDate fecha) {
        this.idReporte = idReporte;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public Reporte() {
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Reporte{" + "idReporte=" + idReporte + ", nombre=" + nombre + ", fecha=" + fecha + '}';
    }
}
