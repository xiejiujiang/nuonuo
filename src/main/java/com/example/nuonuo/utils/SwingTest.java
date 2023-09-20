package com.example.nuonuo.utils;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SwingTest {

    public static void main(String[] args) {
        try {

            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            int x = 1/0;

        }catch (Exception e){
            e.printStackTrace();

            JFrame jFrame = new JFrame("江哥的第一个Swing测试");
            Container container = jFrame.getContentPane(); //获取面板容器

            JPanel jPanel = new JPanel();//创建面板panel 并初始化
            container.add(jPanel);//将面板添加到当前窗口
            jPanel.setLayout(new FlowLayout());//设置布局管理器

            final JLabel jLabel = new JLabel();//创建便签 label 并初始化
            jPanel.add(jLabel);

            JButton jButton = new JButton("按钮啊");
            jPanel.add(jButton);



            //对按钮事件 进行处理
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jLabel.setText("这是什么玩意？？？");
                    JTable jTable = new JTable(15,15);
                    jTable.setRowHeight(50);


                    TableCellEditor tableCellEditor = jTable.getCellEditor(7,7);
                    Component component = tableCellEditor.getTableCellEditorComponent(jTable,true,true,7,7);
                    jTable.setComponentZOrder(component,1);


                    jPanel.setBackground(Color.RED);
                    jPanel.setBounds(2,2,10,10);

                    //jPanel.add(jTable);
                }
            });

            //窗口设置结束，开始显示
            jFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    System.exit(0);
                }
            });

            jFrame.setSize(900,900);
            jFrame.setVisible(true);

        }finally {
            System.out.println("------------------------------程序结束！------------------------------");
        }
    }
}