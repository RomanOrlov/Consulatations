package com.consulatations.presenter;

import com.consulatations.BaseMVP.BasePresenter;
import com.consulatations.backend.ConsultationManager;
import com.consulatations.backend.SheduleManager;
import com.consulatations.backend.entity.Consultation;
import com.consulatations.backend.entity.Day;
import com.consulatations.model.ConsultationModel;
import com.consulatations.view.ConsultationView;
import com.consulatations.view.EditConsultationForm;
import com.vaadin.event.Action;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.And;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Роман on 08.02.2016.
 */
public class ConsultationPresenter
        implements Action.Handler, Button.ClickListener, DropHandler {
    public  ConsultationModel model;
    private ConsultationManager consultationManager;
    private SheduleManager  sheduleManager;
    public ConsultationView view;
    private static final Action ADD_ITEM_ACTION = new Action("Новая консультация");
    private static final Action REMOVE_ITEM_ACTION = new Action("Удалить консультацию");
    private static final Action EDIT_ITEM_ACTION = new Action("Редактировать");
    private static final Action REMOVE_DOCTOR = new Action("Добавить врача");
    private static final Action ADD_DOCTOR = new Action("Удалить врача");
    private static final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy (EEEE)");
    private ArrayList<Consultation> daysList = new ArrayList<>();
    private ArrayList<Consultation> activites = new ArrayList<>();
    private ArrayList<Day> shedule = new ArrayList<>();


    public ConsultationPresenter() {
        model = new ConsultationModel();
        view = new ConsultationView(this);
        //при загрузке по дефолту берём 7 дней
        model.consultationContainer.addAll(consultationManager.listPatients(new  java.sql.Date(0),new  java.sql.Date(0)));
        shedule.addAll(sheduleManager.listPatients(new  java.sql.Date(0),new  java.sql.Date(0)));
        long today = new Date().getTime();
        long oneday = 1000*3600*24;
        for (int i=0;i<7;i++) {
            Consultation day = new Consultation(format.format(new Date(today+i*oneday)),"","","","");
            daysList.add(day);
            model.consultationContainer.addBean(day);
        }
        if (shedule.size()>0) {
            Day zeroday = shedule.get(0);
            Consultation ochno = new Consultation("Очная консультация - "+zeroday.getOchnoe(),"","","","");
            Consultation zaochno = new Consultation("Заочная консультация - "+zeroday.getZaochnoe(),"","","","");
            Consultation radiosurgery = new Consultation("РХ - "+zeroday.getRadiosurgery(),"","","","");
            Consultation oncology = new Consultation("Онкология - "+zeroday.getOncology(),"","","","");

            model.consultationContainer.addAll(Arrays.asList(ochno,zaochno,radiosurgery,oncology));
            view.treeTable.setParent(ochno,daysList.get(0));
            view.treeTable.setParent(zaochno,daysList.get(0));
            view.treeTable.setParent(radiosurgery,daysList.get(0));
            view.treeTable.setParent(oncology,daysList.get(0));
        }

    }

    @Override
    public void buttonClick(Button.ClickEvent event) {

    }

    @Override
    public void drop(DragAndDropEvent event) {
        // TODO handle drop event (TOP and BOTTOM cases)
        DataBoundTransferable t = (DataBoundTransferable)
                event.getTransferable();

        AbstractSelect.AbstractSelectTargetDetails target =
                (AbstractSelect.AbstractSelectTargetDetails) event.getTargetDetails();

        // Get ids of the dragged item and the target item
        Object sourceItemId = t.getItemId();
        Object targetItemId = target.getItemIdOver();
        if (daysList.contains(sourceItemId)||activites.contains(sourceItemId))
            return;
        view.treeTable.setParent(sourceItemId, targetItemId);
    }

    @Override
    public AcceptCriterion getAcceptCriterion() {
        // TODO perform normal criteria (This is really possible)
        return new And(new Not(Tree.TargetItemAllowsChildren.get()), AbstractSelect.VerticalLocationIs.MIDDLE);
    }

    @Override
    public Action[] getActions(Object target, Object sender) {
        if (view.treeTable.areChildrenAllowed(target)) {
            return new Action[] { ADD_ITEM_ACTION };
        } else {
            return new Action[] { REMOVE_ITEM_ACTION,EDIT_ITEM_ACTION };
        }
    }

    @Override
    public void handleAction(Action action, Object sender, Object target) {
        if (action == ADD_ITEM_ACTION) {
            Consultation consultation = new Consultation("?","?","?","?","Не назначено");
            view.treeTable.addItem(consultation);
            view.treeTable.setChildrenAllowed(consultation, false);
            view.treeTable.setParent(consultation,target);
        }  else if (action == REMOVE_ITEM_ACTION&&!(daysList.contains(target)||activites.contains(target))) {
            view.treeTable.removeItem(target);
        } else if (action==EDIT_ITEM_ACTION) {
            // Открытие окна с редактированием поля
            Window window = new Window("Редактирование консультации");
            EditConsultationForm editConsultation = new EditConsultationForm(model.consultationContainer.getItem(target),window);
            window.setContent(editConsultation);
            window.setModal(true);
            window.setWidth(500.0f, Sizeable.Unit.PIXELS);
            UI.getCurrent().addWindow(window);
        }
    }
}
