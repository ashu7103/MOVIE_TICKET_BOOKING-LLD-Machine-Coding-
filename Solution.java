// Fetching code ... please wait ..

import java.util.*;


public class Solution{
    
    Map<Integer,Cinema> listCinemas;
    Map<Integer,Show> listShows;
    Map<String,Ticket> listTicket;
    Map<Integer,Map<Integer,ArrayList<Integer>>> cinemaMap; // movieId->(CitId-> list of cinema)
    Map<Integer,Map<Integer,ArrayList<Integer>>> showMap; // movieId->(CitId-> list of shows)

    Solution(){
        
        listCinemas=new HashMap<Integer, Cinema>();
        listShows=new HashMap<Integer,Show>();
        listTicket=new HashMap<String,Ticket>();
        cinemaMap=new HashMap<Integer,Map<Integer,ArrayList<Integer>>>();
        showMap=new HashMap<Integer,Map<Integer,ArrayList<Integer>>>();
    }

    void addCinema(int cinemaId, int cityId,int screenCount, int screenRow, int screenColumn){
        Cinema t=new Cinema(cinemaId,cityId,screenCount,screenRow,screenColumn);
        listCinemas.put(cinemaId,t);
    }

    void addShow(int showId, int movieId, int cinemaId,int screenIndex, long startTime, long endTime) {
        Show t=new  Show(showId, movieId, cinemaId,screenIndex, startTime, endTime);
        listCinemas.get(cinemaId).screens.get(screenIndex-1).add(t);
        listShows.put(showId,t);

        if(!cinemaMap.containsKey(movieId)) cinemaMap.put(movieId,new HashMap<>());
        int cityId=listCinemas.get(cinemaId).cityId;
        if(!cinemaMap.get(movieId).containsKey(cityId)) cinemaMap.get(movieId).put(cityId, new ArrayList<>());
        cinemaMap.get(movieId).get(cityId).add(cinemaId);

        if(!showMap.containsKey(movieId)) showMap.put(movieId,new HashMap<>());
        if(!showMap.get(movieId).containsKey(cinemaId)) showMap.get(movieId).put(cinemaId, new ArrayList<>());
        showMap.get(movieId).get(cinemaId).add(showId);
    }

    List<String> bookTicket(String ticketId, int showId, int ticketsCount){
        if(getFreeSeatsCount(showId)<ticketsCount) return new ArrayList();
        Show s=listShows.get(showId);
        int screenIndex=s.screenIndex;
        int cinemaId=s.cinemaId;
        boolean [][] currSeats=listCinemas.get(cinemaId).seats[screenIndex-1];

        Ticket t=new Ticket(ticketId,showId,ticketsCount);

        for(int i=0;i< listCinemas.get(cinemaId).screenRow;i++){
            int c=0,st=0;
            for(int j=0;j<listCinemas.get(cinemaId).screenColumn;j++){
                if(!currSeats[i][j]) {
                    c++;
                    if(c==ticketsCount) {
                        List<String> r=new ArrayList();
                        for(int k=st;k<st+ticketsCount;k++){
                            r.add(""+i+"-"+k);
                            t.seatNo.add(new int[]{i,k});
                            listCinemas.get(cinemaId).seats[screenIndex-1][i][k]=true;
                        }
                        listTicket.put(ticketId,t);
                        return r;
                    }
                }
                else {
                    st=j+1;c=0;
                }
            }

        }
        List<String> r=new ArrayList();
        int c=0;
        for(int i=0;i< listCinemas.get(cinemaId).screenRow;i++){
            if(c==ticketsCount) break;
            for(int j=0;j<listCinemas.get(cinemaId).screenColumn;j++){
                if(!currSeats[i][j]){
                    currSeats[i][j]=true;
                    r.add(""+i+"-"+j);
                    t.seatNo.add(new int[]{i,j});
                    listCinemas.get(cinemaId).seats[screenIndex-1][i][j]=true;
                    c++;
                    if(c==ticketsCount){
                        listTicket.put(ticketId,t);
                        break;
                    }
                }
            }
        }
        return r;
    }

