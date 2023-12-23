package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.bo.custom.impl.CustomerBOImpl;
import com.example.layeredarchitecture.bo.custom.impl.ItemBOImpl;
import com.example.layeredarchitecture.bo.custom.impl.PlaceOrderBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getInstance(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BoType{
        CUSTOMER_BO,ITEM_BO,PLACE_ORDER_BO
    }

    public SuperBO getBO(BoType boType){
        switch (boType){
            case CUSTOMER_BO:
                return new CustomerBOImpl();
            case ITEM_BO:
                return new ItemBOImpl();
            case PLACE_ORDER_BO:
                return new PlaceOrderBOImpl();
            default:
                return null;
        }
    }
}

