package tanks;

import java.util.ArrayList;
import java.util.Random;

public class Tank_logic {

    ArrayList<Tank> tanks = new ArrayList();
    ArrayList<Snar> snars = new ArrayList();
    Tank my_tank = new Tank( 20,20);
    private Object rnd;

    Tank_logic() {
        while (true) {
            boolean log = true;
            tanks.removeAll(tanks);
            Random rnd = new Random();
            for (int i = 0; i < 10; i++) {
                tanks.add(new Tank(Math.abs(rnd.nextInt(Tanks.Nx - 2) + 1), Math.abs(rnd.nextInt(Tanks.Ny - 2) + 1)));
            }
            for (int i = 0; i < 10; i++) {
                for (int j = i + 1; j < 10; j++) {
                    if ((Math.abs(tanks.get(i).x - tanks.get(j).x) < 3) & (Math.abs(tanks.get(i).y - tanks.get(j).y) < 3)) {
                        log = false;
                    }
                }
            }
            if (log) {
                break;
            }
        }
    }

    public void fire() {
        for (int i = 0; i < tanks.size(); i++) {
            boolean log = false;
            int[][] mas = get_mas();
            if(tanks.get(i).dx == 1){
                for(int j = tanks.get(i).x + 2; j < Tanks.Nx; j++)
                    if(mas[j][tanks.get(i).y] == 1)
                        log = true;
            }
            if(tanks.get(i).dx == -1){
                for(int j = tanks.get(i).x - 2; j > 0; j--)
                    if(mas[j][tanks.get(i).y] == 1)
                        log = true;
            }
            if(tanks.get(i).dy == 1){
                for(int j = tanks.get(i).y + 2; j < Tanks.Ny; j++)
                    if(mas[tanks.get(i).x][j] == 1)
                        log = true;
            }
            if(tanks.get(i).dy-2 == -1){
                for(int j = tanks.get(i).y - 2; j > 0; j--)
                    if(mas[tanks.get(i).x][j] == 1)
                        log = true;
            }
            if (log) {
                snars.add(new Snar(tanks.get(i).x + 2 * tanks.get(i).dx, tanks.get(i).y + 2 * tanks.get(i).dy, tanks.get(i).dx, tanks.get(i).dy));
            }
        }
    }

