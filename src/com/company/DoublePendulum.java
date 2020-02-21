package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

public class DoublePendulum extends JPanel implements ActionListener {

    Timer timer = new Timer(5,this);

    static int g = 10;

    int radius1 = 200;
    int radius2 = 200;
    int mass1 = 20;
    int mass2 = 20;
    double angle1 = (float)Math.PI/2;
    double angle2 = (float)Math.PI/2;
    int v_a1 = 0;
    int v_a2 = 0;

    int px2 = -1;
    int py2 = -1;
    int cx = this.getWidth() / 2;
    int cy = 50;

    int x_1;
    int y_1;
    int x_2;
    int y_2;

    Line2D line2d;
    BufferedImage bufferedImage;
    Graphics2D g2d;
    Point p1;
    Point p2;
    public DoublePendulum(){

        bufferedImage = new BufferedImage(1000,1000,BufferedImage.TYPE_INT_RGB);
        g2d = (Graphics2D) bufferedImage.getGraphics();
        g2d.setColor(getBackground());
        g2d.fillRect(0,0,1000,1000);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(bufferedImage,0,0,null);
        g.translate(400,400);
        g.setColor(Color.BLACK);

        g.fillOval(x_1-mass1/2,y_1-mass1/2,mass1,mass1);

        g.fillOval(x_2-mass2/2,y_2-mass2/2,mass2, mass2);

        g.drawLine(0,0,x_1,y_1);

        g.drawLine(x_1,y_1,x_2,y_2);

        g2d.setColor(Color.black);
        if(line2d!=null)
        g2d.draw(line2d);

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        double new_x1 = radius1 * Math.sin(angle1);
        double new_y1 = radius1 * Math.cos(angle1);

        double new_x2 = new_x1 + radius2 * Math.sin(angle2);
        double new_y2 = new_y1 + radius2 * Math.cos(angle2);

        x_2 = (int)new_x2;
        y_2 = (int)new_y2;
        x_1 = (int)new_x1;
        y_1 = (int)new_y1;

        angle1 += .01;
        angle2 += .02;

        p1 = new Point(x_2 + 400,y_2 + 400);
        if(p2!=null)
            line2d = new Line2D.Double(p1,p2);
        p2 = p1;

        repaint();
    }
}
