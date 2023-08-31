
package entidades;

import java.sql.Date;


public class ReporteTurnoBean {
    
    private String nombre;
    private Date fecha;
    
    public ReporteTurnoBean(){
    }

    public ReporteTurnoBean(String nombre, Date fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "ReporteTurno{" + "nombre=" + nombre + ", fecha=" + fecha + '}';
    }
    
    
    
}
