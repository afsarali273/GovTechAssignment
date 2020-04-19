package govtech.frameworkcore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DriverFactory {

    private static DriverFactory instance = null;
    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    private DriverFactory() {

    }

    public static DriverFactory getInstance() {
        if (instance == null) {
            instance = new DriverFactory();
        }
        return instance;
    }

    public final void setDriver(String BROWSER) throws Exception {

        DesiredCapabilities caps = null;

        if(BROWSER.equals("Mozilla")){
            driver.set(new FirefoxDriver());
           // Log.info("New driver instantiated");
        }else if(BROWSER.equalsIgnoreCase("Chrome")){
            //java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
            driver.set(new ChromeDriver(DesiredCapabilitiesFactory.getDesiredCapabilities(BrowserType.chrome)));
        }

    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public  void clearCurrentDriver() {

        System.out.println("Current thread removed and clearcurrentDriver() called.. ..");
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }

        driver.remove();
    }
}
