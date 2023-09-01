package dao;

import entidades.OpinionBean;
import entidades.Reporte;
import java.sql.SQLException;

public interface IReporteDAO {
    int guardar(Reporte rep) throws SQLException;
}
