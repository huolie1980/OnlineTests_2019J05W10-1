package com.acquisio.basic.java.question04;


import java.util.ArrayList;
import java.util.List;

class Inventory<T> {

    private List<T> items = new ArrayList<>();

    public void add(T t) {
        items.add(t);
    }

    public T getFirst() {
        if (items.size() > 0) {
            return items.get(0);
        }
        return null;
    }

    public T getLast() {
        int size = items.size();
        if (size > 0) {
            return items.get(size - 1);
        }
        return null;
    }


    public boolean remove(T t) {
        return items.remove(t);
    }

    public List<T> getList(){
    	return items;
    }
}
