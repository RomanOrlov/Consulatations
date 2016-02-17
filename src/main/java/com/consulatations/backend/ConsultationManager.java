package com.consulatations.backend;

import com.consulatations.backend.entity.Consultation;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Роман on 07.02.2016.
 */
// Тянет данные из базы о консультациях
public class ConsultationManager {

    private static final SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");

    public Collection<Consultation> listConsultations(Date from, Date to) {
        try (
                Connection con = DB.getConnection()
        ) {
            /*
            //Использование PrepareStatement
            PreparedStatement st = con.prepareStatement("select * from consultations where consultations.date BETWEEN ? AND ? ");
            st.setDate(1,from);
            st.setDate(2,to);
            ResultSet resultSet = st.executeQuery();
            BeanListHandler<Consultation> handler = new BeanListHandler<>(Consultation.class);
            ArrayList<Consultation> consultationList = new ArrayList<>();
            while (resultSet.next()) {
                consultationList.add(resultSet.getString("name")...);
            }
            resultSet.close();
            return consultationList;*/
            QueryRunner qr = new QueryRunner();
            BeanListHandler<Consultation> handler = new BeanListHandler<>(Consultation.class);
            System.out.println(String.format("select * from sys.consultations where time BETWEEN '%s' AND '%s'",simpleDateFormat.format(from),simpleDateFormat.format(to)));
            return qr.query(con, String.format("select * from sys.consultations where  time BETWEEN '%s' AND '%s'",simpleDateFormat.format(from),simpleDateFormat.format(to)), handler);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // Вставить в базу изменение какое то?
    public void commitChange() {

    }
}
