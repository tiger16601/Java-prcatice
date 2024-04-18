package com.itheima.ui;

import domain.Daily;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class AddJFrame extends JFrame implements ActionListener {

    //定义标题输入框
    JTextField titleText = new JTextField();

    //定义内容的输入区域
    JTextArea contentText = new JTextArea();

    //定义保存按钮
    JButton save = new JButton("保存");

    //定义取消按钮
    JButton cancel = new JButton("取消");

    public AddJFrame() {
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
        if (obj == save) {
            System.out.println("保存按钮被点击了");
            File file = new File("D:\\ProgramData\\diary\\src\\save\\save.txt");
            //设置编号
            int num = 1;
            try {
                if (file.exists()) {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                    ArrayList<Daily> list = (ArrayList<Daily>) ois.readObject();
                    num = list.getLast().getId().charAt(2) - '0';
                    num++;
                    ois.close();
                }
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            //添加新数据到save.txt
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                ArrayList<Daily> list = (ArrayList<Daily>) ois.readObject();
                list.add(new Daily("编号" + num, titleText.getText(), contentText.getText()));
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(list);
                ois.close();
                oos.close();
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
        this.getContentPane().add(titleText);

        //设置内容的输入框
        contentText.setBounds(120, 140, 426, 300);
        contentText.setFont(new Font("宋体", Font.PLAIN, 16));
        this.getContentPane().add(contentText);

        //设置保存按钮
        save.setBounds(132, 466, 140, 40);
        save.setFont(new Font("宋体", Font.PLAIN, 24));
        save.addActionListener(this);
        this.getContentPane().add(save);

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
