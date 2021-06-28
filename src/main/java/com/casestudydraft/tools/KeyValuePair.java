package com.casestudydraft.tools;

import javax.validation.constraints.Min;


/*This is a custom Data Structure I use in a bunch of services usually as values of a hashmap
* I needed hashmaps that have two generic values for each key but didn't know how I would go about it without creating this class
* It works well as it has two type parameters anything I give it
* */
public class KeyValuePair<E, T> {
    @Min(0)
    private E first;
    @Min(0)
    private T second;

    public KeyValuePair() {
    }

    public KeyValuePair(E first, T second) {
        this.first = first;
        this.second = second;
    }

    public E getFirst() {
        return first;
    }

    public void setFirst(E first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "KeyValuePair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
