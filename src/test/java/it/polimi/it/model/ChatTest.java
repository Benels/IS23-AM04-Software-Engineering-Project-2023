package it.polimi.it.model;

import it.polimi.it.view.Cli;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ChatTest {
    private Chat chat;


    @Before
    public void Chat(){
        this.chat = new Chat();
    }


    @Test
    public void basic(){
        chat.newMessage("Franco: Trentatre trentini arrivarono in Trento tutti e trentatre trotterellando");
        chat.newPrivateMessage("[DM]Fvanco: Tventatve tventini avvivavono in Tvento tutti e tventatve tvottevellando");

        List<String> c = chat.getCurrentChat();
        for (String s: c){
            System.out.println(c);
        }
    }

}
