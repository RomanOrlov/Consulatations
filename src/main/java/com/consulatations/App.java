package com.consulatations;

import javax.servlet.annotation.WebServlet;

import com.consulatations.view.ConsultationView;
import com.consulatations.view.LoginView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.consulatations.MyAppWidgetset")
public class App extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        new Navigator(this,this);
        getNavigator().addView(LoginView.NAME,LoginView.class);
        getNavigator().addView(ConsultationView.NAME,ConsultationView.class);
        getNavigator().navigateTo(LoginView.NAME);
        //ArrayList<String> doctorsName = new ArrayList<>(Arrays.asList("Осипов","Маряшев","Осинов","Петрухин","Голанов","Банов"));
        //ConsultationTable table = new ConsultationTable(doctorsName,new Date());
        //setContent(table.treeTable);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = App.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
