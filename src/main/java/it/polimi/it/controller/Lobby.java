package it.polimi.it.controller;

import it.polimi.it.controller.Exceptions.*;
import it.polimi.it.model.Exceptions.InvalidTileException;
import it.polimi.it.model.Exceptions.WrongListException;
import it.polimi.it.model.Game;
import it.polimi.it.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Lobby {

    private ArrayList<User> userList;
    private ArrayList<Game> gameList;
    private ArrayList<GameController> gameControllerList;

    private int gameCounterID;

    public Lobby() {
        userList = new ArrayList<>();
        gameList = new ArrayList<>();
        gameControllerList = new ArrayList<>();
        gameCounterID = 0;

    }

    public User createUser(String  nickname) throws EmptyNicknameException, ExistingNicknameException {

        User user;
        if(nickname.isEmpty()){
            throw new EmptyNicknameException("You must insert a nickname...");
        }else{
            if(userList.stream()
                        .map(currentUser -> currentUser.getNickname())
                        .noneMatch(name -> name.equals(nickname))
            ){
                user = new User(nickname);
                userList.add(user);
            }else{
                throw new ExistingNicknameException("This nickname already exists!");
            }
        }
        //return (User) userList.stream().map(user -> user.getNickname()).filter(name -> name.equals(nickname));
        //return userList.get(userList.size()-1);
        return user;
    }

    public void createGame(User user, int playerNumber) throws IndexOutOfBoundsException, NotExistingUser {

        if(playerNumber < 1 || playerNumber > 4){
            throw new IndexOutOfBoundsException("Wrong number of players");
        }
        if(userList.size()==0){
            throw new NotExistingUser("There aren't any players that might start a game...");
        }
        //pickUser(user); non stai facendo una pick, così togli tutto il riferimento che ti sei passato sul client

        //fai il controllo: l'user che crea il game deve esistere ed essere nella lista, vedi metodo sotto
        Game game = new Game(playerNumber, user, gameCounterID);
        user.setInGame(true);
        gameList.add(game);
        GameController gameContr = new GameController(game, this);
        gameControllerList.add(gameContr);
        gameCounterID++;

    }

    public void joinGame(User user, int gameID) throws NotExistingUser, InvalidIDException, FullGameException, WrongListException, IllegalValueException, InvalidTileException {

        List<Game> findGame = gameList.stream().filter(game -> game.getGameid() == gameID).collect(Collectors.toList());
        if(gameID<=gameCounterID && !findGame.isEmpty()){
            if(findGame.get(0).getCurrentPlayersNum()<findGame.get(0).getNumplayers()){
                if (userList.contains(user) && !user.getInGame()){
                    findGame.get(0).joinGame(user);
                    user.setInGame(true);
                    if (findGame.get(0).getNumplayers()==findGame.get(0).getCurrentPlayersNum()){
                        //starto effettivamente il game
                        gameControllerList.get(gameList.indexOf(findGame.get(0))).firstTurnStarter();
                    }
                }else{
                    throw new NotExistingUser("This user does not exist");
                }
            }else{
                throw new FullGameException("There are already too many players in this game!");
            }
        }else{
            throw new InvalidIDException("The given game ID does not exists");
        }
    }


    ArrayList<User> getUserList(){
        return userList;
    }

    int getGameCounterID(){
        return gameCounterID;
    }

    Game getGame(int ID) throws InvalidIDException {
        for(Game game : gameList){
            if(game.getGameid()==ID){
                return game;
            }
        }
        throw new InvalidIDException("This ID does not exists");
    }



    GameController getGameController(int gameID) throws InvalidIDException{
        for (GameController gameContr: gameControllerList){
            if (gameContr.getGame().getGameid()==gameID){
                return gameContr;
            }
        }
        throw new InvalidIDException("This ID does not exists");
    }



    public void notifyEndGame(int gameID) throws InvalidIDException {

        Game            gameToBeDeleted = getGame(gameID);
        GameController  gCToBeDeleted   = getGameController(gameID);

        gameList            .remove(getGame(gameID));
        gameControllerList  .remove(gCToBeDeleted);
    }

}
