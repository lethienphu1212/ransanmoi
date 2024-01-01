/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ransanmoidoan2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.io.File;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author Administrator
 */
public class GameScreen extends JPanel implements Runnable{
    
     private void playBackgroundMusic() {
        try {
            // Open an audio input stream
            File audioFile = new File("res/nhacnen.wav");  // Replace with the path to your audio file
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioFile);

            // Get a clip resource
            Clip clip = AudioSystem.getClip();

            // Open audio clip and load samples from the audio input stream
            clip.open(audioIn);

            // Loop the background music
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    
    static int [][] bg = new int [20][20];
    
    static boolean  isPlaying = false;
    static boolean isGameEnd = false;
    
    static int Level = 1;
    


    
      private Color backgroundColor = Color.DARK_GRAY;  // Default background color

    public void setBackgroundColor(Color color) {
        backgroundColor = color;
    }
    
    ConRan ran;
    
    Thread thread;
    static int diem =0;
  
    public GameScreen (){
       
        
        Data.loadImage();
       
        ran = new ConRan();
        
        bg [13][17] = 2;
        playBackgroundMusic();  // Call the method to play background music
        thread = new Thread(this);
        thread.start();
        
    
    }
    
    public void run(){
        long t = 0;
        
        while(true){
            
           if(isPlaying){
                if(System.currentTimeMillis()-t>200){
                    
                    t=System.currentTimeMillis();
                }
                ran.update();
            }
            repaint();
           try {
                sleep(20);
            } catch (InterruptedException ex) {}
             
        }
        
    
    }
    //Vẽ môi trường cho Rắn
    public void paintBg(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0,0, 400+300, 400);
        for(int i=0;i<20;i++)
            for(int j=0;j<20;j++){
                if(bg[i][j] == 2){
                    //g.setColor(Color.blue);
                    //g.fillRect(i*20+1, j*20+1, 18, 18);
                    g.drawImage(Data.imageApple, i*20, j*20, null);
                }
                   
            }
    }
//    private void veKhung (Graphics g){
//           g.setColor(Color.orange);
//           g.drawRect(0, 0, 400+300, 400);
//           g.drawRect(1, 1, 400, 400);
//           g.drawRect(2, 2, 400, 400);
//           
    
//    }
    public void paint (Graphics g){
        paintBg(g);
        ran.veRan(g);
//        veKhung(g);
        
        Image image = null;
        try{
            image=ImageIO.read(new File("res/viengame.png"));
            g.drawImage(image, 0, 0, null);
        }catch(Exception e){}
            
        
        
        
        
        
        if(!isPlaying){
            g.setColor(Color.white);
            g.setFont(g.getFont().deriveFont(18.0f));
            g.drawString("ẤN NÚT 'SPACE' ĐỂ CHƠI GAME!", 60, 200);
        
        }
        if(isGameEnd){
            g.setColor(Color.white);
            g.setFont(g.getFont().deriveFont(20.0f));
            g.drawString("GAME OVER!", 100, 250);
         }
        

        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(20.0f));
        g.drawString("Đồ án nhóm 17", 450, 30);
        
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(20.0f));
        g.drawString("Cấp độ : "+Level , 450, 80);
        
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(18.0f));
        g.drawString("ĐIỂM : "+diem , 450, 110);
        
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(20.0f));
        g.drawString("BXH level đạt được", 450, 170);
        
        for(int i = 0; i< RanSanMoiDoAn2.users.size();i++){
            g.drawString(RanSanMoiDoAn2.users.get(i).toString(), 450, i*30+200);
        }
    
    }
    
}
