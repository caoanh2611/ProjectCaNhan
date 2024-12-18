package QuanLyHottel;

import java.util.Scanner;
import java.util.Date;
import java.text.*;

public abstract class Room implements IRoom {

    private String id;
    private String name;
    double basecost;
    private Date checkinDate;
    private Date checkoutDate;

    public Room() {
    }

    public Room(String id, String name, double basecost, Date checkinDate, Date checkoutDate) {
        this.id = id;
        this.name = name;
        this.basecost = basecost;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBasecost() {
        return basecost;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBasecost(double basecost) {
        this.basecost = basecost;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @Override
    public abstract double calculateCost();

    @Override
    public abstract void displayDetails();

    public void enformation(Scanner s) {
       
        System.out.print("Enter Id Room : ");
        this.id = s.nextLine();
        System.out.print("Enter Name Room : ");
        this.name = s.nextLine();
        System.out.print("Enter baseCost : ");
        this.basecost = s.nextDouble();
        s.nextLine();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        while (true) {
            System.out.print("Enter day checkinDate :");
            String Start = s.nextLine();
            System.out.print("Enter day checkoutDate :");
            String end = s.nextLine();
            try {
                this.checkinDate = df.parse(Start);
                this.checkoutDate = df.parse(end);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date. Please enter again.");
            }
        }
    }

    public void display() {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Id Room : " + id);
        System.out.println("Room name : " + name);
        System.out.println("Day in  : " + df.format(checkinDate));
        System.out.println("Day out : " + df.format(checkoutDate));
    }
  
    public long dayStay(){
       if(checkinDate !=null && checkoutDate != null){
           long daystay = checkoutDate.getTime() - checkinDate.getTime();
           return daystay / (1000 * 60 * 60 *24 );
       }else{
           System.out.println("Checkin of check out not find !");
           return 0;
      }
    }
         public double calculateLateFee(Date dueDate, double lateFeePerDay) {
        long diffInMillis = this.getCheckoutDate().getTime() - dueDate.getTime();
        if (diffInMillis <= 0) {
            return 0; 
        }
        long diffInDays = diffInMillis / (1000 * 60 * 60 * 24);
        return diffInDays * lateFeePerDay;
    }       
}