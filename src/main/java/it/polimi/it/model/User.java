package it.polimi.it.model;

import it.polimi.it.model.Board.Board;
import it.polimi.it.model.Exceptions.InvalidTileException;
import it.polimi.it.model.Exceptions.WrongListException;
import it.polimi.it.model.Tiles.Tile;

import java.util.List;

public class User {
    private Board board;
    private Shelfie shelf;
    private Game game;
    private int tilesNumber;
    private final String nickname;
    private boolean inGame = false;

    public User(String nickname){

        this.nickname = nickname;

        this.inGame = true;
    }

    public int maxValueOfTiles() throws IndexOutOfBoundsException{

        int max = shelf.possibleTiles();
        if(max < 1 || max > 3){
            throw new IndexOutOfBoundsException("Il numero di tiles non è accettabile");
        }
        return board.findMaxAdjacent(max);
    }


    public List<List<Tile>> choosableTiles(int tilesNum) throws WrongListException, IndexOutOfBoundsException {

        if(tilesNum < 1 || tilesNum > 3){
            throw new IndexOutOfBoundsException("Wrong tiles number");
        }

        tilesNumber = tilesNum;

        List<List<Tile>> choosableList = board.choosableTiles(tilesNum);

        if(choosableList == null || choosableList.size() == 0){
            throw new WrongListException("Error in the chosen tiles list");
        }
        return choosableList;
    }

    public boolean[] chooseSelectedTiles(List<Tile> chosen) throws InvalidTileException {

        for(Tile t : chosen){
            if(t.getColor().equals("XTILE") || t.getColor().equals("DEFAULT")){
                throw new InvalidTileException("Tiles of this type can't be chosen");
            }
        }

        board.removeTiles(chosen);

        return shelf.chooseColumn(tilesNumber);
    }

    public boolean insertTile(int column, List<Tile> chosen) throws IndexOutOfBoundsException {

        boolean isEnd;
        if(column < 0 || column > 4){

            throw new IndexOutOfBoundsException("The given column value does not exist");

        }else {
            isEnd = shelf.addTile(column, chosen);
        }

        board.refill();
        return isEnd;
    }


    boolean checkInGame(User user) {

        //vedere quando crasha troooppooo broooo
        return true;
    }
    public Shelfie createShelfie() {

        this.board = this.game.getBoard();
        return this.shelf = new Shelfie();
    }

    public Shelfie getShelfie(){
        return this.shelf;
    }

    public Board getBoard(){
        return this.board;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public Game getGame(){
        return this.game;
    }

    public boolean getInGame() {
        return this.inGame;
    }

    public void setInGame(boolean state) {
        inGame=state;
    }
}
