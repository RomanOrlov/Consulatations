package com.consulatations.OldJunk;

import com.consulatations.Patient;
import com.vaadin.data.Item;
import com.vaadin.event.Action;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.*;
import com.vaadin.ui.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Роман on 31.01.2016.
 */
public class OldJunk {
    private static final Action ADD_ITEM_ACTION = new Action("Новая консультация");
    private static final Action REMOVE_ITEM_ACTION = new Action("Удалить консультацию");
    private static final Action EDIT_ITEM_ACTION = new Action("Редактировать");
    private static final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy (EEEE)");
    public final TreeTable treeTable;
    private static final ArrayList<String> duty = new ArrayList<>(Arrays.asList("Очная консультация - ","РХ - ","Заочная консультация - ","Онкология - "));
    private static final ArrayList<Object> roots = new ArrayList<>();
    private final ArrayList<String> doctorsName;
    public OldJunk(ArrayList<String> doctorsName, Date date) {
        this.doctorsName = doctorsName;
        treeTable = new TreeTable(format.format(date));
        for (int i=0;i<duty.size();i++) {
            doctorsName.set(i,duty.get(i)+doctorsName.get(i));
        }
        treeTable.setWidth("100%");
        treeTable.setHeight("100%");
        treeTable.setSelectable(true);
        treeTable.setColumnCollapsingAllowed(true);
        treeTable.setColumnReorderingAllowed(true);
        treeTable.setDragMode(Table.TableDragMode.ROW);
        treeTable.addContainerProperty("Время",String.class,"");
        treeTable.addContainerProperty("ФИО",String.class,"");
        treeTable.addContainerProperty("Дело №",String.class,"");
        treeTable.addContainerProperty("Телефон",String.class,"");
        treeTable.addContainerProperty("Статус",String.class,"");
        final Object root = treeTable.addItem(new Object[] {format.format(date),"","","",""},null);
        roots.add(root);
        for (String s:doctorsName) {
            Patient patient = new Patient();
            Object out = treeTable.addItem(new Object[] {s,"","","",""},null);
            roots.add(out);
            treeTable.setParent(out,root);
            Object in = treeTable.addItem(new Object[]{patient.getTime(),patient.getName(),patient.getCaseNum(),patient.getTelephone(),patient.getStatus()},null);
            treeTable.setParent(in,out);
            treeTable.setChildrenAllowed(in,false);

        }
        setActionHandlers();
        setDropHandler();
        setCellStyleGenerator();
    }

    private void setActionHandlers()  {
        treeTable.addActionHandler(new Action.Handler() {
            @Override
            public void handleAction(final Action action, final Object sender,
                                     final Object target) {
                if (action == ADD_ITEM_ACTION) {
                    // Create new item
                    final Object item = treeTable.addItem(new Object[] {
                            "?","Новый пациент","?","?","Не назначено" }, null);
                    treeTable.setChildrenAllowed(item, false);
                    treeTable.setParent(item, target);
                }  else if (action == REMOVE_ITEM_ACTION&&!roots.contains(target)) {
                    treeTable.removeItem(target);
                } else if (action==EDIT_ITEM_ACTION) {
                    // Открытие окна с редактированием поля
                    EditConsultation editConsultation = new EditConsultation();
                }
            }

            @Override
            public Action[] getActions(final Object target, final Object sender) {

                if (treeTable.areChildrenAllowed(target)) {
                    // Context menu for a category
                    return new Action[] { ADD_ITEM_ACTION };

                } else {
                    // Context menu for an item
                    return new Action[] { REMOVE_ITEM_ACTION,EDIT_ITEM_ACTION };
                }
            }
        });
    }

