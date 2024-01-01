/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ransanmoidoan2;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Phat
 */
public class Data {
    
     public static BufferedImage sprite;
    
    public static Image imageHead;
    public static Image imageBody;
    public static Image imageApple;

    public static void loadImage(){
    try{
        imageHead = ImageIO.read(new File("res/snake_head1.png"));
        imageBody = ImageIO.read(new File("res/snake_body.png"));
        imageApple = ImageIO.read(new File("res/apple.png"));

    }catch(Exception e){}
    
}
}
