
package entidades;

public class OpinionBean {
   private Integer idOpinion; 
   private Integer idPaciente;
   private Integer idDoctor;
   private Integer calificacion;
   private String mensaje;

    public OpinionBean() {
    }

    public OpinionBean(Integer idOpinion, Integer idPaciente, Integer idDoctor, Integer calificacion, String mensaje) {
        this.idOpinion = idOpinion;
        this.idPaciente = idPaciente;
        this.idDoctor = idDoctor;
        this.calificacion = calificacion;
        this.mensaje = mensaje;
    }

    public Integer getIdOpinion() {
        return idOpinion;
    }

    public void setIdOpinion(Integer idOpinion) {
        this.idOpinion = idOpinion;
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

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Opinion{" + "idOpinion=" + idOpinion + ", idPaciente=" + idPaciente + ", idDoctor=" + idDoctor + ", calificacion=" + calificacion + ", mensaje=" + mensaje + '}';
    }
   
   
}

