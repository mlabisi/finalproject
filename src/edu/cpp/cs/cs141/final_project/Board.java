/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Final Project: Spy Game
 *
 * Create a small, yet interesting, text-based game involving a spy
 * and ninjas.
 *
 * Team Magic!~<3
 * Diana Choi, William Hang, Logan Carichner, Robert Delfin, Mora Labisi
 */
package edu.cpp.cs.cs141.final_project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class represents the board that the game takes place on. It is
 * composed of the {@link Square} class and its extended classes,
 * {@link Room} and {@link Hallway}.
 *
 * @author Mora Labisi
 */
public class Board {
    /**
     * This is a 2D array that represents {@code this} game {@link Board}.
     * It can be filled with the {@link #fillBoard()} method.
     */
    private Square[][] board;

    private Random rand;

    private boolean debugMode = false;

    private Player player;

    private Square bulletSquare, invulSquare, radarSquare, caseRoom;

    private Briefcase briefcase;

    private ArrayList<Hallway> hallways;

    private ArrayList<Hallway> validHallways;

    /**
     * This {@code final int} holds the size of the {@link #board}.
     */
    final public int SIZE = 9;

    /**
     * This is the constructor for the board.
     */
    public Board(){
        rand = new Random();
        hallways = new ArrayList<>();
        validHallways = new ArrayList<>();
        briefcase = new Briefcase();
        board = new Square[SIZE][SIZE];

        fillBoard();
        setCaseRoom();
        placeBriefcase();
        placeItems();
        placeEnemies();

        toggleDebugMode();
    }



    /**
     * This method will fill the {@link #board} with the appropriate
     * {@link Square}s.
     */
    private void fillBoard(){
        for (int i = 0; i < SIZE; ++i){
            for (int j = 0; j < SIZE; ++j){
                if((i % 3 == 1) && (j % 3 == 1))
                    board[i][j] = new Room();
                else {
                    board[i][j] = new Hallway();
                    hallways.add((Hallway)board[i][j]);
                    if((i < 4) && (j > 3))
                        validHallways.add((Hallway)board[i][j]);
                }
            }
        }

        ((Hallway)board[0][1]).setIsEntrance(true);
        ((Hallway)board[3][1]).setIsEntrance(true);
        ((Hallway)board[6][1]).setIsEntrance(true);

        board[8][0].setHasPlayer(true);
    }

    private void placeEnemies(){
        Collections.shuffle(validHallways);

        int i = 0;
        for(Hallway hall : validHallways){
            if(hall.checkIsClear()) {
                hall.place(new Enemy());
                ++i;
            }
            if(i > 5) break;
        }
    }

    private void setCaseRoom(){
        switch(rand.nextInt(9)){
            case 0: caseRoom = board[1][1];
                    break;
            case 1: caseRoom = board[1][4];
                    break;
            case 2: caseRoom = board[1][7];
                    break;
            case 3: caseRoom = board[4][1];
                    break;
            case 4: caseRoom = board[4][4];
                    break;
            case 5: caseRoom = board[4][7];
                    break;
            case 6: caseRoom = board[7][1];
                    break;
            case 7: caseRoom = board[7][4];
                    break;
            case 8: caseRoom = board[7][7];
        }

    }

    private void placeBriefcase(){
        caseRoom.place(briefcase);
    }

    private void placeItems(){
        Collections.shuffle(hallways);

        bulletSquare = hallways.get(0);
        invulSquare = hallways.get(1);
        radarSquare = hallways.get(2);

        bulletSquare.place(new ExtraBullet());
        invulSquare.place(new Invulnerability());
        radarSquare.place(new Radar());
    }


    /**
     * @return The game {@link #board}
     */
    public String toString() {
        String str = "";
        int i = 0;
        for (Square[] row : board) {
            for (Square column : row) {
                if(debugMode) {
                    if (i == 9) {
                        str += "\n";
                        i = 0;
                    }
                    if(column.checkHasUser())
                        str += AgentType.PLAYER;
                    else
                        str += column.reveal();
                    ++i;
                } else {
                    if (i == 9) {
                        str += "\n";
                        i = 0;
                    }
                    if(column.checkHasUser())
                        str += AgentType.PLAYER;
                    else
                        str += column;
                    ++i;
                }
            }
        }
        return str;
    }

    /**
     * Toggles debug mode
     */
    public void toggleDebugMode(){
        debugMode = !debugMode;
    }

    /**
     * @return The {@code boolean} value of {@link #debugMode}
     */
    public boolean checkDebugMode(){
        return debugMode;
    }

    public void movePlayer(int row, int column, int newRow, int newColumn){
        board[row][column].setHasPlayer(false);
        board[newRow][newColumn].setHasPlayer(true);
    }

}