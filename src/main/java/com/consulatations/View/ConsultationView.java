package com.consulatations.view;

import com.consulatations.presenter.ConsultationPresenter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Роман on 08.02.2016.
 */
public class ConsultationView extends CustomComponent implements View {

    public final Button calendarButton = new Button("Календарь");
    public final TreeTable treeTable = new TreeTable("Расписание консультаций");
    {
        treeTable.setWidth("100%");
        treeTable.setHeight("100%");
        treeTable.setSelectable(true);
        treeTable.setColumnCollapsingAllowed(true);
        treeTable.setColumnReorderingAllowed(true);
        treeTable.setDragMode(Table.TableDragMode.ROW);
    }

    public ConsultationView(ConsultationPresenter presenter) {
        treeTable.setContainerDataSource(presenter.model.consultationContainer);
        treeTable.setVisibleColumns("time","name","caseNum","telephone","status","sex");
        treeTable.setColumnHeader("time","Время");
        treeTable.setColumnHeader("name","ФИО");
        treeTable.setColumnHeader("caseNum","Дело №");
        treeTable.setColumnHeader("telephone","Телефон");
        treeTable.setColumnHeader("status","Статус");
        treeTable.setColumnHeader("sex","Пол");
        treeTable.addActionHandler(presenter);
        treeTable.setDropHandler(presenter);
        treeTable.setCellStyleGenerator((treeTable,itemId,propertyId)->treeTable.getItem(itemId).getItemProperty("status").getValue().equals("Не назначено")?"highlight-red":null);
        calendarButton.addClickListener(presenter);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        System.out.println("@FDSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        //ArrayList<String> doctorsName = new ArrayList<>(Arrays.asList("Осипов","Маряшев","Осинов","Петрухин","Голанов","Банов"));
        //Button toCalendar = new Button("Календарь", clickEvent -> getUI().getNavigator().navigateTo(CalendarView.NAME));
        //VerticalLayout layout = new VerticalLayout();
        //setCompositionRoot(layout);

    }
}
