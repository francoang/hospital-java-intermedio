package dao;

import entidades.Opinion;
import java.sql.SQLException;

public interface IOpinionDAO {
    int guardar(Opinion opinion) throws SQLException;
}
