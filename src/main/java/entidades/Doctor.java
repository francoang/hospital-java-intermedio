package entidades;

/**
 * Clase Doctor del modelo de negocio. Representa todo objeto que sea un doctor.
 * Implementa un compareTo() donde ordena por nro. de matricula.
 *
 * @author fnang
 */
public class Doctor extends Persona implements Comparable<Doctor> {

    private Integer idDoctor;
    private Integer matricula;

    public Doctor(Integer matricula, Integer idDoctor, Integer documento, String nombreApellido) {
        super(documento, nombreApellido);
        this.idDoctor = idDoctor;
        this.matricula = matricula;
    }
    
    public Doctor(Integer matricula, Integer documento, String nombreApellido) {
        super(documento, nombreApellido);        
        this.matricula = matricula;
    }
    
    public Doctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Integer getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }        

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "\nDoctor: Matricula: " + matricula 
                + "\t " + super.toString();
    }

    @Override
    public int compareTo(Doctor doc) {
        return this.matricula - doc.getMatricula();
    }

}
