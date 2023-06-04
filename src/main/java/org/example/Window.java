package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Window extends JFrame {//
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Program program;
    private TextBox textBox;
    private ChromeDriver chromeDriver;
    private WebElement search = null;
    private Integer count = 0;
    private int messageStatus;
    private boolean isSent;
    private WebElement lastMessage;
    private boolean isRead;
    private JLabel status;



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
        setMessageStatus("1234");
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
            JOptionPane.showMessageDialog(null, "This number is not in your contacts", "Message", JOptionPane.INFORMATION_MESSAGE);
            chromeDriver.get("https://web.whatsapp.com/send/?phone=" + phoneNumber);
            rest(7000);

        }
        WebElement temp = chromeDriver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p"));
        temp.sendKeys(textMessage);// הכנסת המידע בדו שיח בתיבת טקסט
        temp.sendKeys(Keys.ENTER);

        System.out.println("first try");
        new Thread(() -> {
            rest(1000);
            deliveredMessage(chromeDriver);
        }).start();
        System.out.println("Worked!!");
//            JOptionPane.showMessageDialog(null, "Message was sent Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);

    }

    public void openChrome() {
        System.setProperty("webdriver.openqa.driver",
                "C:\\Users\\עומר\\Downloads\\chromedriver_win32");// נתב לגישה למחלקת כרום
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


    //
    public String getLast(ChromeDriver chromeDriver) {
        String lastMessage = null;
        List<WebElement> list = null;
        List<WebElement> divElements = null;
        try {
            divElements = chromeDriver.findElements(By.cssSelector("div[role='row']"));
            WebElement lastDivElement = divElements.get(divElements.size() - 1);
            list = lastDivElement.findElements(By.cssSelector("span[data-icon='msg-dblcheck']"));
            lastMessage = list.get(list.size() - 1).getAccessibleName();
        } catch (Exception e) {
            return lastMessage;
        }
        return lastMessage;
    }

    public void deliveredMessage(ChromeDriver chromeDriver) {
        boolean delievered = false;
        boolean read = false;
        boolean sent = false;
        while (!read) {
            List<WebElement> list = chromeDriver.findElements(By.cssSelector("span[data-icon='msg-dblcheck']"));
            System.out.println(list.get(list.size() - 1).getAccessibleName());
            try {
                String lastMessage = getLast(chromeDriver);
                if (lastMessage.contains("נשלחה") && !sent && !delievered) {
                    this.status.setText("Successfully Sent!");

                    delievered = true;
                }
                if (lastMessage.contains("נמסרה") && !sent) {
                    System.out.println(lastMessage + "נמסרה");
                    this.status.setText("Successfully Delivered!");
                    sent = true;
                }
                if (lastMessage.contains("נקראה")) {
                    this.status.setText("Successfully Read!");
                    read = true;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public void rest(int number) {
        try {
            Thread.sleep(number);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void popMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
    public void setMessageStatus(String status){
        this.status = new JLabel();
        this.status.setText("");
        this.status.setForeground(Color.BLACK);
        this.status.setBounds(400, 300, 800, 40);
        this.status.setFont(new Font("Arial", Font.BOLD, 14));
        this.status.setVisible(true);
        this.add(this.status);
    }

}
