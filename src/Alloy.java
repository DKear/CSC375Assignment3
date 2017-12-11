import java.util.Arrays;

public class Alloy {
    private int height;
    private int width;
    Region[][] regions;
    int iteration;

    int startRow = 0;
    int endRow = 99;


    public void updateTemps(Region[][] r) {
        double temp1;
        double temp2;
        double temp3;
        double[] neighborValues = {0,0,0,0};

        for (int i = startRow; i <= endRow; i++) {
            for(int j = 0; j < r[0].length; j++) {
                if(r[i][j].ID != 0 && r[i][j].ID != r[0].length*r.length - 1) {
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
                }
            }

        }
        for (int i = endRow; i >= startRow; i--) {
            for(int j = r[0].length - 1; j >= 0 ; j--) {
                if(r[i][j].ID != 0 && r[i][j].ID != r[0].length*r.length - 1) {
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
                }
            }

        }

    }
}
