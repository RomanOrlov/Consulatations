package com.consulatations.backend;

import com.consulatations.backend.entity.Doctor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public class DoctorsManager {
    public Collection<Doctor> listDoctors() {
        try (Connection con = DB.getConnection()) {
            QueryRunner qr = new QueryRunner();
            BeanListHandler<Doctor> handler = new BeanListHandler<>(Doctor.class);
            return qr.query(con, "select * from sys.doctors", handler);
        } catch (SQLException e) {
            System.out.println("Problem!");
            throw new RuntimeException(e);
        }
    }
}
