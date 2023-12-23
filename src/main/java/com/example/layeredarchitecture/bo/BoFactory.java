package com.example.layeredarchitecture.bo;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory(){

    }

    public static BoFactory getInstance(){
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BoType{
        CUSTOMER,ITEM,ORDER
    }









}

