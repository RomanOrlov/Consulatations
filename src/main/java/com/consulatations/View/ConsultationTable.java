package com.consulatations.view;

import com.consulatations.backend.entity.Consultation;
import com.vaadin.data.util.BeanItemContainer;
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
import java.util.Date;

/**
 * Created by Роман on 02.02.2016.
 */
public class ConsultationTable {
    private static final Action ADD_ITEM_ACTION = new Action("Новая консультация");
    private static final Action REMOVE_ITEM_ACTION = new Action("Удалить консультацию");
    private static final Action EDIT_ITEM_ACTION = new Action("Редактировать");
    private static final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy (EEEE)");
    private static final ArrayList<String> duty = new ArrayList<>(Arrays.asList("Очная консультация - ","РХ - ","Заочная консультация - ","Онкология - "));
    private static final ArrayList<Consultation> roots = new ArrayList<>();
    private final ArrayList<String> doctorsName;
    public final TreeTable treeTable;
    private final BeanItemContainer<Consultation> patientBeanItemContainer = new BeanItemContainer<>(Consultation.class);

    public ConsultationTable(ArrayList<String> doctorsName,Date date) {
        this.doctorsName = doctorsName;
        for (int i=0;i<duty.size();i++) {
            doctorsName.set(i,duty.get(i)+doctorsName.get(i));
        }
        treeTable = new TreeTable(format.format(date)+" - "+format.format(date),patientBeanItemContainer);
        treeTable.setWidth("100%");
        treeTable.setHeight("100%");
        treeTable.setSelectable(true);
        treeTable.setColumnCollapsingAllowed(true);
        treeTable.setColumnReorderingAllowed(true);
        treeTable.setDragMode(Table.TableDragMode.ROW);
        //Rename columns (may cause errors if change Consultation class)
        treeTable.setColumnHeader("time","Время");
        treeTable.setColumnHeader("name","ФИО");
        treeTable.setColumnHeader("caseNum","Дело №");
        treeTable.setColumnHeader("telephone","Телефон");
        treeTable.setColumnHeader("status","Статус");
        treeTable.setColumnHeader("sex","Пол");
        // Reorder it (this feature still possible inside app columnreorderallow)
        treeTable.setVisibleColumns("time","name","caseNum","telephone","status","sex");
        // Filling with data
        Consultation day = new Consultation(format.format(date),"","","","");
        roots.add(day);
        treeTable.addItem(day);
        // TODO perform some random data (like real)
        for (String s:doctorsName) {
            Consultation procedure = new Consultation(s,"","","","");
            treeTable.addItem(procedure);
            treeTable.setParent(procedure,day);
            roots.add(procedure);
            Consultation consultation = new Consultation();
            treeTable.addItem(consultation);
            treeTable.setChildrenAllowed(consultation,false);
            treeTable.setParent(consultation,procedure);
        }
        treeTable.addActionHandler(getActionHandler());
        treeTable.setDropHandler(getDropHandler());
        treeTable.setCellStyleGenerator((treeTable,itemId,propertyId)->treeTable.getItem(itemId).getItemProperty("status").getValue().equals("Не назначено")?"highlight-red":null);
    }

    private Action.Handler getActionHandler()  {
        return new Action.Handler() {
            @Override
            public void handleAction(final Action action, final Object sender,
                                     final Object target) {
                if (action == ADD_ITEM_ACTION) {
                    // Create new item
                    Consultation consultation = new Consultation("?","?","?","?","Не назначено");
                    treeTable.addItem(consultation);
                    treeTable.setChildrenAllowed(consultation, false);
                    treeTable.setParent(consultation,target);
                }  else if (action == REMOVE_ITEM_ACTION&&!roots.contains(target)) {
                    treeTable.removeItem(target);
                } else if (action==EDIT_ITEM_ACTION) {
                    // Открытие окна с редактированием поля
                    Window window = new Window("Редактирование консультации");
                    EditConsultationForm editConsultation = new EditConsultationForm(patientBeanItemContainer.getItem(target),window);
                    window.setContent(editConsultation);
                    window.setModal(true);
                    window.setWidth(500.0f, Sizeable.Unit.PIXELS);
                    UI.getCurrent().addWindow(window);
                }
            }

            @Override
            public Action[] getActions(final Object target, final Object sender) {
                if (treeTable.areChildrenAllowed(target)) {
                    return new Action[] { ADD_ITEM_ACTION };
                } else {
                    return new Action[] { REMOVE_ITEM_ACTION,EDIT_ITEM_ACTION };
                }
            }
        };
    }

    private DropHandler getDropHandler() {
        return new DropHandler() {
            @Override
            // TODO handle drop event (TOP and BOTTOM cases)
            public void drop(DragAndDropEvent event) {
                // Wrapper for the object that is dragged
                DataBoundTransferable t = (DataBoundTransferable)
                        event.getTransferable();

                AbstractSelect.AbstractSelectTargetDetails target =
                        (AbstractSelect.AbstractSelectTargetDetails) event.getTargetDetails();

                // Get ids of the dragged item and the target item
                Object sourceItemId = t.getItemId();
                Object targetItemId = target.getItemIdOver();
                System.out.println("was here");
                if (roots.contains(sourceItemId))
                    return;
                treeTable.setParent(sourceItemId, targetItemId);
            }

            @Override
            public AcceptCriterion getAcceptCriterion() {
                // TODO perform normal criteria (This is really possible)
                return new And(new Not(Tree.TargetItemAllowsChildren.get()), AbstractSelect.VerticalLocationIs.MIDDLE);
            }
        };
    }
}
