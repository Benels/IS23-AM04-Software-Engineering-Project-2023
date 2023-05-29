package it.polimi.it.model;

import it.polimi.it.model.Board.B2P;
import it.polimi.it.model.Board.B3P;
import it.polimi.it.model.Board.B4P;
import it.polimi.it.model.Board.Board;
import it.polimi.it.model.Card.CommonGoalCards.*;
import it.polimi.it.model.Card.PersonalGoalCards.*;
import it.polimi.it.network.server.VirtualView;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.*;

public class Game implements Serializable {

    private static final long serialVersionUID = 3534520497074933321L;
    private List<User> players;
    private  Integer numplayers;
    private Board board;
    private Integer gameID;

    private ArrayList<User> playersOrder = new ArrayList<>();


    private ArrayList<Integer> points;
    private List<PersonalGoalCard> cards;
    private CommonGoalCard card1;
    private CommonGoalCard card2;


    private List<Integer> checkPersonalScore;
    private List<Integer> commonToken1;
    private List<Integer> commonToken2;
    private  Integer endToken;



    private transient VirtualView virtualView;


    public Game(Integer numplayers, User host, int gameID, VirtualView virtualView){

        this.endToken = -1;
        this.numplayers = numplayers;
        this.gameID = gameID;
        this.virtualView = virtualView;
        this.virtualView.setGame(this);

        host.setGame(this);
        this.players = new ArrayList<>(numplayers);
        this.players.add(host); // controllo se è empty ???
        //start all the player points to zero
        this.points = new ArrayList<>(Collections.nCopies(numplayers, 0));
        //start all the players score from personal cards to zero
        this.checkPersonalScore = new ArrayList<>(Collections.nCopies(numplayers,0));
        this.cards = new ArrayList<>(numplayers);

        this.commonToken1 = new ArrayList<>(numplayers);
        this.commonToken2 = new ArrayList<>(numplayers);


        this.commonToken1.add(0,8);
        this.commonToken1.add(1,0);
        this.commonToken1.add(2,4);
        this.commonToken1.add(3,0);

        this.commonToken2.add(0,8);
        this.commonToken2.add(1,0);
        this.commonToken2.add(2,4);
        this.commonToken2.add(3,0);

        if(numplayers == 2){
            this.board = new B2P();

        }else if(numplayers == 3){
            this.board = new B3P();

            this.commonToken1.set(1,6);
            this.commonToken1.set(3,0);
            this.commonToken2.set(1,6);
            this.commonToken2.set(3,0);


        }else{
            this.board = new B4P();

            this.commonToken1.set(1,6);
            this.commonToken1.set(3,2);
            this.commonToken2.set(1,6);
            this.commonToken2.set(3,2);


        }


        //view: mostro alla view

    }

    public ArrayList<User> randomPlayers () throws RemoteException {
        ArrayList<User> order = new ArrayList<>(this.numplayers);

        Random rdn = new Random();

        boolean[] checkplayers;
        checkplayers = new boolean[]{false, false, false, false};

        int position;
        for(int i = 0; i < this.numplayers ; i++){

            do{
                position = rdn.nextInt(this.numplayers);
            }while(checkplayers[position]);

            checkplayers[position] = true;
            order.add(i,this.players.get(position));
        }


        //mando alla view un po' di cose da inizializzare
        virtualView.startOrder(order);

        virtualView.initialMatrix(board.getMatrix());
        playersOrder=order;
        return order;
    }


    public void pointsFromAdjacent() throws RemoteException {

        //points from adjacent tiles with same color
        int tmp_point;
        for(int i=0; i < this.numplayers; i++){
            tmp_point = this.points.get(i);
            this.points.set(i, tmp_point + this.players.get(i).getShelfie().checkAdjacentsPoints());
        }

        virtualView.finalPoints(this.players,this.points);
    }

    public void endGame (User currentPlayer) throws RemoteException {

        //points from endToken
        int i;
        int tmp_point;
        if(this.endToken == -1){
            i = this.players.indexOf(currentPlayer);
            this.endToken = i;
            tmp_point = this.points.get(i);
            this.points.set(i, tmp_point + 1);

            virtualView.endTokenTaken(this.players.get(i));
        }

    }

    public void drawCommonCards() throws RemoteException {

        Random rnd = new Random();

        CommonDeck deck = new CommonDeck();

        int c1;
        int c2;

        do{
            c1 = rnd.nextInt(12) + 1;
            c2 = rnd.nextInt(12) + 1;
        }while(c1 == c2);

        deck.createCards(c1,c2);
        this.card1 = deck.getCommonCard1();
        this.card2 = deck.getCommonCard2();

        virtualView.drawnCommonCards(card1,card2,commonToken1,commonToken2);
    }

