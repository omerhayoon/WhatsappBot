package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class Program extends JPanel {

    private Image background;
    private JPanel panelText;
    private JPanel panelLogin;
    private boolean resultLogin;
    private JLabel sucsess;
    private ChromeDriver chromeDriver;

    public Program() {
        // addByLine();
        this.add(loginProcess());
        this.add(addText());
        repaint();
        //addBackgroundPicture();
        //this.add(addText());
        // this.panelText.setVisible(resultLogin);
        // this.panelText.requestFocus();
        // if(resultLogin) {
        //  loginProcess().setVisible(false);
    }

    public void addBackgroundPicture() {
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResource("wahtsapp2.jpg")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addByLine() {
        JLabel by = new JLabel("@By Avihay Navon, David Even-Haim, Omer Hayoon, Avihay Ben-Ami, AAC-CS 2023");
        by.setBounds(3, 470, 800, 40);
        by.setFont(new Font("Arial", Font.BOLD, 14));
        by.setVisible(true);
        by.setForeground(Color.BLACK);
        this.add(by);
    }

    public void addSuccessLogin() {
        System.out.println("addSuccessLogin method is stating");
        sucsess = new JLabel();
        sucsess.setText("Login Successed!");
        sucsess.setForeground(Color.GREEN);
        sucsess.setBounds(3, 170, 800, 40);
        sucsess.setFont(new Font("Arial", Font.BOLD, 14));
        sucsess.setVisible(true);
        this.add(sucsess);
        repaint();
    }

    public JPanel loginProcess() {
        panelLogin = new JPanel();
        panelLogin.setLayout(new GridLayout(1, 1, 10, 10));
        JButton log = new JButton("Log on WhatApp ");
        panelLogin.add(log);
        log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openChrome();
                addSuccessLogin();
            }
        });

        return panelLogin;
    }

    public JPanel addText() {
        panelText = new JPanel();
        panelText.setVisible(true);
        panelText.setLayout(new GridLayout(3, 2, 10, 10));
        //panelText.setBounds(20, 50, 200, 200);
        JLabel message = new JLabel("Message: ");
        JTextField messageTF = new JTextField();
        JLabel phoneNumber = new JLabel("Phone Number: ");
        JTextField phoneNumberTF = new JTextField();
        JButton print = new JButton("Print ");
        panelText.add(message);
        panelText.add(messageTF);
        panelText.add(phoneNumber);
        panelText.add(phoneNumberTF);
        panelText.add(print);
        panelText.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Inserting content"),
                BorderFactory.createEmptyBorder(10, 5, 2, 5)));
//        panelText.setVisible(resultLogin);
        print.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (resultLogin) {
                    //print.setText("Print");
                    System.out.println("message : " + messageTF.getText());
                    do {
                        System.out.println("phoneNumber : " + phoneNumberTF.getText());
                        if (!validPhone(phoneNumberTF.getText())) {
                            JOptionPane.showMessageDialog(null, "The number you entered is incorrect ", "Wrong Phone",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } while (!validPhone(phoneNumberTF.getText()));

                } else {
                    JOptionPane.showMessageDialog(null, "Need to log in ", "Wrong ",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        return panelText;
    }

    public boolean validPhone(String phoneNum) {
        boolean result = false;

        if (phoneNum.startsWith("050") && (phoneNum.length() == 10)) {
            phoneNum = "050-" + phoneNum.substring(phoneNum.length() - 7);
        }
        if (phoneNum.startsWith("97250") && (phoneNum.length() == 12)) {
            phoneNum = "050-" + phoneNum.substring(phoneNum.length() - 7);
        }
        if (phoneNum.startsWith("050-") && (phoneNum.length() == 11)) {
            phoneNum = "050-" + phoneNum.substring(phoneNum.length() - 7);
        } else {
            phoneNum = "False number, please reenter a new number";
        }
        return result;
    }

    public void openChrome() {
        System.setProperty("webdriver.openqa.driver",
                "C:\\Users\\עומר\\Downloads\\chromedriver_win32");// נתב לגישה למחלקת כרום
        chromeDriver = new ChromeDriver();// יתירת משתנה כרום
        chromeDriver.get("https://web.whatsapp.com/");// פותח קישור
        chromeDriver.manage().window().maximize();// לפתוח בחלון מלא
        WebElement searchBox = null;
        while (true) {
            try {
                searchBox = chromeDriver.findElement(By.xpath("//*[@id=\"side\"]/div[1]/div/div/div[2]/div/div[1]/p"));
                if (searchBox != null) {
                    break;
                }
            } catch (Exception e) {
            }
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}

