import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class demoMobile {

    public String username = System.getenv("LT_USERNAME");
    public String accesskey = System.getenv("LT_ACCESS_KEY");
    public RemoteWebDriver driver;
    public String gridURL = "hub.lambdatest.com";
    String status;
    String hub;
    SessionId sessionId;


    @org.testng.annotations.Parameters(value = {"browser", "platformVersion", "platform", "deviceName"})
    @BeforeMethod
    public void setUp(String browser, String platformVersion, String platform, String deviceName) throws Exception {
        try {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("name", "Mobile");
            capabilities.setCapability("build", "MobileDemo");
            capabilities.setCapability("deviceName", deviceName);
            capabilities.setCapability("platformVersion", platformVersion);
            capabilities.setCapability("platform", platform);
            capabilities.setCapability("network", true);
            capabilities.setCapability("console", true);
            capabilities.setCapability("visual", true);

            StopWatch driverStart = new StopWatch();
            driverStart.start();

            hub = "https://" + username + ":" + accesskey + "@" + gridURL + "/wd/hub";
            driver = new RemoteWebDriver(new URL(hub), capabilities);

            sessionId = driver.getSessionId();
            System.out.println(sessionId);
            driverStart.stop();
            float timeElapsed = driverStart.getTime() / 1000f;
            System.out.println("Driver initiate time" + "   " + timeElapsed);
            ArrayList<Float> TotalTimeDriverSetup = new ArrayList<Float>();
            TotalTimeDriverSetup.add(timeElapsed);
            System.out.println(TotalTimeDriverSetup);


        } catch (
                MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception f) {
            System.out.println(f);

        }

    }

    @Test
    public void DesktopScript() {
        try {
            ToDo test = new ToDo();
            test.ToDo(driver, status);
        } catch (Exception e) {

            System.out.println(e);
            status = "failed";
        }
    }


    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}

