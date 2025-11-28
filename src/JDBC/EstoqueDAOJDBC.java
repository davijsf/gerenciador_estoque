package JDBC;

import java.util.List;
import Models.estoque;
import Interfaces.EstoqueDAO;
import java.sql.*;
import java.util.ArrayList;

public class EstoqueDAOJDBC implements EstoqueDAO {
    private final Connection conn;

    public EstoqueDAOJDBC(Connection conn) {
        this.conn = conn;
    }


}
