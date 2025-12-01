package DAO;

import JDBC.*;
import Interfaces.*;
import Models.Conexao;
import java.sql.Connection;

public class DAOFactory {

    private static Connection getConnection() {return Conexao.conectar();}

    public static FuncionarioDAO createFuncionarioDAO() {return new FuncionarioDAOJDBC(getConnection());}

    public static ProdutoDAO createProdutoDAO() {return new ProdutoDAOJDBC(getConnection());}

    public static EstoqueDAO createEstoqueDAO() {return new EstoqueDAOJDBC(getConnection());    }

    public static FornecedorDAO createFornecedorDAO() {return new FornecedorDAOJDBC(getConnection());}
}
