/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class FormatDate {
    
    public static String format(Date date){
        
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String formattedDate = simpleDateFormat.format(date);
        
        return formattedDate;
    }
}
