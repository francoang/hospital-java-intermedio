package negocio;

import dao.IPersonaDAO;
import dao.PersonaDAO;
import entidades.Persona;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sql.Conexion.getConnection;

/**
 *
 * @author franc
 */
public class HospitalControlador implements IHospitalControlador{
    
    private IPersonaDAO personaDao;
    private Connection conn;

    public HospitalControlador() {
        conn = realizarConexion();
        personaDao = new PersonaDAO(conn);
    }       
    
    private Connection realizarConexion(){
        try {
            Connection conexion = getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            return conexion;
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public String agregarPersona(Persona persona) {        
        try {
            int result = personaDao.agregar(persona);
            return result > 0 ? "SE AGREGÓ UNA PERSONA CON ÉXITO" : "NO SE AGREGO LA PERSONA";
        } catch (SQLException ex) {
            return "OCURRIO UN PROBLEMA AL AGREGAR UNA PERSONA: "+ ex.getMessage();
        }
    }

    @Override
    public String modificarPersona(Persona per) {        
        try {
            int result = personaDao.modificar(per);
            return result > 0 ? "SE MODIFICÓ UNA PERSONA CON ÉXITO" : "NO SE MODIFICÓ LA PERSONA";
        } catch (SQLException ex) {
            return "OCURRIO UN PROBLEMA AL MODIFICAR UNA PERSONA: "+ ex.getMessage();
        }
    }

    @Override
    public String borrarPersona(Persona per) {
        try {
            int result = personaDao.borrar(per);
            return result > 0 ? "SE BORRÓ UNA PERSONA CON ÉXITO" : "NO SE BORRÓ LA PERSONA";
        } catch (SQLException ex) {
            return "OCURRIO UN PROBLEMA AL BORRAR UNA PERSONA: "+ ex.getMessage();
        }
    }

    @Override
    public String buscarPorDNI(int documento) {
        return null;
    }

    @Override
    public String obtenerTodasPersonas() {
        List<Persona> personas;
        
        try {
            personas = personaDao.obtenerTodasPersonas();
            if (!personas.isEmpty()) {
                return "LISTADO DE PERSONAS REGISTRADAS: \n" + personas;
            }
        } catch (SQLException ex) {
            return "OCURRIO UN ERROR: " + ex.getMessage();
        }

        return "AÚN NO HAY PERSONAS REGISTRADAS.\n";
    }

    @Override
    public String cambiarPersona(Persona per) {
        try {
            int cambios = personaDao.cambiar(per);
            conn.commit();
            
            return (cambios > 0) ? "PACIENTE CAMBIADO CON ÉXITO." 
                    : "NO SE HA ENCONTRADO EL PACIENTE.";
        } catch (SQLException ex) {  
            try {
                conn.rollback();
                return "NO SE HA PODIDO CAMBIAR AL PACIENTE: " + ex.getMessage();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }
        return null;
    }
    
}
