/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Beans.SalesBreakdown;
import Beans.Staff;
import Model.DBConnection;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class PrinterHandle {
    private DBConnection dBConnection = null;
    private ArrayList<SalesBreakdown> salesBreakdown;
    private String customerName;
    private String soldBy;
    private String date;
//    private Staff staff;

    public PrinterHandle() {
        dBConnection = new DBConnection();
    }

    public PrinterHandle(ArrayList<SalesBreakdown> salesBreakdown, String customerName, String soldBy, String date) {
        this();
        this.salesBreakdown = salesBreakdown;
        this.customerName = customerName;
        this.soldBy = soldBy;
        this.date = date;
        
    }
    
    
    public PageFormat getPageFormat(PrinterJob pj){
        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();
        
        double middleHeight = 8.0;
        double headerHeight = 2.0;
        double footerHeight = 2.0;
        double width = convert_CM_To_PPT(12);    //printer knows only point per inch. default value
        double height = convert_CM_To_PPT(headerHeight + middleHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(
                0,
                10,
                width,
                height - convert_CM_To_PPT(1)
        );  //define border size after that print area width is about 100 points
        
        pf.setOrientation(PageFormat.PORTRAIT); //select orientation portrait or landscape
        pf.setPaper(paper);
        
        return  pf;
    }
    
    protected static double  convert_CM_To_PPT(double cm){
        return toPPT(cm * 0.490700787);
    }
    
    protected static double toPPT(double inch){
        return inch * 72d;
    }
    
    
    public class BillPrintable implements Printable{

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            double totalPurchase = 0;
            
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0){
                
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
                
                
                try{
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 15;
                    int headerRectHeights = 40;
                    
                ////////////////// Product Names Get ////////////////////    
                    String item1N = "Prod 1"; //item1.getText();
                    String item2N = "Prod 2"; //item2.getText();
                    String item3N = "Prod 3"; //item3.getText();
                    String item4N = "Prod 4"; //item4.getText();
                ////////////////// Product Names Get //////////////////// 
                
                    
                ////////////////// Product Price Get ////////////////////
                    int item1P = 12000; //Integer.valueOf(item1Price.getText());
                    int item2P = 20000; //Integer.valueOf(item2Price.getText());
                    int item3P = 10000; //Integer.valueOf(item3Price.getText());
                    int item4P = 20000; //Integer.valueOf(item4Price.getText());
                    int sum = item1P + item2P + item3P + item4P;
                ////////////////// Product Price Get ////////////////////
                
//                g2d.setFont(new Font("Monospaced", Font.BOLD, 9));
                g2d.drawString("              SEKARO INTEGRATED SERVICES                   ", 12, y);y += yShift;
                g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                g2d.drawString("    House Appliances, Provision & General Supplies         ", 12, y);y += yShift;
                g2d.drawString("     -Import   -Distributor   -WholeSale & Retail          ", 12, y);y += yShift;
                g2d.drawString("-----------------------------------------------------------", 12, y);y += yShift;
                g2d.drawString("  Head Office: 62 Aba Road, Opposite Govt Craft Dev.       ", 12, y);y += yShift;
                g2d.drawString(" Center, by Gada Junction, Port Harcourt Rivers State      ", 12, y);y += yShift;y += yShift;
                g2d.drawString("Branch Office: Road 43, House B5 Federal House Authority,  ", 12, y);y += yShift;
                g2d.drawString("                          Abuja.                           ", 12, y);y += yShift;
                g2d.drawString("             Tel: 08032727999, 08137879809                 ", 12, y);y += yShift;
                g2d.drawString("-----------------------------------------------------------", 12, y);y += headerRectHeights;
                
                g2d.drawString("-----------------------------------------------------------", 10, y);y += yShift;
                g2d.drawString(" QTY |    DESCRIPTION OF GOODS     |  RATE   |    AMOUNT   ", 10, y);y += yShift;
                g2d.drawString("-----------------------------------------------------------", 10, y);y += headerRectHeight;
                
                for(int index = 0; index < salesBreakdown.size(); index++){
                    int qty = salesBreakdown.get(index).getQuantity();
                    String name = salesBreakdown.get(index).getProductName();
                    double price = salesBreakdown.get(index).getSalesPrice();
                    double totalPrice = salesBreakdown.get(index).getQuantityXprice();
            
                    g2d.drawString(" "+qty+"      "+name+"                        "+price+"        "+totalPrice, 10, y);y += yShift;
            
                    totalPurchase += totalPrice;
                }
                
//                g2d.drawString(" "+1+"      "+item1N+"                        "+1600+"        "+1600, 10, y);y += yShift;
//                g2d.drawString(" "+2+"      "+item2N+"                        "+7800+"        "+1600, 10, y);y += yShift;
//                g2d.drawString(" "+3+"      "+item3N+"                        "+5400+"        "+1600, 10, y);y += yShift;
//                g2d.drawString(" "+4+"      "+item4N+"                        "+11600+"       "+1600, 10, y);y += yShift;
                g2d.drawString("", 10, y);y += headerRectHeights;
                g2d.drawString("------------------------------------------------------------", 10, y);y += yShift;
                g2d.drawString(" Total amount: "+sum+"                                         ", 10, y);y += yShift;
                g2d.drawString("-----------------------------------------------------------", 10, y);y += yShift;y += yShift;
                g2d.drawString("Customer Name: " + customerName, 10, y);y += yShift;
                g2d.drawString("Sold By: " + soldBy, 10, y);y += yShift;
                g2d.drawString("Date: " + date, 10, y);y += headerRectHeight;
                g2d.drawString("-----------------------------------------------------------", 10, y);y += yShift;
                g2d.drawString("                THANKS FOR VISITING US                     ", 10, y);y += yShift;
                
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                
                result = PAGE_EXISTS;
            }
            return  result;
        }
        
    }
}
