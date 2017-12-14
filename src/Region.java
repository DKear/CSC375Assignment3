import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Region {
    Integer ID = -1;
    Region[] Neighbors = new Region[4];
    private double MainBaseMetalComp;
    private double BaseMetal2Comp;
    private double BaseMetal3Comp;
    //Random compRandom = new Random();
    double Temperature;
    int LocationX=0;
    int LocationY=0;
    int neighborCount;
    JPanel panel = new JPanel();
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
        randomLeft = randomLeft - (int)BaseMetal2Comp;
        if (randomLeft != 0) {
            BaseMetal3Comp = compRandom.nextInt(randomLeft);
            randomLeft = randomLeft - (int)BaseMetal3Comp;
        }
        MainBaseMetalComp = 75 + randomLeft;

    }

    public double getTemperature(){
        return Temperature;
    }

    public void setTemperature(double t){
        Temperature = t;
    }

    public double getBaseMetal3Comp(){
        return BaseMetal3Comp;
    }
    public double getBaseMetal2Comp(){
        return BaseMetal2Comp;
    }

    public double getMainBaseMetalComp(){
        return MainBaseMetalComp;
    }

    private Image createHeatImage(Color c){
        BufferedImage bufferedImage = new BufferedImage(10,10, BufferedImage.TYPE_INT_RGB);
        int rgb = c.getRGB();

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                bufferedImage.setRGB(i, j, rgb);
            }
        }
        return bufferedImage;
    }

    public void paint(Graphics g){

        double heatDouble = this.getTemperature();
        Color heatColor;

        if(heatDouble < 255){
            heatColor = new Color(255 - (int)heatDouble, 255, 255);
        }else if(heatDouble < 510){
            heatColor = new Color(0, 255 - ((int)heatDouble % 255), 255);
        }else if(heatDouble < 765){
            heatColor = new Color((int)heatDouble%510, 0, 255);
        }else if(heatDouble < 1020){
            heatColor = new Color(255, 0,255 - ((int)heatDouble%765));
        } else if(heatDouble < 1275){
            heatColor = new Color( 255, (int)heatDouble%1020, 0);
        } else{
            heatColor = new Color( 255,255, 0);
        }

        Image img = createHeatImage(heatColor);
        g.drawImage(img, this.LocationX *10, this.LocationY *10, panel);
    }
}
