package dao;

import entidades.OpinionBean;
import java.sql.SQLException;

public interface IOpinionDAO {
    int guardar(OpinionBean opinion) throws SQLException;
}
