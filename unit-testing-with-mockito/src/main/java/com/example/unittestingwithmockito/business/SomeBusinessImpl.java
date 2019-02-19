package com.example.unittestingwithmockito.business;

import com.example.unittestingwithmockito.data.SomeDataService;

import java.util.Arrays;

public class SomeBusinessImpl {

    //step 3 .. adding the data service
    private SomeDataService someDataService;
    public void setSomeDataService(SomeDataService someDataService) {
        this.someDataService = someDataService;
    }

    int calculateSum(int [] data ){
        return Arrays.stream(data).reduce(Integer::sum).orElse(0);
    }

    //step 3: adding a new method tha uses a data service that is used to provide the data
    int calculateSumUsingDataService(){
        int sum =0;
        int [] data = someDataService.retrieveAllData();
        for(int i : data)
            sum += i;
        return sum;
    }
}
