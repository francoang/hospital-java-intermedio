package negocio;

import dao.*;
import dto.CambiarPersonaDTO;
import entidades.*;
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
    private IOpinionDAO opinionDao;
    private ITurnoDAO turnoDAO;
    private IReporteTurnoDAO reporteTurnoDAO;
    private Connection conn;

    public HospitalControlador(boolean esTransaccion) {        
        if(esTransaccion){
            conn = realizarConexion();
            personaDao = new PersonaDAO(conn);
            opinionDao = new OpinionDAO(conn);
            turnoDAO = new TurnoDAO(conn);
            reporteTurnoDAO = new ReporteTurnoDAO(conn);
        }else{
            personaDao = new PersonaDAO();
            opinionDao = new OpinionDAO();
            turnoDAO = new TurnoDAO();
            reporteTurnoDAO = new ReporteTurnoDAO();
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
        List<Paciente> pacientes;
        StringBuilder cadena = new StringBuilder();
        
        try {
            pacientes = personaDao.obtenerPacientes();
            for (Paciente paciente : pacientes) {
                cadena.append(paciente).append("\n");
            }
            
            if (!pacientes.isEmpty()) {
                return cadena.toString();
            }else{
                return "NO HAY PACIENTES REGISTRADOS.\n";
            }
        } catch (SQLException ex) {
            return "OCURRIO UN ERROR: " + ex.getMessage();
        }
    }
    
    public List<Paciente> obtenerListaPacientes() {
        try {
            return personaDao.obtenerPacientes();
        } catch (SQLException ex) {
            return null;
        }        
    }

    @Override
    public String obtenerDoctores() {
        List<Doctor> doctores;
        StringBuilder cadena = new StringBuilder();
        
        try {
            doctores = personaDao.obtenerDoctores();
            for (Doctor doctor : doctores) {
                cadena.append(doctor).append("\n");
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
    
    @Override
    public List<Doctor> obtenerListaDoctores() {
        try {
            return personaDao.obtenerDoctores();
        } catch (SQLException ex) {
            return null;
        }        
    }

    @Override
    public String guardarOpinion(OpinionBean opinion) {
        try {                      
            int result = opinionDao.guardar(opinion);
            return result > 0 ? "SE AGREGÓ UNA OPINION CON ÉXITO" : "NO SE AGREGO LA OPINION";
        } catch (SQLException ex) {
            return "OCURRIO UN PROBLEMA AL AGREGAR UNA OPINION: "+ ex.getMessage();
        }
    }

    @Override
    public String guardarTurno(TurnoBean turno) {
        try {
            int result = turnoDAO.guardarTurno(turno);
            return result > 0 ? "SE AGREGÓ EL TURNO CON ÉXITO" : "NO SE AGREGO EL TURNO";
        } catch (SQLException ex) {
            return "OCURRIO UN PROBLEMA AL AGREGAR UN TURNO: "+ ex.getMessage();
        }
    }    
    
    @Override
    public Doctor buscarDoctorPorId(Doctor doc) {
        try {
            return personaDao.buscarDoctorPorId(doc);
        } catch (SQLException ex) {
            return null;
        }
    }
        
    @Override
    public Paciente buscarPacientePorId(Paciente pac) {
        try {
            return personaDao.buscarPacientePorId(pac);
        } catch (SQLException ex) {
            return null;
        }
    }   

    @Override
    public String enviarReporteTurno(ReporteTurnoBean reporteTurno) {
        try {
            int result = reporteTurnoDAO.enviarReporteTurno(reporteTurno);
            return result > 0 ? "SE AGREGO REPORTE TURNO CON EXITO" : "NO SE AGREGO REPORTE TURNO";
        } catch (SQLException ex) {
            return "OCURRIO UN PROBLEMA AL AGREGAR EL REPORTE TURNO: "+ ex.getMessage();
        }
    }
}
