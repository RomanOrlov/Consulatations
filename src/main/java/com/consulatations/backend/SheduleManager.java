package com.consulatations.backend;

import com.consulatations.backend.entity.Day;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class SheduleManager {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Получаем расписание врачей по дням
    public Collection<Day> listShedule(Date from, Date to) {
        try (
                Connection con = DB.getConnection()
        ) {
            QueryRunner qr = new QueryRunner();
            BeanListHandler<Day> handler = new BeanListHandler<>(Day.class);
            System.out.println(String.format("select * from sys.shedule where  day BETWEEN '%s' AND '%s'", simpleDateFormat.format(from), simpleDateFormat.format(to)));
            return qr.query(con, String.format("select * from sys.shedule where  day BETWEEN '%s' AND '%s'", simpleDateFormat.format(from), simpleDateFormat.format(to)), handler);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
