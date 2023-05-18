package it.polimi.it.model.Card.CommonGoalCards;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import it.polimi.it.model.Shelfie;
import it.polimi.it.model.Tiles.Tile;

import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;

public class CommonGroup3 extends CommonGoalCard  implements Serializable {

    private static final long serialVersionUID = -7419756574191643439L;
    ArrayList<Tile> checked = new ArrayList<>();

    /**
     * constructor of the CommonGroup3.
     * @param id is the identification code of the CommonGoalCard, in this group the option for the ids are 3,4
     */
    public CommonGroup3(int id){ //carte 3 e 4
        super(id);
    }

    /**
     * method that find the adjacent tiles of a given tile
     * @param shelfie is the shelfie we need to check
     * @param x is the column of the tile
     * @param y is the row of the tile
     * @return the adjacent tiles
     */
    private ArrayList<Tile> adjacent(Shelfie shelfie, int x, int y) {
        ArrayList<Tile> adjacent = new ArrayList<>();
        if (x > 0) {
            adjacent.add(shelfie.getCell(x-1,y));
        }
        if (y > 0) {
            adjacent.add(shelfie.getCell(x,y-1));
        }
        if (x < 4) {
            adjacent.add(shelfie.getCell(x+1,y));
        }
        if (y < 5) {
            adjacent.add(shelfie.getCell(x,y+1));
        }
        return adjacent;
    }

    /**
     * the method that find only the adjacent tiles that are usefully for the common goal
     * @param shelfie that we need to check
     * @param x is the colum of the tile
     * @param y row of the tile
     * @param toVisit arrayList of tile not already checked
     * @param jsonObject contains information for the check
     * @return list of tiles
     */
    private  ArrayList<Tile> recursiveAdjacent(Shelfie shelfie, int x, int y, ArrayList<Tile> toVisit, JsonObject jsonObject) {
        int i;
        ArrayList<Tile> tmp = new  ArrayList<>();
        try {
            if(toVisit.size() == jsonObject.get("numOfTile").getAsInt()){
                for(Tile tile : toVisit){
                    if(!checked.contains(tile))
                        checked.add(tile);
                }
                return toVisit;
            }

            ArrayList<Tile> adjacent = adjacent(shelfie, x, y);
            checked.add(shelfie.getCell(x,y));
            for (Tile tile : adjacent) {
                if (!checked.contains(tile) && shelfie.getCell(x,y).getColor().equals(shelfie.getCell(tile.getColumn(),tile.getRow()).getColor())) {
                    toVisit.add(tile);
                    if(toVisit.size() == jsonObject.get("numOfTile").getAsInt()){
                        checked.add(tile);
                        return toVisit;
                    }
                    tmp.add(tile);
                }
            }

            for (Tile tile : tmp) {
                recursiveAdjacent(shelfie, tile.getColumn(), tile.getRow(), toVisit, jsonObject);
            }
            return toVisit;
        }catch (Exception e){
           throw new RuntimeException(e);
        }
    }

    /**
     * Method that check the goal of the CommonGoalCard
     * @param shelfie is the shelfie that we need to check
     * @return true or false
     */
    public Boolean checkGoal(Shelfie shelfie){
        Gson gson = new Gson();
        ArrayList<Tile> tmp;
        int i,row, column;
        int count=0;
        try{
            JsonReader reader = new JsonReader(new FileReader("src/main/resources/CommonGroup3.json"));
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
            for(i=0; jsonArray.get(i).getAsJsonObject().get("type").getAsInt() != id ; i++);
            JsonObject jsonObject= jsonArray.get(i).getAsJsonObject();
            for(row=0; row< 6; row++){
                for(column=0; column<5; column++){
                    ArrayList<Tile> toVisit = new ArrayList<>();
                    if((checked.isEmpty() || !checked.contains(shelfie.getCell(column,row))&& !shelfie.getCell(column,row).getColor().equals("DEFAULT"))){
                        tmp= recursiveAdjacent(shelfie, column, row,toVisit, jsonObject);
                        if(tmp.size() == jsonObject.get("numOfTile").getAsInt()){
                            count++;
                        }
                    }
                    if(count == jsonObject.get("numOfGroups").getAsInt())
                        return true;
                }
            }
            return false;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}

