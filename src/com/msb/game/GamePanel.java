package com.msb.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel {

    int length;
    int[] snakeX = new int[200];
    int[] snakeY = new int[200];
    boolean isStart = false;
    //定时器
    Timer timer;
    //定义蛇方向
    String direction;
    //食物坐标
    int foodX;
    int foodY;
    //积分
    int score;
    //死亡状态判断
    boolean isDie=false;

    public void init(){
        //初始长度
        length = 3;
        //蛇头坐标
        snakeX[0] = 175;
        snakeY[0] = 275;
        //蛇身子
        snakeX[1] = 150;
        snakeY[1] = 275;
        snakeX[2] = 125;
        snakeY[2] = 275;
        //初始化蛇头方向
        direction = "D";
        //初始化食物位置
        foodX=300;
        foodY=200;
    }

    public GamePanel(){
        init();
        this.setFocusable(true);
        //监听
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                System.out.println(keyCode);
                if(keyCode == KeyEvent.VK_SPACE){//空格监听
                    if(isDie){
                        init();
                        isDie=false;
                    }else {
                        isStart = !isStart;
                        repaint();
                    }
                }
                if(keyCode == KeyEvent.VK_W){
                    direction="W";
                }
                if(keyCode == KeyEvent.VK_S){
                    direction="S";
                }
                if(keyCode == KeyEvent.VK_A){
                    direction="A";
                }
                if(keyCode == KeyEvent.VK_D){
                    direction="D";
                }
            }
        });
        //对定时器初始化
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStart&&isDie==false){
                    for(int i=length-1;i>0;i--){
                        snakeX[i]=snakeX[i-1];
                        snakeY[i]=snakeY[i-1];
                    }
                    if("D".equals(direction)){
                        snakeX[0] +=25;
                    }
                    if("A".equals(direction)){
                        snakeX[0] -=25;
                    }
                    if("W".equals(direction)){
                        snakeY[0] -=25;
                    }
                    if("S".equals(direction)){
                        snakeY[0] +=25;
                    }
                    if(snakeX[0]>750){
                        snakeX[0]=25;
                    }
                    if(snakeX[0]<25){
                        snakeX[0]=750;
                    }
                    if(snakeY[0]<75){
                        snakeY[0]=725;
                    }
                    if(snakeY[0]>725){
                        snakeY[0]=75;
                    }

                    //吃食物
                    if(snakeX[0]==foodX&&snakeY[0]==foodY){
                        length++;
                        foodX=((int)(Math.random()*30)+1)*25;
                        foodY=(new Random().nextInt(26)+4)*25;
                        score +=10;
                    }
                    //死亡
                    for(int i=1;i<length;i++){
                        if(snakeX[0]==snakeX[i]&&snakeY[0]==snakeY[i]){
                            isDie=true;
                            score=0;
                        }
                    }

                    repaint();
                }
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

        //画蛇头
        if("D".equals(direction)) {
            images.youImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if("W".equals(direction)) {
            images.shangImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if("S".equals(direction)) {
            images.xiaImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if("A".equals(direction)) {
            images.zuoImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        //蛇身子
        for(int i=1;i<length;i++) {
            images.shengImg.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        if(isStart == false){
            g.setColor(new Color(219, 20, 20));
            //字体，加粗，字号
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("点击空格开始游戏",250,330);
        }

        //食物
        images.doImg.paintIcon(this,g,foodX,foodY);

        g.setColor(new Color(221, 236, 6));
        g.setFont(new Font("微软雅黑",Font.BOLD,20));
        g.drawString("WSAD为上下左右，空格暂停",120,40);
        //积分
        g.setColor(new Color(221, 236, 6));
        g.setFont(new Font("微软雅黑",Font.BOLD,20));
        g.drawString("积分："+score,620,40);

        //画入死亡状态
        if(isDie){
            g.setColor(new Color(219, 20, 20));
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("小蛇死了，按下空格重新开始游戏",100,330);
        }
    }
}
