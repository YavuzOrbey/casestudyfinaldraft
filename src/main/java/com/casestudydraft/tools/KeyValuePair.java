package com.casestudydraft.tools;

import javax.validation.constraints.Min;

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