    public void joinGame (User joiner){

        joiner.setGame(this);
        this.players.add(joiner);
    }

    public void drawPersonalCard () throws RemoteException {

        PersonalGoalCard card;
        Random rnd = new Random();
        int id;

        do{
            id = rnd.nextInt(12) + 1;
            card  = new PersonalGoalCard(id);
        }while(isCardIdPresent(id));

        this.cards.add(card);

        User user = players.get(cards.size() - 1);
        virtualView.drawnPersonalCard(user.getNickname(),card);
    }

    public void pointCount(User player) throws RemoteException {

        int i = players.indexOf(player);
        //User player = this.players.get(i);
        PersonalGoalCard persCard = this.cards.get(i);

        Shelfie shelfie = player.getShelfie();

        //points from personal cards
        int personalScore = persCard.checkScore(shelfie);
        int tmp_point;
        int tmp_score;
        while(this.checkPersonalScore.get(i) < personalScore){
            tmp_score = this.checkPersonalScore.get(i);
            this.checkPersonalScore.set(i,tmp_score + 1);

            tmp_point = this.points.get(i);
            if(this.checkPersonalScore.get(i) <= 2){
                this.points.set(i, tmp_point + 1);
            }else if(this.checkPersonalScore.get(i) >= 3 && this.checkPersonalScore.get(i) <= 4){
                this.points.set(i, tmp_point + 2);
            }else {
                this.points.set(i, tmp_point + 3);
            }

        }
        //i punti dell'endtoken sono dati dal metodo end game



        //points from common card 1
        if(player.getShelfie().getCommonToken1() == 0 && card1.checkGoal(shelfie) ){

            int j=0;
            while(this.commonToken1.get(j) == 0){
                j++;
            }

            player.getShelfie().setCommonToken1(this.commonToken1.get(i));
            tmp_point = this.points.get(i);
            this.points.set(i, tmp_point + this.commonToken1.get(i));
            this.commonToken1.set(i, 0);
        }

        //points from common card 2
        if(player.getShelfie().getCommonToken2() == 0 && card2.checkGoal(shelfie) ){

            int j=0;
            while(this.commonToken2.get(j) == 0){
                j++;
            }

            player.getShelfie().setCommonToken2(this.commonToken2.get(i));
            tmp_point = this.points.get(i);
            this.points.set(i, tmp_point + this.commonToken2.get(i));
            this.commonToken2.set(i, 0);
        }

        virtualView.pointsUpdate(player, this.points.get(i), commonToken1, commonToken2);
    }

    public ArrayList<User> playersOrder(){
        return playersOrder;
    }

    private boolean isCardIdPresent(int id) {
        for (PersonalGoalCard existingCard : this.cards) {
            if (existingCard.getId() == id) {
                return true;
            }
        }
        return false;
    }


    //methods for testing----------------------------------------------------
    //getter
    public VirtualView getVirtualView(){
        return this.virtualView;
    }
    public Board getBoard(){
        return this.board;
    }
    public int getCurrentPlayersNum(){
        return this.players.size();
    }

    public Integer getEndToken(){
        return this.endToken;
    }

    public Integer getNumplayers(){
        return this.numplayers;
    }

    public User getPlayer(int i){
        return this.players.get(i);
    }

    public Integer getPoint(int i){
        return this.points.get(i);
    }

    public Integer getCheckPersonalScore (int i){
        return this.checkPersonalScore.get(i);
    }

    public Integer getCommonToken1(int i){
        return this.commonToken1.get(i);
    }

    public Integer getCommonToken2(int i){
        return  this.commonToken2.get(i);
    }

    public int getGameid(){
        return this.gameID;
    }

    public CommonGoalCard getCommonCard1(){
        return this.card1;
    }

    public CommonGoalCard getCommonCard2(){
        return this.card2;
    }

    public PersonalGoalCard getPersonalCard(int i){
        return this.cards.get(i);
    }

    public PersonalGoalCard getPersonalCard(User user){
        return this.cards.get(players.indexOf(user));
    }

    public void swapPlaysers(User old, User newborn) {
        players.set(players.indexOf(old), newborn);
       // playersOrder.set(playersOrder.indexOf(old), newborn);
        newborn.setBoard(board);
        newborn.setGame(this);
        newborn.setInGame(true);
    }
}
