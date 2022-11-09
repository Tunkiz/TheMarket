package com.darkCoders.TheMarket.models.exceptions;

import java.text.MessageFormat;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(long id){
        super(MessageFormat.format("Could not find user with id: {0}", id));
    }
}
