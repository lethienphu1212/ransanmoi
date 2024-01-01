/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ransanmoidoan2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Administrator
 */
public class RanSanMoiDoAn2  extends JFrame{
    private GameScreen game;

    public static ArrayList<User> users;
    
    public RanSanMoiDoAn2 (){
       
        

        // Thêm panel chứa các nút vào giao diện
    
      
        setFocusable(true);
        JButton deleteButton = new JButton("Xóa User");
           deleteButton.setBackground(Color.RED); 
           deleteButton.setForeground(Color.WHITE); 
           deleteButton.setOpaque(true);
           deleteButton.setBorderPainted(false); 

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!users.isEmpty()) {
                    users.remove(0); 
                }
            }
        });
        
        //tao chuc nang thay doi mau backround
        JButton changeColorButton = new JButton("backround");
        changeColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                Color newColor = JColorChooser.showDialog(null, "Thay đổi màu backround", game.getBackground());
                if (newColor != null) {
                    game.setBackgroundColor(newColor);
                }
            }
        });
        //tao nut chuc nang
       JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 50, 10));
        buttonPanel.add(changeColorButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel); // Thêm nút xóa vào giao diện

        // Hiển thị giao diện
        setSize(1150, 440);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        // lọc bxh 
          JButton filterButton = new JButton("Lọc BXh");
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortUsersByLevel();
            }
        });

       
      
        buttonPanel.add(filterButton);
        add(buttonPanel); // Thêm cả nút xóa người dùng và nút lọc bảng xếp hạng vào giao diện

       
        
        users = new ArrayList<User>();
        ReadData();
        
        game = new GameScreen();
        add(game);
        
        this.addKeyListener(new dichuyen ());
        
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
            UpdateData();
            }
       
    });
        
        setVisible(true);
          
        
    }
 

// Phương thức sắp xếp người dùng theo level từ cao tới thấp
    public void sortUsersByLevel() {
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return Integer.compare(Integer.parseInt(u2.getLevel()), Integer.parseInt(u1.getLevel()));
            }
        });
    }
    
    public static void main(String[] args) {
        RanSanMoiDoAn2 f = new RanSanMoiDoAn2();
        
    }
    private class dichuyen implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            
            if(e.getKeyCode()== KeyEvent.VK_SPACE){
                    GameScreen.isPlaying=!GameScreen.isPlaying;
                    if(GameScreen.isGameEnd) {
                        GameScreen.isGameEnd=false;
                        game.ran.resetGame();
                        
                    }
                 
            }
            
            if(e.getKeyCode()== KeyEvent.VK_UP){
                    game.ran.setVector(ConRan.GoUP);
            }
            if(e.getKeyCode()== KeyEvent.VK_DOWN){
                    game.ran.setVector(ConRan.GoDown);
            }
            if(e.getKeyCode()== KeyEvent.VK_LEFT){
                    game.ran.setVector(ConRan.GoLeft);
            }
            if(e.getKeyCode()== KeyEvent.VK_RIGHT){
                    game.ran.setVector(ConRan.GoRight);
            }
        
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    
    
    }
    
    //Ghi dữ liệu bxh
    public static void UpdateData(){
     
      
        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter("data/data.txt");
            bw= new BufferedWriter(fw);

            for(User u: users){
                bw.write(u.getName()+" "+u.getLevel());
                bw.newLine();
            }
            
        } 
        catch (IOException ex) {}
        finally{
            try {
                bw.close();
            } catch (IOException ex) {}
        }
        
   

    // Ghi dữ liệu bxh
  
   
    }
    public static void sortUsersByScore() {
        
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                 return Integer.compare(Integer.parseInt(u2.getLevel()), Integer.parseInt(u1.getLevel()));
            }
        });
    }
    //BXH
    public static void ReadData(){
        
        try {
            FileReader fr = new FileReader("data/data.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String line = null;
            while((line = br.readLine())!=null){
                String[] str = line.split(" ");
                users.add(new User(str[0],str[1]));
            }
            
            br.close();
        } catch (IOException ex) {}
        
    }
}
