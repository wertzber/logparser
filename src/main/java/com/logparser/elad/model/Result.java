package com.logparser.elad.model;

/**
 * Created by eladw on 3/26/17.
 */
public class Result{

    private String key;
    private int count;


    public Result(String key, int count) {
        this.key = key;
        this.count = count;
    }

    public String getKey() {
        return key;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("key='").append(key).append('\'');
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }

//    @Override
//    public int compareTo(Object o) {
//        return this.count - ((Result)o).count;
//    }
}
