/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class BrowseImage {
    

    //read image from file
    public static void browseImage(JLabel productDisplayLabel, JDialog dialog) {
        byte[] imgB = null;
        JFileChooser choserObj = new JFileChooser();

        int fileChosed = choserObj.showOpenDialog(dialog);
        if (fileChosed == JFileChooser.APPROVE_OPTION) {
            File file = choserObj.getSelectedFile();
            String fileName = file.getName();
            
            String imagePath = file.getAbsolutePath();  //image path

            try {
                BufferedImage im = ImageIO.read(file);
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                ImageIO.write(im, "jpg", output);
                imgB = output.toByteArray();
                ImageIcon byteIcon = new ImageIcon(imgB);
                ImageIcon passportImage = new ImageIcon(byteIcon.getImage().getScaledInstance(productDisplayLabel.getWidth(), productDisplayLabel.getHeight(), Image.SCALE_SMOOTH));
                productDisplayLabel.setText("");
                productDisplayLabel.setIcon(passportImage);
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            }
            //catch (SQLException ex) {}
        }
    }
}
