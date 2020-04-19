package govtech.frameworkcore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilitiesFactory {


    public static DesiredCapabilities getDesiredCapabilities(BrowserType browserType) {
        switch (browserType) {
            case chrome:
                return getChromeDesiredCapabilities();
            case firefox:
                return getFirefoxDesiredCapabilities();
            case edge:
                return getEdgeDesiredCapabilities();
            case internetExplorer:
                return getInternetExplorerDesiredCapabilities();
            default:
                return null;
        }
    }

    private static DesiredCapabilities getChromeDesiredCapabilities() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return desiredCapabilities;
    }

    private static DesiredCapabilities getFirefoxDesiredCapabilities() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.download.folderList", 2);
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
        firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
        firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        firefoxProfile.setPreference("browser.download.manager.focusWhenStarting", false);
        firefoxProfile.setPreference("browser.download.manager.useWindow", false);
        firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
        firefoxProfile.setPreference("browser.download.manager.closeWhenDone", false);
        firefoxProfile.setPreference("pdfjs.disabled", true);
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        desiredCapabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        return desiredCapabilities;
    }



    private static DesiredCapabilities getEdgeDesiredCapabilities() {
        return DesiredCapabilities.edge();
    }

    private static DesiredCapabilities getInternetExplorerDesiredCapabilities() {
        return DesiredCapabilities.internetExplorer();
    }
}
