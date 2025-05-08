public class Show{
    int showId;
    int movieId;
    int cinemaId;
    int screenIndex;
    long startTime;
    long endTime;
    
    public Show(int showId, int movieId, int cinemaId,int screenIndex, long startTime, long endTime){
        this.showId=showId;
        this.movieId= movieId;
        this.cinemaId=cinemaId;
        this.screenIndex=screenIndex;
        this.startTime=startTime;
        this.endTime=endTime;
    }
}