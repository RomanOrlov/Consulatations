package com.consulatations.view;

import com.consulatations.App;
import com.consulatations.presenter.ConsultationPresenter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

public class ConsultationView extends CustomComponent implements View {

    private final ConsultationPresenter presenter;
    private final Button calendarButton = new Button("Календарь");
    public final TreeTable treeTable = new TreeTable("Расписание консультаций");
    public final PopupDateField from = new PopupDateField("From");
    public final PopupDateField to = new PopupDateField("To");
    private final Button showFromTo = new Button("Найти");

    {
        treeTable.setWidth("100%");
        treeTable.setHeight("100%");
        treeTable.setSelectable(true);
        treeTable.setColumnCollapsingAllowed(true);
        treeTable.setColumnReorderingAllowed(true);
        treeTable.setDragMode(Table.TableDragMode.ROW);
    }

    public ConsultationView() {
        presenter = new ConsultationPresenter(this);
        //treeTable.setContainerDataSource(presenter.model.consultationContainer);
        treeTable.setVisibleColumns("displayedTime", "name", "caseNum", "telephone", "status", "sex");
        treeTable.setColumnHeader("displayedTime", "Время");
        treeTable.setColumnHeader("name", "ФИО");
        treeTable.setColumnHeader("caseNum", "Дело №");
        treeTable.setColumnHeader("telephone", "Телефон");
        treeTable.setColumnHeader("status", "Статус");
        treeTable.setColumnHeader("sex", "Пол");
        treeTable.addActionHandler(presenter);
        treeTable.setDropHandler(presenter);
        showFromTo.addClickListener(presenter);
        treeTable.setCellStyleGenerator((treeTable, itemId, propertyId) -> treeTable.getItem(itemId).getItemProperty("status").getValue().equals("Не назначено") ? "highlight-red" : null);
        calendarButton.addClickListener(click -> UI.getCurrent().getNavigator().navigateTo(App.CALENDAR_VIEW));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        HorizontalLayout h1 = new HorizontalLayout(to, showFromTo);
        h1.setSpacing(true);
        VerticalLayout v1 = new VerticalLayout(from, h1);
        v1.setSpacing(true);
        HorizontalLayout h2 = new HorizontalLayout(calendarButton, v1);
        h2.setSpacing(true);
        VerticalLayout v2 = new VerticalLayout(h2, treeTable);
        v2.setSpacing(true);
        setCompositionRoot(v2);
        //ArrayList<String> doctorsName = new ArrayList<>(Arrays.asList("Осипов","Маряшев","Осинов","Петрухин","Голанов","Банов"));
        //Button toCalendar = new Button("Календарь", clickEvent -> getUI().getNavigator().navigateTo(CalendarView.NAME));
        //VerticalLayout layout = new VerticalLayout();
        //setCompositionRoot(layout);

    }
}
