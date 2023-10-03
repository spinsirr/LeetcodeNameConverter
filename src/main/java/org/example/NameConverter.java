package org.example;

/**
 * @Author: Spencer Zhao
 * @Email: yunpenz3@uci.edu
 * @Date: 10/2/2023-10:56 PM
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NameConverter {
    
    private final JFrame f = new JFrame("leetcode name converter");
    private Container c;
    
    void window() {
        f.setBounds(400, 200, 400, 150);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c = f.getContentPane();
        c.setLayout(new FlowLayout());
    }
    
    void text() {
        JTextField jTextField = new JTextField("请输入待转换的字符串");
        jTextField.setColumns(20);
        jTextField.setFont(new Font("黑体", Font.BOLD, 20));
        jTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (jTextField.getText().equals("请输入待转换的字符串")) {
                    jTextField.setForeground(Color.BLACK);
                    jTextField.setText("");
                }
            }
            
            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (jTextField.getText().isEmpty()) {
                    jTextField.setForeground(Color.gray);
                    jTextField.setText("请输入待转换的字符串");
                }
            }
        });
        c.add(jTextField);
        
        JButton button = new JButton("转换");
        button.addActionListener(actionEvent -> {
            String s = jTextField.getText();
            String alert = converter(s);
            JOptionPane.showMessageDialog(f, alert);
            jTextField.setText("");
            jTextField.requestFocus();
        });
        c.add(button);
    }
    
    public static void main(String[] args) {
        NameConverter converter = new NameConverter();
        converter.window();
        converter.text();
        converter.f.setVisible(true);
    }
    
    private String converter(String s) {
        if (s == null) {
            return "字符串为空";
        }
        char[] sArray = s.toCharArray();
        
        int fast = 0, slow = 0;
        while (fast < sArray.length && slow < sArray.length) {
            if (sArray[fast] != ' ' && sArray[fast] != '.') {
                sArray[slow] = sArray[fast];
                slow++;
            }
            fast++;
        }
        sArray = Arrays.copyOf(sArray, slow);
        StringBuilder sb = new StringBuilder();
        for (char a : sArray) {
            if (a >= 'A' && a <= 'z') {
                sb.append(a);
            }
        }
        for (char b : sArray) {
            if (b >= '0' && b <= '9') {
                sb.append(b);
            }
        }
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(sb.toString());
        clip.setContents(tText, null);
        return "已复制到剪贴板";
    }
}