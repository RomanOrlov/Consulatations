package com.consulatations.backend;

import com.consulatations.backend.entity.Consultation;
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
public class SheduleManager {
    // Получаем расписание врачей по дням
    public Collection<? extends Day> listPatients(Date from, Date to) {
        try (
                Connection con = DB.getConnection()
        ) {
            QueryRunner qr = new QueryRunner();
            BeanListHandler<Day> handler = new BeanListHandler<>(Day.class);
            return qr.query(con, "select * from ", handler);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void commitChange() {

    }
}
