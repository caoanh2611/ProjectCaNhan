package QuanLyHottel;

import QuanLyHottel.BedRoom;
import QuanLyHottel.MeetingRoom;
import QuanLyHottel.Room;
import java.util.*;

public class RoomList {

    private ArrayList<Room> lits;
    Scanner s = new Scanner(System.in);

    public RoomList() {
        this.lits = new ArrayList<>();
    }

    public RoomList(ArrayList<Room> lits) {
        this.lits = lits;
    }

    public void addRoom(Room room) {
        lits.add(room);
    }

    public void enformationroom() {
        System.out.println("List Room !");
        System.out.print("Enter number room :");
        int n = s.nextInt();
        s.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Room number " + (i + 1) + " !");
            System.out.print("Tpye Room (1.MeetingRoom / 2.BedRoom) : ");
            int type = s.nextInt();
            if (type == 1) {
                MeetingRoom mr = new MeetingRoom();
                mr.enformation(s);
                addRoom(mr);
            } else if (type == 2) {
                BedRoom br = new BedRoom();
                br.enformation(s);;
                addRoom(br);
            } else {
                System.out.println("Not found type Room !");
            }

        }
    }

    public void displayAllRooms() {
        System.out.println("List Room !");
        for (Room room : lits) {
            room.displayDetails();
        }
    }

    public Room findRoomById(String id) {
        for (Room room : lits) {
            if (room.getId().equals(id)) {
                return room;
            }
        }
        return null;
    }

    public boolean updateRoomById(String id) {
        Room found = findRoomById(id);
        if (found != null) {
            System.out.println("Update information Room : ");
            found.update();
            return true;
        }
        return false;
    }
    public boolean deleteRoomById(String id){
        Room dele = findRoomById(id);
        if(dele != null){
            lits.remove(dele);
            return true ;
        }
         return false;
    }
  public Room findMostExpensiveRoom(){
      Room MostExpensiveRoom = lits.get(0);
      for(Room room :lits){
          if(Double.compare(room.calculateCost() , MostExpensiveRoom.calculateCost()) >  0){
              MostExpensiveRoom= room;
          }
      }
        return MostExpensiveRoom;
  }
  public void countRooms(){
      int count = 0 ;
      int count1 = 0 ; 
      for(Room room : lits){
  
          if(room instanceof MeetingRoom){
              count++ ;
          }else if(room instanceof BedRoom){
              count1++;
          }
      }
      System.out.println("Total MeetingRoom : " +count);
          System.out.println("Total BedRoom : " +count1);
  }

    Iterable<Room> getList() {
       return  lits;
    }
}
