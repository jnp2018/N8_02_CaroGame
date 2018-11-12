/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caro.common;

import caro.client.Main;
import caro.server.ClientHandler;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class Room implements Serializable{
    int id = 0;
    public ClientHandler client1 = null;
    public ClientHandler client2 = null;
    
    public ArrayList<ClientHandler> lstClientView = null;

    public CPiece[][] pieceses;
    
    String roomName;
    int soQuanThang = 4;
    boolean chan2Dau = true;
    int time;
    boolean kt1 = false;
    boolean kt2 = false;
    
    static final int NOT       = 0;
    static final int OK        = 1;
    /*static final int ILL_CHOUREN= 2;
    static final int ILL_33        = 3;
    static final int ILL_44        = 4;
    static final int ILL_NOT    = 5;*/

    int Dx[];
    int Dy[];

    static final int D_UP        = 0;
    static final int D_UPRIGHT    = 1;
    static final int D_RIGHT    = 2;
    static final int D_DOWNRIGHT= 3;
    static final int D_DOWN        = 4;
    static final int D_DOWNLEFT    = 5;
    static final int D_LEFT        = 6;
    static final int D_UPLEFT    = 7;
    
    static final int BOARDSIZE = 25;
    
    FileOutputStream fos = null;
    DataOutputStream dos = null;
    void NewGame() throws FileNotFoundException, IOException
    {
        pieceses = new CPiece[25][25];
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                pieceses[i][j] = new CPiece();
            }
        }
        fos = new FileOutputStream("data.txt");
        dos = new DataOutputStream(fos);
      
        
    }
    
    public Room(int _id, int soQuanThang,boolean chan2dau, int time) throws FileNotFoundException, IOException {
        id = _id;
        this.soQuanThang = soQuanThang;
        this.chan2Dau = chan2dau;
        this.time = time;
        
        lstClientView = new ArrayList<ClientHandler>();
        
        NewGame();
        
      
        
        Dx = new int[8];
        Dy = new int[8];

        Dx[0] =  0;  Dy[0] = -1;
        Dx[1] =  1;  Dy[1] = -1;
        Dx[2] =  1;  Dy[2] =  0;
        Dx[3] =  1;  Dy[3] =  1;
        Dx[4] =  0;  Dy[4] =  1;
        Dx[5] = -1;  Dy[5] =  1;
        Dx[6] = -1;  Dy[6] =  0;
        Dx[7] = -1;  Dy[7] = -1;
    }
    
    
    
    
    
    
    public int GetSequence(int color,int x,int y,int direction, int id) {
        if (id == 1){
            kt1 = false;
        }else{
            kt2 = false;
        }
        int num = 0;
        int dx = Dx[direction];
        int dy = Dy[direction];

        Boolean Space = false;

        while(true) {
            num++;
            x += dx; y += dy;
            if( x < 0 || x >= BOARDSIZE || y < 0 || y >= BOARDSIZE ) break;
            if(pieceses[x][y].State == CPiece.EMPTY) {
                Space = true;
                break;
            }
            if (pieceses[x][y].State != color){
                if (id == 1){
                    kt1 = true;
                }else{
                    kt2 = true;
                }
                break;
            }
        }
        return num;
    }
    
    
    public int Find5Block(int color,int x,int y) {

        int max,a;
        max = 0;
        
        max = 0;
        a = GetSequence(color,x,y,D_UP,1) + GetSequence(color,x,y,D_DOWN,2) - 1 ;
        if (chan2Dau == true){
            if (kt1 == false || kt2 == false){
                max = Math.max(max,a);
            }
        }else{
            max = Math.max(max,a);
        }
        a =GetSequence(color,x,y,D_LEFT,1) + GetSequence(color,x,y,D_RIGHT,2) - 1 ;
        if (chan2Dau == true){
            if (kt1 == false || kt2 == false){
                max = Math.max(max,a);
            }
        }else{
            max = Math.max(max,a);
        }
     
        a = GetSequence(color,x,y,D_UPLEFT,1) + GetSequence(color,x,y,D_DOWNRIGHT,2) -1 ;
        if (chan2Dau == true){
            if (kt1 == false || kt2 == false){
                max = Math.max(max,a);
            }
        }else{
            max = Math.max(max,a);
        }
        
        a = GetSequence(color,x,y,D_UPRIGHT,1) + GetSequence(color,x,y,D_DOWNLEFT,2) - 1 ;
        if (chan2Dau == true){
            if (kt1 == false || kt2 == false){
                max = Math.max(max,a);
            }
        }else{
            max = Math.max(max,a);
        }
        
        if( max >= soQuanThang)
            return OK;

        return NOT;
    }

    public void clientWinLose(ClientHandler client, Boolean isWin)
    {
       
    }
    
    public int put(ClientHandler client, GPos gPos) throws IOException
    {
       
        boolean kt = false;
        
        //danh
        if (client == client1)
        {
            pieceses[gPos.x][gPos.y].State = CPiece.BLACK;
            if (Find5Block(CPiece.BLACK, gPos.x, gPos.y)==OK)
            {
                System.out.printf("Black win");
                clientWinLose(client1, true);
                client1.SendMessage(35, "win");
                clientWinLose(client2, false);
                client2.SendMessage(35, "lose");
                this.updateDataMatch(client1, client2);
                kt = true;
            }
            else
            {
                client2.SendMessage(31, null);
                client1.SendMessage(36, null);
            }
        }
        else
        {
            pieceses[gPos.x][gPos.y].State = CPiece.WHITE;
            if (Find5Block(CPiece.WHITE, gPos.x, gPos.y)==OK)
            {
                System.out.printf("WHITE win");
                clientWinLose(client2, true);
                client2.SendMessage(35, "win");
                clientWinLose(client1, false);
                client1.SendMessage(35, "lose");
                this.updateDataMatch(client2, client1);
                kt = true;
            }
            else
            {
                client1.SendMessage(31, null);
                client2.SendMessage(36, null);
            }
        }
        
        client1.SendMessage(30, pieceses);
        client2.SendMessage(30, pieceses);
        
//        System.out.println(client.getUser().getUsername());
//        for (int i = 0; i < 25; i++) {
//            for (int j = 0; j < 25; j++) {
//                System.out.print((pieceses[i][j].State)+" ");
//            }
//            System.out.println("");
//        }
        
        //ghi file
        
        //dos.writeChars(client.getName());
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                dos.writeInt(pieceses[i][j].State);
            }
        }
        if (kt == true){
            dos.close();
            fos.close();
        }
        return 1;
    }
    
    public int countAvailable()
    {
        int n = 2;
        if (client1 != null)
            n--;
        if (client2 != null)
            n--;
        return n;
    }
    
    public Boolean add(ClientHandler client) throws IOException
    {
        if (client1==null)
        {
            
            client1 = client;
            
            
            return true;
        }
        if (client2==null)
        {
            client2 = client;
            NewGame();
            dos.writeUTF(client1.getUser().getUsername());
            dos.writeUTF(client2.getUser().getUsername());
            //client1.SendMessage(66, 20);
            //client2.SendMessage(66, 20);
            client1.SendMessage(31, null);
            client2.SendMessage(36, null);
            return true;
        }
        return false;
    }
    
    public void updateDataMatch(Users winner, Users loser){
          
    }
    public int getTime(){
        return time;
    }
    public void clientExit(ClientHandler clientHandler) throws IOException
    {
        if (countAvailable()==1)
        {
            if (client1!=null)
                client1.room = null;
            client1 = null;
            if (client2!=null)
                client2.room = null;
            client2 = null;
        }
        else if (countAvailable()==0)
        {
            if (client1==clientHandler)
            {
                clientWinLose(client2, true);
                client2.SendMessage(35, "win");
                this.updateDataMatch(client2, client1);
            }
            else
            {
                clientWinLose(client1, true);
                client1.SendMessage(35, "win");
                this.updateDataMatch(client1, client2);
            }
            if (client1!=null)
                client1.room = null;
            client1 = null;
            if (client2!=null)
                client2.room = null;
            client2 = null;
        }
        
        
    }
    void updateDataMatch(ClientHandler winner,ClientHandler loser){
        
    }
    @Override
    public String toString()
    {
        int n = 2;
        if (client1 != null)
            n--;
        if (client2 != null)
            n--;
        return "Room " + id + ": " + n + " available";
    }
}
