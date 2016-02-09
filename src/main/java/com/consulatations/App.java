package com.consulatations;

import javax.servlet.annotation.WebServlet;

import com.consulatations.backend.DB;
import com.consulatations.presenter.ConsultationPresenter;
import com.consulatations.view.ConsultationView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.BeanItemContainer;
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
    public static final String CONSULTATION_VIEW = "consultation";
    @Override
    protected void init(VaadinRequest vaadinRequest) {/*
        try (Connection connection = DB.getConnection()){
            QueryRunner qr = new QueryRunner();
            BeanListHandler<TestData> handler = new BeanListHandler<>(TestData.class);
            Collection<TestData> simples = qr.query(connection, "select * from sys.consl", handler);
            System.out.println(simples.size());
            for (TestData s1: simples) {
                System.out.println(s1.getName()+" "+s1.getTime());
            }
            BeanItemContainer<TestData> beanItemContainer = new BeanItemContainer<>(TestData.class,simples);
            Table table = new Table("whatitis",beanItemContainer);
            TestData simple1 = new TestData();
            beanItemContainer.addItem(simple1);
            setContent(table);
            qr.update(connection,"INSERT INTO sys.consl (`NAME`, `TIME`) VALUES ('1', '33')");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

*/
        //new Navigator(this,this);
        //UI.getCurrent().getNavigator().addView(CONSULTATION_VIEW,ConsultationView.class);
        ConsultationPresenter presenter = new ConsultationPresenter();
        setContent(presenter.view.treeTable);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = App.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
