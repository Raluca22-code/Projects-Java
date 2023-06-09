package lab8;


public class Simulator {

    public static void main(String[] args) {

        //build station Cluj-Napoca
        Controler c1 = new Controler("Cluj-Napoca");

        Segment s1 = new Segment(1);
        Segment s2 = new Segment(2);
        Segment s3 = new Segment(3);

        c1.addControlledSegment(s1);
        c1.addControlledSegment(s2);
        c1.addControlledSegment(s3);

        //build station Bucuresti
        Controler c2 = new Controler("Bucuresti");

        Segment s4 = new Segment(4);
        Segment s5 = new Segment(5);
        Segment s6 = new Segment(6);

        c2.addControlledSegment(s4);
        c2.addControlledSegment(s5);
        c2.addControlledSegment(s6);

        Controler c3 = new Controler("Timisoara");

        Segment s7 = new Segment(7);
        Segment s8 = new Segment(8);
        Segment s9 = new Segment(9);
        c3.addControlledSegment(s7);
        c3.addControlledSegment(s8);
        c3.addControlledSegment(s9);

        //connect the 2 controllers

        c1.setNeighbourController(c2);
        c1.setNeighbourController(c3);
        c2.setNeighbourController(c1);
        c2.setNeighbourController(c3);
        c3.setNeighbourController(c1);
        c3.setNeighbourController(c2);

        //testing

        Train t1 = new Train("Bucuresti", "IC-001");
        s1.arriveTrain(t1);
        Train t10=new Train("Timisoara","XX-555");
        s2.arriveTrain(t10);
        Train t2 = new Train("Cluj-Napoca","R-002");
        s5.arriveTrain(t2);
        Train t9=new Train("Timisoara","DQ-123");
        s6.arriveTrain(t9);
        Train t3=new Train("Bucuresti","RA-107");
        s8.arriveTrain(t3);
        Train t4=new Train("Cluj-Napoca","EX-999");
        s7.arriveTrain(t4);

        c1.displayStationState();
        c2.displayStationState();
        c3.displayStationState();
        System.out.println("\nStart train control\n");

        //execute 3 times controller steps
        for(int i = 0;i<3;i++){
            System.out.println("### Step "+i+" ###");
            c1.controlStep();
            c2.controlStep();
            c3.controlStep();

            System.out.println();

            c1.displayStationState();
            c2.displayStationState();
            c3.displayStationState();
        }




    }
}
