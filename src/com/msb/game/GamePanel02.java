package com.msb.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel02 extends JPanel{
    //定义计时器
    Timer timer;
    //定义远动方向
    String direction;
    //障碍物
    int[][] obstacle = new int[7][7];
}
