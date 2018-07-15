package com.example.nguyenduy.projectbase.base;

import java.io.Serializable;

public interface IBaseIntent {

    boolean isIntentBundle();

    <O extends Serializable> O getIntentObject(String key, O valueDefault);

    String getIntentString(String key, String valueDefault);

    Long getIntentLong(String key, long valueDefault);

    Integer getIntentInt(String key, int valueDefault);

}
