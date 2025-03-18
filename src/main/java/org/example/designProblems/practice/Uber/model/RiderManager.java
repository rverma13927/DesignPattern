package org.example.designProblems.practice.Uber.model;

import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.Map;


public class RiderManager {
    private Map<Integer, Rider> riderMap= new HashMap<>();

    private static RiderManager riderManager = null;

    public static RiderManager getRiderManagerInstance(){

        if(riderManager==null){
            synchronized (RiderManager.class){
                if(riderManager==null){
                    riderManager= new RiderManager();
                }
            }
        }
        return riderManager;
    }


   public boolean addRider(Rider rider){
        riderMap.put(rider.id,rider);
        return true;
    }
    public Rider getAvialableRider(){
        return riderMap.get(1);
    }
}
