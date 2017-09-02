package gesture;

import com.nakal.ScreenExecutor.NakalExecutor;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.im4java.core.IM4JavaException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class GestureTest extends BaseUserTest {

    public void waitForPageToLoad(String element) throws InterruptedException, IOException, IM4JavaException {
        new WebDriverWait(driver,30).until(ExpectedConditions.
                elementToBeClickable(MobileBy.AccessibilityId(element)));
        new NakalExecutor().nakalExecutorNativeCompare(element);
    }

    @Test
    public void dragAndDrop() throws InterruptedException, IOException, IM4JavaException {
        waitForPageToLoad("login");
        driver.findElementByAccessibilityId("login").click();
        waitForPageToLoad("dragAndDrop");
        driver.findElementByAccessibilityId("dragAndDrop").click();
        waitForPageToLoad("dragMe");
    }
}
