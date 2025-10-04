/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;
/**
 *
 * @author Iagod
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL = "jdbc:mysql://localhost:3306/space_ijppython?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "sua_senha_aqui";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // driver do Connector/J 8.x
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
