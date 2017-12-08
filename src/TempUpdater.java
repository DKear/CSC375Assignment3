import java.util.concurrent.RecursiveAction;
import java.util.Arrays;


public class TempUpdater extends RecursiveAction {

    Alloy alloy;
    Region[][] regions;
    private Region start;
    private Region end;
    private int startRow;
    private int endRow;

    public TempUpdater(Region[][] r, int s, int e){
        this.regions = r;
        this.startRow = s;
        this.endRow = e;

    }

    public int splitTop(Region [][] r){
        if(r.length % 2 == 1){
            return (endRow/2) + 1;
        }else{
            return endRow/2;
        }
    }

    public int splitBot(){

            return endRow/2;

    }

    /*public Region[] splitTop(Region[][] r){
        Region[] split = new Region[2];

        split[0] = start;
        split[1] = r[end.LocationY/2][end.LocationX];

        return split;

    }*/

    /*public Region[] splitEnd(Region[][] r){

        Region[] split = new Region[2];

        split[0] = r[end.LocationY/2][end.LocationX];
        split[1] = end;

        return split;

    }*/

    public void updateTemps(Region[][] r) {
        double temp1;
        double temp2;
        double temp3;
        double[] neighborValues = {0,0,0,0};

        int neighborCount = 0;

        for (int i = startRow; i <= endRow; i++) {
            for(int j = 0; j < regions[0].length; j++) {
                for(int k = 0; k < regions[i][j].Neighbors.length; k++){
                    if(regions[i][j].Neighbors[k].ID != -1) {
                        neighborValues[k] = regions[i][j].Neighbors[k].Temperature * (regions[i][j].Neighbors[k].getMainBaseMetalComp()/100);
                    }

                }
                temp1 = Main.metalConstant1 * ((neighborValues[0] + neighborValues[1] + neighborValues[2] + neighborValues[3])/(regions[i][j].neighborCount));

                Arrays.fill(neighborValues, 0);

                for(int k = 0; k < regions[i][j].Neighbors.length; k++){
                    if(regions[i][j].Neighbors[k].ID != -1) {
                        neighborValues[k] = regions[i][j].Neighbors[k].Temperature * (regions[i][j].Neighbors[k].getBaseMetal2Comp()/100);
                    }

                }
                temp2 = Main.metalConstant2 * ((neighborValues[0] + neighborValues[1] + neighborValues[2] + neighborValues[3])/(regions[i][j].neighborCount));

                Arrays.fill(neighborValues, 0);

                for(int k = 0; k < regions[i][j].Neighbors.length; k++){
                    if(regions[i][j].Neighbors[k].ID != -1) {
                        neighborValues[k] = regions[i][j].Neighbors[k].Temperature * (regions[i][j].Neighbors[k].getBaseMetal3Comp()/100);
                    }

                }
                temp3 = Main.metalConstant3 * ((neighborValues[0] + neighborValues[1] + neighborValues[2] + neighborValues[3])/(regions[i][j].neighborCount));

                Arrays.fill(neighborValues, 0);
                regions[i][j].setTemperature(temp1 + temp2 + temp3);
            }

        }

    }

    public void compute(){

        if(regions[0].length * regions.length < 2000){
            updateTemps(regions);
        }else{
            invokeAll(new TempUpdater(regions, startRow, splitTop(regions)), new TempUpdater(regions, splitBot(), endRow));

        }

    }
}
