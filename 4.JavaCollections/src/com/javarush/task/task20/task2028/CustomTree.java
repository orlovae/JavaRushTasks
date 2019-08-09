package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Serializable, Cloneable {
    Entry<String> root;
    List<Entry<String>>  list = new ArrayList<>();
    public CustomTree() {
        this.root = new Entry<>("0");
        list.add(root);
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof String) {
            Entry<String> removeEntry = null;

            for (Entry<String> item:list
                    ) {
                if (item.getElementName().equals(o)) {
                    removeEntry = item;
                    break;
                }
            }
            List<Entry<String>> listAllChildrenRemoveEntry = new ArrayList<>();
            listAllChildrenRemoveEntry.add(removeEntry);
            listAllChildrenRemoveEntry =
                    checkAllChildren(listAllChildrenRemoveEntry, removeEntry);


            for (Entry<String> item:listAllChildrenRemoveEntry
                 ) {
                if (whatIsTheChildParent(item)) {
                    item.getParent().setAvailableToAddLeftChildren(true);
                } else {
                    item.getParent().setAvailableToAddRightChildren(true);
                }
                list.remove(item);
            }

//            System.out.println("listRemoveEntry");
//            for (Entry<String> item:list
//                    ) {
//                System.out.println(item.getElementName());
//            }
            return true;

        } else {
            throw new UnsupportedOperationException();
        }
    }

    private boolean whatIsTheChildParent(Entry<String> entry){
        Entry<String> parent = entry.getParent();
        if (parent.getLeftChild().equals(entry)) {
            return true;
        }
        return false;
    }

    private List<Entry<String>> checkAllChildren(List<Entry<String>> listAllChildrenRemoveEntry,
                                          Entry<String> parent) {
        if (parent.getLeftChild() != null) {
            listAllChildrenRemoveEntry.add(parent.getLeftChild());
            listAllChildrenRemoveEntry =
                    checkAllChildren(listAllChildrenRemoveEntry, parent.getLeftChild());
        }
        if (parent.getRightChild() != null) {
            listAllChildrenRemoveEntry.add(parent.getRightChild());
            listAllChildrenRemoveEntry =
                    checkAllChildren(listAllChildrenRemoveEntry, parent.getRightChild());
        }

        return listAllChildrenRemoveEntry;
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return list.size() - 1;
    }

    @Override
    public boolean add(String s) {
        Entry<String> parent = getLastEntryParent();
        return list.add(getNewEntry(s, parent));
    }

    private Entry<String> getNewEntry(String s, Entry<String> parent) {
        Entry<String> entry = new Entry<>(s);
        entry.setParent(parent);
        if (parent.isAvailableToAddLeftChildren()){
            parent.setLeftChild(entry);
            parent.setAvailableToAddLeftChildren(false);
        } else {
            parent.setRightChild(entry);
            parent.setAvailableToAddRightChildren(false);
        }
        return entry;
    }

    private Entry<String> getLastEntryParent(){
        Entry<String> parent = null;
        for (Entry<String> item:list
             ) {
            if (item.isAvailableToAddChildren()) {
                parent = item;
                break;
            }
        }
        return parent;
    }

    public String getParent(String s) {
        Entry<String> checkEntry = null;
        for (Entry<String> item:list
                ) {
            if (item.getElementName().equals(s)) {
                checkEntry = item;
                break;
            }
        }
        return checkEntry != null ? checkEntry.getParent().getElementName() : "Неверно заданно поисковое значение";
    }

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren = true;
        boolean availableToAddRightChildren = true;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public String getElementName() {
            return elementName;
        }

        boolean isAvailableToAddLeftChildren() {
            return availableToAddLeftChildren;
        }

        void setAvailableToAddLeftChildren(boolean availableToAddLeftChildren) {
            this.availableToAddLeftChildren = availableToAddLeftChildren;
        }

        public boolean isAvailableToAddRightChildren() {
            return availableToAddRightChildren;
        }

        void setAvailableToAddRightChildren(boolean availableToAddRightChildren) {
            this.availableToAddRightChildren = availableToAddRightChildren;
        }

        public Entry<T> getParent() {
            return parent;
        }

        void setParent(Entry<T> parent) {
            this.parent = parent;
        }

        Entry<T> getLeftChild() {
            return leftChild;
        }

        void setLeftChild(Entry<T> leftChild) {
            this.leftChild = leftChild;
        }

        Entry<T> getRightChild() {
            return rightChild;
        }

        void setRightChild(Entry<T> rightChild) {
            this.rightChild = rightChild;
        }

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }


    }

}
