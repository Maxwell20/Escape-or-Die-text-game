/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EscapeOrDie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ajw0026
 * gets the first line of the file then
 * gets the second line which is the room's image path
 * adds the room to the list of rooms then
 * create and add the events to the room
 * 
 */

public class DataParser {
    static Scanner fileScanner;     //Breaks up the file by lines
    static Scanner lineScanner;     //Breaks up each line in the file
    static String fileBuffer;
    static String lineBuffer;
    static String roomImagePath;
    static String roomDescription;
    static String itemName;
    static String itemDescription;
    static String itemImagePath;
    static int index;
    static Map map;
    static ArrayList<Room> rooms;
    
    public DataParser()
    {
        index = 0;
        try {
            fileScanner = new Scanner(new BufferedReader(new FileReader("rooms.txt")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void loadMap()
    {
        rooms = Map.getRooms();
        while(fileScanner.hasNext())
        {
            //get the first line
            fileBuffer = fileScanner.nextLine();
            if(fileBuffer.equals("#"))
            {
                char currentChar;
                System.out.println("Reached room matrix");
                Map.initMatrices(rooms.size());
                for(int i = 0; i < index; i++)
                {
                    System.out.println("Scanning room: "+i);
                    fileBuffer = fileScanner.nextLine();
                    lineScanner = new Scanner(fileBuffer);
                    for(int j = 0; j < index; j++)
                    {
                        currentChar = lineScanner.next().charAt(0);
                        if(currentChar == '0')
                        {
                            currentChar = 0;
                        }
                        Map.setRoomMatrix(i, j, currentChar);
                    }
                }
                fileBuffer = fileScanner.nextLine();
                for(int i = 0; i < index; i++)
                {
                    System.out.println("Scanning lock: "+i);
                    fileBuffer = fileScanner.nextLine();
                    lineScanner = new Scanner(fileBuffer);
                    for(int j = 0; j < index; j++)
                    {
                        currentChar = lineScanner.next().charAt(0);
                        if(currentChar == '0')
                        {
                            currentChar = 0;
                        }
                        else
                        {
                            currentChar = 1;    
                        }
                        Map.setLcokMatrix(i, j, currentChar);
                    }
                }
                continue;
            }
            
            roomDescription = fileBuffer;
            //get the second line which is the room's image path
            fileBuffer = fileScanner.nextLine();
            roomImagePath = fileBuffer;
            //add the room to the list of rooms
            rooms.add(new Room(roomDescription, roomImagePath));
            
            fileBuffer = fileScanner.nextLine();
            //while we are still reading events, create and add the events to the room
            while(!(fileBuffer.equals("")))
            {
                lineScanner = new Scanner(fileBuffer);
                lineBuffer = lineScanner.next();
                int enabledNum;
                boolean enabled;
                String fileName;
                String msg;
                String itemName;
                String itemDescription;
                int itemRoomNum;
                int itemEventNum;
                switch (lineBuffer) {
                    case "Unlock":
                        int roomOne = lineScanner.nextInt();
                        int roomTwo = lineScanner.nextInt();
                        enabledNum = lineScanner.nextInt();
                        enabled = false;
                        if(enabledNum == 1)
                        {
                            enabled = true;
                        }
                        fileBuffer = fileScanner.nextLine();
                        rooms.get(index).addEvent(new UnlockEvent(fileBuffer, enabled, roomOne, roomTwo));
                        break;
                    case "PopUp":
                        fileName = lineScanner.next();
                        enabledNum = lineScanner.nextInt();
                        enabled = false;
                        if(enabledNum == 1)
                        {
                            enabled = true;
                        }
                        fileBuffer = fileScanner.nextLine();
                        rooms.get(index).addEvent(new PopUpEvent(fileBuffer, enabled, fileName));
                        break;
                    case "Message":
                        enabledNum = lineScanner.nextInt();
                        msg = fileScanner.nextLine();
                        fileBuffer = fileScanner.nextLine();
                        enabled = false;
                        if(enabledNum == 1)
                        {
                            enabled = true;
                        }
                        rooms.get(index).addEvent(new MessageEvent(fileBuffer, msg, enabled));
                        break;
                    case "Ending":
                        fileName = lineScanner.next();
                        enabledNum = lineScanner.nextInt();
                        msg = fileScanner.nextLine();
                        fileBuffer = fileScanner.nextLine();
                        enabled = false;
                        if(enabledNum == 1)
                        {
                            enabled = true;
                        }
                        rooms.get(index).addEvent(new EndingEvent(fileBuffer, fileName, msg, enabled));
                        break;
                    case "Item":
                        fileName = lineScanner.next();
                        itemName = lineScanner.next();
                        enabledNum = lineScanner.nextInt();
                        enabled = false;
                        if(enabledNum == 1)
                        {
                            enabled = true;
                        }
                        fileBuffer = fileScanner.nextLine();
                        lineScanner = new Scanner(fileBuffer);
                        itemRoomNum = lineScanner.nextInt();
                        itemEventNum = lineScanner.nextInt();
                        itemDescription = fileScanner.nextLine();
                        fileBuffer = fileScanner.nextLine();
                        rooms.get(index).addEvent(new ItemEvent(fileBuffer, fileName, itemName, itemRoomNum, itemEventNum, itemDescription, enabled));     
                }
                fileBuffer = fileScanner.nextLine();
            }

            index++;
            System.out.println("Room index incremented: "+index);
        }
        
    }
}
