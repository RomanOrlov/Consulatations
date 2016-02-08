package com.consulatations.backend;

import com.consulatations.backend.entity.Day;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Роман on 09.02.2016.
 */
public class DoctorsManager {
    public Collection<String> listPatients(Date from, Date to) {
        try (
                Connection con = DB.getConnection()
        ) {
            //Cписок доступных врачей
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
