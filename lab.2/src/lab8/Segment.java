package lab8;

public class Segment {
    int id;
    Train train;

    Segment(int id){
        this.id = id;
    }

    boolean hasTrain(){
        return train!=null;
    }

    Train departTRain(){
        Train tmp = train;
        this.train = null;
        return tmp;
    }

    void arriveTrain(Train t){
        train = t;
    }

    Train getTrain(){
        return train;
    }
}
