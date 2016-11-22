package github.driver;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import com.google.common.base.Predicate;


public class Wait {
    private static final int FIVETHOUSAND = 5000;
    private static final int SIXTYTHOUSAND = 60000;
    private static final int THREETHOUSAND = 3000;
    private static final int THOUSAND = 1000;
    private static final int HUNDRED = 100;


    private Wait() {

    }

    public static boolean untilElementAppears(WebElement obj) {
        long maxTimeout = FIVETHOUSAND;
        return untilElementAppears(obj, maxTimeout);
    }

    public static boolean untilElementDisappears(WebElement obj) {
        long maxTimeout = FIVETHOUSAND;
        return untilElementDisappears(obj, maxTimeout);
    }

    public static boolean untilElementAppears(WebElement obj, long maxTimeout) {
        FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(obj);

        fluentWait.pollingEvery(HUNDRED, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(maxTimeout, TimeUnit.MILLISECONDS);

        try {
            fluentWait.until(new Predicate<WebElement>() {
                public boolean apply(WebElement obj) {
                    try {
                        return obj.isDisplayed();
                    } catch (NoSuchElementException
                            | StaleElementReferenceException e) {
                        return false;
                    }
                }
            });
            return obj.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException
                | TimeoutException e) {
            return false;
        }
    }

    public static boolean refreshUntilElementAppears(WebElement obj,
            long maxTimeout) {
        FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(obj);

        fluentWait.pollingEvery(THOUSAND, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(maxTimeout, TimeUnit.MILLISECONDS);

        try {
            fluentWait.until(new Predicate<WebElement>() {
                public boolean apply(WebElement obj) {
                    try {
                        DriverObject.getDriver().navigate().refresh();
                        return obj.isDisplayed();
                    } catch (NoSuchElementException
                            | StaleElementReferenceException e) {
                        return false;
                    }
                }
            });

            Wait.sleep(THOUSAND);
            DriverObject.getDriver().navigate().refresh();
            return obj.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException
                | TimeoutException e) {
            return false;
        }
    }

    

    public static boolean untilElementDisappears(WebElement obj, long maxTimeout) {
        FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(obj);

        fluentWait.pollingEvery(HUNDRED, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(maxTimeout, TimeUnit.MILLISECONDS);

        try {

            fluentWait.until(new Predicate<WebElement>() {
                public boolean apply(WebElement obj) {
                    try {
                        return !obj.isDisplayed();
                    } catch (NoSuchElementException
                            | StaleElementReferenceException e) {
                        return true;
                    }
                }
            });

            Wait.sleep(THOUSAND);
            return !obj.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException
                | TimeoutException e) {
            return true;
        }
    }

    public static boolean refreshUntilElementDisappears(WebElement obj,
            long maxTimeout) {

        FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(obj);

        fluentWait.pollingEvery(HUNDRED, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(maxTimeout, TimeUnit.MILLISECONDS);

        try {

            fluentWait.until(new Predicate<WebElement>() {
                public boolean apply(WebElement obj) {
                    try {
                        DriverObject.getDriver().navigate().refresh();
                        return !obj.isDisplayed();
                    } catch (NoSuchElementException
                            | StaleElementReferenceException e) {
                        return true;
                    }
                }
            });

            Wait.sleep(THOUSAND);
            DriverObject.getDriver().navigate().refresh();
            return !obj.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException
                | TimeoutException e) {
            return true;
        }
    }

    public static void sleep(long millis) {
            try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				
			}
       
      
    }

    public static boolean untilElementIsEnabled(WebElement obj) {
        long maxTimeout = THREETHOUSAND;
        return untilElementIsEnabled(obj, maxTimeout);
    }

    public static boolean untilElementIsDisabled(WebElement obj) {
        long maxTimeout = THREETHOUSAND;
        return untilElementIsDisabled(obj, maxTimeout);
    }

    public static boolean untilElementIsEnabled(WebElement obj, long maxTimeout) {

        boolean enabled = false;
        for (int i = 0; i < (maxTimeout / THOUSAND); i++) {
            if (obj.isEnabled()) {
                enabled = true;
                break;
            }
            sleep(THOUSAND);
        }
        return enabled;
    }

    public static boolean untilElementIsDisabled(WebElement obj, long maxTimeout) {

        boolean disabled = false;
        for (int i = 0; i < (maxTimeout / THOUSAND); i++) {
            if (!obj.isEnabled()) {
                disabled = true;
                break;
            }
            sleep(THOUSAND);
        }
        return disabled;
    }

    

    public static void oneSecond() {
        sleep(THOUSAND);
    }

    public static void threeSecond(){
    	sleep(THREETHOUSAND);
    }
    public static void smallWait() {
        sleep(FIVETHOUSAND);
    }

    public static void oneMinute() {
        sleep(SIXTYTHOUSAND);
    }

    public static void twoMinute() {
        sleep(SIXTYTHOUSAND * 2);
    }
}