    boolean cancelTicket(String ticketId){
        if(!listTicket.containsKey(ticketId)) return false;
        Ticket t= listTicket.get(ticketId);
        listTicket.remove(ticketId);
        int currShowId=t.showId;
        int currCinemaId=listShows.get(currShowId).cinemaId;
        int currScreenNo=listShows.get(currShowId).screenIndex;
        for(int[]i: t.seatNo){
            listCinemas.get(currCinemaId).seats[currScreenNo-1][i[0]][i[1]]=false;
        }
        return true;
    }

    int getFreeSeatsCount(int showId){
        int c=0;
        if(listShows.containsKey(showId)){
            Show s=listShows.get(showId);
            int screenIndex=s.screenIndex;
            int cinemaId=s.cinemaId;
            boolean [][] currSeats=listCinemas.get(cinemaId).seats[screenIndex-1];
            for(int i=0;i< listCinemas.get(cinemaId).screenRow;i++){
                for(int j=0;j<listCinemas.get(cinemaId).screenColumn;j++){
                    if(!currSeats[i][j]) c++;
                }
            }
            return c;
        }
        return 0;
    }
    List<Integer> listCinemas(int movieId, int cityId){
        if(cinemaMap.containsKey(movieId)  && cinemaMap.get(movieId).containsKey(cityId)) {
            List<Integer> r= cinemaMap.get(movieId).get(cityId);
            Collections.sort(r);
            return r;
        }
        return new ArrayList<Integer>();
    }
    List<Integer> listShows(int movieId, int cinemaId){
        if(showMap.containsKey(movieId) && showMap.get(movieId).containsKey(cinemaId)){
            List<Integer> r=showMap.get(movieId).get(cinemaId);
            Collections.sort(r,(a,b)-> Long.compare(listShows.get(a).startTime, listShows.get(b).startTime));
            return r;
        } 
        List<Integer> r=new ArrayList<Integer>();
        return r;
    }
    public static void main(String[] args) {
        Solution obj = new Solution();
       
        // Add cinema
        obj.addCinema(0, 1, 4, 5, 10); 
        // Adds cinema with cinemaId=0, cityId=1, 4 screens, each with 5 rows and 10 columns

        // Add shows
        obj.addShow(1, 4, 0, 1, 1710516108725L, 1710523308725L); 
        // Adds showId=1 for movieId=4 in cinemaId=0 on screen 1
        obj.addShow(2, 11, 0, 3, 1710516108725L, 1710523308725L); 
        // Adds showId=2 for movieId=11 in cinemaId=0 on screen 3

        // List cinemas for a movie (movieId=0, cityId=1)
        System.out.println("Cinemas showing movie 0 in city 1: " + obj.listCinemas(0, 1));
        // Expected: [] (since movieId=0 is not added)

        // List shows for movieId=4 in cinemaId=0
        System.out.println("Shows for movie 4 in cinema 0: " + obj.listShows(4, 0));
        // Expected: [1]

        // List shows for movieId=11 in cinemaId=0
        System.out.println("Shows for movie 11 in cinema 0: " + obj.listShows(11, 0));
        // Expected: [2]

        // Get free seats in showId=1
        System.out.println("Free seats in show 1: " + obj.getFreeSeatsCount(1));
        // Expected: 50 (5 rows x 10 columns)

        // Book 4 tickets for showId=1
        System.out.println("Booking 4 tickets for show 1: " + obj.bookTicket("tkt-1", 1, 4));
        // Expected: [0-0, 0-1, 0-2, 0-3] (first 4 seats in row 0)
        
        // Get updated free seats
        System.out.println("Free seats in show 1 after booking: " + obj.getFreeSeatsCount(1));
        // Expected: 46

        // Cancel the ticket
        System.out.println("Cancel ticket tkt-1: " + obj.cancelTicket("tkt-1"));
        // Expected: true

        // Get free seats again after cancellation
        System.out.println("Free seats in show 1 after cancellation: " + obj.getFreeSeatsCount(1));
        // Expected: 50
    }
}