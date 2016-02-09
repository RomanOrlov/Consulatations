package com.consulatations.view;

import com.consulatations.backend.entity.Consultation;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;

/**
 * Created by Роман on 03.02.2016.
 */
public class EditConsultationForm extends FormLayout{
    // TODO do something with buttons!
    public EditConsultationForm(BeanItem<? extends Consultation> beanItem, Window window) {
        setSpacing(true);
        setSizeUndefined();
        setMargin(true);
        FieldGroup fieldGroup = new FieldGroup(beanItem);
        fieldGroup.setBuffered(true);
        this.addComponent(fieldGroup.buildAndBind("Время","time"));
        this.addComponent(fieldGroup.buildAndBind("ФИО","name"));
        this.addComponent(fieldGroup.buildAndBind("№ Дела","caseNum"));
        this.addComponent(fieldGroup.buildAndBind("Телефон","telephone"));
        this.addComponent(fieldGroup.buildAndBind("Статус","status"));
        this.addComponent(fieldGroup.buildAndBind("Пол","sex"));
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        horizontalLayout.addComponent(new Button("Сохранить",event -> {
            try {fieldGroup.commit();
            window.close();}
        catch (FieldGroup.CommitException ex) {ex.getStackTrace();}}));
        horizontalLayout.addComponent(new Button("Отмена",event ->  window.close()));
        horizontalLayout.addComponent(new Button("Сброс",event -> fieldGroup.discard()));
        this.addComponent(horizontalLayout);
    }
}
