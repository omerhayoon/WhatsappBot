package org.example;

import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.openqa.driver",
                "C:\\Users\\עומר\\Downloads\\chromedriver_win32");
        ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.get("https://web.whatsapp.com/");
        chromeDriver.manage().window().maximize();

    }
}