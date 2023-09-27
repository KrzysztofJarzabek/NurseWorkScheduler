package org.example;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;


public class DataBaseClient {

//    JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceSQL.getMySqlDataSource());
//
//
//    public List<NurseData> getAllDataFromDB() {
//
//        try {
//            List<NurseData> query = jdbcTemplate.query("SELECT * FROM worker_data;",
//                    BeanPropertyRowMapper.newInstance(NurseData.class));
//
//            System.out.println(query.get(0).getId());
//            return query;
//        } catch (DataAccessException e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//
//
//    }



    PreparedStatement preparedStatement;
    Connection connection;

    public void connect() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital_worker_data", "root", "Pol123456!");
            System.out.println("Connected successfully!");

        } catch (SQLTimeoutException ex) {
            System.out.println(ex.getMessage());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
//
//    public void addRecords(String name, int value, int valueToItem) {
//        try {
//            preparedStatement = connection.prepareStatement("insert into save_table(name, value, value_to_item) values(?,?,?)");
//            preparedStatement.setString(1, name);
//            preparedStatement.setInt(2, value);
//            preparedStatement.setInt(3, valueToItem);
//            preparedStatement.executeUpdate();
//            System.out.println("SQL database updated. ");
//
//        } catch (SQLException ex) {
//            ex.getMessage();
//            System.out.println("SQL database NOT updated. ");
//        }
//
//    }

    public void readRecords(int recordId) {
        try {
            preparedStatement = connection.prepareStatement("SELECT name, value, value_to_item FROM save_table WHERE id LIKE ?");
            preparedStatement.setInt(1, recordId);
            ResultSet queryResult = preparedStatement.executeQuery();

            queryResult.next();
            System.out.println("Record: " + queryResult.getString(1) + ", "
                    + queryResult.getString(2) + ", " + queryResult.getString(3));

        } catch (SQLException ex) {
            ex.getMessage();
            System.out.println("Can not find record. ");
        }
    }

}
