package negocio;

import dao.IPersonaDAO;
import dao.PersonaDAO;
import dto.CambiarPersonaDTO;
import entidades.Doctor;
import entidades.Persona;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import sql.Conexion;
import static sql.Conexion.getConnection;

/**
 *
 * @author franc
 */
public class HospitalControlador implements IHospitalControlador{
    
    private IPersonaDAO personaDao;
    private Connection conn;

    public HospitalControlador(boolean esTransaccion) {        
        if(esTransaccion){
            conn = realizarConexion();
            personaDao = new PersonaDAO(conn);
        }else{
            personaDao = new PersonaDAO();
        }
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
    public Persona buscarPorDNI(Persona per) {
        try {
            return personaDao.buscarPorDNI(per);
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public String obtenerTodasPersonas() {
        List<Persona> personas;
        StringBuilder cadena = new StringBuilder();
        
        try {
            personas = personaDao.obtenerTodasPersonas();
            for (Persona persona : personas) {
                //Para personalizar la salida
                cadena.append(persona).append("\n");
            }
            if (!personas.isEmpty()) {
                return cadena.toString();
            }else{
                return "NO HAY PERSONAS REGISTRADAS.\n";
            }
        } catch (SQLException ex) {
            return "OCURRIO UN ERROR: " + ex.getMessage();
        }        
    }

    @Override
    public String cambiarPersona(CambiarPersonaDTO per) {
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
        } finally {
            try {
                Conexion.close(conn);
            } catch (SQLException ex) {
                return "OCURRIO UN PROBLEMA: " + ex.getMessage();
            }
        }
        return null;
    }

    @Override
    public String obtenerPacientes() {
        return null;
    }

    @Override
    public String obtenerDoctores() {
       List<Doctor> doctores;
        StringBuilder cadena = new StringBuilder();
        
        try {
            doctores = personaDao.obtenerDoctores();
            for (Doctor doctor : doctores) {
                //Para personalizar la salida
                cadena.append(doctores).append("\n");
            }
            if (!doctores.isEmpty()) {
                return cadena.toString();
            }else{
                return "NO HAY DOCTORES REGISTRADAS.\n";
            }
        } catch (SQLException ex) {
            return "OCURRIO UN ERROR: " + ex.getMessage();
        }        
    }
    
    
}//
