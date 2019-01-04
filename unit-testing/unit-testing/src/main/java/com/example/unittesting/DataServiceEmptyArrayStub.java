package com.example.unittesting;

/**
 * Created by jasonl on 03/01/19
 */
public class DataServiceEmptyArrayStub implements DataService
{
    @Override
    public int[] retrieveAllData()
    {
        return new int[]{};
    }
}
