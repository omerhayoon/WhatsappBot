package org.example;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {//

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Program program;
    private TextBox textBox;


    public Window() {
        program = new Program(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("WhatsappBOT");
        this.add(program);
        this.program.setBounds(0, 0, WIDTH, HEIGHT);
    }
    public void creatTextBox(){
        textBox=new TextBox();
        textBox.setVisible(true);
        this.add(textBox);
        textBox.setBounds(520,200,200,300);
        textBox.requestFocus();

    }

}
