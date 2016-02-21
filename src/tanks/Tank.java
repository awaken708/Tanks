package tanks;

import java.util.ArrayList;
import java.util.Random;

public class Tank {

    int x, y;
    int dx,dy;
    ArrayList<Point> figure = new ArrayList();

    Tank(int x, int y) {
        this.x = x;
        this.y = y;
        Random rnd = new Random();
        int pos = rnd.nextInt(4);
        if (pos == 0) {
            rotate_up();
        }
        if (pos == 1) {
            rotate_down();
        }
        if (pos == 2) {
            rotate_left();
        }
        if (pos == 3) {
            rotate_right();
        }

    }

    public void rotate_up() {
        dx = 0;
        dy = -1;
        figure.removeAll(figure);
        figure.add(new Point(x, y));//up
        figure.add(new Point(x, y - 1));
        figure.add(new Point(x - 1, y));
        figure.add(new Point(x + 1, y));
        figure.add(new Point(x - 1, y + 1));
        figure.add(new Point(x + 1, y + 1));
    }

    public void rotate_down() {
        dx = 0;
        dy = 1;
        figure.removeAll(figure);
        figure.add(new Point(x, y));//down
        figure.add(new Point(x, y + 1));
        figure.add(new Point(x - 1, y));
        figure.add(new Point(x + 1, y));
        figure.add(new Point(x - 1, y - 1));
        figure.add(new Point(x + 1, y - 1));
    }

    public void rotate_left() {
        dx = -1;
        dy = 0;
        figure.removeAll(figure);
        figure.add(new Point(x, y));//left
        figure.add(new Point(x - 1, y));
        figure.add(new Point(x, y - 1));
        figure.add(new Point(x, y + 1));
        figure.add(new Point(x + 1, y + 1));
        figure.add(new Point(x + 1, y - 1));
    }

    public void rotate_right() {
        dy = 0;
        dx = 1;
        figure.removeAll(figure);
        figure.add(new Point(x, y));//right
        figure.add(new Point(x + 1, y));
        figure.add(new Point(x, y - 1));
        figure.add(new Point(x, y + 1));
        figure.add(new Point(x - 1, y + 1));
        figure.add(new Point(x - 1, y - 1));
    }

    public void up() {
        y--;
        if (y > 0) {
            for (int i = 0; i < figure.size(); i++) {
                figure.set(i, new Point(figure.get(i).x, figure.get(i).y - 1));
            }
        } else {
            y++;
        }
    }

    public void left() {
        x--;
        if (x > 0) {
            for (int i = 0; i < figure.size(); i++) {
                figure.set(i, new Point(figure.get(i).x - 1, figure.get(i).y));
            }
        } else {
            x++;
        }
    }

    public void right() {
        x++;
        if (x < (Tanks.Nx - 1)) {
            for (int i = 0; i < figure.size(); i++) {
                figure.set(i, new Point(figure.get(i).x + 1, figure.get(i).y));
            }
        } else {
            x--;
        }

    }

    public void down() {
        y++;
        if (y < (Tanks.Ny - 1)){
        for (int i = 0; i < figure.size(); i++) {
            figure.set(i, new Point(figure.get(i).x, figure.get(i).y + 1));
        }
        }else{
            y--;
        }
    }

}
