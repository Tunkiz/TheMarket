package com.darkCoders.TheMarket.models.exceptions;

import java.text.MessageFormat;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(long id){
        super(MessageFormat.format("Could not find cart with id: {0}", id));
    }
}
