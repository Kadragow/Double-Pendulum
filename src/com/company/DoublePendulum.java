package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

public class DoublePendulum extends JPanel implements ActionListener {

    Timer timer;
    static double g = 1;

    int radius1 = 100;
    int radius2 = 200;
    int mass1 = 20;
    int mass2 = 20;
    double angle1 = Math.PI/2;
    double angle2 = Math.PI/2;
    double velocity_angle1 = 0;
    double velocity_angle2 = 0;

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
        timer = new Timer(1,this);
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
        double O1_up = -g * (2*mass1 + mass2) * Math.sin(angle1)
                - mass2 * g * Math.sin(angle1 - 2 * angle2)
                - 2 * Math.sin(angle1-angle2) * mass2
                * ((velocity_angle2 * velocity_angle2 * radius2) + (velocity_angle1 * velocity_angle1 * radius1 * Math.cos(angle1 - angle2)));
        double O1_down = radius1 * (2*mass1 + mass2 - mass2 * Math.cos(2*angle1- 2*angle2));
        double O1 = O1_up/O1_down;

        double O2_up = 2*Math.sin(angle1 - angle2) * (velocity_angle1 * velocity_angle1 * radius1 * (mass1 + mass2)
                + g * (mass1 + mass2) * Math.cos(angle1)
                + velocity_angle2 * velocity_angle2 * radius2 * mass2 * Math.cos(angle1 - angle2));
        double O2_down = radius2 * (2*mass1 + mass2 - mass2 * Math.cos(2*angle1- 2*angle2));
        double O2 = O2_up/O2_down;

        velocity_angle1 += O1;
        velocity_angle2 += O2;
        angle1 += velocity_angle1;
        angle2 += velocity_angle2;

        double new_x1 = radius1 * Math.sin(angle1);
        double new_y1 = radius1 * Math.cos(angle1);

        double new_x2 = new_x1 + radius2 * Math.sin(angle2);
        double new_y2 = new_y1 + radius2 * Math.cos(angle2);

        x_1 = (int)new_x1;
        y_1 = (int)new_y1;
        x_2 = (int)new_x2;
        y_2 = (int)new_y2;

        //velocity_angle1 *= .999;
        //velocity_angle2 *= .999;

        p1 = new Point(x_2 + 400,y_2 + 400);
        if(p2!=null)
            line2d = new Line2D.Double(p1,p2);
        p2 = p1;

        repaint();
    }
}
