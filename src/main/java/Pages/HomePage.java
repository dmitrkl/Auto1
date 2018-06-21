package Pages;

import Framework.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {

    @FindBy (xpath = SEARCH_LINK_XPATH)
    WebElement search_link;

    public SearchPage navigateToSearchPage(){
        search_link.click();

        return initPage(SearchPage.class);
    }

    public static final String SEARCH_LINK_XPATH = "//a[contains(text(), 'Suche')]";

}
