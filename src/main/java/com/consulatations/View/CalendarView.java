package com.consulatations.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CustomComponent;

public class CalendarView extends CustomComponent implements View {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setCompositionRoot(new Calendar());
    }
}
