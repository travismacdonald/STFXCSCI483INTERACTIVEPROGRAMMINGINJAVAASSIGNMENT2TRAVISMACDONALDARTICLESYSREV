package com.travismacdonald.articlerevsys.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A lot of code from EasyShift group project:
 * https://github.com/pijner/483_project/blob/main/project/src/main/java/com/gameofthreads/project/controller/DBConnector.java
 */
public class ArsRepository {

    private static final String USER = "travism";
    private static final String PASSWORD = "myPassword123$";
    private static final String SERVER_NAME = "localhost";
    private static final String DATABASE_NAME = "ArticleRevTravis";
    private static final int PORT_NUMBER = 3306;

    private static final ArsRepository instance = new ArsRepository();

    private MysqlDataSource dataSource = new MysqlDataSource();

    private ArsRepository() {
        connectToDataSource();
    }

    public static ArsRepository getInstance() {
        return instance;
    }

    public List<Publication> getAllPublications() {
        Connection dbConnection = null;
        Statement dbStatement = null;
        ResultSet result = null;
        Publication publication = null;

        try {
            dbConnection = dataSource.getConnection();
            dbStatement = dbConnection.createStatement();
            String query = "SELECT * FROM Publication";
            result = dbStatement.executeQuery(query);
            result.next();
            System.out.println("Q: " + result.getString("title"));

        } catch (SQLException ex) {
            Logger.getLogger(ArsRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(result, dbStatement, dbConnection);
        }
        return null;
    }

    private void connectToDataSource() {
        dataSource.setURL(getDataSourceUrl());
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
    }

    private void close(ResultSet rs, Statement st, Connection cn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArsRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArsRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (cn != null) {
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArsRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private String getDataSourceUrl() {
        return String.format(
                "jdbc:mysql://%s:%d/%s?allowPublicKeyRetrieval=true&useSSL=false",
                SERVER_NAME,
                PORT_NUMBER,
                DATABASE_NAME
        );
    }

}
