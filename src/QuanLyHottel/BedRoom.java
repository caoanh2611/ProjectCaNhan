/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyHottel;
import java.util.Date;
import java.util.Scanner;
public class BedRoom extends Room{
 private int capcity ;

    public BedRoom() {
      
    }

    public BedRoom(int capcity, String id, String name, double basecost, Date checkinDate, Date checkoutDate  ) {
        super(id, name, basecost, checkinDate, checkoutDate);
        this.capcity = capcity;
    }
 
 @Override
     public void enformation(Scanner s) {
         super.enformation(s);
        System.out.print("Enter capcity : ");
        this.capcity =  s.nextInt();
     }
   
    @Override
    public double calculateCost() {
     if(capcity > 50){
         return this.getBasecost() *1.2 * this.dayStay();
     }
     return this.getBasecost() * this.dayStay();
     
     }
    

    @Override
    public void displayDetails() {
      super.display();
      System.out.print("Capcity : " +capcity);
       
    }
 @Override
    public  void update(){
        super.update();
          Scanner s = new Scanner(System.in);
         System.out.print("Enter capcity : ");
        this.capcity =  s.nextInt();
    }

    public int getCapcity() {
        return capcity;
    }

    public void setCapcity(int capcity) {
        this.capcity = capcity;
    }

    
    
}
