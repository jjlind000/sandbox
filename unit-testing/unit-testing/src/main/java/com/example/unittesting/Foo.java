package com.example.unittesting;

/**
 * Created by jasonl on 03/01/19
 */
public class Foo
{

    private DataService dataService;

    public Foo(){
        System.out.println("Foo const");
    }


    public void setDataService(DataService dataService)
    {
        this.dataService = dataService;
    }

    public int calculateSum(int [] data){
        int sum=0;
        for(int value : data){
            sum+=value;
        }

        return sum;
    }

    public int calculateSumUsingDataService(){

        int[] data = dataService.retrieveAllData();

        int sum=0;
        for(int value : data){
            sum+=value;
        }

        return sum;
    }

}
