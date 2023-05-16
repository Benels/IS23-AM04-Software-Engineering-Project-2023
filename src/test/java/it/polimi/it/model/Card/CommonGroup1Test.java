package it.polimi.it.model.Card;

import it.polimi.it.model.Card.CommonGoalCards.CommonDeck;
import it.polimi.it.model.Card.CommonGoalCards.CommonGoalCard;
import it.polimi.it.model.Shelfie;
import it.polimi.it.model.Tiles.PossibleColors;
import it.polimi.it.model.Tiles.Tile;
import it.polimi.it.model.Tiles.TilesBag;
import it.polimi.it.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class CommonGroup1Test {
    private CommonGoalCard card1;
    private CommonGoalCard card2;
    private CommonDeck commonDeck;

    private Shelfie shelfie;
    private TilesBag bag;

    private User u = new User(" ");

    @Before
    public void card(){
        this.commonDeck = new CommonDeck();
        commonDeck.createCards(1,10);
        this.card1 = commonDeck.getCommonCard1();
        this.card2 = commonDeck.getCommonCard2();
        this.shelfie = new Shelfie(u);
        this.bag = new TilesBag();
    }

    @Test
    public void checkGoalTest1(){ //testing card 1
        int column, row;
        Tile[][] matrix = new Tile[6][5];

        matrix[0][0] = new Tile(0, 0, PossibleColors.PINK);
        matrix[1][0] = new Tile(1,0,PossibleColors.PINK);
        matrix[2][0] = new Tile(2,0,PossibleColors.CYAN);
        matrix[3][0] = new Tile(3, 0,PossibleColors.BLUE);
        matrix[4][0] = new Tile(4,0,PossibleColors.WHITE);
        matrix[5][0] = new Tile(5,0,PossibleColors.PINK);

        matrix[0][1] = new Tile(0, 1, PossibleColors.PINK);
        matrix[1][1] = new Tile(1,1,PossibleColors.PINK);
        matrix[2][1] = new Tile(2,1,PossibleColors.CYAN);
        matrix[3][1] = new Tile(3, 1,PossibleColors.BLUE);
        matrix[4][1] = new Tile(4,1,PossibleColors.YELLOW);
        matrix[5][1] = new Tile(5,1,PossibleColors.PINK);

        matrix[0][2] = new Tile(0, 2, PossibleColors.PINK);
        matrix[1][2] = new Tile(1,2,PossibleColors.YELLOW);
        matrix[2][2] = new Tile(2,2,PossibleColors.CYAN);
        matrix[3][2] = new Tile(3, 2,PossibleColors.PINK);
        matrix[4][2] = new Tile(4,2,PossibleColors.PINK);
        matrix[5][2] = new Tile(5,2,PossibleColors.PINK);

        matrix[0][3] = new Tile(0, 3, PossibleColors.PINK);
        matrix[1][3] = new Tile(1,3,PossibleColors.BLUE);
        matrix[2][3] = new Tile(2,3,PossibleColors.CYAN);
        matrix[3][3] = new Tile(3, 3,PossibleColors.PINK);
        matrix[4][3] = new Tile(4,3,PossibleColors.PINK);
        matrix[5][3] = new Tile(5,3,PossibleColors.PINK);

        matrix[0][4] = new Tile(0, 4, PossibleColors.PINK);
        matrix[1][4] = new Tile(1,4,PossibleColors.CYAN);
        matrix[2][4] = new Tile(2,4,PossibleColors.CYAN);
        matrix[3][4] = new Tile(3, 4,PossibleColors.BLUE);
        matrix[4][4] = new Tile(4,4,PossibleColors.WHITE);
        matrix[5][4] = new Tile(5,4,PossibleColors.CYAN);

        shelfie.setShelf(matrix);

        if(!card1.checkGoal(shelfie))
            fail("Test failed");
    }

    @Test
    public void checkGoalTest2(){ //testing card 1
        int column, row;
        Tile[][] matrix = new Tile[6][5];

        matrix[0][0] = new Tile(0, 0, PossibleColors.PINK);
        matrix[1][0] = new Tile(1,0,PossibleColors.PINK);
        matrix[2][0] = new Tile(2,0,PossibleColors.CYAN);
        matrix[3][0] = new Tile(3, 0,PossibleColors.BLUE);
        matrix[4][0] = new Tile(4,0,PossibleColors.WHITE);
        matrix[5][0] = new Tile(5,0,PossibleColors.PINK);

        matrix[0][1] = new Tile(0, 1, PossibleColors.YELLOW);
        matrix[1][1] = new Tile(1,1,PossibleColors.PINK);
        matrix[2][1] = new Tile(2,1,PossibleColors.CYAN);
        matrix[3][1] = new Tile(3, 1,PossibleColors.BLUE);
        matrix[4][1] = new Tile(4,1,PossibleColors.YELLOW);
        matrix[5][1] = new Tile(5,1,PossibleColors.PINK);

        matrix[0][2] = new Tile(0, 2, PossibleColors.PINK);
        matrix[1][2] = new Tile(1,2,PossibleColors.YELLOW);
        matrix[2][2] = new Tile(2,2,PossibleColors.CYAN);
        matrix[3][2] = new Tile(3, 2,PossibleColors.PINK);
        matrix[4][2] = new Tile(4,2,PossibleColors.PINK);
        matrix[5][2] = new Tile(5,2,PossibleColors.PINK);

        matrix[0][3] = new Tile(0, 3, PossibleColors.PINK);
        matrix[1][3] = new Tile(1,3,PossibleColors.BLUE);
        matrix[2][3] = new Tile(2,3,PossibleColors.CYAN);
        matrix[3][3] = new Tile(3, 3,PossibleColors.PINK);
        matrix[4][3] = new Tile(4,3,PossibleColors.PINK);
        matrix[5][3] = new Tile(5,3,PossibleColors.PINK);

        matrix[0][4] = new Tile(0, 4, PossibleColors.PINK);
        matrix[1][4] = new Tile(1,4,PossibleColors.CYAN);
        matrix[2][4] = new Tile(2,4,PossibleColors.CYAN);
        matrix[3][4] = new Tile(3, 4,PossibleColors.BLUE);
        matrix[4][4] = new Tile(4,4,PossibleColors.WHITE);
        matrix[5][4] = new Tile(5,4,PossibleColors.CYAN);

        shelfie.setShelf(matrix);

        if(card1.checkGoal(shelfie))
            fail("Test failed");
    }

    @Test
    public void checkGoalTest3(){ //testing card 1
        int column, row;
        Tile[][] matrix = new Tile[6][5];

        matrix[0][0] = new Tile(0, 0, PossibleColors.PINK);
        matrix[1][0] = new Tile(1,0,PossibleColors.PINK);
        matrix[2][0] = new Tile(2,0,PossibleColors.CYAN);
        matrix[3][0] = new Tile(3, 0,PossibleColors.BLUE);
        matrix[4][0] = new Tile(4,0,PossibleColors.WHITE);
        matrix[5][0] = new Tile(5,0,PossibleColors.WHITE);

        matrix[0][1] = new Tile(0, 1, PossibleColors.YELLOW);
        matrix[1][1] = new Tile(1,1,PossibleColors.PINK);
        matrix[2][1] = new Tile(2,1,PossibleColors.WHITE);
        matrix[3][1] = new Tile(3, 1,PossibleColors.WHITE);
        matrix[4][1] = new Tile(4,1,PossibleColors.WHITE);
        matrix[5][1] = new Tile(5,1,PossibleColors.WHITE);

        matrix[0][2] = new Tile(0, 2, PossibleColors.PINK);
        matrix[1][2] = new Tile(1,2,PossibleColors.YELLOW);
        matrix[2][2] = new Tile(2,2,PossibleColors.WHITE);
        matrix[3][2] = new Tile(3, 2,PossibleColors.WHITE);
        matrix[4][2] = new Tile(4,2,PossibleColors.PINK);
        matrix[5][2] = new Tile(5,2,PossibleColors.WHITE);

        matrix[0][3] = new Tile(0, 3, PossibleColors.PINK);
        matrix[1][3] = new Tile(1,3,PossibleColors.BLUE);
        matrix[2][3] = new Tile(2,3,PossibleColors.CYAN);
        matrix[3][3] = new Tile(3, 3,PossibleColors.PINK);
        matrix[4][3] = new Tile(4,3,PossibleColors.PINK);
        matrix[5][3] = new Tile(5,3,PossibleColors.PINK);

        matrix[0][4] = new Tile(0, 4, PossibleColors.PINK);
        matrix[1][4] = new Tile(1,4,PossibleColors.CYAN);
        matrix[2][4] = new Tile(2,4,PossibleColors.CYAN);
        matrix[3][4] = new Tile(3, 4,PossibleColors.BLUE);
        matrix[4][4] = new Tile(4,4,PossibleColors.WHITE);
        matrix[5][4] = new Tile(5,4,PossibleColors.CYAN);

        shelfie.setShelf(matrix);

        if(!card1.checkGoal(shelfie))
            fail("Test failed");
    }


    @Test
    public void checkGoalTest4(){ //testing card 1
        int column, row;
        Tile[][] matrix = new Tile[6][5];

        matrix[0][0] = new Tile(0, 0, PossibleColors.YELLOW);
        matrix[1][0] = new Tile(1,0,PossibleColors.PINK);
        matrix[2][0] = new Tile(2,0,PossibleColors.CYAN);
        matrix[3][0] = new Tile(3, 0,PossibleColors.BLUE);
        matrix[4][0] = new Tile(4,0,PossibleColors.WHITE);
        matrix[5][0] = new Tile(5,0,PossibleColors.PINK);

        matrix[0][1] = new Tile(0, 1, PossibleColors.PINK);
        matrix[1][1] = new Tile(1,1,PossibleColors.PINK);
        matrix[2][1] = new Tile(2,1,PossibleColors.CYAN);
        matrix[3][1] = new Tile(3, 1,PossibleColors.BLUE);
        matrix[4][1] = new Tile(4,1,PossibleColors.YELLOW);
        matrix[5][1] = new Tile(5,1,PossibleColors.PINK);

        matrix[0][2] = new Tile(0, 2, PossibleColors.PINK);
        matrix[1][2] = new Tile(1,2,PossibleColors.PINK);
        matrix[2][2] = new Tile(2,2,PossibleColors.CYAN);
        matrix[3][2] = new Tile(3, 2,PossibleColors.GREEN);
        matrix[4][2] = new Tile(4,2,PossibleColors.GREEN);
        matrix[5][2] = new Tile(5,2,PossibleColors.GREEN);

        matrix[0][3] = new Tile(0, 3, PossibleColors.PINK);
        matrix[1][3] = new Tile(1,3,PossibleColors.PINK);
        matrix[2][3] = new Tile(2,3,PossibleColors.CYAN);
        matrix[3][3] = new Tile(3, 3,PossibleColors.GREEN);
        matrix[4][3] = new Tile(4,3,PossibleColors.GREEN);
        matrix[5][3] = new Tile(5,3,PossibleColors.PINK);

        matrix[0][4] = new Tile(0, 4, PossibleColors.PINK);
        matrix[1][4] = new Tile(1,4,PossibleColors.CYAN);
        matrix[2][4] = new Tile(2,4,PossibleColors.CYAN);
        matrix[3][4] = new Tile(3, 4,PossibleColors.BLUE);
        matrix[4][4] = new Tile(4,4,PossibleColors.WHITE);
        matrix[5][4] = new Tile(5,4,PossibleColors.CYAN);

        shelfie.setShelf(matrix);

        if(card1.checkGoal(shelfie))
            fail("Test failed");
    }

    @Test
    public void checkGoalTest5(){ //testing card 10
        int column, row;
        Tile[][] matrix = new Tile[6][5];

        matrix[0][0] = new Tile(0, 0, PossibleColors.YELLOW);
        matrix[1][0] = new Tile(1,0,PossibleColors.PINK);
        matrix[2][0] = new Tile(2,0,PossibleColors.CYAN);
        matrix[3][0] = new Tile(3, 0,PossibleColors.BLUE);
        matrix[4][0] = new Tile(4,0,PossibleColors.WHITE);
        matrix[5][0] = new Tile(5,0,PossibleColors.PINK);

        matrix[0][1] = new Tile(0, 1, PossibleColors.PINK);
        matrix[1][1] = new Tile(1,1,PossibleColors.PINK);
        matrix[2][1] = new Tile(2,1,PossibleColors.CYAN);
        matrix[3][1] = new Tile(3, 1,PossibleColors.PINK);
        matrix[4][1] = new Tile(4,1,PossibleColors.YELLOW);
        matrix[5][1] = new Tile(5,1,PossibleColors.PINK);

        matrix[0][2] = new Tile(0, 2, PossibleColors.PINK);
        matrix[1][2] = new Tile(1,2,PossibleColors.PINK);
        matrix[2][2] = new Tile(2,2,PossibleColors.PINK);
        matrix[3][2] = new Tile(3, 2,PossibleColors.GREEN);
        matrix[4][2] = new Tile(4,2,PossibleColors.GREEN);
        matrix[5][2] = new Tile(5,2,PossibleColors.GREEN);

        matrix[0][3] = new Tile(0, 3, PossibleColors.PINK);
        matrix[1][3] = new Tile(1,3,PossibleColors.PINK);
        matrix[2][3] = new Tile(2,3,PossibleColors.CYAN);
        matrix[3][3] = new Tile(3, 3,PossibleColors.PINK);
        matrix[4][3] = new Tile(4,3,PossibleColors.GREEN);
        matrix[5][3] = new Tile(5,3,PossibleColors.PINK);

        matrix[0][4] = new Tile(0, 4, PossibleColors.PINK);
        matrix[1][4] = new Tile(1,4,PossibleColors.CYAN);
        matrix[2][4] = new Tile(2,4,PossibleColors.CYAN);
        matrix[3][4] = new Tile(3, 4,PossibleColors.BLUE);
        matrix[4][4] = new Tile(4,4,PossibleColors.WHITE);
        matrix[5][4] = new Tile(5,4,PossibleColors.CYAN);

        shelfie.setShelf(matrix);

        if(!card2.checkGoal(shelfie))
            fail("Test failed");
    }

    @Test
    public void checkGoalTest6(){ //testing card 10
        int column, row;
        Tile[][] matrix = new Tile[6][5];

        matrix[0][0] = new Tile(0, 0, PossibleColors.YELLOW);
        matrix[1][0] = new Tile(1,0,PossibleColors.PINK);
        matrix[2][0] = new Tile(2,0,PossibleColors.CYAN);
        matrix[3][0] = new Tile(3, 0,PossibleColors.BLUE);
        matrix[4][0] = new Tile(4,0,PossibleColors.WHITE);
        matrix[5][0] = new Tile(5,0,PossibleColors.PINK);

        matrix[0][1] = new Tile(0, 1, PossibleColors.PINK);
        matrix[1][1] = new Tile(1,1,PossibleColors.PINK);
        matrix[2][1] = new Tile(2,1,PossibleColors.CYAN);
        matrix[3][1] = new Tile(3, 1,PossibleColors.PINK);
        matrix[4][1] = new Tile(4,1,PossibleColors.YELLOW);
        matrix[5][1] = new Tile(5,1,PossibleColors.PINK);

        matrix[0][2] = new Tile(0, 2, PossibleColors.PINK);
        matrix[1][2] = new Tile(1,2,PossibleColors.PINK);
        matrix[2][2] = new Tile(2,2,PossibleColors.WHITE);
        matrix[3][2] = new Tile(3, 2,PossibleColors.GREEN);
        matrix[4][2] = new Tile(4,2,PossibleColors.GREEN);
        matrix[5][2] = new Tile(5,2,PossibleColors.GREEN);

        matrix[0][3] = new Tile(0, 3, PossibleColors.PINK);
        matrix[1][3] = new Tile(1,3,PossibleColors.PINK);
        matrix[2][3] = new Tile(2,3,PossibleColors.CYAN);
        matrix[3][3] = new Tile(3, 3,PossibleColors.PINK);
        matrix[4][3] = new Tile(4,3,PossibleColors.GREEN);
        matrix[5][3] = new Tile(5,3,PossibleColors.PINK);

        matrix[0][4] = new Tile(0, 4, PossibleColors.PINK);
        matrix[1][4] = new Tile(1,4,PossibleColors.CYAN);
        matrix[2][4] = new Tile(2,4,PossibleColors.CYAN);
        matrix[3][4] = new Tile(3, 4,PossibleColors.BLUE);
        matrix[4][4] = new Tile(4,4,PossibleColors.WHITE);
        matrix[5][4] = new Tile(5,4,PossibleColors.CYAN);

        shelfie.setShelf(matrix);

        if(card2.checkGoal(shelfie))
            fail("Test failed");
    }

    @Test
    public void checkGoalTest7(){ //testing card 10
        int column, row;
        Tile[][] matrix = new Tile[6][5];

        matrix[0][0] = new Tile(0, 0, PossibleColors.YELLOW);
        matrix[1][0] = new Tile(1,0,PossibleColors.PINK);
        matrix[2][0] = new Tile(2,0,PossibleColors.CYAN);
        matrix[3][0] = new Tile(3, 0,PossibleColors.BLUE);
        matrix[4][0] = new Tile(4,0,PossibleColors.WHITE);
        matrix[5][0] = new Tile(5,0,PossibleColors.PINK);

        matrix[0][1] = new Tile(0, 1, PossibleColors.PINK);
        matrix[1][1] = new Tile(1,1,PossibleColors.PINK);
        matrix[2][1] = new Tile(2,1,PossibleColors.CYAN);
        matrix[3][1] = new Tile(3, 1,PossibleColors.PINK);
        matrix[4][1] = new Tile(4,1,PossibleColors.YELLOW);
        matrix[5][1] = new Tile(5,1,PossibleColors.PINK);

        matrix[0][2] = new Tile(0, 2, PossibleColors.PINK);
        matrix[1][2] = new Tile(1,2,PossibleColors.PINK);
        matrix[2][2] = new Tile(2,2,PossibleColors.WHITE);
        matrix[3][2] = new Tile(3, 2,PossibleColors.GREEN);
        matrix[4][2] = new Tile(4,2,PossibleColors.GREEN);
        matrix[5][2] = new Tile(5,2,PossibleColors.GREEN);

        matrix[0][3] = new Tile(0, 3, PossibleColors.PINK);
        matrix[1][3] = new Tile(1,3,PossibleColors.PINK);
        matrix[2][3] = new Tile(2,3,PossibleColors.CYAN);
        matrix[3][3] = new Tile(3, 3,PossibleColors.PINK);
        matrix[4][3] = new Tile(4,3,PossibleColors.GREEN);
        matrix[5][3] = new Tile(5,3,PossibleColors.PINK);

        matrix[0][4] = new Tile(0, 4, PossibleColors.PINK);
        matrix[1][4] = new Tile(1,4,PossibleColors.CYAN);
        matrix[2][4] = new Tile(2,4,PossibleColors.CYAN);
        matrix[3][4] = new Tile(3, 4,PossibleColors.GREEN);
        matrix[4][4] = new Tile(4,4,PossibleColors.WHITE);
        matrix[5][4] = new Tile(5,4,PossibleColors.GREEN);

        shelfie.setShelf(matrix);

        if(!card2.checkGoal(shelfie))
            fail("Test failed");
    }

    @Test
    public void checkGoalTest8(){ //testing card 10
        int column, row;
        Tile[][] matrix = new Tile[6][5];

        matrix[0][0] = new Tile(0, 0, PossibleColors.YELLOW);
        matrix[1][0] = new Tile(1,0,PossibleColors.PINK);
        matrix[2][0] = new Tile(2,0,PossibleColors.CYAN);
        matrix[3][0] = new Tile(3, 0,PossibleColors.BLUE);
        matrix[4][0] = new Tile(4,0,PossibleColors.PINK);
        matrix[5][0] = new Tile(5,0,PossibleColors.PINK);

        matrix[0][1] = new Tile(0, 1, PossibleColors.PINK);
        matrix[1][1] = new Tile(1,1,PossibleColors.PINK);
        matrix[2][1] = new Tile(2,1,PossibleColors.CYAN);
        matrix[3][1] = new Tile(3, 1,PossibleColors.PINK);
        matrix[4][1] = new Tile(4,1,PossibleColors.YELLOW);
        matrix[5][1] = new Tile(5,1,PossibleColors.PINK);

        matrix[0][2] = new Tile(0, 2, PossibleColors.PINK);
        matrix[1][2] = new Tile(1,2,PossibleColors.PINK);
        matrix[2][2] = new Tile(2,2,PossibleColors.WHITE);
        matrix[3][2] = new Tile(3, 2,PossibleColors.GREEN);
        matrix[4][2] = new Tile(4,2,PossibleColors.PINK);
        matrix[5][2] = new Tile(5,2,PossibleColors.GREEN);

        matrix[0][3] = new Tile(0, 3, PossibleColors.PINK);
        matrix[1][3] = new Tile(1,3,PossibleColors.PINK);
        matrix[2][3] = new Tile(2,3,PossibleColors.CYAN);
        matrix[3][3] = new Tile(3, 3,PossibleColors.PINK);
        matrix[4][3] = new Tile(4,3,PossibleColors.GREEN);
        matrix[5][3] = new Tile(5,3,PossibleColors.BLUE);

        matrix[0][4] = new Tile(0, 4, PossibleColors.PINK);
        matrix[1][4] = new Tile(1,4,PossibleColors.CYAN);
        matrix[2][4] = new Tile(2,4,PossibleColors.CYAN);
        matrix[3][4] = new Tile(3, 4,PossibleColors.GREEN);
        matrix[4][4] = new Tile(4,4,PossibleColors.WHITE);
        matrix[5][4] = new Tile(5,4,PossibleColors.BLUE);

        shelfie.setShelf(matrix);

        if(card2.checkGoal(shelfie))
            fail("Test failed");
    }
}
