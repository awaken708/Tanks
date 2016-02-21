package tanks;

import java.awt.*;
import java.awt.event.*;


public class Visual extends Frame {

    int[][] map_class;
    int Nx,Ny;

    Visual(int Nx, int Ny) {
        map_class = new int[Nx][Ny];
        this.Nx = Nx;
        this.Ny = Ny;
        setSize(Nx*10, Ny*10);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        });

    }

    public void set_map(int[][] map) {
        map_class = map;
    }
    public int[][] get_map(){
        return map_class;
    }
    
    Image offscreen;
    Graphics g2;

    public void paint(Graphics g) {

        offscreen = createImage(Nx*10, Ny*10);
        g2 = offscreen.getGraphics();
        g2.clearRect(0, 0, Nx*10, Ny*10);

        for (int i = 0; i < Nx; i++) {
            for (int j = 0; j < Ny; j++) {
                if (map_class[i][j] == 1) {
                    g2.setColor(Color.RED);
                    g2.fillRect(i*10 + 10, j*10 + 10, 10, 10);}
                if (map_class[i][j] == 2) {
                    g2.setColor(Color.GREEN);
                    g2.fillRect(i*10 + 10, j*10 + 10, 10, 10);}
                if (map_class[i][j] == 3) {
                    g2.setColor(Color.BLUE);
                    g2.fillRect(i*10 + 10, j*10 + 10, 10, 10);}
                
            }
        }
        //if(!offscreen.getGraphics().equals(g))
        g.drawImage(offscreen, 0, 0, null);

    }

    public void update(Graphics g) {
        paint(g);
    }

}
