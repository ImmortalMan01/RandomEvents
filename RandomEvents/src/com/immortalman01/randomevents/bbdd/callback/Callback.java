package com.immortalman01.randomevents.bbdd.callback;

public interface Callback<V extends Object, T extends Throwable> {
    public void call(V result, T thrown);
}