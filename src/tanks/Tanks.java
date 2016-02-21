/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Татьяненко Илья
 */
public class Tanks {

    static int Nx = 40;
    static int Ny = 40;
    public static void main(String[] args) throws InterruptedException {
    Tank_logic l = new Tank_logic();
    Visual sc = new Visual(Nx,Ny);
    sc.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 37) { l.my_tank.rotate_left();l.my_tank.left(); sc.repaint();}
                if(e.getKeyCode() == 38) { l.my_tank.rotate_up();l.my_tank.up(); sc.repaint();}
                if(e.getKeyCode() == 39) { l.my_tank.rotate_right();l.my_tank.right(); sc.repaint();}
                if(e.getKeyCode() == 40) { l.my_tank.rotate_down();l.my_tank.down(); sc.repaint();}
                if(e.getKeyCode() == 32) { l.myFire(); sc.repaint();}
                System.out.println(e.getKeyCode());
            }});
    
    
    while(true){
        l.fire();
        
        sc.set_map(l.get_mas());
        sc.repaint();
        
        l.step();
        Thread.sleep(100);
    }
    }
    
}
