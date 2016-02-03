package com.consulatations;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.consulatations.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        // Getting from DB or somewhere else
        ArrayList<String> doctorsName = new ArrayList<>(Arrays.asList("Осипов","Маряшев","Осинов","Петрухин","Голанов","Банов"));
        ConsultationTable table = new ConsultationTable(doctorsName,new Date());
        setContent(table.treeTable);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