    private void setDropHandler() {
        treeTable.setDropHandler(new DropHandler() {
            @Override
            public void drop(DragAndDropEvent dragAndDropEvent) {
                /*
                // Wrapper for the object that is dragged
                DataBoundTransferable t = (DataBoundTransferable)
                        dragAndDropEvent.getTransferable();

                AbstractSelect.AbstractSelectTargetDetails target =
                        (AbstractSelect.AbstractSelectTargetDetails) dragAndDropEvent.getTargetDetails();

                // On which side of the target was the item dropped
                VerticalDropLocation location = target.getDropLocation();

                // Get ids of the dragged item and the target item
                Object sourceItemId = t.getItemId();
                Object targetItemId = target.getItemIdOver();
                System.out.println("Was here");
                if (roots.contains(sourceItemId)||sourceItemId.equals(targetItemId)||(!roots.contains(targetItemId)&&location.equals(VerticalDropLocation.MIDDLE)))
                    return;
                System.out.println(treeTable.getItem(sourceItemId).getItemProperty("ФИО").getValue());
                System.out.println(treeTable.getItem(targetItemId).getItemProperty("ФИО").getValue());
                HierarchicalContainer container =
                        (HierarchicalContainer) treeTable.getContainerDataSource();
                Container sourceContainer = t.getSourceContainer();

                if (location == VerticalDropLocation.MIDDLE) {
                    treeTable.setParent(sourceItemId, targetItemId);
                }
                /*
                else if (location == VerticalDropLocation.TOP) {
                    Object parentId = treeTable.getParent(targetItemId);
                    treeTable.setParent(sourceItemId, parentId);
                    treeTable.moveAfterSibling(beanItem, targetItemId);
                    container.moveAfterSibling(targetItemId, beanItem);
                }
                else if (location == VerticalDropLocation.BOTTOM) {
                    Object parentId = container.getParent(targetItemId);
                    treeTable.setParent(beanItem, parentId);
                    container.moveAfterSibling(beanItem, targetItemId);
                }*/


            }

            @Override
            public AcceptCriterion getAcceptCriterion() {
                return new Not(Tree.TargetItemAllowsChildren.get());
            }
        });
        /*
        treeTable.setDropHandler(new DropHandler() {
            @Override
            public AcceptCriterion getAcceptCriterion() {
                // Accept drops in the middle of items that can have
                // children, and below and above all items.
                return new Or(Tree.TargetItemAllowsChildren.get(),
                        new Not(AbstractSelect.VerticalLocationIs.MIDDLE));
            }
            @Override
            public void drop(DragAndDropEvent event) {
                // Wrapper for the object that is dragged
                DataBoundTransferable t = (DataBoundTransferable)
                        event.getTransferable();

                AbstractSelect.AbstractSelectTargetDetails target =
                        (AbstractSelect.AbstractSelectTargetDetails) event.getTargetDetails();

                // Get ids of the dragged item and the target item
                Object sourceItemId = t.getData("itemId");
                Object targetItemId = target.getItemIdOver();

                // Check that the target is not in the subtree of
                // the dragged item itself
                for (Object itemId = targetItemId; itemId != null; itemId = treeTable.getParent(itemId))
                    if (itemId == sourceItemId)
                        return;

                // On which side of the target was the item dropped
                VerticalDropLocation location = target.getDropLocation();

                HierarchicalContainer container =
                        (HierarchicalContainer) treeTable.getContainerDataSource();

                // Do some hassle with the example data representation
                BeanItem<?> beanItem = null;
                if (sourceItemId instanceof BeanItem<?>)
                    beanItem = (BeanItem<?>) sourceItemId;
                else if (sourceItemId instanceof Patient)
                    beanItem = new BeanItem<Patient>((Patient) sourceItemId);

                // Remove the item from the source container and
                // add it to the tree's container
                Container sourceContainer = t.getSourceContainer();
                sourceContainer.removeItem(sourceItemId);
                if (beanItem==null)
                treeTable.addItem(beanItem); // Hassle

                // More hassle: copy the property values from the BeanItem to the container item
                for (Object propertyId: container.getContainerPropertyIds())
                    container.getItem(beanItem).getItemProperty(propertyId).setValue(beanItem.getItemProperty(propertyId));

                Patient bean = (Patient) beanItem.getBean();
                treeTable.setChildrenAllowed(beanItem, false);

                // Drop right on an item -> make it a child
                if (location == VerticalDropLocation.MIDDLE)
                    treeTable.setParent(beanItem, targetItemId);

                    // Drop at the top of a subtree -> make it previous
                else if (location == VerticalDropLocation.TOP) {
                    Object parentId = container.getParent(targetItemId);
                    treeTable.setParent(beanItem, parentId);
                    container.moveAfterSibling(beanItem, targetItemId);
                    container.moveAfterSibling(targetItemId, beanItem);
                }

                // Drop below another item -> make it next
                else if (location == VerticalDropLocation.BOTTOM) {
                    Object parentId = container.getParent(targetItemId);
                    treeTable.setParent(beanItem, parentId);
                    container.moveAfterSibling(beanItem, targetItemId);
                }

                treeTable.setItemCaption(beanItem, bean.getName());
            }
        });*/
    }

    private void setCellStyleGenerator() {
        treeTable.setCellStyleGenerator(new Table.CellStyleGenerator() {
            @Override
            public String getStyle(Table source, Object itemId, Object propertyId) {
                Item item = treeTable.getItem(itemId);
                System.out.println(item.getItemProperty("Статус").getValue());
                if (item.getItemProperty("Статус").getValue().equals("Не назначено"))
                    return "highlight-red";
                return null;
            }
        });
    }


}
