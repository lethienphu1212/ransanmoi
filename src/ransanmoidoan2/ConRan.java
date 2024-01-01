/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ransanmoidoan2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class ConRan {
    
     private void playEatFoodSound() {
        try {
            File audioFile = new File("res/amthanhan.wav");  // Replace with the path to your sound file
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    int doDai = 3;
    int []x;
    int []y;
    
    public static int GoUP = 1;
    public static int GoDown = -1;
    public static int GoRight = 2;
    public static int GoLeft = -2;
    
    int vector = ConRan.GoDown;
    
    long t1 = 0;
    
    int speed = 200;
    
    int maxDai= 5;
    
      boolean udAfrerChangeVt = true;
    
    public ConRan(){
        x = new int [20];
        y = new int [20];
        
        x[0] = 5;
        y[0] = 4;
        x[1] = 5;
        y[1] = 3;
        x[2] = 5;
        y[2] = 2;
        
     }
    
    public void resetGame(){
        doDai=3;
        x = new int [100];
        y = new int [100];
         speed=200;
        x[0] = 5;
        y[0] = 4;
        x[1] = 5;
        y[1] = 3;
        x[2] = 5;
        y[2] = 2;
        
        vector = ConRan.GoUP;
        
    }
    //tranhs truong hop di chuyen len ma ko xuong dc
    public void setVector (int v){
        if(vector != -v && udAfrerChangeVt)
        vector = v;
        udAfrerChangeVt = false; 
   }
    //vị trí xuất hiện mồi Random
    public Point toaDoMoi () {
        Random r = new Random();
        int x = r.nextInt(19);
        int y = r.nextInt(19);
        return new Point (x,y);
        
    
    
    }
    //lay toc do cua ran qua level
    public int getSpeed(){
        
        for(int i = 0; i< GameScreen.Level; i++)
            speed*=0.8;
            return speed;
    
    
    }
    
    public void update (){
        
        if(doDai == maxDai){ 
            GameScreen.isPlaying=false;
            resetGame();
            GameScreen.Level++;
            maxDai +=5;
            speed= getSpeed();
            
        }
      
        
        
        //đầu rắn di chuyển vào thân => end game
        for(int i = 1;i< doDai; i++){
            if(x[0]== x[i] && y[0] == y[i]){
              
                 
           //nhap ten nguoi choi sau khi thua
           String name= JOptionPane.showInputDialog("Mời bạn nhập tên:");
           RanSanMoiDoAn2.users.add(new User(name, String.valueOf(GameScreen.Level)));
                
           GameScreen.isPlaying=false;
           GameScreen.isGameEnd=true;
            
            
           GameScreen.Level = 1;
            GameScreen.diem = 0;
            }
        }
         if(System.currentTimeMillis()-t1>speed){
             
             udAfrerChangeVt = true;
            
            if(GameScreen.bg[x[0]][y[0]]==2){
                doDai++;
                GameScreen.bg[x[0]][y[0]]=0;
                GameScreen.bg[toaDoMoi().x][toaDoMoi().y]=2;
                
                GameScreen.diem+=100;
                playEatFoodSound();//tieng an se phat ra
            }
           //Nối đầu rắn với thân
            for(int i=doDai-1;i>0;i--){
                x[i]=x[i-1];
                y[i]=y[i-1];
            }
            
            if(vector == ConRan.GoUP) y[0]--;
            if(vector == ConRan.GoDown) y[0]++;
            if(vector == ConRan.GoLeft) x[0]--;
            if(vector == ConRan.GoRight) x[0]++;

             
            //Rắn đi qua tường
            if(x[0]<0) x[0]=19;
            if(x[0]>19) x[0]=0;
            if(y[0]<0) y[0]=19;
            if(y[0]>19) y[0]=0;

            t1 = System.currentTimeMillis();
        }
            
           
        
        
    }
    //Vẽ rắn
     public void veRan (Graphics g) {
//         g.setColor(Color.red);
//         for(int i=0;i<doDai;i++)
            for(int i=0;i<doDai;i++)
            g.drawImage(Data.imageBody, x[i]*20, y[i]*20, null);
            g.drawImage(Data.imageHead, x[0]*20, y[0]*20, null);
//            g.fillRect(x[i]*20+1, y[i]*20+1, 18, 18); 
 
        
     }
     
     


    // Phương thức để khôi phục trạng thái trò chơi
    
    
}
