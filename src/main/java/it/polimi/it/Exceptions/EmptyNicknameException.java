package it.polimi.it.Exceptions;

import java.io.Serializable;

public class EmptyNicknameException extends Exception implements Serializable {
    private static final long serialVersionUID = 5571593270962157540L;

    public EmptyNicknameException(String message) {
        super(message);
    }
}
