package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Window extends JFrame {//
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Program program;
    private TextBox textBox;
    private String phoneNumber;
    private String text;
    private ChromeDriver chromeDriver;
    private WebElement search = null;


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

    public void openConversation(String phoneNumber, String textMessage) {
        search = chromeDriver.findElement(By.xpath("//*[@id=\"side\"]/div[1]/div/div/div[2]/div/div[1]/p"));//חיפוש איש קשר
        search.sendKeys(phoneNumber);//הכנסת מספר באיש קשר
        search.sendKeys(Keys.ENTER);// אישור בכנסת איש קשר
        try {
            Thread.sleep(1000);
        } catch (Exception f) {
        }

        WebElement sendMessage1 = null;//תיבת טקסט בדו שייח
        WebElement sendMessage2 = null;
        try {
            Thread.sleep(1000);
            sendMessage1 = chromeDriver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p"));
        } catch (Exception e) {

        }
        try {
            Thread.sleep(1000);
            sendMessage2 = chromeDriver.findElement(By.xpath("//*[@id=\"pane-side\"]/div[1]/div/span"));
        } catch (Exception e) {

        }
        if (sendMessage2 != null) {
            JOptionPane.showMessageDialog(null, "this number is not at contacts", "Message", JOptionPane.INFORMATION_MESSAGE);
            WebElement temp = chromeDriver.findElement(By.xpath("//*[@id=\"side\"]/div[1]/div/div/span/button/span"));// כפתור איקס למחיקת מספר
            temp.click();
        } else {
            assert sendMessage1 != null;
            sendMessage1.sendKeys(textMessage);
            sendMessage1.sendKeys(Keys.ENTER);
            JOptionPane.showMessageDialog(null, "Message was sent Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    public void openChrome(String phoneNumber, String textMessage) {
        System.setProperty("webdriver.openqa.driver",
                "C:\\Users\\עומר\\Downloads\\chromedriver_win32");// נתב לגישה למחלקת כרום
//        ChromeOptions options=new ChromeOptions();
//        options.addArguments("user-data-dir=C:\\Users\\aviha\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
//        chromeDriver = new ChromeDriver(options);// יתירת משתנה כרום
        chromeDriver = new ChromeDriver();// יתירת משתנה כרום
        chromeDriver.get("https://web.whatsapp.com/");// פותח קישור
        chromeDriver.manage().window().maximize();// לפתוח בחלון מלא
        WebElement searchBox;
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
}
