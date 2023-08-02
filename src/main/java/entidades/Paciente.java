package entidades;

/**
 * Clase Paciente del modelo de negocio Representa a todos los pacientes del
 * sistema. Implementa un compareTo() que ordena por nroObraSocial.
 *
 * @author Angonoa Franco
 */
public class Paciente extends Persona {

    private Integer idPaciente;
    private Boolean obraSocial;

    public Paciente(Boolean obraSocial, Integer idPaciente, Integer documento, String nombreApellido) {
        super(documento, nombreApellido);
        this.idPaciente = idPaciente;
        this.obraSocial = obraSocial;
    }
    
    public Paciente(Boolean obraSocial, Integer documento, String nombreApellido) {
        super(documento, nombreApellido);        
        this.obraSocial = obraSocial;
    }

    public Paciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Paciente() {
    }        
            
    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }    

    public Boolean tieneObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(Boolean obraSocial) {
        this.obraSocial = obraSocial;
    }

    @Override
    public String toString() {
        return "\nPaciente: Tiene obra social? " + obraSocial 
                + "\t " + super.toString();
    }

}