    public void step() {
        for (int i = 0; i < snars.size(); i++) {
            snars.set(i, new Snar(snars.get(i).x + snars.get(i).dx, snars.get(i).y + snars.get(i).dy, snars.get(i).dx, snars.get(i).dy));
        }
        
        for (int i = 0; i < snars.size(); i++) {
            if ((snars.get(i).x >= Tanks.Nx) | (snars.get(i).y >= Tanks.Ny) | (snars.get(i).x < 0) | (snars.get(i).y < 0)) {
                
                snars.remove(i);
                
                i--;
            }
        }
        for (int i = 0; i < tanks.size(); i++) {
            boolean log = false;
            for (int j = 0; j < tanks.get(i).figure.size(); j++) {
                for (int k = 0; k < snars.size(); k++) {
                    if ((snars.get(k).x == tanks.get(i).figure.get(j).x) & (snars.get(k).y == tanks.get(i).figure.get(j).y)) {
                        log = true;
                        snars.remove(k);
                        k--;
                    }
                }
            }
            if (log) {
                tanks.remove(i);
                i--;
            }
        }
        while (tanks.size() < 5) {
            Random rnd1 = new Random();
            Tank temp_tank = new Tank(Math.abs(rnd1.nextInt(Tanks.Nx - 2) + 1), Math.abs(rnd1.nextInt(Tanks.Ny - 2) + 1));
            boolean log = true;
            for (int i = 0; i < tanks.size(); i++) {
                for (int j = 0; j < tanks.get(i).figure.size(); j++) {
                    for (int k = 0; k < temp_tank.figure.size(); k++) {
                        if ((temp_tank.figure.get(k).x == tanks.get(i).figure.get(j).x) & (temp_tank.figure.get(k).y == tanks.get(i).figure.get(j).y)) {
                            log = false;
                        }
                    }
                }
            }
            if (log) {
                tanks.add(temp_tank);
            }
        }
        for (int i = 0; i < tanks.size(); i++) {
            Random rnd1 = new Random();
            int[][] temp_map = get_mas();
            if (rnd1.nextInt(10) < 8) {
                if (tanks.get(i).dx == 1) {
                    if(tanks.get(i).x < (Tanks.Nx - 2))
                        if((temp_map[tanks.get(i).x+2][tanks.get(i).y] < 1)&(temp_map[tanks.get(i).x+2][tanks.get(i).y-1] < 1)&((temp_map[tanks.get(i).x+2][tanks.get(i).y+1] < 1)))
                             tanks.get(i).right();
                }
                if (tanks.get(i).dx == -1) {
                    if(tanks.get(i).x > 1)
                        if((temp_map[tanks.get(i).x - 2][tanks.get(i).y] < 1)&(temp_map[tanks.get(i).x - 2][tanks.get(i).y - 1] < 1)&(temp_map[tanks.get(i).x - 2][tanks.get(i).y + 1] < 1))
                            tanks.get(i).left();
                }
                if (tanks.get(i).dy == 1) {
                    if(tanks.get(i).y < (Tanks.Ny - 2))
                        if((temp_map[tanks.get(i).x][tanks.get(i).y + 2] < 1)&(temp_map[tanks.get(i).x - 1][tanks.get(i).y + 2] < 1)&(temp_map[tanks.get(i).x+1][tanks.get(i).y + 2] < 1))
                           tanks.get(i).down();
                }
                if (tanks.get(i).dy == -1) {
                    if(tanks.get(i).y > 1)
                        if((temp_map[tanks.get(i).x][tanks.get(i).y - 2] < 1)&(temp_map[tanks.get(i).x - 1][tanks.get(i).y - 2] < 1)&(temp_map[tanks.get(i).x+1][tanks.get(i).y - 2] < 1))
                            tanks.get(i).up();
                }
            } else {
                if (tanks.get(i).dx != 0) {
                    if (rnd1.nextInt(10) < 5) {
                        tanks.get(i).rotate_up();
                    } else {
                        tanks.get(i).rotate_down();
                    }
                } else if (rnd1.nextInt(10) < 5) {
                    tanks.get(i).rotate_left();
                } else {
                    tanks.get(i).rotate_right();
                }
            }
            if ((tanks.get(i).dx == 1) & (tanks.get(i).x >= 38)) {
                tanks.get(i).rotate_left();
            }
            if ((tanks.get(i).dx == -1) & (tanks.get(i).x <= 1)) {
                tanks.get(i).rotate_left();
            }
            if ((tanks.get(i).dy == 1) & (tanks.get(i).y >= 38)) {
                tanks.get(i).rotate_up();
            }
            if ((tanks.get(i).dy == -1) & (tanks.get(i).y <= 1)) {
                tanks.get(i).rotate_down();
            }

        }
    }

    public void myFire(){
        snars.add(new Snar(my_tank.x + 2 * my_tank.dx, my_tank.y + 2 * my_tank.dy, my_tank.dx, my_tank.dy));
    }
    
    public int[][] get_mas() {
        int[][] mas = new int[Tanks.Nx][Tanks.Ny];
        for (int i = 0; i < tanks.size(); i++) {
            for (int j = 0; j < tanks.get(i).figure.size(); j++) {
                mas[tanks.get(i).figure.get(j).x][tanks.get(i).figure.get(j).y] = 1;
            }
        }
        for (int i = 0; i < snars.size(); i++) {
            //System.out.println(snars.get(i).x+" "+snars.get(i).y+" "+snars.size()+" "+i);
            if((snars.get(i).x != -1)&(snars.get(i).x != 40)&(snars.get(i).y != -1)&(snars.get(i).y != 40))
            mas[snars.get(i).x][snars.get(i).y] = 2;
        }
        for(int i = 0 ; i < my_tank.figure.size(); i++)
            mas[my_tank.figure.get(i).x][my_tank.figure.get(i).y] = 3;
        return mas;
    }

}
