package com.consulatations.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TreeTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Роман on 05.02.2016.
 */
public class ConsultationView extends CustomComponent implements View {
    public static final String NAME = "consultations";
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        ArrayList<String> doctorsName = new ArrayList<>(Arrays.asList("Осипов","Маряшев","Осинов","Петрухин","Голанов","Банов"));
        ConsultationTable table = new ConsultationTable(doctorsName,new Date());
        setCompositionRoot(table.treeTable);
    }
}
