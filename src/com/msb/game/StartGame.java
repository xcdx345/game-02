package com.msb.game;

import javax.swing.*;
import java.awt.*;
import java.util.jar.JarFile;

public class StartGame {
    public static void main(String[] args){
        JFrame jf = new JFrame();
        jf.setTitle("自制小游戏");
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        jf.setBounds((width-800)/2,(height-800)/2,800,800);
        //固定窗体
        jf.setResizable(false);
        //控制程序随窗口关闭结束
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GamePanel gp = new GamePanel();
        jf.add(gp);
        //窗体不隐藏
        jf.setVisible(true);
    }
}
