package org.example;




import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Program extends JPanel {

    private Image background;
    private JButton loginButton;
    private JLabel success;
    private Window window;

    public Program(Window window) {
        this.window = window;
        this.setLayout(null);
        addBackgroundPicture();
        addByLine();
        loginProcess();
        repaint();
    }

    public void addBackgroundPicture() {
        try {
            background=ImageIO.read(new File("src/main/resources/wahtsapp2.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addByLine() {
        JLabel by = new JLabel("@By Avihay Navon, David Even-Haim, Omer Hayoon, Avihay Ben-Ami,Idan Zakheym AAC-CS 2023");
        by.setBounds(3, 530, 800, 40);
        by.setFont(new Font("Arial", Font.BOLD, 14));
        by.setVisible(true);
        by.setForeground(Color.WHITE);
        this.add(by);
        success = new JLabel();
        success.setText("");
        success.setForeground(Color.GREEN);
        success.setBounds(575, 460, 800, 40);
        success.setFont(new Font("Arial", Font.BOLD, 14));
        success.setVisible(true);
        this.add(success);
    }

    public void loginProcess() {
        this.loginButton = new JButton("Log on WhatApp");
        this.loginButton.setBounds(545, 80, 175, 50);
        this.loginButton.setVisible(true);
        this.add(loginButton);
        this.loginButton.addActionListener(e -> {
            window.openChrome();
            addSuccessLogin();
        });
    }
    public void addSuccessLogin() {
        success.setText("Login Succeed!");
        this.window.createTextBox();
    }
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}

