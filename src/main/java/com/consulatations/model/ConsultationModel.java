package com.consulatations.model;

import com.consulatations.backend.entity.Consultation;
import com.consulatations.backend.entity.Day;
import com.vaadin.data.util.BeanItemContainer;

public class ConsultationModel {

    // Содержит в себе информацию о самих консультациях
    public final BeanItemContainer<Consultation> consultationContainer = new BeanItemContainer<>(Consultation.class);
    // Содержит информацию о дате, о том, кто какой консультацией занимается в этот день
    private final BeanItemContainer<Day> dayBeanItemContainer = new BeanItemContainer<>(Day.class);

    public BeanItemContainer<Consultation> getConsultationContainer() {
        return consultationContainer;
    }

    public BeanItemContainer<Day> getDayBeanItemContainer() {
        return dayBeanItemContainer;
    }
}
