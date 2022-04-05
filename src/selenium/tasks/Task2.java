package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class Task2 {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");


    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all fields are empty and no tick are clicked
        assertEquals("", driver.findElement(By.id("fb_name")).getText());
        assertEquals("", driver.findElement(By.id("fb_age")).getText());
        List<WebElement> languageList = driver.findElements(By.className("w3-check"));
        for (WebElement language : languageList) {
            assertFalse(language.isSelected());
        }
        assertFalse(driver.findElements(By.name("gender")).get(0).isSelected());
        assertFalse(driver.findElements(By.name("gender")).get(1).isSelected());
//         "Don't know" is selected in "Genre"
        assertTrue(driver.findElements(By.name("gender")).get(2).isSelected());
//         "Choose your option" in "How do you like us?"
        WebElement dropDown = driver.findElement(By.id("like_us"));
        Select dropDownSelect = new Select(dropDown);
        List<WebElement> allSelections;
        allSelections = dropDownSelect.getAllSelectedOptions();
        assertEquals(1, allSelections.size());
        assertEquals("Choose your option", allSelections.get(0).getText());
        assertEquals("", driver.findElement(By.name("comment")).getText());
//         check that the button send is blue with white letters
        assertEquals("rgba(33, 150, 243, 1)", driver.findElement(By.tagName("button")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.tagName("button")).getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.tagName("button")).click();
//         check fields are empty or null
        List<WebElement> resultFields = driver.findElements(By.className("description"));
        for (WebElement result : resultFields) {
            assertNull(result.getAttribute("value"));
        }
//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        String name = "John";
        String age = "22";
        driver.findElement(By.id("fb_name")).sendKeys(name);
        driver.findElement(By.id("fb_age")).sendKeys(age);
        driver.findElement(By.xpath("//*[@value='English']")).click();
        driver.findElement(By.xpath("//*[@value='male']")).click();
        WebElement dropDown = driver.findElement(By.id("like_us"));
        Select dropDownSelect = new Select(dropDown);
        dropDownSelect.selectByValue("Ok, i guess");
        driver.findElement(By.name("comment")).sendKeys("Have a nice day!");
        driver.findElement(By.tagName("button")).click();
//         check fields are filled correctly
        assertEquals(name, driver.findElement(By.id("name")).getText());
        assertEquals(age, driver.findElement(By.id("age")).getText());
        assertEquals("English", driver.findElement(By.id("language")).getText());
        assertEquals("male", driver.findElement(By.id("gender")).getText());
        assertEquals("Ok, i guess", driver.findElement(By.id("option")).getText());
        assertEquals("Have a nice day!", driver.findElement(By.id("comment")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        driver.findElement(By.id("fb_name")).sendKeys("Jonas");
//         click "Send"
        driver.findElement(By.tagName("button")).click();
//         click "Yes"
        driver.findElement(By.className("w3-green")).click();
//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, Jonas, for your feedback!", driver.findElement(By.id("fb_thx")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.id("message")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.id("message")).getCssValue("color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.tagName("button")).click();
//         click "Yes"
        driver.findElement(By.className("w3-green")).click();
//         check message text: "Thank you for your feedback!"
        assertEquals("Thank you for your feedback!", driver.findElement(By.id("fb_thx")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.id("message")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.id("message")).getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        String name = "Ona";
        String age = "22";
        driver.findElement(By.id("fb_name")).sendKeys(name);
        driver.findElement(By.id("fb_age")).sendKeys(age);
        driver.findElement(By.xpath("//*[@value='English']")).click();
        driver.findElement(By.xpath("//*[@value='female']")).click();
        WebElement dropDown = driver.findElement(By.id("like_us"));
        Select dropDownSelect = new Select(dropDown);
        dropDownSelect.selectByValue("Ok, i guess");
        driver.findElement(By.name("comment")).sendKeys("Have a nice day!");
//         click "Send"
        driver.findElement(By.tagName("button")).click();
//         click "No"
        driver.findElement(By.className("w3-red")).click();
//         check fields are filled correctly
        assertEquals(name, driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals(age, driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertTrue(driver.findElement(By.xpath("//*[@value='English']")).isSelected());
        assertTrue(driver.findElement(By.xpath("//*[@value='female']")).isSelected());
        assertEquals("Ok, i guess", driver.findElement(By.id("like_us")).getAttribute("value"));
        assertEquals("Have a nice day!", driver.findElement(By.name("comment")).getAttribute("value"));
    }
}
