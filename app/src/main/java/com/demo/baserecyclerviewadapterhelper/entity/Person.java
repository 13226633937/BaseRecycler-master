package com.demo.baserecyclerviewadapterhelper.entity;

import com.demo.baserecyclerviewadapterhelper.adapter.ExpandableItemAdapter;
import com.chanven.lib.cptr.recyclerview.entity.MultiItemEntity;

/**
 * Created by luoxw on 2016/8/10.
 */

public class Person implements MultiItemEntity {
    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public String name;
    public int age;

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_PERSON;
    }
}