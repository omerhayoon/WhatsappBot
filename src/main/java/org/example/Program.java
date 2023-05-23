package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeOptions;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class Program extends JPanel {

    private Image background;
    private JButton loginButton;
    private JLabel success;
//    private ChromeDriver chromeDriver;
    private Window window;

    public Program(Window window) {
        this.window = window;
        this.setLayout(null);
        //addBackgroundPicture();
        addByLine();
        loginProcess();
        repaint();
    }

    public void addBackgroundPicture() {
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResource("unnamed.jpg")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addByLine() {
        JLabel by = new JLabel("@By Avihay Navon, David Even-Haim, Omer Hayoon, Avihay Ben-Ami,Idan Zakheym AAC-CS 2023");
        by.setBounds(3, 530, 800, 40);
        by.setFont(new Font("Arial", Font.BOLD, 14));
        by.setVisible(true);
        by.setForeground(Color.BLACK);
        this.add(by);
    }

    public void loginProcess() {
        this.loginButton = new JButton("Log on WhatApp");
        this.loginButton.setBounds(530, 100, 175, 50);
        this.loginButton.setVisible(true);
        this.add(loginButton);
        this.loginButton.addActionListener(e -> {
            window.openChrome(null, null);
            addSuccessLogin();
        });
    }

    public void addSuccessLogin() {
        success.setText("Login Succeed!");
        this.window.createTextBox();


    }

//    public void openChrome(String phoneNumber, String textMessage) {
//        System.setProperty("webdriver.openqa.driver",
//                "C:\\Users\\עומר\\Downloads\\chromedriver_win32");// נתב לגישה למחלקת כרום
////        ChromeOptions options=new ChromeOptions();
////        options.addArguments("user-data-dir=C:\\Users\\aviha\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
////        chromeDriver = new ChromeDriver(options);// יתירת משתנה כרום
//        chromeDriver = new ChromeDriver();// יתירת משתנה כרום
//        chromeDriver.get("https://web.whatsapp.com/");// פותח קישור
//        chromeDriver.manage().window().maximize();// לפתוח בחלון מלא
//        WebElement searchBox;
//        while (true) {
//            try {
//                searchBox = chromeDriver.findElement(By.xpath("//*[@id=\"side\"]/div[1]/div/div/div[2]/div/div[1]/p"));
//                if (searchBox != null) {
//                    break;
//                }
//            } catch (Exception e) {
//            }
//        }
////        chromeDriver.get("https://api.whatsapp.com/send?phone=" + window.resultTextBox()[0]);
////        WebElement sendMessage = chromeDriver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p"));
////        sendMessage.sendKeys(window.resultTextBox()[1]);
////        sendMessage.sendKeys(Keys.ENTER);
////        System.out.println("Message was sent Successfully");
//
//    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}

