package com.consulatations;

import javax.servlet.annotation.WebServlet;

import com.consulatations.backend.DB;
import com.consulatations.view.CalendarView;
import com.consulatations.view.ConsultationView;
import com.consulatations.view.LoginView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.consulatations.MyAppWidgetset")
public class App extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        try (Connection connection = DB.getConnection()){
            QueryRunner qr = new QueryRunner();
            BeanListHandler<Simple> handler = new BeanListHandler<>(Simple.class);
            Collection<Simple> simples = qr.query(connection, "select * from sys.consl", handler);
            System.out.println(simples.size());
            for (Simple s1: simples) {
                System.out.println(s1.getName()+" "+s1.getTime());
            }
            BeanItemContainer<Simple> beanItemContainer = new BeanItemContainer<>(Simple.class,simples);
            Table table = new Table("whatitis",beanItemContainer);
            Simple simple1 = new Simple();
            beanItemContainer.addItem(simple1);
            setContent(table);
            qr.update(connection,"INSERT INTO sys.consl (`NAME`, `TIME`) VALUES ('1', '33')");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        new Navigator(this,this);
        getNavigator().addView(LoginView.NAME,LoginView.class);
        getNavigator().addView(ConsultationView.NAME,ConsultationView.class);
        getNavigator().addView(CalendarView.NAME,CalendarView.class);
        getNavigator().navigateTo(LoginView.NAME);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = App.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
