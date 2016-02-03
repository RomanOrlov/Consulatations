package com.consulatations.OldJunk;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;

/**
 * Created by Роман on 01.02.2016.
 */
public class EditConsultation extends FormLayout {
    final Window window = new Window("Редактирование консультации");
    private TextField time = new TextField("Время");
    private TextField name = new TextField("ФИО");
    private TextField casenum = new TextField("№ Дела");
    private TextField phone = new TextField("Телефон");
    private NativeSelect status = new NativeSelect("Статус");
    public static final String[] CONSULTATION_STATUS = new String[]{"Назначена","Не назначена","Отменена","Произошла"};
    private Button save = new Button("Сохранить");
    private Button cancel = new Button("Отмена",this::cancel);

    public EditConsultation() {
        buildLayout();
    }

    private void buildLayout() {
        window.setWidth(500.0f, Sizeable.Unit.PIXELS);
        window.setModal(true);
        setSizeUndefined();
        setMargin(true);
        for (int i=0;i<CONSULTATION_STATUS.length;i++) {
            status.addItem(i);
            status.setItemCaption(i, CONSULTATION_STATUS[i]);
        }
        //setComponentAlignment(time,Alignment.MIDDLE_CENTER);
        HorizontalLayout actions = new HorizontalLayout(save, cancel);
        actions.setSpacing(true);
        addComponents(time, name, casenum,phone, status ,actions);
        window.setContent(this);
        UI.getCurrent().addWindow(window);
        cancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                window.close();
            }
        });
    }

    public void save(Button.ClickEvent event) {}
    public void cancel(Button.ClickEvent event) {window.close();}
}
