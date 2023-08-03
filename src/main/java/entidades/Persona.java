package entidades;

/**
 * Clase Persona del modelo de negocio. Representa a todas las personas del
 * sistema.
 *
 * @author Angonoa Franco
 */
public class Persona {

    private Integer documento;
    private String nombreApellido;

    public Persona() {
    }        

    public Persona(Integer documento, String nombreApellido) {        
        this.documento = documento;
        this.nombreApellido = nombreApellido;
    }        

    public Integer getDocumento() {
        return documento;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    @Override
    public String toString() {
        return "Nro. Documento: " + documento + ", Nombre y apellido: " + nombreApellido;
    }
}
