import java.util.*;

public class Ticket{
    int ticketsCount;
    String ticketId;
    int showId;
    ArrayList<int[]> seatNo;
    Ticket(String ticketId,int showId,int ticketsCount){
        this.ticketId=ticketId;
        this.showId=showId;
        this.ticketsCount=ticketsCount;
        seatNo=new  ArrayList<int[]>();
    }
}
