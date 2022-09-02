package com.msb.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GamePanel02 extends JPanel{
    //定义计时器
    Timer timer;
    //定义远动方向
    String direction;
    //障碍物
    int[][] obstacle = new int[7][7];
    //得分
    int score;
    //存放鼠标点击的点
    ArrayList points;
    boolean isStart = false;

    public void init(){
        //障碍物输出
        Random rand = new Random();
        int num_ob = rand.nextInt(6,8);
        while(true){
            int create_ob = rand.nextInt(0,49);
            if(obstacle[create_ob / 7][create_ob % 7] == 0) {
                num_ob--;
                obstacle[create_ob / 7][create_ob % 7] = 1;
            }
            if(num_ob == 0)
                break;
        }
    }

    public GamePanel02(){
        init();
        this.setFocusable(true);
        points = new ArrayList<>();
        //监听鼠标的动作
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                //判断初始放置位置
                if(e.getX()== 90 && e.getY() == 80){

                }
            }

        });

        //对定时器初始化
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        timer.start();


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(33, 199, 178));
        g.setColor(new Color(250, 250, 250));
        g.fillRect(10,70,770,685);



        if(isStart == false){
            g.setColor(new Color(219, 20, 20));
            //字体，加粗，字号
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("点击开始游戏",250,330);
        }


        g.setColor(new Color(221, 236, 6));
        g.setFont(new Font("微软雅黑",Font.BOLD,20));
        g.drawString("选择一个通道",120,40);
        //积分
        g.setColor(new Color(221, 236, 6));
        g.setFont(new Font("微软雅黑",Font.BOLD,20));
        g.drawString("积分："+score,620,40);


    }

}
