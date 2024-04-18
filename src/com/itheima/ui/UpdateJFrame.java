package com.itheima.ui;

import domain.Daily;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class UpdateJFrame extends JFrame implements ActionListener {
    //定义标题输入框
    JTextField titleText = new JTextField();

    //定义内容的输入区域
    JTextArea contentText = new JTextArea();

    //定义修改按钮
    JButton update = new JButton("修改");

    //定义取消按钮
    JButton cancel = new JButton("取消");
    //修改的行数
    private int row;

    public UpdateJFrame(int row) {
        this.row = row;

        //初始化界面
        initFrame();

        //初始化组件
        initView();

        //让界面展示出来
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == update) {
            System.out.println("修改按钮被点击了");
            File file = new File("D:\\ProgramData\\diary\\src\\save\\save.txt");
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                ArrayList<Daily> list = (ArrayList<Daily>) ois.readObject();
                Daily daily = list.get(row);
                daily.setTitle(titleText.getText());
                daily.setContent(contentText.getText());
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(list);
                oos.close();
                ois.close();
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        } else if (obj == cancel) {
            System.out.println("取消按钮被点击了");
        }
        this.setVisible(false);
        new NoteJFrame();
    }

    private void initView() {
        //读取原有的数据
        Daily daily = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\ProgramData\\diary\\src\\save\\save.txt"));
            ArrayList<Daily> list = (ArrayList<Daily>) ois.readObject();
            daily = list.get(row);
            ois.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //定义最上面的每日一记
        JLabel title = new JLabel("每日一记");
        title.setBounds(220, 20, 584, 50);
        title.setFont(new Font("宋体", Font.BOLD, 32));
        this.getContentPane().add(title);

        //定义文字：标题
        JLabel subject = new JLabel("标题");
        subject.setBounds(70, 90, 100, 30);
        subject.setFont(new Font("宋体", Font.PLAIN, 16));
        this.getContentPane().add(subject);

        //定义文字：内容
        JLabel content = new JLabel("内容");
        content.setBounds(70, 140, 100, 30);
        content.setFont(new Font("宋体", Font.PLAIN, 16));
        this.getContentPane().add(content);


        //设置标题的输入框
        titleText.setBounds(120, 90, 426, 30);
        titleText.setFont(new Font("宋体", Font.PLAIN, 16));
        titleText.setText(daily.getTitle());
        this.getContentPane().add(titleText);

        //设置内容的输入框
        contentText.setBounds(120, 140, 426, 300);
        contentText.setFont(new Font("宋体", Font.PLAIN, 16));
        contentText.setText(daily.getContent());
        this.getContentPane().add(contentText);

        //设置保存按钮
        update.setBounds(132, 466, 140, 40);
        update.setFont(new Font("宋体", Font.PLAIN, 24));
        update.addActionListener(this);
        this.getContentPane().add(update);

        //设置取消按钮
        cancel.setBounds(312, 466, 140, 40);
        cancel.setFont(new Font("宋体", Font.PLAIN, 24));
        cancel.addActionListener(this);
        this.getContentPane().add(cancel);

    }


    //对添加界面的一些设置
    private void initFrame() {
        //设置界面的宽高
        this.setSize(600, 600);
        //设置界面的标题
        this.setTitle("每日一记（添加日记）");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        //设置背景颜色
        this.getContentPane().setBackground(new Color(212, 212, 212));
    }
}
