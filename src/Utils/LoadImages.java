/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template imageFile, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class LoadImages {

    private static final String UPLOAD_FOLDER = "C:\\Users\\Emmanuel Nzekwe\\Pictures\\da1\\";
    private static String imagePath = "";
    private static File newFile = null;
    private static ImageIcon productImage = null;

    public static void fetchImage(JLabel imageDisplayLabel, JDialog dialog) {

        JFileChooser chooser = new JFileChooser();
        int fileChosed = chooser.showOpenDialog(dialog);

        if (fileChosed == JFileChooser.APPROVE_OPTION) {
            
            File imageFile = chooser.getSelectedFile();
            imagePath = imageFile.getAbsolutePath();  //image path

            ImageIcon imageIcon = new ImageIcon(imagePath);
            productImage = new ImageIcon(imageIcon.getImage().getScaledInstance(imageDisplayLabel.getWidth(), imageDisplayLabel.getHeight(), Image.SCALE_SMOOTH));
            imageDisplayLabel.setIcon(productImage);

        }
        
    }

    
    public static String uploadImage() {

        String fileName = String.valueOf(System.currentTimeMillis());
        String imageExtension = imagePath.substring(imagePath.lastIndexOf(".") + 1);
        String newFilePath = "";

        File file = new File(imagePath);
        BufferedImage image = null;
        
        try {
            
            newFilePath = UPLOAD_FOLDER + fileName + "." +imageExtension;
            
            image = ImageIO.read(file);
            ImageIO.write(image, imageExtension, new File(newFilePath));
            
            
        } catch (IOException e) {
            newFilePath = "";
            e.printStackTrace();
            System.err.println("An error occurred while uploading the file:" + e.getMessage());
        }
        
        return newFilePath;
    }
    
    
    public static void downloadImage(JLabel imageDisplayLabel, JDialog dialog, String imagePath){
        File file = new File(imagePath);
        BufferedImage image = null;
        
        try {
            image = ImageIO.read(file);
            ImageIcon imageIcon = new ImageIcon(image);
            productImage = new ImageIcon(imageIcon.getImage().getScaledInstance(imageDisplayLabel.getWidth(), imageDisplayLabel.getHeight(), Image.SCALE_SMOOTH));
            imageDisplayLabel.setIcon(productImage);
            
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("An error occurred while downloading the file:" + e.getMessage());
        }
        
    }
    
}
