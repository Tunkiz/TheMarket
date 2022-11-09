package com.darkCoders.TheMarket.models.exceptions;

import java.text.MessageFormat;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(long id){
        super(MessageFormat.format("Could not find category with Id: {id}", id));
    }
}
