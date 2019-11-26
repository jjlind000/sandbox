package com.logicbig;

import com.jwidgets.SimpleRenderer;

/**
 * Created by jasonl on 04/11/19
 */
public class RendererSupport
{
    public void render(Object object){
        new SimpleRenderer().renderAsString(object);
    }
}
