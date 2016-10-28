package com.consulatations.view;

import com.consulatations.backend.entity.Consultation;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;

public class EditConsultationForm extends FormLayout {

    public EditConsultationForm(BeanItem<? extends Consultation> beanItem, Window window) {
        setSpacing(true);
        setMargin(true);
        setSizeUndefined();
        FieldGroup fieldGroup = new FieldGroup(beanItem);
        fieldGroup.setBuffered(true);
        this.addComponents(fieldGroup.buildAndBind("Время", "time"),
                fieldGroup.buildAndBind("ФИО", "name"),
                fieldGroup.buildAndBind("№ Дела", "caseNum"),
                fieldGroup.buildAndBind("Телефон", "telephone"),
                fieldGroup.buildAndBind("Статус", "status"),
                fieldGroup.buildAndBind("Пол", "sex"));
        Button saveButton = new Button("Сохранить", event -> {
            try {
                fieldGroup.commit();
                window.close();
            } catch (FieldGroup.CommitException ex) {
                ex.getStackTrace();
            }
        });
        Button cancelButton = new Button("Отмена", event -> window.close());
        Button discardButton = new Button("Сброс", event -> fieldGroup.discard());
        HorizontalLayout horizontalLayout = new HorizontalLayout(saveButton, cancelButton, discardButton);
        horizontalLayout.setSpacing(true);
        this.addComponent(horizontalLayout);
    }
}
