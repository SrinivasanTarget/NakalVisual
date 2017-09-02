package gesture;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.net.MalformedURLException;

public class BaseUserTest {
    private static AppiumDriverLocalService service;
    protected static AppiumDriver<MobileElement> driver;

    /**
     * initialization.
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .usingAnyFreePort();
        service = builder.build();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException(
                    "An appium server node is not started!");
        }
    }

    private static void androidCaps() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 700000);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UIAutomator2");
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/VodQA.apk");
        driver = new AndroidDriver<MobileElement>(service.getUrl(), capabilities);
    }

    private static void iosCaps() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability("--log-timestamp",true);
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 700000);
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/VodQAReactNative1.zip");
        driver = new IOSDriver<MobileElement>(service.getUrl(), capabilities);
    }


    @BeforeMethod
    public void launchApp() throws MalformedURLException {
      iosCaps();
    }


    @AfterMethod
    public void quitApp(){
        if (driver != null) {
            driver.quit();
        }
    }
    /**
     * finishing.
     */
    @AfterClass
    public static void afterClass() {
        if (service != null) {
            service.stop();
        }
    }
}
