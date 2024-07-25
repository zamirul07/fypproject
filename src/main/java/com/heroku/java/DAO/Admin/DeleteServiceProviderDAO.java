package com.heroku.java.DAO.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeleteServiceProviderDAO {

    private DataSource dataSource;

    @Autowired
    public DeleteServiceProviderDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void deleteServiceProvider(int sid) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String deleteServiceProvidersql = "DELETE FROM serviceprovider WHERE sid = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteServiceProvidersql);
            deleteStatement.setInt(1, sid);
            deleteStatement.execute();
        } catch (SQLException e) {
            throw e;
        }
    }
}
