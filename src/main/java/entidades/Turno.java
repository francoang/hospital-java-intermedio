
package entidades;

public class Turno {
    
    private Integer idTurno;
    private Integer idPaciente;
    private Integer idDoctor;
    private Integer fecha;
    private String motivo;

    public Turno() {
    }

    public Turno(Integer idTurno, Integer idPaciente, Integer idDoctor, Integer fecha, String motivo) {
        this.idTurno = idTurno;
        this.idPaciente = idPaciente;
        this.idDoctor = idDoctor;
        this.fecha = fecha;
        this.motivo = motivo;
    }

    public Integer getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Integer idTurno) {
        this.idTurno = idTurno;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Integer getFecha() {
        return fecha;
    }

    public void setFecha(Integer fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "Turnos{" + "idTurno=" + idTurno + ", idPaciente=" + idPaciente + ", idDoctor=" + idDoctor + ", fecha=" + fecha + ", motivo=" + motivo + '}';
    }
    
    
    
}
