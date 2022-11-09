package com.darkCoders.TheMarket.models.exceptions;

import java.text.MessageFormat;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(long id){
        super(MessageFormat.format("Could not find product with id: {0}", id));
    }
}
