import java.util.*;

public  class Cinema{
    int cinemaId;
    int cityId;
    int screenCount;
    List<ArrayList<Show>> screens;
    int screenRow;
    int screenColumn;
    boolean seats[][][];

    Cinema(int cinemaId,int cityId,int screenCount,int screenRow,int screenColumn){
        this.cinemaId=cinemaId;
        this.cityId=cityId;
        this.screenCount=screenCount;
        this.screenRow=screenRow;
        this.screenColumn=screenColumn;
        screens=new ArrayList<>();
        for(int i=0;i<screenCount;i++) screens.add(new ArrayList<Show>());
        seats=new boolean[screenCount][screenRow][screenColumn];
    }
}