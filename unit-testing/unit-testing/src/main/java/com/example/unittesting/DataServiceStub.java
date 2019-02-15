package com.example.unittesting;

import javax.xml.crypto.Data;

/**
 * Created by jasonl on 03/01/19
 */
public class DataServiceStub implements DataService
{

    DataServiceStub(){
        System.out.println("DSS const");
    }

    public void bar(){
        System.out.println("in dss");
    }

    @Override
    public int[] retrieveAllData()
    {
        return new int[]{1,2,3};
    }
}
