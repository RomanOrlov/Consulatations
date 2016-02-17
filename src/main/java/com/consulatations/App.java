package com.consulatations;

import javax.servlet.annotation.WebServlet;

import com.consulatations.view.CalendarView;
import com.consulatations.view.ConsultationView;
import com.consulatations.view.ShuduleEditView;
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
    public static final String CONSULTATION_VIEW = "consultation";
    public static final String CALENDAR_VIEW = "calendar";
    public static final String SHEDULE_VIEW = "shedule";

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        new Navigator(this,this);
        UI.getCurrent().getNavigator().addView(CONSULTATION_VIEW,ConsultationView.class);
        UI.getCurrent().getNavigator().addView(CALENDAR_VIEW,CalendarView.class);
        UI.getCurrent().getNavigator().addView(SHEDULE_VIEW, ShuduleEditView.class);
        UI.getCurrent().getNavigator().navigateTo(CONSULTATION_VIEW);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = App.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
