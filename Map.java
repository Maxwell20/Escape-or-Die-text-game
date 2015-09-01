/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EscapeOrDie;

import java.util.ArrayList;

/**
 * A special map for testing locked doors and message events
 * @author Alan
 */
public class Map {
    private Room currentRoom;
    private static int currentRoomNumber;
    private static char[][] roomMatrix;
    private static int[][] lockMatrix;     //This will keep track of which doors are locked.
    private static ArrayList<Room> rooms;
    public int instanceNumber;
    private static Map map = null;
    static int counter = 1;
    
    public Map()
    {
        rooms = new ArrayList<>();
        DataParser.loadMap();
        /*roomMatrix = new char[][]{
            {0,'E','S',0},
            {'W',0,0,'S'},
            {'N',0,0,0},
            {0,'N',0,0}
        };*/
        /*lockMatrix = new int[][] {
            {0,0,0,0},
            {0,0,0,1},
            {0,0,0,0},
            {0,1,0,0}      
        };*/
        currentRoom = rooms.get(0);
        currentRoomNumber = 0;
    }
    /**
     * This will be used so that all classes and functions can operate
     * on the one and only map object
     * @return A reference to the map object
     */
    public static Map getMap()
    {
        if(map == null)
        {
            map = new Map();
        }
        return map; 
    }
    // sets the size of the map 
    public static void initMatrices(int size)
    {
        roomMatrix = new char[size][size];
        lockMatrix = new int[size][size];
    }
    //sets the room movements for the map taking in @param x @param y @param direction
    public static void setRoomMatrix(int x, int y, char direction)
    {
        roomMatrix[x][y] = direction;
    }
    //sets the matrix for locked doors
    public static void setLcokMatrix(int x, int y, int locked)
    {
        lockMatrix[x][y] = locked;
    }
    //returns the room matrix
    public static char[][] getRoomMatrix()
    {
        return roomMatrix;
    }
    //returns the room value
    public static ArrayList<Room> getRooms()
    {
        return rooms;
    }
    //used for unlocking rooms @param roomOne @param roomTwo
    public static void unlockRoom(int roomOne, int roomTwo)
    {
        lockMatrix[roomOne][roomTwo] = 0; 
        lockMatrix[roomTwo][roomOne] = 0;
    }
    //gets the currnet room
    public static Room getCurrentRoom()
    {
        //TODO: return copy of room instead of room itself
        return rooms.get(currentRoomNumber);
    }
    //gets the room
    public static Room getRoom(int roomNum)
    {
        return rooms.get(roomNum);
    }
    //sets the current room for the player
    public void setCurrentRoom(int roomNum)
    {
        currentRoom = rooms.get(roomNum);
        currentRoomNumber = roomNum;
    }
    //checks for exits in the current room 
    public int checkExit(char direction)
    {
        for(int i = 0; i < rooms.size(); i++)
        {
            if(direction == roomMatrix[currentRoomNumber][i])
            {
                return i;
            }
        }
        return -1;
    }
    //checks lockmatrix for current room to see if door is locked
    public boolean isLocked(int roomNum)
    {
        return lockMatrix[currentRoomNumber][roomNum] == 1;
    }
    /**
     * This overrides the clone function to prevent anybody from cloning
     * the map. You've got to cover all your bases when using Singleton pattern.
     * @return
     * @throws CloneNotSupportedException 
     */
   
    @Override
    public Object clone() throws CloneNotSupportedException{
        throw new CloneNotSupportedException();
    }
}
    
