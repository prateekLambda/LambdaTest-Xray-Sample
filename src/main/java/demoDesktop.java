import org.apache.commons.lang.time.StopWatch;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class demoDesktop {

    public String username = System.getenv("LT_USERNAME");
    public String accesskey = System.getenv("LT_ACCESS_KEY");
    public RemoteWebDriver driver;
    public String gridURL = "hub.lambdatest.com";
    String status;
    String hub;
    SessionId sessionId;
    String buildName = null;



    @org.testng.annotations.Parameters(value = {"browser", "version", "platform"})

    @BeforeTest
    public void setUp(String browser, String version, String platform) throws Exception {
        try {
            buildName = "Demo Failed Test";
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("build", buildName);
            caps.setCapability("name", "Demo test-1");
            caps.setCapability("platform", platform);
            caps.setCapability("browserName", browser);
            caps.setCapability("version", version);
            caps.setCapability("network", true);
            caps.setCapability("visual", true);
            caps.setCapability("console", "error");
            caps.setCapability("headless", false);
            caps.setCapability("selenium_version", "3.13.0");
            //  caps.setCapability("driver_version", "3.141.59");

            StopWatch driverStart = new StopWatch();
            driverStart.start();

            hub = "https://" + username + ":" + accesskey + "@" + gridURL + "/wd/hub";
            driver = new RemoteWebDriver(new URL(hub), caps);

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

    @Test(priority = 0)
    public void DesktopScript() {
        try {
            ToDo test = new ToDo();
            test.ToDo(driver, status);
            ITestResult result = Reporter.getCurrentTestResult();
            result.setAttribute("requirement", "CALC-1234");
            result.setAttribute("test", "CALC-2");
            result.setAttribute("labels", "core addition");
        } catch (Exception e) {

            System.out.println(e);
        }
    }


    @AfterTest
    public void tearDown() throws Exception {
        if (driver != null) {

            driver.quit();
        }
    }
}

