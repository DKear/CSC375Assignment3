import java.util.concurrent.ForkJoinPool;

public class Main {

    public static double metalConstant1;
    public static double metalConstant2;
    public static double metalConstant3;

    //args[] parameters: (Top left corner heat, Bottom right corner heat, Constant 1, Constant 2, Constant 3, Height, threshold)
    public static void main(String args[]){

        metalConstant1 = Double.parseDouble(args[2]);
        metalConstant2 = Double.parseDouble(args[3]);
        metalConstant3 = Double.parseDouble(args[4]);

        final int height = Integer.parseInt(args[5]);
        final int width = height * 2;
        int regionCount = 0;

        Alloy alloy = new Alloy();
        alloy.regions = new Region[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                alloy.regions[i][j] = new Region();
                alloy.regions[i][j].setID(regionCount);
                alloy.regions[i][j].setLocation(i, j);
                regionCount++;
            }
        }

        for (int i = 0; i < alloy.regions.length; i++) {
            for (int j = 0; j < alloy.regions[0].length; j++) {
                alloy.regions[i][j].setNeighbors(alloy.regions);
                alloy.regions[i][j].setNeighborCount();
                alloy.regions[i][j].setCompositions();
            }
        }

        alloy.regions[0][0].setTemperature(Double.parseDouble(args[0]));
        alloy.regions[height - 1][width - 1].setTemperature(Double.parseDouble(args[1]));

        ForkJoinPool pool = new ForkJoinPool();
        TempUpdater tempUpdater = new TempUpdater(alloy.regions,0, height -1);

        pool.execute(tempUpdater);

        pool.shutdown();




    }
}
