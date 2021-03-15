package com.travismacdonald.articlerevsys.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

    private static ArsRepository instance = null;

    private final MysqlDataSource dataSource = new MysqlDataSource();

    private ArsRepository() {
        connectToDataSource();
    }

    public static ArsRepository getInstance() {
        if (instance == null) {
            instance = new ArsRepository();
        }
        return instance;
    }

    public List<Review> getAllReviews() {
        final List<Review> reviews = new ArrayList();
 
        
        Statement query = null;
        String queryStr = null;
        ResultSet rs = null;

        try {
            final Map<Integer, Publication> publicationMap = new HashMap();
            final Map<Integer, List<String>> positivesMap = new HashMap();
            final Map<Integer, List<String>> negativesMap = new HashMap();
            final Map<Integer, List<String>> majorPointsMap = new HashMap();
            final Map<Integer, List<String>> minorPointsMap = new HashMap();
            
            Connection conn = dataSource.getConnection();

            // Get publications
            query = conn.createStatement();
            queryStr = "SELECT * FROM Publication;";
            rs = query.executeQuery(queryStr);

            while (rs.next()) {
                final int publicationId = rs.getInt("pub_id");
                final String publicationTitle = rs.getString("title");
                final String publicationUrl = rs.getString("pub_url");
                final Publication publication = new Publication(
                        publicationTitle,
                        publicationUrl,
                        publicationId
                );
                publicationMap.put(publicationId, publication);
            }

            rs.close();
            query.close();

            // Get positives
            query = conn.createStatement();
            queryStr = "SELECT rev_id, point_body FROM Point WHERE point_type='pos';";
            rs = query.executeQuery(queryStr);

            while (rs.next()) {
                final int reviewId = rs.getInt("rev_id");
                final String pointBody = rs.getString("point_body");

                if (positivesMap.containsKey(reviewId)) {
                    final List<String> pointList = (List<String>) positivesMap.get(reviewId);
                    pointList.add(pointBody);
                } else {
                    final List<String> pointList = new ArrayList();
                    pointList.add(pointBody);
                    positivesMap.put(reviewId, pointList);
                }
            }

            rs.close();
            query.close();

            // Get negatives
            query = conn.createStatement();
            queryStr = "SELECT rev_id, point_body FROM Point WHERE point_type='ngt';";
            rs = query.executeQuery(queryStr);

            while (rs.next()) {
                final int reviewId = rs.getInt("rev_id");
                final String pointBody = rs.getString("point_body");

                if (negativesMap.containsKey(reviewId)) {
                    final List<String> pointList = (List<String>) negativesMap.get(reviewId);
                    pointList.add(pointBody);
                } else {
                    final List<String> pointList = new ArrayList();
                    pointList.add(pointBody);
                    negativesMap.put(reviewId, pointList);
                }
            }

            rs.close();
            query.close();

            // Get Major Points
            query = conn.createStatement();
            queryStr = "SELECT rev_id, point_body FROM Point WHERE point_type='maj';";
            rs = query.executeQuery(queryStr);

            while (rs.next()) {
                final int reviewId = rs.getInt("rev_id");
                final String pointBody = rs.getString("point_body");

                if (majorPointsMap.containsKey(reviewId)) {
                    final List<String> pointList = (List<String>) majorPointsMap.get(reviewId);
                    pointList.add(pointBody);
                } else {
                    final List<String> pointList = new ArrayList();
                    pointList.add(pointBody);
                    majorPointsMap.put(reviewId, pointList);
                }
            }

            rs.close();
            query.close();

            // Get Minor Points
            query = conn.createStatement();
            queryStr = "SELECT rev_id, point_body FROM Point WHERE point_type='min';";
            rs = query.executeQuery(queryStr);

            while (rs.next()) {
                final int reviewId = rs.getInt("rev_id");
                final String pointBody = rs.getString("point_body");

                if (minorPointsMap.containsKey(reviewId)) {
                    final List<String> pointList = (List<String>) minorPointsMap.get(reviewId);
                    pointList.add(pointBody);
                } else {
                    final List<String> pointList = new ArrayList();
                    pointList.add(pointBody);
                    minorPointsMap.put(reviewId, pointList);
                }
            }

            rs.close();
            query.close();

            // Get all reviews
            query = conn.createStatement();
            queryStr = "SELECT * FROM Review;";
            rs = query.executeQuery(queryStr);

            while (rs.next()) {
                final int reviewId = rs.getInt("rev_id");
                final int publicationId = rs.getInt("pub_id");
                final String summary = rs.getString("summary");
                final String recommendation = rs.getString("recommendation");
                final String reviewerName = rs.getString("reviewer_name");

                final Review review = new Review(
                        publicationMap.get(publicationId),
                        summary,
                        positivesMap.get(reviewId),
                        negativesMap.get(reviewId),
                        majorPointsMap.get(reviewId),
                        minorPointsMap.get(reviewId),
//                        null,
                        Recommendation.valueOf(recommendation),
                        reviewerName,
                        reviewId
                );
                reviews.add(review);
            }

            rs.close();
            query.close();

            conn.close();

        } catch (SQLException ex) {

        }
        return reviews;
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

    private String getDataSourceUrl() {
        return String.format(
                "jdbc:mysql://%s:%d/%s?allowPublicKeyRetrieval=true&useSSL=false",
                SERVER_NAME,
                PORT_NUMBER,
                DATABASE_NAME
        );
    }
    
    private Recommendation recStrToEnum(String recStr) {
        switch (recStr){
            case "Accept":
                return Recommendation.ACCEPT;
        }
        return null;
    }

}
