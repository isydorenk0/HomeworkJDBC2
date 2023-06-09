package DAO;

import java.sql.Connection;

public interface IDAOFactory {
    Connection getConnection();
}