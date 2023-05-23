package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextBox extends JPanel {

    private String phoneNumber;
    private String textMessage;
    private Window window;

    public TextBox(Window window) {
        this.window=window;
        this.setLayout(null);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Phone number and message"),
                BorderFactory.createEmptyBorder(10, 5, 2, 5)));
        JLabel message = new JLabel("Message: ");
        JTextField messageTF = new JTextField();
        JLabel phoneNumber = new JLabel("Phone Number: ");
        JTextField phoneNumberTF = new JTextField();
        JButton sendMessage = new JButton("Send Message");
        sendMessage.setBounds(40, 240, 120, 30);
        this.add(sendMessage);

        sendMessage.addActionListener(e -> {

            if (validPhone(phoneNumberTF.getText())) {
                if (!textArea.getText().isEmpty()) {
                    System.out.println("phoneNumber : " + phoneNumberTF.getText());
                    System.out.println("message : " + textArea.getText());
                    this.phoneNumber = phoneNumberTF.getText();
                    this.textMessage = textArea.getText();
                    this.window.openConversation(this.phoneNumber,this.textMessage);


                } else {
                    JOptionPane.showMessageDialog(null, "You need to enter text ", "Wrong Message",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "The number you entered is incorrect ", "Wrong Phone",
                        JOptionPane.INFORMATION_MESSAGE);
                phoneNumberTF.setText("");
            }
        });
    }
        public boolean validPhone (String phoneNumber){
            long length = phoneNumber.length();
            if (length == 12 && phoneNumber.startsWith("9725")) {
                return true;
            }
            if (length == 11 && phoneNumber.startsWith("05") && phoneNumber.charAt(3) == '-') {
                return true;
            }
            if (length == 10 && phoneNumber.startsWith("05")) {
                return true;
            }
            return false;
        }


        public String getPhoneNumber () {
            return phoneNumber;
        }

        public String getTextMessage () {
            return textMessage;
        }

        public void paintComponent (Graphics graphics){
            super.paintComponent(graphics);
        }

}
