package lab8;

import java.util.ArrayList;

public class Controler {
    String stationName;

    //Storing an unlimited ammount of neighbour controllers
    public ArrayList<Controler> neighbourController= new ArrayList<>();
    //storing station train track segments
    public   ArrayList<Segment> list = new ArrayList<>();

    public Controler(String gara) {
        stationName = gara;
    }

    void setNeighbourController(Controler v){
        neighbourController.add(v);
    }

    void addControlledSegment(Segment s){
        list.add(s);
    }

    /**
     * Check controlled segments and return the id of the first free segment or -1 in case there is no free segment in this station
     *
     */
    int getFreeSegmentId(){
        for(Segment s:list){
            if(!s.hasTrain())
                return s.id;
        }
        return -1;
    }

    void controlStep(){
        //check which train must be sent
        for(Controler controler:neighbourController) {
            for(Segment segment:list){
                if(segment.hasTrain()){
                    Train t = segment.getTrain();
                    if (t.getDestination().equals(controler.stationName)) {
                        //check if there is a free segment
                        int id = controler.getFreeSegmentId();
                        if (id == -1) {
                            System.out.println("Trenul +" + t.name + "din gara " + stationName + " nu poate fi trimis catre " + controler.stationName + ". Nici un segment disponibil!");
                            return;
                        }
                        //send train
                        System.out.println("Trenul " + t.name + " pleaca din gara " + stationName + " spre gara " + controler.stationName);
                        segment.departTRain();
                        controler.arriveTrain(t, id);
                    }

                }

            }
        }//.for

    }//.


    public void arriveTrain(Train t, int idSegment){
        for(Segment segment:list){
            //search id segment and add train on it
            if(segment.id == idSegment)
                if(segment.hasTrain()){
                    System.out.println("CRASH! Train "+t.name+" colided with "+segment.getTrain().name+" on segment "+segment.id+" in station "+stationName);
                    return;
                }else{
                    System.out.println("Train "+t.name+" arrived on segment "+segment.id+" in station "+stationName);
                    segment.arriveTrain(t);
                    return;
                }
        }

        //this should not happen
        System.out.println("Train "+t.name+" cannot be received "+stationName+". Check controller logic algorithm!");

    }


    public void displayStationState(){
        System.out.println("=== STATION "+stationName+" ===");
        for(Segment s:list){
            if(s.hasTrain())
                System.out.println("|----------ID="+s.id+"__Train="+s.getTrain().name+" to "+s.getTrain().destination+"__----------|");
            else
                System.out.println("|----------ID="+s.id+"__Train=______ catre ________----------|");
        }
    }
}
