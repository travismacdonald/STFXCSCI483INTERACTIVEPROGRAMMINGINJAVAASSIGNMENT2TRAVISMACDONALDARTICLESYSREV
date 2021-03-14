package com.travismacdonald.articlerevsys.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A lot of code from EasyShift group project:
 * https://github.com/pijner/483_project/blob/main/project/src/main/java/com/gameofthreads/project/controller/DBConnector.java
 */
public class ArsRepository {

    private static final int AUTO_INCREMENT = 0;

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

    public boolean addReview(Review review) {
        Connection connection = null;
        PreparedStatement publicationStatement = null;
        PreparedStatement reviewStatement = null;
        try {
            final Publication publication = review.getPublication();
            connection = dataSource.getConnection();

            String str2 = "INSERT INTO Publication VALUES(?, ?, ?);";
            PreparedStatement query = connection.prepareStatement(
                    str2,
                    Statement.RETURN_GENERATED_KEYS
            );
            query.setInt(1, AUTO_INCREMENT);
            query.setString(2, publication.getTitle());
            query.setString(3, publication.getUrl());
            query.execute();

            ResultSet rs = query.getGeneratedKeys();
            rs.next();
            int publicationId = rs.getInt(1);
            System.out.println("PUBLICATION ID: " + publicationId);

//            final String publicationInsertionString = String.format(
//                    "INSERT INTO Publication VALUES(0, '%s', '%s');",
//                    publication.getTitle(),
//                    publication.getUrl()
//            );
//            publicationStatement = connection.prepareStatement(
//                    publicationInsertionString,
//                    Statement.RETURN_GENERATED_KEYS
//            );
//            publicationStatement.executeUpdate();          
//            ResultSet rs = publicationStatement.getGeneratedKeys();
//            rs.next();
//            int publicationId = rs.getInt(1);
//            System.out.println("PUBLICATION ID: " + publicationId);
            rs.close();
//            publicationStatement.close();

            final String reviewInsertionString = String.format(
                    "INSERT INTO Review VALUES(0, %d, '%s', '%s', '%s');",
                    publicationId,
                    review.getSummary(),
                    review.getRecommendation().name(),
                    review.getReviewerName()
            );
            reviewStatement = connection.prepareStatement(
                    reviewInsertionString,
                    Statement.RETURN_GENERATED_KEYS
            );
            reviewStatement.executeUpdate();
            rs = reviewStatement.getGeneratedKeys();
            rs.next();
            int reviewId = rs.getInt(1);
            System.out.println("REVIEW ID: " + reviewId);

            reviewStatement.close();
            rs.close();

            Statement pointsStatement = connection.createStatement();
            final List<String> pointsInsertionStrings = new ArrayList();
            for (String pos : review.getPositives()) {
                final String str = String.format(
                        "INSERT INTO Point VALUES(0, %d, '%s', '%s');",
                        reviewId,
                        pos,
                        "pos"
                );
                pointsStatement.addBatch(str);
            }
            for (String ngt : review.getNegatives()) {
                final String str = String.format(
                        "INSERT INTO Point VALUES(0, %d, '%s', '%s');",
                        reviewId,
                        ngt,
                        "ngt"
                );
                pointsStatement.addBatch(str);
            }
            for (String maj : review.getMajorPoints()) {
                final String str = String.format(
                        "INSERT INTO Point VALUES(0, %d, '%s', '%s');",
                        reviewId,
                        maj,
                        "maj"
                );
                pointsStatement.addBatch(str);
            }
            for (String min : review.getMinorPoints()) {
                final String str = String.format(
                        "INSERT INTO Point VALUES(0, %d, '%s', '%s');",
                        reviewId,
                        min,
                        "min"
                );
                pointsStatement.addBatch(str);
            }
            pointsStatement.executeBatch();

            pointsStatement.close();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ArsRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
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
