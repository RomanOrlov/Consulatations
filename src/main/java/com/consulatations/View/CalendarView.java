package com.consulatations.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.components.calendar.event.BasicEventProvider;

/**
 * Created by Роман on 05.02.2016.
 */
public class CalendarView extends CustomComponent implements View{
    public static final String NAME = "calendar";
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        BasicEventProvider provider = new BasicEventProvider();
        Calendar calendar = new Calendar("toy",provider);
        setCompositionRoot(calendar);
    }
}
