package com.example.unittesting;

/**
 * Created by jasonl on 03/01/19
 */
public class Foo
{

    private DataService dataService;
    private X x;

    public void setX(X x)
    {
        System.out.println("setting x");
        this.x = x;
    }

    public Foo(){System.out.println("Foo const");}

    public Foo(int i){
        System.out.println("Foo const: " + i);
    }

//    public Foo(X x){
//        System.out.println("Foo(X) const");
//    }


    public void printDataService(){
        System.out.println("My DS is:" + dataService.getClass().getSimpleName());
    }

    public DataService getDataService()
    {
        return dataService;
    }

    public void setDataService(DataService dataService)
    {
        System.out.println("setting dataService");
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
