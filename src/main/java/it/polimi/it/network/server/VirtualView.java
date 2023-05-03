package it.polimi.it.network.server;

import it.polimi.it.model.Card.CommonGoalCards.CommonGoalCard;
import it.polimi.it.model.Card.PersonalGoalCards.PersonalGoalCard;
import it.polimi.it.model.Game;
import it.polimi.it.model.Tiles.Tile;
import it.polimi.it.model.User;
import it.polimi.it.network.client.ClientRMI;
import it.polimi.it.network.message.Message;
import it.polimi.it.network.message.MessageType;
import it.polimi.it.network.message.responses.*;
import it.polimi.it.network.server.Exceptions.NotTcpUserException;

import java.io.IOException;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VirtualView {

    private Game game;

    private HashMap<User,String > typeOfConnection;
    private ServerTCP serverTCP;

    private HashMap<User, ObjectOutputStream> userTCP;
    private RMIImplementation serverRMI;

    private HashMap<User, ClientRMI> userRMI;

    public VirtualView( ServerTCP serverTCP ,RMIImplementation serverRMI){
        typeOfConnection = new HashMap<>();
        this.serverTCP = serverTCP;
        this.serverRMI = serverRMI;
    }

    public void setGame(Game game){
        this.game = game;
    }
    public void setUser(User user){
        try {
            Socket socket = serverTCP.getUserTCP(user);
            typeOfConnection.put(user, "TCP");
            try{
                userTCP.put(user, new ObjectOutputStream(socket.getOutputStream()));
            } catch (IOException e) {
            throw new RuntimeException(e); /// gestiscoooooooooooooooooooooo
            }
        } catch (NotTcpUserException e) {
            userRMI.put(user, serverRMI.getUserRMI(user));
            typeOfConnection.put(user, "RMI");
        }
    }

    //metodi di inizio partita
    public void startOrder(ArrayList<User> order){
        for (int i=0; i < game.getNumplayers(); i++){
            User user = game.getPlayer(i);

            if(typeOfConnection.get(user).equals("TCP")){

                StartOrderMessage startOrderMessage = new StartOrderMessage(order);
                Message message = new Message(MessageType.STARTORDERPLAYER, startOrderMessage);
                sendTCPMessage(userTCP.get(user), message);

            }else if(typeOfConnection.get(game.getPlayer(i)).equals("RMI")){
                //sviluppo in RMI
            }

        }
    }

    //mando la matrice iniziale e la lista dei token
    public void initialMatrix(Tile[][] matrix){
        for (int i=0; i < game.getNumplayers(); i++) {
            User user = game.getPlayer(i);

            if (typeOfConnection.get(user).equals("TCP")) {

                InitialMatrixMessage initialMatrixMessage = new InitialMatrixMessage(matrix);
                Message message = new Message(MessageType.INITIALMATRIX, initialMatrixMessage);
                sendTCPMessage(userTCP.get(user), message);

            } else if (typeOfConnection.get(game.getPlayer(i)).equals("RMI")) {
                //sviluppo in RMI
            }
        }
    }

    public void drawnCommonCards(CommonGoalCard card1, CommonGoalCard card2, List<Integer> commonToken1, List<Integer> commonToken2){
        for (int i=0; i < game.getNumplayers(); i++) {
            User user = game.getPlayer(i);

            if (typeOfConnection.get(user).equals("TCP")) {

                DrawnCommonCardsMessage drawnCommonCardsMessage = new DrawnCommonCardsMessage(card1,card2,commonToken1,commonToken2);
                Message message = new Message(MessageType.DRAWNCOMMONCARDS, drawnCommonCardsMessage);
                sendTCPMessage(userTCP.get(user), message);

            } else if (typeOfConnection.get(user).equals("RMI")) {
                //sviluppo in RMI
            }
        }
    }

    public void drawnPersonalCard(User user, PersonalGoalCard card){

        if (typeOfConnection.get(user).equals("TCP")) {

            DrawnPersonalCardMessage drawnPersonalCardMessage = new DrawnPersonalCardMessage(card);
            Message message = new Message(MessageType.DRAWNPERSONALCARD,drawnPersonalCardMessage);
            sendTCPMessage(userTCP.get(user), message);

        } else if (typeOfConnection.get(user).equals("RMI")) {
            //sviluppo in RMI
        }
    }

    //during the turn
    //starting turn
    public void startTurn(User user,int maxValueofTiles){

        if (typeOfConnection.get(user).equals("TCP")) {

            StartTurnMessage startTurnMessage = new StartTurnMessage(maxValueofTiles);
            Message message = new Message(MessageType.STARTTURN,startTurnMessage);
            sendTCPMessage(userTCP.get(user), message);

        } else if (typeOfConnection.get(user).equals("RMI")) {
            //sviluppo in RMI
        }
    }
    public void takeableTiles(User user, List<List<Tile>> choosableTilesList){
        if(typeOfConnection.get(user).equals("TCP")){

                TakeableTilesResponse takeableTilesResponse = new TakeableTilesResponse(choosableTilesList);
                Message response = new Message(MessageType.TAKEABLETILES , takeableTilesResponse);
                sendTCPMessage(userTCP.get(user), response);


        }else if(typeOfConnection.get(user).equals("RMI")){
            ClientRMI clientRMI = userRMI.get(user);
            clientRMI.takeableTiles(choosableTilesList);
        }
    }


    public void possibleColumns(User user, boolean[] choosableColumns){

        if(typeOfConnection.get(user).equals("TCP")){

            PossibleColumnsResponse possibleColumnsResponse = new PossibleColumnsResponse(choosableColumns);
            Message response = new Message(MessageType.POSSIBLECOLUMNS , possibleColumnsResponse);
            sendTCPMessage(userTCP.get(user), response);


        }else if(typeOfConnection.get(user).equals("RMI")){
            //sviluppo in RMI
        }
    }

    public void shelfieUpdate(User user, int column, List<Tile> chosen){
        for (int i=0; i < game.getNumplayers(); i++) {
            User  receiver = game.getPlayer(i);

            if (typeOfConnection.get(receiver).equals("TCP")) {

                ShelfieUpdateMessage shelfieUpdateMessage = new ShelfieUpdateMessage(user, column, chosen);
                Message message = new Message(MessageType.SHELFIEUPDATE, shelfieUpdateMessage);
                sendTCPMessage(userTCP.get(receiver), message);

            } else if (typeOfConnection.get(receiver).equals("RMI")) {
                //sviluppo in RMI
            }
        }
    }

    public void boardUpdate(Tile[][] matrix){
        for (int i=0; i < game.getNumplayers(); i++) {
            User  receiver = game.getPlayer(i);

            if (typeOfConnection.get(receiver).equals("TCP")) {

                BoardUpdateMessage boardUpdateMessage = new BoardUpdateMessage(matrix);
                Message message = new Message(MessageType.BOARDUPDATE, boardUpdateMessage);
                sendTCPMessage(userTCP.get(receiver), message);

            } else if (typeOfConnection.get(receiver).equals("RMI")) {
                //sviluppo in RMI
            }
        }
    }

    public void pointsUpdate(User user, Integer point, List<Integer> commonToken1, List<Integer> commonToken2){
        for (int i=0; i < game.getNumplayers(); i++) {
            User  receiver = game.getPlayer(i);

            if (typeOfConnection.get(receiver).equals("TCP")) {

                PointsUpdateMessage pointsUpdateMessage = new PointsUpdateMessage(user,point,commonToken1,commonToken2);
                Message message = new Message(MessageType.POINTSUPDATE, pointsUpdateMessage);
                sendTCPMessage(userTCP.get(receiver), message);

            } else if (typeOfConnection.get(receiver).equals("RMI")) {
                //sviluppo in RMI
            }
        }
    }

    public void endTokenTaken(User user){
        for (int i=0; i < game.getNumplayers(); i++) {
            User  receiver = game.getPlayer(i);

            if (typeOfConnection.get(receiver).equals("TCP")) {

                EndTokenTakenMessage endTokenTakenMessage = new EndTokenTakenMessage(user);
                Message message = new Message(MessageType.ENDTOKEN, endTokenTakenMessage);
                sendTCPMessage(userTCP.get(receiver), message);

            } else if (typeOfConnection.get(receiver).equals("RMI")) {
                //sviluppo in RMI
            }
        }
    }


    public void finalPoints(List<User> users, ArrayList<Integer> points){
        for (int i=0; i < game.getNumplayers(); i++) {
            User  receiver = game.getPlayer(i);

            if (typeOfConnection.get(receiver).equals("TCP")) {

                FinalPointsMessage finalPointsMessage = new FinalPointsMessage(users,points);
                Message message = new Message(MessageType.FINALPOINTS, finalPointsMessage);
                sendTCPMessage(userTCP.get(receiver), message);

            } else if (typeOfConnection.get(receiver).equals("RMI")) {
                //sviluppo in RMI
            }
        }
    }

    public void sendTCPMessage(ObjectOutputStream out, Message message){

        synchronized (out){
            try {
                out.writeObject(message);
                out.flush();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
