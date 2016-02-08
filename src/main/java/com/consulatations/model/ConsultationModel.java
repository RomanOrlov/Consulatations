package com.consulatations.model;

import com.consulatations.backend.entity.Consultation;
import com.consulatations.backend.entity.Day;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;

/**
 * Created by Роман on 08.02.2016.
 */
public class ConsultationModel {

    // Содержит в себе информацию о самих консультациях
    public final BeanItemContainer<Consultation> consultationContainer = new BeanItemContainer<>(Consultation.class);
    // Содержит информацию о дате, о том, кто какой консультацией занимается в этот день
    public final BeanItemContainer<Day> dayBeanItemContainer = new BeanItemContainer<>(Day.class);

    public BeanItemContainer<Consultation> getConsultationContainer() {
        return consultationContainer;
    }

    public BeanItemContainer<Day> getDayBeanItemContainer() {
        return dayBeanItemContainer;
    }
}
