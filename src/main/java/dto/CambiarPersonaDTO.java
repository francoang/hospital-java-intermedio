
package dto;

/**
 *
 * @author Angonoa Franco
 */
public class CambiarPersonaDTO {
    
    private int documento;
    private int matricula;

    public CambiarPersonaDTO(int documento, int matricula) {
        this.documento = documento;
        this.matricula = matricula;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    
    
}
