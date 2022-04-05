package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class ColorLoadingPage extends GenericSamplePage {
    @FindBy(how = How.ID, using = "start_green")
    private WebElement startGreen;
    @FindBy(how = How.ID, using = "loading_green")
    private WebElement loadingGreen;
    @FindBy(how = How.ID, using = "finish_green")
    private WebElement finishGreen;



    public void clickStartGreen() {
        startGreen.click();
    }

    public boolean startGreenDisplayed() { return startGreen.isDisplayed();}

    public boolean loadingGreenDisplayed() { return loadingGreen.isDisplayed();}

    public String loadingGreenText() { return loadingGreen.getText();}

    public boolean finishGreenDisplayed() { return finishGreen.isDisplayed();}

    public String loadedGreen() {return finishGreen.getText();}
}
