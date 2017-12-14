import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main extends JPanel {



    public static double metalConstant1;
    public static double metalConstant2;
    public static double metalConstant3;

    //args[] parameters: (Top left corner heat, Bottom right corner heat, Constant 1, Constant 2, Constant 3, Height, threshold)
    public static void main(String args[]){

        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new GridBagLayout());
        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setVisible(true);

        metalConstant1 = Double.parseDouble(args[2]) / 100;
        metalConstant2 = Double.parseDouble(args[3]) / 100;
        metalConstant3 = Double.parseDouble(args[4]) / 100;

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
        //alloy.regions[10][10].setTemperature(20000);
        alloy.regions[height - 1][width - 1].setTemperature(Double.parseDouble(args[1]));

        ForkJoinPool pool = new ForkJoinPool();
        TempUpdater tempUpdater = new TempUpdater(alloy.regions,0, height -1, panel);
        /*for(;;) {
            System.out.println("test");
            alloy.updateTemps(alloy.regions, panel);
        }*/

            pool.execute(tempUpdater);


            pool.shutdown();





    }
}
