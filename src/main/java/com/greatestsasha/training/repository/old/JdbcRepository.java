/*
package com.greatestsasha.training.repository;

import com.greatestsasha.training.util.Util;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class JdbcRepository implements AutoCloseable {

    private static final String DB_URL = "jdbc:clickhouse://localhost:8123/default";

    private final Connection conn;

    */
/**
     * Creates new instance
     * @throws SQLException in case of connection issue
     *//*

    public JdbcRepository() throws SQLException {
        conn = DriverManager.getConnection(DB_URL);
    }

    */
/**
     * Queries db to get most popular flight route for ths given year
     * @throws SQLException in case of query issue
     *//*

    public void createTable(String tableName) throws SQLException {
        String query = "CREATE TABLE " + tableName + " (" +
                "    id UUID primary key, " +
                "    name VARCHAR(20)" +
                ") ";
        executeQuery(query);
    }

    */
/**
     * Queries db to get most popular flight route for ths given year
     * @param year year to query
     * @throws SQLException in case of query issue
     *//*

    public void popularYearRoutes(int year) throws SQLException {
        String query = "SELECT " +
                "    OriginCityName, " +
                "    DestCityName, " +
                "    COUNT(*) AS flights, " +
                "    bar(flights, 0, 20000, 40) AS bar " +
                "FROM ontime" +
                " WHERE Year = ? " +
                "GROUP BY OriginCityName, DestCityName " +
                "ORDER BY flights DESC LIMIT 20";
        executeQuery(year, query);
    }

    private void executeQuery(int year, String query) throws SQLException {
        long time = System.currentTimeMillis();
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, year);
            try (ResultSet rs = statement.executeQuery()) {
                Util.printRs(rs);
            }
        }
        System.out.println("Time: " + (System.currentTimeMillis() - time) +" ms");
    }

    private void executeQuery(String query) throws SQLException {
        long time = System.currentTimeMillis();
        try (PreparedStatement statement = conn.prepareStatement(query)) {
//            statement.setString(0, string);
            try (ResultSet rs = statement.executeQuery()) {
                Util.printRs(rs);
            }
        }
        System.out.println("Time: " + (System.currentTimeMillis() - time) +" ms");
    }

    @Override
    public void close() throws Exception {
        if (conn != null) {
            conn.close();
        }
    }
}*/
