package it.polimi.it.Exceptions;

import java.io.Serializable;

public class IllegalValueException extends Exception implements Serializable {
    private static final long serialVersionUID = 1088061435862726317L;

    public  IllegalValueException(String message) {
        super(message);
    }
}
