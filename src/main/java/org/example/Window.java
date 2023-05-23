package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {//
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Program program;
    private TextBox textBox;


    public Window() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("WhatsappBOT");
        createProgram();
    }

    public void createProgram() {
        program = new Program(this);
        this.add(program);
        program.setBounds(0, 0, WIDTH, HEIGHT);
        program.setVisible(true);

    }

    public void createTextBox() {
        this.textBox = new TextBox(this);
        this.add(textBox);
        this.textBox.setBounds(520, 200, 200, 300);
        this.textBox.setVisible(true);
        this.textBox.requestFocus();
    }

}
