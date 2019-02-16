package com.example.unittesting;

/**
 * Created by jasonl on 03/01/19
 */
public class DataServiceEmptyArrayStub implements DataService
{
    DataServiceEmptyArrayStub(){
        System.out.println("DSEAS const");
    }

    @Override
    public int[] retrieveAllData()
    {
        return new int[]{};
    }

    @Override
    public void bar(){
        System.out.println("in dseas");
    }

}
