package org.example;

import javax.swing.*;

public class Window extends JFrame {//

    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;
    private Program program;


    public Window() {
        program=new Program();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("WhatsappBOT");
        this.add(program);
        this.program.setBounds(0, 0, WIDTH, HEIGHT);
        program.requestFocus();
    }

}
