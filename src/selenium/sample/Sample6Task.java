package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Sample6Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void findElementByXPath() throws Exception {
//         TODO:
//        2 ways to find text: "Heading 2 text":
        String textToFind = "Heading 2 text";
        assertEquals(textToFind, driver.findElement(By.xpath("//*[@id='heading_2']")).getText());
        assertEquals(textToFind, driver.findElement(By.xpath("//h2[contains(text(), 'Heading 2 text')]")).getText());
//        1-2 ways to find text: "Test Text 1"
        textToFind = "Test Text 1";
        assertEquals(textToFind, driver.findElement(By.xpath("//*[@id='test1']/p[@class='test']")).getText());
//        1-2 ways to find text: "Test Text 2"
        textToFind = "Test Text 2";
        assertEquals(textToFind, driver.findElement(By.xpath("//*[@class='twoTest']")).getText());
        assertEquals(textToFind, driver.findElement(By.xpath("//*[@id='test1']/p[2]")).getText());
//        1-2 ways to find text: "Test Text 3"
        textToFind = "Test Text 3";
        assertEquals(textToFind, driver.findElement(By.xpath("//*[@id='test3']/p[1]")).getText());
//        1-2 ways to find text: "Test Text 4"
        textToFind = "Test Text 4";
        assertEquals(textToFind, driver.findElement(By.xpath("//*[@id='test3']/p[2]")).getText());
        assertEquals(textToFind, driver.findElement(By.xpath("//*[@class='test' and contains(text(), '4')]")).getText());
//        1-2 ways to find text: "Test Text 5"
        textToFind = "Test Text 5";
        assertEquals(textToFind, driver.findElement(By.xpath("//*[@id='test2']/p[1]")).getText());
//        1-2 ways to find text: "This is also a button"
        textToFind = "This is also a button";
        assertEquals(textToFind, driver.findElement(By.xpath("//*[@id='buttonId']")).getAttribute("value"));
        assertEquals(textToFind, driver.findElement(By.xpath("//input[@name='randomButton2']")).getAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
        String textToFind = "Heading 2 text";
        assertEquals(textToFind, driver.findElement(By.cssSelector("#heading_2")).getText());
//        1-2 ways to find text: "Test Text 1"
        textToFind = "Test Text 1";
        assertEquals(textToFind, driver.findElement(By.cssSelector("#test1 > .test")).getText());
//        1-2 ways to find text: "Test Text 2"
        textToFind = "Test Text 2";
        assertEquals(textToFind, driver.findElement(By.cssSelector("#test1 > .twoTest")).getText());
//        1-2 ways to find text: "Test Text 3"
        textToFind = "Test Text 3";
        assertEquals(textToFind, driver.findElements(By.cssSelector("#test3 > .test")).get(0).getText());
//        1-2 ways to find text: "This is also a button"
        textToFind = "This is also a button";
        assertEquals(textToFind, driver.findElement(By.cssSelector("#buttonId")).getAttribute("value"));
        assertEquals(textToFind, driver.findElement(By.cssSelector("[name=\"randomButton2\"]")).getAttribute("value"));
        assertEquals(textToFind, driver.findElement(By.cssSelector("[name='randomButton2']")).getAttribute("value"));
    }
}
