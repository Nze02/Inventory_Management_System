/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class SizeImage {
    
    //method to size up background image to label's size
    public static void sizeImageToLabelSize(JLabel containerLabel){
        int width = containerLabel.getWidth();
        int height = containerLabel.getHeight();
        
        ImageIcon originalIcon = (ImageIcon) containerLabel.getIcon();
        Image img = originalIcon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon original = new ImageIcon(scaledImg);
        containerLabel.setIcon(original);
    }
    
    //method to size up background image to label's size
    public static void sizeImageToLabelSize2(JLabel containerLabel){
        int width = containerLabel.getWidth() - 50;
        int height = containerLabel.getHeight();
        
        ImageIcon originalIcon = (ImageIcon) containerLabel.getIcon();
        Image img = originalIcon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon original = new ImageIcon(scaledImg);
        containerLabel.setIcon(original);
    }
}
