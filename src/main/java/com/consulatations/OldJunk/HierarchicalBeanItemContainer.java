package com.consulatations.OldJunk;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Роман on 03.02.2016.
 */
public class HierarchicalBeanItemContainer<T>
        extends BeanItemContainer<T> implements Container.Hierarchical {
    // The contained bean type uses this property to store
    // the parent relationship.
    Object parentPID;

    public HierarchicalBeanItemContainer(Class<T> type,
                                         Object parentPropertyId) {
        super(type);

        this.parentPID = parentPropertyId;
    }

    @Override
    public Collection<?> getChildren(Object itemId) {
        LinkedList<Object> children = new LinkedList<Object>();

        // This implementation has O(n^2) complexity when
        // painting the tree, so it's really inefficient.
        for (Object candidateId: getItemIds()) {
            Object parentRef = getItem(candidateId).
                    getItemProperty(parentPID).getValue();
            if (parentRef == itemId)
                children.add(candidateId);
        }

        if (children.size() > 0)
            return children;
        else
            return null;
    }

    @Override
    public Object getParent(Object itemId) {
        return getItem(itemId).
                getItemProperty(parentPID).getValue();
    }

    @Override
    public Collection<?> rootItemIds() {
        LinkedList<Object> result = new LinkedList<Object>();
        for (Object candidateId: getItemIds()) {
            Object parentRef = getItem(candidateId).
                    getItemProperty(parentPID).getValue();
            if (parentRef == null)
                result.add(candidateId);
        }

        if (result.size() > 0)
            return result;
        else
            return null;
    }

    @Override
    public boolean setParent(Object itemId, Object newParentId)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                "Not implemented here");
    }

    @Override
    public boolean areChildrenAllowed(Object itemId) {
        return hasChildren(itemId);
    }

    @Override
    public boolean setChildrenAllowed(Object itemId,
                                      boolean childrenAllowed)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                "Not implemented here");
    }

    @Override
    public boolean isRoot(Object itemId) {
        return getItem(itemId).getItemProperty(parentPID).
                getValue() == null;
    }

    @Override
    public boolean hasChildren(Object itemId) {
        for (Object candidateId: getItemIds()) {
            Object parentRef = getItem(candidateId).
                    getItemProperty(parentPID).getValue();
            if (parentRef == itemId)
                return true;
        }
        return false;
    }
}
