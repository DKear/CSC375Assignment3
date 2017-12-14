import javax.swing.*;
import java.util.concurrent.RecursiveAction;
import java.util.Arrays;


public class TempUpdater extends RecursiveAction {

    //Alloy alloy;
    Region[][] regions;
    private int startRow;
    private int endRow;

    public JPanel panel = new JPanel();

    public TempUpdater(Region[][] r, int s, int e, JPanel p){
        this.regions = r;
        this.startRow = s;
        this.endRow = e;
        this.panel = p;

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

        double oldTemp;
        double newTemp;
        double totalChange;

        //for(;;) {
        //while(totalChange > .01){
            //System.out.println("mark 1");

            r[0][0].paint(panel.getGraphics());
            r[r.length - 1][r[0].length -1].paint(panel.getGraphics());

            for (int i = startRow; i <= endRow; i++) {
                for (int j = 0; j < r[0].length; j++) {
                    if (r[i][j].ID != 0 && r[i][j].ID != r[0].length * r.length - 1) {
                        for (int k = 0; k < r[i][j].Neighbors.length; k++) {
                            if (r[i][j].Neighbors[k].ID != -1) {
                                neighborValues[k] = r[i][j].Neighbors[k].Temperature * (r[i][j].Neighbors[k].getMainBaseMetalComp() / 100);
                            }

                        }
                        temp1 = Main.metalConstant1 * ((neighborValues[0] + neighborValues[1] + neighborValues[2] + neighborValues[3]) / (r[i][j].neighborCount));

                        Arrays.fill(neighborValues, 0);

                        for (int k = 0; k < r[i][j].Neighbors.length; k++) {
                            if (r[i][j].Neighbors[k].ID != -1) {
                                neighborValues[k] = r[i][j].Neighbors[k].Temperature * (r[i][j].Neighbors[k].getBaseMetal2Comp() / 100);
                            }

                        }
                        temp2 = Main.metalConstant2 * ((neighborValues[0] + neighborValues[1] + neighborValues[2] + neighborValues[3]) / (r[i][j].neighborCount));

                        Arrays.fill(neighborValues, 0);

                        for (int k = 0; k < r[i][j].Neighbors.length; k++) {
                            if (r[i][j].Neighbors[k].ID != -1) {
                                neighborValues[k] = r[i][j].Neighbors[k].Temperature * (r[i][j].Neighbors[k].getBaseMetal3Comp() / 100);
                            }

                        }
                        temp3 = Main.metalConstant3 * ((neighborValues[0] + neighborValues[1] + neighborValues[2] + neighborValues[3]) / (r[i][j].neighborCount));

                        Arrays.fill(neighborValues, 0);
                        r[i][j].setTemperature(temp1 + temp2 + temp3);
                        r[i][j].paint(panel.getGraphics());
                        System.out.println("painting " + i + ", " + j);
                    }
                }

            }
            for (int i = endRow; i >= startRow; i--) {
                for (int j = r[0].length - 1; j >= 0; j--) {
                    if (r[i][j].ID != 0 && r[i][j].ID != r[0].length * r.length - 1) {
                        for (int k = 0; k < r[i][j].Neighbors.length; k++) {
                            if (r[i][j].Neighbors[k].ID != -1) {
                                neighborValues[k] = r[i][j].Neighbors[k].Temperature * (r[i][j].Neighbors[k].getMainBaseMetalComp() / 100);
                            }

                        }
                        temp1 = Main.metalConstant1 * ((neighborValues[0] + neighborValues[1] + neighborValues[2] + neighborValues[3]) / (r[i][j].neighborCount));

                        Arrays.fill(neighborValues, 0);

                        for (int k = 0; k < r[i][j].Neighbors.length; k++) {
                            if (r[i][j].Neighbors[k].ID != -1) {
                                neighborValues[k] = r[i][j].Neighbors[k].Temperature * (r[i][j].Neighbors[k].getBaseMetal2Comp() / 100);
                            }

                        }
                        temp2 = Main.metalConstant2 * ((neighborValues[0] + neighborValues[1] + neighborValues[2] + neighborValues[3]) / (r[i][j].neighborCount));

                        Arrays.fill(neighborValues, 0);

                        for (int k = 0; k < r[i][j].Neighbors.length; k++) {
                            if (r[i][j].Neighbors[k].ID != -1) {
                                neighborValues[k] = r[i][j].Neighbors[k].Temperature * (r[i][j].Neighbors[k].getBaseMetal3Comp() / 100);
                            }

                        }
                        temp3 = Main.metalConstant3 * ((neighborValues[0] + neighborValues[1] + neighborValues[2] + neighborValues[3]) / (r[i][j].neighborCount));

                        Arrays.fill(neighborValues, 0);
                        r[i][j].setTemperature(temp1 + temp2 + temp3);
                        r[i][j].paint(panel.getGraphics());

                    }
                }

            }
        //}  //here

    }

    public void compute(){

        //updateTemps(regions);

        if (regions[0].length * ((endRow + 1) - startRow) <= 100) {

            updateTemps(regions);

        } else {

            int mid = (startRow + endRow) / 2;

            TempUpdater split1 = new TempUpdater(regions, startRow, mid, panel);
            TempUpdater split2 = new TempUpdater(regions, mid + 1, endRow, panel);

            System.out.println("mark 2");
            invokeAll(split1, split2);


        }


    }
}
