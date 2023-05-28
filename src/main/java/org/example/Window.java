package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;

public class Window extends JFrame {//
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Program program;
    private TextBox textBox;
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
        this.textBox.setBounds(535, 160, 200, 300);
        this.textBox.setVisible(true);
        this.textBox.requestFocus();
    }

    public void openConversation(String phoneNumber, String textMessage) {
        search = chromeDriver.findElement(By.xpath("//*[@id=\"side\"]/div[1]/div/div/div[2]/div/div[1]/p"));//חיפוש איש קשר
        search.sendKeys(phoneNumber);//הכנסת מספר באיש קשר
        search.sendKeys(Keys.ENTER);// לחיצה על הכנסת איש קשר
        try {
            Thread.sleep(100);
        } catch (Exception f) {
        }

        WebElement sendMessage1 = null;
        WebElement sendMessage2 = null;
        try {
            Thread.sleep(100);
            sendMessage1 = chromeDriver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p"));//תיבת טקסט בדו שייח
        } catch (Exception e) {

        }
        try {
            Thread.sleep(100);
            sendMessage2 = chromeDriver.findElement(By.xpath("//*[@id=\"pane-side\"]/div[1]/div/span"));// כותרת לא נימצאו אנשי קשר
        } catch (Exception e) {

        }
        if (sendMessage2 != null) {
            JOptionPane.showMessageDialog(null, "this number is not at contacts", "Message", JOptionPane.INFORMATION_MESSAGE);
            WebElement temp = chromeDriver.findElement(By.xpath("//*[@id=\"side\"]/div[1]/div/div/span/button/span"));// כפתור איקס למחיקת מספר
            temp.click();
        } else {
            if (sendMessage1 != null) {
                sendMessage1.sendKeys(textMessage);// הכנסת המידע בדו שיח בתיבת טקסט
                sendMessage1.sendKeys(Keys.ENTER);
                System.out.println("first try");
                checkSentMessage();
                System.out.println("second try");
//            JOptionPane.showMessageDialog(null, "Message was sent Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void checkSentMessage() {
        new Thread(()->{
            WebElement checkV = chromeDriver.findElement(By.cssSelector("span[data-testid='msg-dblcheck']"));
            String status = checkV.getAttribute("aria-label");
            System.out.println("Good luck");
            if (status.contains("נקראה")) {
                JOptionPane.showMessageDialog(null, "The message was successfully sent and read", "Message", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Worked");
            }
        }).start();



    }
    ////*[@id="main"]/div[2]/div/div[2]/div[2]/div[10]/div/div/div/div[1]/div[1]/div[2]/div/div/span
//
//    public void checkSentMessage() {
//        WebElement blueV = null;
//        WebElement checkVV = null;
//        WebElement checkV = null;
//
//
//        while (true) {
//            try {
//                checkV = chromeDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[5]/div/div[2]/div/div[2]/div[3]/div[43]/div/div/div/div[1]/div[1]/div[2]/div/div/span"));
//                System.out.println("1 V");
//            } catch (Exception e) {
//            }
//            ////*[@id="main"]/div[2]/div/div[2]/div[3]/div[23]/div/div/div/div[1]/div[1]/div[2]/div/div/span
//            //
//            try {
//                checkVV = chromeDriver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[2]/div[3]/div[24]/div/div/div/div[1]/div[1]/div[2]/div/div/span"));
//                System.out.println("2 V");
//            } catch (Exception e) {
//            }
//            try {
//                blueV = chromeDriver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[2]/div[2]/div[10]/div/div/div/div[1]/div[1]/div[2]/div/div/span"));
//                System.out.println("B V");
//
//            } catch (Exception e) {
//            }
//            if (blueV != null) {
//                JOptionPane.showMessageDialog(null, "The message was successfully sent and read", "Message", JOptionPane.INFORMATION_MESSAGE);
//                break;
//            } else if (checkVV != null) {
//                JOptionPane.showMessageDialog(null, "The message was successfully sent and received", "Message", JOptionPane.INFORMATION_MESSAGE);
//                break;
//            } else if (checkV != null) {
//                JOptionPane.showMessageDialog(null, "The message was delivered but not received", "Message", JOptionPane.INFORMATION_MESSAGE);
//            }
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//
//
//    }


    public void openChrome() {
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
