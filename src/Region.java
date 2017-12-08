import java.util.Random;

public class Region {
    Integer ID = -1;
    Region[] Neighbors = new Region[4];
    private int MainBaseMetalComp;
    private int BaseMetal2Comp;
    private int BaseMetal3Comp;
    //Random compRandom = new Random();
    double Temperature;
    int LocationX=0;
    int LocationY=0;
    int neighborCount;
    String neighborString;

    public void setLocation(int y, int x){
        LocationX = x;
        LocationY = y;
    }

    public void setID(int id){
        ID = id;
    }

    public void setNeighbors(Region[][] r){
        Neighbors[0] = new Region();
        Neighbors[1] = new Region();
        Neighbors[2] = new Region();
        Neighbors[3] = new Region();

        if(LocationY - 1 >= 0){
            Neighbors[0] = r[LocationY - 1][LocationX];
        }
        if(LocationX + 1 < r[LocationY].length){
            Neighbors[1] = r[LocationY][LocationX + 1];
        }
        if(LocationY + 1 < r.length){
            Neighbors[2] = r[LocationY + 1 ][LocationX];
        }
        if(LocationX - 1 >= 0){
            Neighbors[3] = r[LocationY][LocationX - 1];
        }
    }

    public void setNeighborCount(){
        int count = 0;
        for (int i = 0; i < Neighbors.length; i++){
            if (Neighbors[i].ID != -1 ){
                count++;
            }
        }
        neighborCount = count;
    }



    public void setCompositions(){
        int randomLeft = 25;
        Random compRandom = new Random();
        BaseMetal2Comp = compRandom.nextInt(26);
        randomLeft = randomLeft - BaseMetal2Comp;
        if (randomLeft != 0) {
            BaseMetal3Comp = compRandom.nextInt(randomLeft);
            randomLeft = randomLeft - BaseMetal3Comp;
        }
        MainBaseMetalComp = 75 + randomLeft;

    }

    public double getTemperature(){
        return Temperature;
    }

    public void setTemperature(double t){
        Temperature = t;
    }

    public int getBaseMetal3Comp(){
        return BaseMetal3Comp;
    }
    public int getBaseMetal2Comp(){
        return BaseMetal2Comp;
    }

    public int getMainBaseMetalComp(){
        return MainBaseMetalComp;
    }
}
