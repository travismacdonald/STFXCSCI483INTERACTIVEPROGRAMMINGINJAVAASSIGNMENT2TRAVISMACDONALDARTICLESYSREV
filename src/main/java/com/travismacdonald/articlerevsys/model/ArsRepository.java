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
//        Connection dbConnection = null;
//        Statement dbStatement = null;
//        ResultSet result = null;
//        Publication publication = null;
//
//        try {
//            dbConnection = dataSource.getConnection();
//            dbStatement = dbConnection.createStatement();
//            String query = "SELECT * FROM Publication";
//            result = dbStatement.executeQuery(query);
//            result.next();
//            System.out.println("Q: " + result.getString("title"));
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ArsRepository.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            close(result, dbStatement, dbConnection);
//        }
        return null;
    }

    public boolean addReview(Review review) {
        Connection conn = null;
        PreparedStatement publicationStatement = null;
        PreparedStatement reviewStatement = null;
        try {
            conn = dataSource.getConnection();

            final Publication publication = review.getPublication();
            insertPublication(conn, review.getPublication());
            insertReview(conn, review);
            insertPoints(conn, review);
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ArsRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    // Inserts Publication into database and updates its ID to the generated value 
    // from the database
    private void insertPublication(Connection conn, Publication publication)
            throws SQLException {
        String publicationQueryStr = "INSERT INTO Publication VALUES(?, ?, ?);";
        PreparedStatement publicationQuery = conn.prepareStatement(
                publicationQueryStr,
                Statement.RETURN_GENERATED_KEYS
        );

        publicationQuery.setInt(1, AUTO_INCREMENT);
        publicationQuery.setString(2, publication.getTitle());
        publicationQuery.setString(3, publication.getUrl());
        publicationQuery.execute();

        // Get generated publication ID
        ResultSet rs = publicationQuery.getGeneratedKeys();
        rs.next();
        final int publicationId = rs.getInt(1);
        publication.setId(publicationId);
        publicationQuery.close();
        rs.close();
    }

    // Inserts Review into database and updates its ID to the generated value 
    // from the database
    private void insertReview(Connection conn, Review review)
            throws SQLException {
        String reviewQueryStr = "INSERT INTO Review VALUES(?, ?, ?, ?, ?);";
        PreparedStatement reviewQuery = conn.prepareStatement(
                reviewQueryStr,
                Statement.RETURN_GENERATED_KEYS
        );
        reviewQuery.setInt(1, AUTO_INCREMENT);
        reviewQuery.setInt(2, review.getPublication().getId());
        reviewQuery.setString(3, review.getSummary());
        reviewQuery.setString(4, review.getRecommendation().name());
        reviewQuery.setString(5, review.getReviewerName());
        reviewQuery.execute();

        // Get generated review ID
        final ResultSet rs = reviewQuery.getGeneratedKeys();
        rs.next();
        final int reviewId = rs.getInt(1);
        review.setId(reviewId);
        reviewQuery.close();
        rs.close();
    }

    private void insertPoints(Connection conn, Review review)
            throws SQLException {

        final String pointsQueryStr = "INSERT INTO Point VALUES(?, ?, ?, ?);";
        final PreparedStatement pointsQuery = conn.prepareStatement(pointsQueryStr);
        
        for (String pos : review.getPositives()) {
            pointsQuery.setInt(1, AUTO_INCREMENT);
            pointsQuery.setInt(2, review.getId());
            pointsQuery.setString(3, pos);
            pointsQuery.setString(4, "pos");
            pointsQuery.addBatch();
        }
        for (String ngt : review.getNegatives()) {
            pointsQuery.setInt(1, AUTO_INCREMENT);
            pointsQuery.setInt(2, review.getId());
            pointsQuery.setString(3, ngt);
            pointsQuery.setString(4, "ngt");
            pointsQuery.addBatch();
        }
        for (String maj : review.getMajorPoints()) {
            pointsQuery.setInt(1, AUTO_INCREMENT);
            pointsQuery.setInt(2, review.getId());
            pointsQuery.setString(3, maj);
            pointsQuery.setString(4, "maj");
            pointsQuery.addBatch();
        }
        for (String min : review.getMinorPoints()) {
            pointsQuery.setInt(1, AUTO_INCREMENT);
            pointsQuery.setInt(2, review.getId());
            pointsQuery.setString(3, min);
            pointsQuery.setString(4, "min");
            pointsQuery.addBatch();
        }
        
        pointsQuery.executeBatch();
        pointsQuery.close();
    }

    private void connectToDataSource() {
        dataSource.setURL(getDataSourceUrl());
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
    }

//    private void close(ResultSet rs, Statement st, Connection cn) {
//        if (rs != null) {
//            try {
//                rs.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(ArsRepository.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        if (st != null) {
//            try {
//                st.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(ArsRepository.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        if (cn != null) {
//            try {
//                cn.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(ArsRepository.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }

    private String getDataSourceUrl() {
        return String.format(
                "jdbc:mysql://%s:%d/%s?allowPublicKeyRetrieval=true&useSSL=false",
                SERVER_NAME,
                PORT_NUMBER,
                DATABASE_NAME
        );
    }

}
