package it.polimi.it.network.message.responses;

import it.polimi.it.network.message.Payload;

import java.io.Serializable;


public class PossibleColumnsResponse extends Payload implements Serializable {

    private static final long serialVersionUID = 3708993146076032130L;
    private boolean[] choosableColumns;

    public PossibleColumnsResponse(boolean[] choosableColumns){
        this.choosableColumns = choosableColumns;
    }

    public boolean[] getChoosableColumns() {
        return choosableColumns;
    }
}
