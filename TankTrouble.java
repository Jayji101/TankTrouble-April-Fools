
//required import statements
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
//import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;

import javax.swing.ImageIcon;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankTrouble extends JLabel {

    private static final int WIDTH = 1350;
    private static final int HEIGHT = 900;

    // required global variables
    private BufferedImage image;
    private ArrayList<Tile> tiles;
    private int maxTime = 900;
    private int delayTime = 20;
    private ArrayList<Wall> borderWalls;
    private ArrayList<Wall> insideVertWalls;
    private ArrayList<Wall> insideHorizWalls;
    private double randv;
    private double randh;
    private int updx, updy;
    private int gupdx, gupdy;
    private Graphics g;
    private int redscore = 0;
    private int greenscore = 0;
    private RedTank red;
    private GreenTank green;
    private Timer timer;
    private int loophelper;
    private boolean up, left, right, down;
    private boolean gup, gleft, gright, gown;
    private Bullet rb1, rb2, rb3, rb4, rb5;
    private Bullet gb1, gb2, gb3, gb4, gb5;
    private int count = 1;
    private int gount = 1;
    private boolean[] booleanhHelper;
    private boolean[] booleanvHelper;
    private boolean drawn1 = false, drawn2 = false, drawn3 = false, drawn4 = false, drawn5 = false;
    private boolean grawn1 = false, grawn2 = false, grawn3 = false, grawn4 = false, grawn5 = false;
    private int time1 = 0, time2 = 0, time3 = 0, time4 = 0, time5 = 0;
    private int gime1 = 0, gime2 = 0, gime3 = 0, gime4 = 0, gime5 = 0;

    // Constructor required by BufferedImage
    public TankTrouble() {
        // set up Buffered Image and Graphics objects
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();
        ImageIcon image = new ImageIcon("BlankMaze.png");
        g.drawImage(image.getImage(), 0, 0, WIDTH, HEIGHT, null);
        red = new RedTank(130, 200);
        green = new GreenTank(850, 370);
        randh = Math.random();
        randv = Math.random();

        booleanhHelper = new boolean[136];
        booleanvHelper = new boolean[141];

        /*
         * if (red.getDir() <= 13 && red.getDir() >= 5) {
         * updx = red.getX() + red.getHeight();
         * updy = red.getYcent();
         * } else if (red.getDir() <= 31 && red.getDir() >= 23) {
         * updx = red.getX();
         * updy = red.getYcent();
         * } else if (red.getDir() <= 4 && red.getDir() >= 32) {
         * updx = red.getXcent();
         * updy = red.getY();
         * } else if (red.getDir() <= 22 && red.getDir() >= 14) {
         * updx = red.getXcent();
         * updy = red.getY() + red.getHeight();
         * }
         */
        updx = red.getXcent();
        updy = red.getYcent();
        gupdx = green.getXcent();
        gupdy = green.getYcent();

        rb1 = new Bullet(updx, updy);
        rb1.setSpeed(4, 4);
        rb2 = new Bullet(updx, updy);
        rb2.setSpeed(4, 4);
        rb3 = new Bullet(updx, updy);
        rb3.setSpeed(4, 4);
        rb4 = new Bullet(updx, updy);
        rb4.setSpeed(4, 4);
        rb5 = new Bullet(updx, updy);
        rb5.setSpeed(4, 4);

        gb1 = new Bullet(gupdx, gupdy);
        gb1.setSpeed(4, 4);
        gb2 = new Bullet(gupdx, gupdy);
        gb2.setSpeed(4, 4);
        gb3 = new Bullet(gupdx, gupdy);
        gb3.setSpeed(4, 4);
        gb4 = new Bullet(gupdx, gupdy);
        gb4.setSpeed(4, 4);
        gb5 = new Bullet(gupdx, gupdy);
        gb5.setSpeed(4, 4);

        red.addTanks();
        green.addTanks();

        tiles = new ArrayList<>();
        borderWalls = new ArrayList<>();
        insideVertWalls = new ArrayList<>();
        insideHorizWalls = new ArrayList<>();

        for (int i = 0; i < 150; i++) {
            if (i % 2 == 0) {
                tiles.add(new Tile(Color.GRAY));
            } else {
                tiles.add(new Tile(Color.LIGHT_GRAY));
            }
        }

        for (int i = 0; i < 50; i++) {
            borderWalls.add(new Wall());
        }

        for (int i = 0; i < 141; i++) {
            insideVertWalls.add(new Wall());
        }

        for (int i = 0; i < 136; i++) {
            insideHorizWalls.add(new Wall());
        }

        for (int i = 0; i < booleanhHelper.length; i++) {
            randh = Math.random();
            booleanhHelper[i] = (randh <= 0.4);
        }

        for (int i = 0; i < booleanvHelper.length; i++) {
            randv = Math.random();
            booleanvHelper[i] = (randv <= 0.4);
        }

        // set up and start the Timer
        timer = new Timer(1, new TimerListener());
        timer.start();
        addKeyListener(new Key());

        setFocusable(true);
    }

    private class Key implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_A) {
                left = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                gleft = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                right = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                gright = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                down = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                gown = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                up = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                gup = true;
            }

            if (left) {
                red.turnLeft(g);
            }
            if (gleft) {
                green.turnLeft(g);
            }
            if (right) {
                red.turnRight(g);
            }
            if (gright) {
                green.turnRight(g);
            }
            if (down) {
                red.setVel(9);
                red.moveBackward(g);
            }
            if (gown) {
                green.setVel(9);
                green.moveBackward(g);
            }
            if (up) {
                red.setVel(9);
                red.moveForward(g);
            }
            if (gup) {
                green.setVel(9);
                green.moveForward(g);
            }

            if (e.getKeyCode() == KeyEvent.VK_Q) {
                if (count == 1) {
                    if (time1 != maxTime) {
                        drawn1 = true;
                    }
                }
                if (count == 2) {
                    if (time2 != maxTime) {
                        drawn2 = true;
                    }
                }
                if (count == 3) {
                    if (time3 != maxTime) {
                        drawn3 = true;
                    }
                }
                if (count == 4) {
                    if (time4 != maxTime) {
                        drawn4 = true;
                    }
                }
                if (count == 5) {
                    if (time5 != maxTime) {
                        drawn5 = true;
                    }
                }

                count++;
                if (count > 5) {
                    count = 1;
                }

                if (time1 == maxTime) {
                    time1 = 0;
                    drawn1 = false;
                }
                if (time2 == maxTime) {
                    time2 = 0;
                    drawn2 = false;
                }
                if (time3 == maxTime) {
                    time3 = 0;
                    drawn3 = false;
                }
                if (time4 == maxTime) {
                    time4 = 0;
                    drawn4 = false;
                }
                if (time5 == maxTime) {
                    time5 = 0;
                    drawn5 = false;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_M) {
                if (gount == 1) {
                    if (gime1 != maxTime) {
                        grawn1 = true;
                    }
                }
                if (gount == 2) {
                    if (gime2 != maxTime) {
                        grawn2 = true;
                    }
                }
                if (gount == 3) {
                    if (gime3 != maxTime) {
                        grawn3 = true;
                    }
                }
                if (gount == 4) {
                    if (gime4 != maxTime) {
                        grawn4 = true;
                    }
                }
                if (gount == 5) {
                    if (gime5 != maxTime) {
                        grawn5 = true;
                    }
                }

                gount++;
                if (gount > 5) {
                    gount = 1;
                }

                if (gime1 == maxTime) {
                    gime1 = 0;
                    grawn1 = false;
                }
                if (gime2 == maxTime) {
                    gime2 = 0;
                    grawn2 = false;
                }
                if (gime3 == maxTime) {
                    gime3 = 0;
                    grawn3 = false;
                }
                if (gime4 == maxTime) {
                    gime4 = 0;
                    grawn4 = false;
                }
                if (gime5 == maxTime) {
                    gime5 = 0;
                    grawn5 = false;
                }
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                left = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                gleft = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                right = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                gright = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                down = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                gown = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                up = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                gup = false;
            }
        }

    }

    // TimerListener class that is called repeatedly by the timer
    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            red.setCenter();
            green.setCenter();

            updx = red.getXcent();
            updy = red.getYcent();
            gupdx = green.getXcent();
            gupdy = green.getYcent();

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, WIDTH, HEIGHT);

            for (int loop = 0; loop < tiles.size(); loop++) {
                loophelper = loop / 15;
                tiles.get(loop).draw(g, (loop % 15) * 90, loophelper * 90);
            }

            for (int wall = 0; wall < borderWalls.size(); wall++) {
                if (wall < 15) {
                    borderWalls.get(wall).drawHoriz(g, wall * 90, 0);
                } else if (wall < 25) {
                    borderWalls.get(wall).drawVert(g, WIDTH - 10, (wall - 15) * 90);
                } else if (wall < 40) {
                    borderWalls.get(wall).drawHoriz(g, (wall - 25) * 90, HEIGHT - 10);
                } else if (wall < borderWalls.size()) {
                    borderWalls.get(wall).drawVert(g, 0, (wall - 40) * 90);
                }
            }

            for (int i = 0; i < 141; i++) {

                if (booleanvHelper[i]) {
                    int loophelper = i / 14;
                    insideVertWalls.get(i / 3).drawVert(g, ((i % 14) * 90) + 90, loophelper * 90);
                } else {
                    insideVertWalls.get(i * 2 / 3).drawVert(g, ((i % 14) * 90) + 90, loophelper * 90);
                }
            }

            for (int i = 0; i < 136; i++) {
                if (booleanhHelper[i]) {
                    int loophelper = i / 15;
                    insideHorizWalls.get(i / 3).drawHoriz(g, (i % 15) * 90, (loophelper * 90) + 90);
                } else {
                    insideHorizWalls.get(i * 2 / 3).drawHoriz(g, (i % 15) * 90, (loophelper * 90) + 90);
                }
            }
            /*
             * for (int i = 0; i<insideHorizWalls.size(); i++) {
             * insideHorizWalls.get(i).ghorizIntersectsWithTank(green);
             * insideHorizWalls.get(i).horizIntersectsWithTank(red);
             * insideHorizWalls.get(i).gvertIntersectsWithTank(green);
             * insideHorizWalls.get(i).vertIntersectsWithTank(red);
             * }
             * for (int i = 0; i<insideVertWalls.size(); i++) {
             * insideVertWalls.get(i).ghorizIntersectsWithTank(green);
             * insideVertWalls.get(i).horizIntersectsWithTank(red);
             * insideVertWalls.get(i).gvertIntersectsWithTank(green);
             * insideVertWalls.get(i).vertIntersectsWithTank(red);
             * }
             */

            g.setColor(Color.RED);
            g.setFont(new Font("Comic Sans MS", 50, 50));
            g.drawString("Player 1: " + redscore, 100, 100);
            g.setColor(Color.GREEN);
            g.drawString("Player 2: " + greenscore, 1000, 100);
            ;

            if (drawn1 && time1 != maxTime) {
                rb1.draw(g);
                rb1.move(WIDTH, HEIGHT);
                time1++;
            }
            if (drawn2 && time2 != maxTime) {
                rb2.draw(g);
                rb2.move(WIDTH, HEIGHT);
                time2++;
            }
            if (drawn3 && time3 != maxTime) {
                rb3.draw(g);
                rb3.move(WIDTH, HEIGHT);
                time3++;
            }
            if (drawn4 && time4 != maxTime) {
                rb4.draw(g);
                rb4.move(WIDTH, HEIGHT);
                time4++;
            }
            if (drawn5 && time5 != maxTime) {
                rb5.draw(g);
                rb5.move(WIDTH, HEIGHT);
                time5++;
            }
            if (grawn1 && gime1 != maxTime) {
                gb1.draw(g);
                gb1.move(WIDTH, HEIGHT);
                gime1++;
            }
            if (grawn2 && gime2 != maxTime) {
                gb2.draw(g);
                gb2.move(WIDTH, HEIGHT);
                gime2++;
            }
            if (grawn3 && gime3 != maxTime) {
                gb3.draw(g);
                gb3.move(WIDTH, HEIGHT);
                gime3++;
            }
            if (grawn4 && gime4 != maxTime) {
                gb4.draw(g);
                gb4.move(WIDTH, HEIGHT);
                gime4++;
            }
            if (grawn5 && gime5 != maxTime) {
                gb5.draw(g);
                gb5.move(WIDTH, HEIGHT);
                gime5++;
            }

            for (int i = 0; i < insideVertWalls.size(); i++) {
                if (gb1.intersectsWithVert(insideVertWalls.get(i))) {
                    gb1.setSpeed(gb1.getXSpeed() * -1, gb1.getYSpeed());
                }
                if (gb2.intersectsWithVert(insideVertWalls.get(i))) {
                    gb2.setSpeed(gb2.getXSpeed() * -1, gb2.getYSpeed());
                }
                if (gb3.intersectsWithVert(insideVertWalls.get(i))) {
                    gb3.setSpeed(gb3.getXSpeed() * -1, gb3.getYSpeed());
                }
                if (gb4.intersectsWithVert(insideVertWalls.get(i))) {
                    gb4.setSpeed(gb4.getXSpeed() * -1, gb4.getYSpeed());
                }
                if (gb5.intersectsWithVert(insideVertWalls.get(i))) {
                    gb5.setSpeed(gb5.getXSpeed() * -1, gb5.getYSpeed());
                }
                if (rb1.intersectsWithVert(insideVertWalls.get(i))) {
                    rb1.setSpeed(rb1.getXSpeed() * -1, rb1.getYSpeed());
                }
                if (rb2.intersectsWithVert(insideVertWalls.get(i))) {
                    rb2.setSpeed(rb2.getXSpeed() * -1, rb2.getYSpeed());
                }

            }

            if (!rb1.collides(red) && !rb2.collides(red) && !rb3.collides(red) && !rb4.collides(red)
                    && !rb5.collides(red) && !gb1.collides(red) && !gb2.collides(red) && !gb3.collides(red)
                    && !gb4.collides(red)
                    && !gb5.collides(red)) {
                red.drawTank(g);
            } else {
                if (time1 <= delayTime || time1 == maxTime || time2 <= delayTime || time2 == maxTime
                        || time3 <= delayTime || time3 == maxTime || time4 <= delayTime || time4 == maxTime
                        || time5 <= delayTime
                        || time5 == maxTime
                                && (!rb1.collides(red) && !rb2.collides(red) && !rb3.collides(red) && !rb4.collides(red)
                                        && !rb5.collides(red))) {
                    red.drawTank(g);
                } else {
                    red.setLocation(Integer.MAX_VALUE, Integer.MAX_VALUE);
                }
            }
            if (!gb1.gollides(green) && !gb2.gollides(green) && !gb3.gollides(green) && !gb4.gollides(green)
                    && !gb5.gollides(green) && !rb1.gollides(green) && !rb2.gollides(green) && !rb3.gollides(green)
                    && !rb4.gollides(green)
                    && !rb5.gollides(green)) {
                green.drawTank(g);
            } else {
                if (gime1 <= delayTime || gime1 == maxTime || gime2 <= delayTime || gime2 == maxTime
                        || gime3 <= delayTime || gime3 == maxTime || gime4 <= delayTime || gime4 == maxTime
                        || gime5 <= delayTime
                        || gime5 == maxTime) {
                    green.drawTank(g);
                } else {
                    green.setLocation(Integer.MAX_VALUE, Integer.MAX_VALUE);
                }
            }

            if (time1 == maxTime || time1 == 0) {
                rb1.setLocation(updx, updy);
                if (red.getDir() == 0) {
                    rb1.setSpeed(0, -2.5);
                } else if (red.getDir() == 9) {
                    rb1.setSpeed(2.7, 0);
                } else if (red.getDir() == 18) {
                    rb1.setSpeed(0, 2.7);
                } else if (red.getDir() == 27) {
                    rb1.setSpeed(-2.7, 0);
                    // angled portions next
                    // y portions positive

                } else if (red.getDir() == 1 || red.getDir() == 35) {
                    rb1.setYSpeed(-2.4);
                } else if (red.getDir() == 2 || red.getDir() == 34) {
                    rb1.setYSpeed(-2.1);
                } else if (red.getDir() == 3 || red.getDir() == 33) {
                    rb1.setYSpeed(-1.8);
                } else if (red.getDir() == 4 || red.getDir() == 32) {
                    rb1.setYSpeed(-1.5);
                } else if (red.getDir() == 5 || red.getDir() == 31) {
                    rb1.setYSpeed(-1.2);
                } else if (red.getDir() == 6 || red.getDir() == 30) {
                    rb1.setYSpeed(-0.9);
                } else if (red.getDir() == 7 || red.getDir() == 29) {
                    rb1.setYSpeed(-0.6);
                } else if (red.getDir() == 8 || red.getDir() == 28) {
                    rb1.setYSpeed(-0.3);
                    // y portion negative

                } else if (red.getDir() == 10 || red.getDir() == 26) {
                    rb1.setYSpeed(0.3);
                } else if (red.getDir() == 11 || red.getDir() == 25) {
                    rb1.setYSpeed(0.6);
                } else if (red.getDir() == 12 || red.getDir() == 24) {
                    rb1.setYSpeed(0.9);
                } else if (red.getDir() == 13 || red.getDir() == 23) {
                    rb1.setYSpeed(1.2);
                } else if (red.getDir() == 14 || red.getDir() == 22) {
                    rb1.setYSpeed(1.5);
                } else if (red.getDir() == 15 || red.getDir() == 21) {
                    rb1.setYSpeed(1.8);
                } else if (red.getDir() == 16 || red.getDir() == 20) {
                    rb1.setYSpeed(2.1);
                } else if (red.getDir() == 17 || red.getDir() == 19) {
                    rb1.setYSpeed(2.4);
                }
                // x portions positive

                if (red.getDir() == 8 || red.getDir() == 10) {
                    rb1.setXSpeed(2.4);
                } else if (red.getDir() == 7 || red.getDir() == 11) {
                    rb1.setXSpeed(2.1);
                } else if (red.getDir() == 6 || red.getDir() == 12) {
                    rb1.setXSpeed(1.8);
                } else if (red.getDir() == 5 || red.getDir() == 13) {
                    rb1.setXSpeed(1.5);
                } else if (red.getDir() == 4 || red.getDir() == 14) {
                    rb1.setXSpeed(1.2);
                } else if (red.getDir() == 3 || red.getDir() == 15) {
                    rb1.setXSpeed(0.9);
                } else if (red.getDir() == 2 || red.getDir() == 16) {
                    rb1.setXSpeed(0.6);
                } else if (red.getDir() == 1 || red.getDir() == 17) {
                    rb1.setXSpeed(0.3);
                    // x portion negative

                } else if (red.getDir() == 28 || red.getDir() == 26) {
                    rb1.setXSpeed(-2.4);
                } else if (red.getDir() == 29 || red.getDir() == 25) {
                    rb1.setXSpeed(-2.1);
                } else if (red.getDir() == 30 || red.getDir() == 24) {
                    rb1.setXSpeed(-1.8);
                } else if (red.getDir() == 31 || red.getDir() == 23) {
                    rb1.setXSpeed(-1.5);
                } else if (red.getDir() == 32 || red.getDir() == 22) {
                    rb1.setXSpeed(-1.2);
                } else if (red.getDir() == 33 || red.getDir() == 21) {
                    rb1.setXSpeed(-0.9);
                } else if (red.getDir() == 34 || red.getDir() == 20) {
                    rb1.setXSpeed(-0.6);
                } else if (red.getDir() == 35 || red.getDir() == 19) {
                    rb1.setXSpeed(-0.3);
                }
            }
            if (time2 == maxTime || time2 == 0) {
                rb2.setLocation(updx, updy);
                if (red.getDir() == 0) {
                    rb2.setSpeed(0, -2.5);
                } else if (red.getDir() == 9) {
                    rb2.setSpeed(2.7, 0);
                } else if (red.getDir() == 18) {
                    rb2.setSpeed(0, 2.7);
                } else if (red.getDir() == 27) {
                    rb2.setSpeed(-2.7, 0);
                    // angled portions next
                    // y portions positive

                } else if (red.getDir() == 1 || red.getDir() == 35) {
                    rb2.setYSpeed(-2.4);
                } else if (red.getDir() == 2 || red.getDir() == 34) {
                    rb2.setYSpeed(-2.1);
                } else if (red.getDir() == 3 || red.getDir() == 33) {
                    rb2.setYSpeed(-1.8);
                } else if (red.getDir() == 4 || red.getDir() == 32) {
                    rb2.setYSpeed(-1.5);
                } else if (red.getDir() == 5 || red.getDir() == 31) {
                    rb2.setYSpeed(-1.2);
                } else if (red.getDir() == 6 || red.getDir() == 30) {
                    rb2.setYSpeed(-0.9);
                } else if (red.getDir() == 7 || red.getDir() == 29) {
                    rb2.setYSpeed(-0.6);
                } else if (red.getDir() == 8 || red.getDir() == 28) {
                    rb2.setYSpeed(-0.3);
                    // y portion negative

                } else if (red.getDir() == 10 || red.getDir() == 26) {
                    rb2.setYSpeed(0.3);
                } else if (red.getDir() == 11 || red.getDir() == 25) {
                    rb2.setYSpeed(0.6);
                } else if (red.getDir() == 12 || red.getDir() == 24) {
                    rb2.setYSpeed(0.9);
                } else if (red.getDir() == 13 || red.getDir() == 23) {
                    rb2.setYSpeed(1.2);
                } else if (red.getDir() == 14 || red.getDir() == 22) {
                    rb2.setYSpeed(1.5);
                } else if (red.getDir() == 15 || red.getDir() == 21) {
                    rb2.setYSpeed(1.8);
                } else if (red.getDir() == 16 || red.getDir() == 20) {
                    rb2.setYSpeed(2.1);
                } else if (red.getDir() == 17 || red.getDir() == 19) {
                    rb2.setYSpeed(2.4);
                }
                // x portions positive

                if (red.getDir() == 8 || red.getDir() == 10) {
                    rb2.setXSpeed(2.4);
                } else if (red.getDir() == 7 || red.getDir() == 11) {
                    rb2.setXSpeed(2.1);
                } else if (red.getDir() == 6 || red.getDir() == 12) {
                    rb2.setXSpeed(1.8);
                } else if (red.getDir() == 5 || red.getDir() == 13) {
                    rb2.setXSpeed(1.5);
                } else if (red.getDir() == 4 || red.getDir() == 14) {
                    rb2.setXSpeed(1.2);
                } else if (red.getDir() == 3 || red.getDir() == 15) {
                    rb2.setXSpeed(0.9);
                } else if (red.getDir() == 2 || red.getDir() == 16) {
                    rb2.setXSpeed(0.6);
                } else if (red.getDir() == 1 || red.getDir() == 17) {
                    rb2.setXSpeed(0.3);
                    // x portion negative

                } else if (red.getDir() == 28 || red.getDir() == 26) {
                    rb2.setXSpeed(-2.4);
                } else if (red.getDir() == 29 || red.getDir() == 25) {
                    rb2.setXSpeed(-2.1);
                } else if (red.getDir() == 30 || red.getDir() == 24) {
                    rb2.setXSpeed(-1.8);
                } else if (red.getDir() == 31 || red.getDir() == 23) {
                    rb2.setXSpeed(-1.5);
                } else if (red.getDir() == 32 || red.getDir() == 22) {
                    rb2.setXSpeed(-1.2);
                } else if (red.getDir() == 33 || red.getDir() == 21) {
                    rb2.setXSpeed(-0.9);
                } else if (red.getDir() == 34 || red.getDir() == 20) {
                    rb2.setXSpeed(-0.6);
                } else if (red.getDir() == 35 || red.getDir() == 19) {
                    rb2.setXSpeed(-0.3);
                }
            }
            if (time3 == maxTime || time3 == 0) {
                rb3.setLocation(updx, updy);
                if (red.getDir() == 0) {
                    rb3.setSpeed(0, -2.5);
                } else if (red.getDir() == 9) {
                    rb3.setSpeed(2.7, 0);
                } else if (red.getDir() == 18) {
                    rb3.setSpeed(0, 2.7);
                } else if (red.getDir() == 27) {
                    rb3.setSpeed(-2.7, 0);
                    // angled portions next
                    // y portions positive

                } else if (red.getDir() == 1 || red.getDir() == 35) {
                    rb3.setYSpeed(-2.4);
                } else if (red.getDir() == 2 || red.getDir() == 34) {
                    rb3.setYSpeed(-2.1);
                } else if (red.getDir() == 3 || red.getDir() == 33) {
                    rb3.setYSpeed(-1.8);
                } else if (red.getDir() == 4 || red.getDir() == 32) {
                    rb3.setYSpeed(-1.5);
                } else if (red.getDir() == 5 || red.getDir() == 31) {
                    rb3.setYSpeed(-1.2);
                } else if (red.getDir() == 6 || red.getDir() == 30) {
                    rb3.setYSpeed(-0.9);
                } else if (red.getDir() == 7 || red.getDir() == 29) {
                    rb3.setYSpeed(-0.6);
                } else if (red.getDir() == 8 || red.getDir() == 28) {
                    rb3.setYSpeed(-0.3);
                    // y portion negative

                } else if (red.getDir() == 10 || red.getDir() == 26) {
                    rb3.setYSpeed(0.3);
                } else if (red.getDir() == 11 || red.getDir() == 25) {
                    rb3.setYSpeed(0.6);
                } else if (red.getDir() == 12 || red.getDir() == 24) {
                    rb3.setYSpeed(0.9);
                } else if (red.getDir() == 13 || red.getDir() == 23) {
                    rb3.setYSpeed(1.2);
                } else if (red.getDir() == 14 || red.getDir() == 22) {
                    rb3.setYSpeed(1.5);
                } else if (red.getDir() == 15 || red.getDir() == 21) {
                    rb3.setYSpeed(1.8);
                } else if (red.getDir() == 16 || red.getDir() == 20) {
                    rb3.setYSpeed(2.1);
                } else if (red.getDir() == 17 || red.getDir() == 19) {
                    rb3.setYSpeed(2.4);
                }
                // x portions positive

                if (red.getDir() == 8 || red.getDir() == 10) {
                    rb3.setXSpeed(2.4);
                } else if (red.getDir() == 7 || red.getDir() == 11) {
                    rb3.setXSpeed(2.1);
                } else if (red.getDir() == 6 || red.getDir() == 12) {
                    rb3.setXSpeed(1.8);
                } else if (red.getDir() == 5 || red.getDir() == 13) {
                    rb3.setXSpeed(1.5);
                } else if (red.getDir() == 4 || red.getDir() == 14) {
                    rb3.setXSpeed(1.2);
                } else if (red.getDir() == 3 || red.getDir() == 15) {
                    rb3.setXSpeed(0.9);
                } else if (red.getDir() == 2 || red.getDir() == 16) {
                    rb3.setXSpeed(0.6);
                } else if (red.getDir() == 1 || red.getDir() == 17) {
                    rb3.setXSpeed(0.3);
                    // x portion negative

                } else if (red.getDir() == 28 || red.getDir() == 26) {
                    rb3.setXSpeed(-2.4);
                } else if (red.getDir() == 29 || red.getDir() == 25) {
                    rb3.setXSpeed(-2.1);
                } else if (red.getDir() == 30 || red.getDir() == 24) {
                    rb3.setXSpeed(-1.8);
                } else if (red.getDir() == 31 || red.getDir() == 23) {
                    rb3.setXSpeed(-1.5);
                } else if (red.getDir() == 32 || red.getDir() == 22) {
                    rb3.setXSpeed(-1.2);
                } else if (red.getDir() == 33 || red.getDir() == 21) {
                    rb3.setXSpeed(-0.9);
                } else if (red.getDir() == 34 || red.getDir() == 20) {
                    rb3.setXSpeed(-0.6);
                } else if (red.getDir() == 35 || red.getDir() == 19) {
                    rb3.setXSpeed(-0.3);
                }
            }
            if (time4 == maxTime || time4 == 0) {
                rb4.setLocation(updx, updy);
                if (red.getDir() == 0) {
                    rb4.setSpeed(0, -2.5);
                } else if (red.getDir() == 9) {
                    rb4.setSpeed(2.7, 0);
                } else if (red.getDir() == 18) {
                    rb4.setSpeed(0, 2.7);
                } else if (red.getDir() == 27) {
                    rb4.setSpeed(-2.7, 0);
                    // angled portions next
                    // y portions positive

                } else if (red.getDir() == 1 || red.getDir() == 35) {
                    rb4.setYSpeed(-2.4);
                } else if (red.getDir() == 2 || red.getDir() == 34) {
                    rb4.setYSpeed(-2.1);
                } else if (red.getDir() == 3 || red.getDir() == 33) {
                    rb4.setYSpeed(-1.8);
                } else if (red.getDir() == 4 || red.getDir() == 32) {
                    rb4.setYSpeed(-1.5);
                } else if (red.getDir() == 5 || red.getDir() == 31) {
                    rb4.setYSpeed(-1.2);
                } else if (red.getDir() == 6 || red.getDir() == 30) {
                    rb4.setYSpeed(-0.9);
                } else if (red.getDir() == 7 || red.getDir() == 29) {
                    rb4.setYSpeed(-0.6);
                } else if (red.getDir() == 8 || red.getDir() == 28) {
                    rb4.setYSpeed(-0.3);
                    // y portion negative

                } else if (red.getDir() == 10 || red.getDir() == 26) {
                    rb4.setYSpeed(0.3);
                } else if (red.getDir() == 11 || red.getDir() == 25) {
                    rb4.setYSpeed(0.6);
                } else if (red.getDir() == 12 || red.getDir() == 24) {
                    rb4.setYSpeed(0.9);
                } else if (red.getDir() == 13 || red.getDir() == 23) {
                    rb4.setYSpeed(1.2);
                } else if (red.getDir() == 14 || red.getDir() == 22) {
                    rb4.setYSpeed(1.5);
                } else if (red.getDir() == 15 || red.getDir() == 21) {
                    rb4.setYSpeed(1.8);
                } else if (red.getDir() == 16 || red.getDir() == 20) {
                    rb4.setYSpeed(2.1);
                } else if (red.getDir() == 17 || red.getDir() == 19) {
                    rb4.setYSpeed(2.4);
                }
                // x portions positive

                if (red.getDir() == 8 || red.getDir() == 10) {
                    rb4.setXSpeed(2.4);
                } else if (red.getDir() == 7 || red.getDir() == 11) {
                    rb4.setXSpeed(2.1);
                } else if (red.getDir() == 6 || red.getDir() == 12) {
                    rb4.setXSpeed(1.8);
                } else if (red.getDir() == 5 || red.getDir() == 13) {
                    rb4.setXSpeed(1.5);
                } else if (red.getDir() == 4 || red.getDir() == 14) {
                    rb4.setXSpeed(1.2);
                } else if (red.getDir() == 3 || red.getDir() == 15) {
                    rb4.setXSpeed(0.9);
                } else if (red.getDir() == 2 || red.getDir() == 16) {
                    rb4.setXSpeed(0.6);
                } else if (red.getDir() == 1 || red.getDir() == 17) {
                    rb4.setXSpeed(0.3);
                    // x portion negative

                } else if (red.getDir() == 28 || red.getDir() == 26) {
                    rb4.setXSpeed(-2.4);
                } else if (red.getDir() == 29 || red.getDir() == 25) {
                    rb4.setXSpeed(-2.1);
                } else if (red.getDir() == 30 || red.getDir() == 24) {
                    rb4.setXSpeed(-1.8);
                } else if (red.getDir() == 31 || red.getDir() == 23) {
                    rb4.setXSpeed(-1.5);
                } else if (red.getDir() == 32 || red.getDir() == 22) {
                    rb4.setXSpeed(-1.2);
                } else if (red.getDir() == 33 || red.getDir() == 21) {
                    rb4.setXSpeed(-0.9);
                } else if (red.getDir() == 34 || red.getDir() == 20) {
                    rb4.setXSpeed(-0.6);
                } else if (red.getDir() == 35 || red.getDir() == 19) {
                    rb4.setXSpeed(-0.3);
                }
            }
            if (time5 == maxTime || time5 == 0) {
                rb5.setLocation(updx, updy);
                if (red.getDir() == 0) {
                    rb5.setSpeed(0, -2.5);
                } else if (red.getDir() == 9) {
                    rb5.setSpeed(2.7, 0);
                } else if (red.getDir() == 18) {
                    rb5.setSpeed(0, 2.7);
                } else if (red.getDir() == 27) {
                    rb5.setSpeed(-2.7, 0);
                    // angled portions next
                    // y portions positive

                } else if (red.getDir() == 1 || red.getDir() == 35) {
                    rb5.setYSpeed(-2.4);
                } else if (red.getDir() == 2 || red.getDir() == 34) {
                    rb5.setYSpeed(-2.1);
                } else if (red.getDir() == 3 || red.getDir() == 33) {
                    rb5.setYSpeed(-1.8);
                } else if (red.getDir() == 4 || red.getDir() == 32) {
                    rb5.setYSpeed(-1.5);
                } else if (red.getDir() == 5 || red.getDir() == 31) {
                    rb5.setYSpeed(-1.2);
                } else if (red.getDir() == 6 || red.getDir() == 30) {
                    rb5.setYSpeed(-0.9);
                } else if (red.getDir() == 7 || red.getDir() == 29) {
                    rb5.setYSpeed(-0.6);
                } else if (red.getDir() == 8 || red.getDir() == 28) {
                    rb5.setYSpeed(-0.3);
                    // y portion negative

                } else if (red.getDir() == 10 || red.getDir() == 26) {
                    rb5.setYSpeed(0.3);
                } else if (red.getDir() == 11 || red.getDir() == 25) {
                    rb5.setYSpeed(0.6);
                } else if (red.getDir() == 12 || red.getDir() == 24) {
                    rb5.setYSpeed(0.9);
                } else if (red.getDir() == 13 || red.getDir() == 23) {
                    rb5.setYSpeed(1.2);
                } else if (red.getDir() == 14 || red.getDir() == 22) {
                    rb5.setYSpeed(1.5);
                } else if (red.getDir() == 15 || red.getDir() == 21) {
                    rb5.setYSpeed(1.8);
                } else if (red.getDir() == 16 || red.getDir() == 20) {
                    rb5.setYSpeed(2.1);
                } else if (red.getDir() == 17 || red.getDir() == 19) {
                    rb5.setYSpeed(2.4);
                }
                // x portions positive

                if (red.getDir() == 8 || red.getDir() == 10) {
                    rb5.setXSpeed(2.4);
                } else if (red.getDir() == 7 || red.getDir() == 11) {
                    rb5.setXSpeed(2.1);
                } else if (red.getDir() == 6 || red.getDir() == 12) {
                    rb5.setXSpeed(1.8);
                } else if (red.getDir() == 5 || red.getDir() == 13) {
                    rb5.setXSpeed(1.5);
                } else if (red.getDir() == 4 || red.getDir() == 14) {
                    rb5.setXSpeed(1.2);
                } else if (red.getDir() == 3 || red.getDir() == 15) {
                    rb5.setXSpeed(0.9);
                } else if (red.getDir() == 2 || red.getDir() == 16) {
                    rb5.setXSpeed(0.6);
                } else if (red.getDir() == 1 || red.getDir() == 17) {
                    rb5.setXSpeed(0.3);
                    // x portion negative

                } else if (red.getDir() == 28 || red.getDir() == 26) {
                    rb5.setXSpeed(-2.4);
                } else if (red.getDir() == 29 || red.getDir() == 25) {
                    rb5.setXSpeed(-2.1);
                } else if (red.getDir() == 30 || red.getDir() == 24) {
                    rb5.setXSpeed(-1.8);
                } else if (red.getDir() == 31 || red.getDir() == 23) {
                    rb5.setXSpeed(-1.5);
                } else if (red.getDir() == 32 || red.getDir() == 22) {
                    rb5.setXSpeed(-1.2);
                } else if (red.getDir() == 33 || red.getDir() == 21) {
                    rb5.setXSpeed(-0.9);
                } else if (red.getDir() == 34 || red.getDir() == 20) {
                    rb5.setXSpeed(-0.6);
                } else if (red.getDir() == 35 || red.getDir() == 19) {
                    rb5.setXSpeed(-0.3);
                }
            }
            if (gime1 == maxTime || gime1 == 0) {
                gb1.setLocation(gupdx, gupdy);
                if (green.getDir() == 0) {
                    gb1.setSpeed(0, -2.5);
                } else if (green.getDir() == 9) {
                    gb1.setSpeed(2.7, 0);
                } else if (green.getDir() == 18) {
                    gb1.setSpeed(0, 2.7);
                } else if (green.getDir() == 27) {
                    gb1.setSpeed(-2.7, 0);
                    // angled portions next
                    // y portions positive

                } else if (green.getDir() == 1 || green.getDir() == 35) {
                    gb1.setYSpeed(-2.4);
                } else if (green.getDir() == 2 || green.getDir() == 34) {
                    gb1.setYSpeed(-2.1);
                } else if (green.getDir() == 3 || green.getDir() == 33) {
                    gb1.setYSpeed(-1.8);
                } else if (green.getDir() == 4 || green.getDir() == 32) {
                    gb1.setYSpeed(-1.5);
                } else if (green.getDir() == 5 || green.getDir() == 31) {
                    gb1.setYSpeed(-1.2);
                } else if (green.getDir() == 6 || green.getDir() == 30) {
                    gb1.setYSpeed(-0.9);
                } else if (green.getDir() == 7 || green.getDir() == 29) {
                    gb1.setYSpeed(-0.6);
                } else if (green.getDir() == 8 || green.getDir() == 28) {
                    gb1.setYSpeed(-0.3);
                    // y portion negative

                } else if (green.getDir() == 10 || green.getDir() == 26) {
                    gb1.setYSpeed(0.3);
                } else if (green.getDir() == 11 || green.getDir() == 25) {
                    gb1.setYSpeed(0.6);
                } else if (green.getDir() == 12 || green.getDir() == 24) {
                    gb1.setYSpeed(0.9);
                } else if (green.getDir() == 13 || green.getDir() == 23) {
                    gb1.setYSpeed(1.2);
                } else if (green.getDir() == 14 || green.getDir() == 22) {
                    gb1.setYSpeed(1.5);
                } else if (green.getDir() == 15 || green.getDir() == 21) {
                    gb1.setYSpeed(1.8);
                } else if (green.getDir() == 16 || green.getDir() == 20) {
                    gb1.setYSpeed(2.1);
                } else if (green.getDir() == 17 || green.getDir() == 19) {
                    gb1.setYSpeed(2.4);
                }
                // x portions positive

                if (green.getDir() == 8 || green.getDir() == 10) {
                    gb1.setXSpeed(2.4);
                } else if (green.getDir() == 7 || green.getDir() == 11) {
                    gb1.setXSpeed(2.1);
                } else if (green.getDir() == 6 || green.getDir() == 12) {
                    gb1.setXSpeed(1.8);
                } else if (green.getDir() == 5 || green.getDir() == 13) {
                    gb1.setXSpeed(1.5);
                } else if (green.getDir() == 4 || green.getDir() == 14) {
                    gb1.setXSpeed(1.2);
                } else if (green.getDir() == 3 || green.getDir() == 15) {
                    gb1.setXSpeed(0.9);
                } else if (green.getDir() == 2 || green.getDir() == 16) {
                    gb1.setXSpeed(0.6);
                } else if (green.getDir() == 1 || green.getDir() == 17) {
                    gb1.setXSpeed(0.3);
                    // x portion negative

                } else if (green.getDir() == 28 || green.getDir() == 26) {
                    gb1.setXSpeed(-2.4);
                } else if (green.getDir() == 29 || green.getDir() == 25) {
                    gb1.setXSpeed(-2.1);
                } else if (green.getDir() == 30 || green.getDir() == 24) {
                    gb1.setXSpeed(-1.8);
                } else if (green.getDir() == 31 || green.getDir() == 23) {
                    gb1.setXSpeed(-1.5);
                } else if (green.getDir() == 32 || green.getDir() == 22) {
                    gb1.setXSpeed(-1.2);
                } else if (green.getDir() == 33 || green.getDir() == 21) {
                    gb1.setXSpeed(-0.9);
                } else if (green.getDir() == 34 || green.getDir() == 20) {
                    gb1.setXSpeed(-0.6);
                } else if (green.getDir() == 35 || green.getDir() == 19) {
                    gb1.setXSpeed(-0.3);
                }
            }
            if (gime2 == maxTime || gime2 == 0) {
                gb2.setLocation(gupdx, gupdy);
                if (green.getDir() == 0) {
                    gb2.setSpeed(0, -2.5);
                } else if (green.getDir() == 9) {
                    gb2.setSpeed(2.7, 0);
                } else if (green.getDir() == 18) {
                    gb2.setSpeed(0, 2.7);
                } else if (green.getDir() == 27) {
                    gb2.setSpeed(-2.7, 0);
                    // angled portions next
                    // y portions positive

                } else if (green.getDir() == 1 || green.getDir() == 35) {
                    gb2.setYSpeed(-2.4);
                } else if (green.getDir() == 2 || green.getDir() == 34) {
                    gb2.setYSpeed(-2.1);
                } else if (green.getDir() == 3 || green.getDir() == 33) {
                    gb2.setYSpeed(-1.8);
                } else if (green.getDir() == 4 || green.getDir() == 32) {
                    gb2.setYSpeed(-1.5);
                } else if (green.getDir() == 5 || green.getDir() == 31) {
                    gb2.setYSpeed(-1.2);
                } else if (green.getDir() == 6 || green.getDir() == 30) {
                    gb2.setYSpeed(-0.9);
                } else if (green.getDir() == 7 || green.getDir() == 29) {
                    gb2.setYSpeed(-0.6);
                } else if (green.getDir() == 8 || green.getDir() == 28) {
                    gb2.setYSpeed(-0.3);
                    // y portion negative

                } else if (green.getDir() == 10 || green.getDir() == 26) {
                    gb2.setYSpeed(0.3);
                } else if (green.getDir() == 11 || green.getDir() == 25) {
                    gb2.setYSpeed(0.6);
                } else if (green.getDir() == 12 || green.getDir() == 24) {
                    gb2.setYSpeed(0.9);
                } else if (green.getDir() == 13 || green.getDir() == 23) {
                    gb2.setYSpeed(1.2);
                } else if (green.getDir() == 14 || green.getDir() == 22) {
                    gb2.setYSpeed(1.5);
                } else if (green.getDir() == 15 || green.getDir() == 21) {
                    gb2.setYSpeed(1.8);
                } else if (green.getDir() == 16 || green.getDir() == 20) {
                    gb2.setYSpeed(2.1);
                } else if (green.getDir() == 17 || green.getDir() == 19) {
                    gb2.setYSpeed(2.4);
                }
                // x portions positive

                if (green.getDir() == 8 || green.getDir() == 10) {
                    gb2.setXSpeed(2.4);
                } else if (green.getDir() == 7 || green.getDir() == 11) {
                    gb2.setXSpeed(2.1);
                } else if (green.getDir() == 6 || green.getDir() == 12) {
                    gb2.setXSpeed(1.8);
                } else if (green.getDir() == 5 || green.getDir() == 13) {
                    gb2.setXSpeed(1.5);
                } else if (green.getDir() == 4 || green.getDir() == 14) {
                    gb2.setXSpeed(1.2);
                } else if (green.getDir() == 3 || green.getDir() == 15) {
                    gb2.setXSpeed(0.9);
                } else if (green.getDir() == 2 || green.getDir() == 16) {
                    gb2.setXSpeed(0.6);
                } else if (green.getDir() == 1 || green.getDir() == 17) {
                    gb2.setXSpeed(0.3);
                    // x portion negative

                } else if (green.getDir() == 28 || green.getDir() == 26) {
                    gb2.setXSpeed(-2.4);
                } else if (green.getDir() == 29 || green.getDir() == 25) {
                    gb2.setXSpeed(-2.1);
                } else if (green.getDir() == 30 || green.getDir() == 24) {
                    gb2.setXSpeed(-1.8);
                } else if (green.getDir() == 31 || green.getDir() == 23) {
                    gb2.setXSpeed(-1.5);
                } else if (green.getDir() == 32 || green.getDir() == 22) {
                    gb2.setXSpeed(-1.2);
                } else if (green.getDir() == 33 || green.getDir() == 21) {
                    gb2.setXSpeed(-0.9);
                } else if (green.getDir() == 34 || green.getDir() == 20) {
                    gb2.setXSpeed(-0.6);
                } else if (green.getDir() == 35 || green.getDir() == 19) {
                    gb2.setXSpeed(-0.3);
                }
            }
            if (gime3 == maxTime || gime3 == 0) {
                gb3.setLocation(gupdx, gupdy);
                if (green.getDir() == 0) {
                    gb3.setSpeed(0, -2.5);
                } else if (green.getDir() == 9) {
                    gb3.setSpeed(2.7, 0);
                } else if (green.getDir() == 18) {
                    gb3.setSpeed(0, 2.7);
                } else if (green.getDir() == 27) {
                    gb3.setSpeed(-2.7, 0);
                    // angled portions next
                    // y portions positive

                } else if (green.getDir() == 1 || green.getDir() == 35) {
                    gb3.setYSpeed(-2.4);
                } else if (green.getDir() == 2 || green.getDir() == 34) {
                    gb3.setYSpeed(-2.1);
                } else if (green.getDir() == 3 || green.getDir() == 33) {
                    gb3.setYSpeed(-1.8);
                } else if (green.getDir() == 4 || green.getDir() == 32) {
                    gb3.setYSpeed(-1.5);
                } else if (green.getDir() == 5 || green.getDir() == 31) {
                    gb3.setYSpeed(-1.2);
                } else if (green.getDir() == 6 || green.getDir() == 30) {
                    gb3.setYSpeed(-0.9);
                } else if (green.getDir() == 7 || green.getDir() == 29) {
                    gb3.setYSpeed(-0.6);
                } else if (green.getDir() == 8 || green.getDir() == 28) {
                    gb3.setYSpeed(-0.3);
                    // y portion negative

                } else if (green.getDir() == 10 || green.getDir() == 26) {
                    gb3.setYSpeed(0.3);
                } else if (green.getDir() == 11 || green.getDir() == 25) {
                    gb3.setYSpeed(0.6);
                } else if (green.getDir() == 12 || green.getDir() == 24) {
                    gb3.setYSpeed(0.9);
                } else if (green.getDir() == 13 || green.getDir() == 23) {
                    gb3.setYSpeed(1.2);
                } else if (green.getDir() == 14 || green.getDir() == 22) {
                    gb3.setYSpeed(1.5);
                } else if (green.getDir() == 15 || green.getDir() == 21) {
                    gb3.setYSpeed(1.8);
                } else if (green.getDir() == 16 || green.getDir() == 20) {
                    gb3.setYSpeed(2.1);
                } else if (green.getDir() == 17 || green.getDir() == 19) {
                    gb3.setYSpeed(2.4);
                }
                // x portions positive

                if (green.getDir() == 8 || green.getDir() == 10) {
                    gb3.setXSpeed(2.4);
                } else if (green.getDir() == 7 || green.getDir() == 11) {
                    gb3.setXSpeed(2.1);
                } else if (green.getDir() == 6 || green.getDir() == 12) {
                    gb3.setXSpeed(1.8);
                } else if (green.getDir() == 5 || green.getDir() == 13) {
                    gb3.setXSpeed(1.5);
                } else if (green.getDir() == 4 || green.getDir() == 14) {
                    gb3.setXSpeed(1.2);
                } else if (green.getDir() == 3 || green.getDir() == 15) {
                    gb3.setXSpeed(0.9);
                } else if (green.getDir() == 2 || green.getDir() == 16) {
                    gb3.setXSpeed(0.6);
                } else if (green.getDir() == 1 || green.getDir() == 17) {
                    gb3.setXSpeed(0.3);
                    // x portion negative

                } else if (green.getDir() == 28 || green.getDir() == 26) {
                    gb3.setXSpeed(-2.4);
                } else if (green.getDir() == 29 || green.getDir() == 25) {
                    gb3.setXSpeed(-2.1);
                } else if (green.getDir() == 30 || green.getDir() == 24) {
                    gb3.setXSpeed(-1.8);
                } else if (green.getDir() == 31 || green.getDir() == 23) {
                    gb3.setXSpeed(-1.5);
                } else if (green.getDir() == 32 || green.getDir() == 22) {
                    gb3.setXSpeed(-1.2);
                } else if (green.getDir() == 33 || green.getDir() == 21) {
                    gb3.setXSpeed(-0.9);
                } else if (green.getDir() == 34 || green.getDir() == 20) {
                    gb3.setXSpeed(-0.6);
                } else if (green.getDir() == 35 || green.getDir() == 19) {
                    gb3.setXSpeed(-0.3);
                }
            }
            if (gime4 == maxTime || gime4 == 0) {
                gb4.setLocation(gupdx, gupdy);
                if (green.getDir() == 0) {
                    gb4.setSpeed(0, -2.5);
                } else if (green.getDir() == 9) {
                    gb4.setSpeed(2.7, 0);
                } else if (green.getDir() == 18) {
                    gb4.setSpeed(0, 2.7);
                } else if (green.getDir() == 27) {
                    gb4.setSpeed(-2.7, 0);
                    // angled portions next
                    // y portions positive

                } else if (green.getDir() == 1 || green.getDir() == 35) {
                    gb4.setYSpeed(-2.4);
                } else if (green.getDir() == 2 || green.getDir() == 34) {
                    gb4.setYSpeed(-2.1);
                } else if (green.getDir() == 3 || green.getDir() == 33) {
                    gb4.setYSpeed(-1.8);
                } else if (green.getDir() == 4 || green.getDir() == 32) {
                    gb4.setYSpeed(-1.5);
                } else if (green.getDir() == 5 || green.getDir() == 31) {
                    gb4.setYSpeed(-1.2);
                } else if (green.getDir() == 6 || green.getDir() == 30) {
                    gb4.setYSpeed(-0.9);
                } else if (green.getDir() == 7 || green.getDir() == 29) {
                    gb4.setYSpeed(-0.6);
                } else if (green.getDir() == 8 || green.getDir() == 28) {
                    gb4.setYSpeed(-0.3);
                    // y portion negative

                } else if (green.getDir() == 10 || green.getDir() == 26) {
                    gb4.setYSpeed(0.3);
                } else if (green.getDir() == 11 || green.getDir() == 25) {
                    gb4.setYSpeed(0.6);
                } else if (green.getDir() == 12 || green.getDir() == 24) {
                    gb4.setYSpeed(0.9);
                } else if (green.getDir() == 13 || green.getDir() == 23) {
                    gb4.setYSpeed(1.2);
                } else if (green.getDir() == 14 || green.getDir() == 22) {
                    gb4.setYSpeed(1.5);
                } else if (green.getDir() == 15 || green.getDir() == 21) {
                    gb4.setYSpeed(1.8);
                } else if (green.getDir() == 16 || green.getDir() == 20) {
                    gb4.setYSpeed(2.1);
                } else if (green.getDir() == 17 || green.getDir() == 19) {
                    gb4.setYSpeed(2.4);
                }
                // x portions positive

                if (green.getDir() == 8 || green.getDir() == 10) {
                    gb4.setXSpeed(2.4);
                } else if (green.getDir() == 7 || green.getDir() == 11) {
                    gb4.setXSpeed(2.1);
                } else if (green.getDir() == 6 || green.getDir() == 12) {
                    gb4.setXSpeed(1.8);
                } else if (green.getDir() == 5 || green.getDir() == 13) {
                    gb4.setXSpeed(1.5);
                } else if (green.getDir() == 4 || green.getDir() == 14) {
                    gb4.setXSpeed(1.2);
                } else if (green.getDir() == 3 || green.getDir() == 15) {
                    gb4.setXSpeed(0.9);
                } else if (green.getDir() == 2 || green.getDir() == 16) {
                    gb4.setXSpeed(0.6);
                } else if (green.getDir() == 1 || green.getDir() == 17) {
                    gb4.setXSpeed(0.3);
                    // x portion negative

                } else if (green.getDir() == 28 || green.getDir() == 26) {
                    gb4.setXSpeed(-2.4);
                } else if (green.getDir() == 29 || green.getDir() == 25) {
                    gb4.setXSpeed(-2.1);
                } else if (green.getDir() == 30 || green.getDir() == 24) {
                    gb4.setXSpeed(-1.8);
                } else if (green.getDir() == 31 || green.getDir() == 23) {
                    gb4.setXSpeed(-1.5);
                } else if (green.getDir() == 32 || green.getDir() == 22) {
                    gb4.setXSpeed(-1.2);
                } else if (green.getDir() == 33 || green.getDir() == 21) {
                    gb4.setXSpeed(-0.9);
                } else if (green.getDir() == 34 || green.getDir() == 20) {
                    gb4.setXSpeed(-0.6);
                } else if (green.getDir() == 35 || green.getDir() == 19) {
                    gb4.setXSpeed(-0.3);
                }
            }
            if (gime5 == maxTime || gime5 == 0) {
                gb5.setLocation(gupdx, gupdy);
                if (green.getDir() == 0) {
                    gb5.setSpeed(0, -2.5);
                } else if (green.getDir() == 9) {
                    gb5.setSpeed(2.7, 0);
                } else if (green.getDir() == 18) {
                    gb5.setSpeed(0, 2.7);
                } else if (green.getDir() == 27) {
                    gb5.setSpeed(-2.7, 0);
                    // angled portions next
                    // y portions positive

                } else if (green.getDir() == 1 || green.getDir() == 35) {
                    gb5.setYSpeed(-2.4);
                } else if (green.getDir() == 2 || green.getDir() == 34) {
                    gb5.setYSpeed(-2.1);
                } else if (green.getDir() == 3 || green.getDir() == 33) {
                    gb5.setYSpeed(-1.8);
                } else if (green.getDir() == 4 || green.getDir() == 32) {
                    gb5.setYSpeed(-1.5);
                } else if (green.getDir() == 5 || green.getDir() == 31) {
                    gb5.setYSpeed(-1.2);
                } else if (green.getDir() == 6 || green.getDir() == 30) {
                    gb5.setYSpeed(-0.9);
                } else if (green.getDir() == 7 || green.getDir() == 29) {
                    gb5.setYSpeed(-0.6);
                } else if (green.getDir() == 8 || green.getDir() == 28) {
                    gb5.setYSpeed(-0.3);
                    // y portion negative

                } else if (green.getDir() == 10 || green.getDir() == 26) {
                    gb5.setYSpeed(0.3);
                } else if (green.getDir() == 11 || green.getDir() == 25) {
                    gb5.setYSpeed(0.6);
                } else if (green.getDir() == 12 || green.getDir() == 24) {
                    gb5.setYSpeed(0.9);
                } else if (green.getDir() == 13 || green.getDir() == 23) {
                    gb5.setYSpeed(1.2);
                } else if (green.getDir() == 14 || green.getDir() == 22) {
                    gb5.setYSpeed(1.5);
                } else if (green.getDir() == 15 || green.getDir() == 21) {
                    gb5.setYSpeed(1.8);
                } else if (green.getDir() == 16 || green.getDir() == 20) {
                    gb5.setYSpeed(2.1);
                } else if (green.getDir() == 17 || green.getDir() == 19) {
                    gb5.setYSpeed(2.4);
                }
                // x portions positive

                if (green.getDir() == 8 || green.getDir() == 10) {
                    gb5.setXSpeed(2.4);
                } else if (green.getDir() == 7 || green.getDir() == 11) {
                    gb5.setXSpeed(2.1);
                } else if (green.getDir() == 6 || green.getDir() == 12) {
                    gb5.setXSpeed(1.8);
                } else if (green.getDir() == 5 || green.getDir() == 13) {
                    gb5.setXSpeed(1.5);
                } else if (green.getDir() == 4 || green.getDir() == 14) {
                    gb5.setXSpeed(1.2);
                } else if (green.getDir() == 3 || green.getDir() == 15) {
                    gb5.setXSpeed(0.9);
                } else if (green.getDir() == 2 || green.getDir() == 16) {
                    gb5.setXSpeed(0.6);
                } else if (green.getDir() == 1 || green.getDir() == 17) {
                    gb5.setXSpeed(0.3);
                    // x portion negative

                } else if (green.getDir() == 28 || green.getDir() == 26) {
                    gb5.setXSpeed(-2.4);
                } else if (green.getDir() == 29 || green.getDir() == 25) {
                    gb5.setXSpeed(-2.1);
                } else if (green.getDir() == 30 || green.getDir() == 24) {
                    gb5.setXSpeed(-1.8);
                } else if (green.getDir() == 31 || green.getDir() == 23) {
                    gb5.setXSpeed(-1.5);
                } else if (green.getDir() == 32 || green.getDir() == 22) {
                    gb5.setXSpeed(-1.2);
                } else if (green.getDir() == 33 || green.getDir() == 21) {
                    gb5.setXSpeed(-0.9);
                } else if (green.getDir() == 34 || green.getDir() == 20) {
                    gb5.setXSpeed(-0.6);
                } else if (green.getDir() == 35 || green.getDir() == 19) {
                    gb5.setXSpeed(-0.3);
                }
            }

            if (red.getX() > 100000) {
                greenscore++;
                red.setLocation(130, 200);
                green.setLocation(850, 370);
                red.setDir((int) (Math.random() * 36));
                green.setDir((int) (Math.random() * 36));

                time1 = 0;
                time2 = 0;
                time3 = 0;
                time4 = 0;
                time5 = 0;

                gime1 = 0;
                gime2 = 0;
                gime3 = 0;
                gime4 = 0;
                gime5 = 0;

                count = 0;
                gount = 0;

                drawn1 = false;
                drawn2 = false;
                drawn3 = false;
                drawn4 = false;
                drawn5 = false;

                grawn1 = false;
                grawn2 = false;
                grawn3 = false;
                grawn4 = false;
                grawn5 = false;

                rb1.setLocation(updx, updy);
                rb2.setLocation(updx, updy);
                rb3.setLocation(updx, updy);
                rb4.setLocation(updx, updy);
                rb5.setLocation(updx, updy);

                gb1.setLocation(gupdx, gupdy);
                gb2.setLocation(gupdx, gupdy);
                gb3.setLocation(gupdx, gupdy);
                gb4.setLocation(gupdx, gupdy);
                gb5.setLocation(gupdx, gupdy);

                for (int i = 0; i < booleanhHelper.length; i++) {
                    randh = Math.random();
                    booleanhHelper[i] = (randh <= 0.4);
                }

                for (int i = 0; i < booleanvHelper.length; i++) {
                    randv = Math.random();
                    booleanvHelper[i] = (randv <= 0.4);
                }

               // System.out.println("Green Score: " + greenscore);
            }
            if (green.getX() > 100000) {
                redscore++;
                red.setLocation(130, 200);
                green.setLocation(850, 370);
                red.setDir((int) (Math.random() * 36));
                green.setDir((int) (Math.random() * 36));
               // System.out.println(red.getX());

                time1 = 0;
                time2 = 0;
                time3 = 0;
                time4 = 0;
                time5 = 0;

                gime1 = 0;
                gime2 = 0;
                gime3 = 0;
                gime4 = 0;
                gime5 = 0;

                count = 0;
                gount = 0;

                drawn1 = false;
                drawn2 = false;
                drawn3 = false;
                drawn4 = false;
                drawn5 = false;

                grawn1 = false;
                grawn2 = false;
                grawn3 = false;
                grawn4 = false;
                grawn5 = false;

                rb1.setLocation(updx, updy);
                rb2.setLocation(updx, updy);
                rb3.setLocation(updx, updy);
                rb4.setLocation(updx, updy);
                rb5.setLocation(updx, updy);

                gb1.setLocation(gupdx, gupdy);
                gb2.setLocation(gupdx, gupdy);
                gb3.setLocation(gupdx, gupdy);
                gb4.setLocation(gupdx, gupdy);
                gb5.setLocation(gupdx, gupdy);

              //  System.out.println("Red Score: " + redscore);

                for (int i = 0; i < booleanhHelper.length; i++) {
                    randh = Math.random();
                    booleanhHelper[i] = (randh <= 0.4);
                }

                for (int i = 0; i < booleanvHelper.length; i++) {
                    randv = Math.random();
                    booleanvHelper[i] = (randv <= 0.4);
                }
            }

            repaint(); // leave this alone, it MUST be the last thing in this method
        }

    }

    // do not modify this
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    // main method with standard graphics code
    public static void main(String[] args) {
        JFrame frame = new JFrame("The Ultimate Coffee Break Game");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new TankTrouble());
        frame.setVisible(true);
    }

}
