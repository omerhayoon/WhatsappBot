package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextBox extends JPanel {
    public TextBox(){
        this.setLayout(new GridLayout(3, 2, 10, 10));
        //panelText.setBounds(20, 50, 200, 200);
        JLabel message = new JLabel("Message: ");
        JTextField messageTF = new JTextField();
        JLabel phoneNumber = new JLabel("Phone Number: ");
        JTextField phoneNumberTF = new JTextField();
        JButton print = new JButton("Print ");
        this.add(message);
        this.add(messageTF);
        this.add(phoneNumber);
        this.add(phoneNumberTF);
        this.add(print);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Inserting content"),
                BorderFactory.createEmptyBorder(10, 5, 2, 5)));
//        panelText.setVisible(resultLogin);
        print.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                    //print.setText("Print");
                    System.out.println("message : " + messageTF.getText());
                    do {
                        System.out.println("phoneNumber : " + phoneNumberTF.getText());
                        if (!validPhone(phoneNumberTF.getText())) {
                            JOptionPane.showMessageDialog(null, "The number you entered is incorrect ", "Wrong Phone",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } while (!validPhone(phoneNumberTF.getText()));

                }

        });

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

}
