import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class Main {

    private String propWebDriver() {

        Properties property = new Properties();
        String browser = "";

        try {
            InputStream fis = getClass().getClassLoader().getResourceAsStream("config.properties");
            property.load(fis);

            browser = property.getProperty("browser.name");

        } catch (IOException e) {
            System.err.println("Property file not found");
        }

        return browser;
    }

    public WebDriver getDriver() {

        String browser = propWebDriver();
        WebDriver driver = null;
        int c = 0;

        if (browser.toLowerCase().equals("firefox"))
            c = 1;
        else if (browser.toLowerCase().equals("ie") || browser.toLowerCase().equals("internetexplorer"))
            c = 2;
        else if (browser.toLowerCase().equals("chrome") || browser.toLowerCase().equals("googlechrome"))
            c = 3;

        switch (c) {
            case 1:
                driver = new FirefoxDriver();
                break;
            case 2:
                System.setProperty("webdriver.ie.driver", getClass().getClassLoader().getResource("IEDriverServer.exe").getPath());
                driver = new InternetExplorerDriver();
                break;
            case 3:
                System.setProperty("webdriver.chrome.driver", getClass().getClassLoader().getResource("chromedriver.exe").getPath());
                driver = new ChromeDriver();
                break;
            default:
                break;
        }

        return driver;
    }
}
