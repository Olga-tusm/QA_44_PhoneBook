package pages;

import dto.ContactDtoLombok;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactPage extends BasePage {
    public ContactPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//a[text()='CONTACTS']")
    WebElement btnContact;
    @FindBy(xpath = "//div[@class='contact-page_leftdiv__yhyke']//div[@class='contact-item_card__2SOIM'][last()]/h3")
    WebElement lastPhoneInList;

    @FindBy(xpath = "//div[@class='contact-page_leftdiv__yhyke']//div[@class='contact-item_card__2SOIM']")
    WebElement firstContactOnList;

    @FindBy(xpath = "//button[text()='Remove']")
    WebElement btnRemoveContact;
    //================================
    @FindBy(xpath = "//button[text()='Edit']")
    WebElement btnEditContact;
    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElement inputName;
    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement inputLastName;
    @FindBy(xpath = "//input[@placeholder='Phone']")
    WebElement inputPhone;
    @FindBy(xpath = "//input[@placeholder='email']")
    WebElement inputEmail;
    @FindBy(xpath = "//input[@placeholder='Address']")
    WebElement inputAddress;
    @FindBy(xpath = "//input[@placeholder='desc']")
    WebElement inputDescription;
    @FindBy(xpath = "//button[text()='Save']")
    WebElement btnSaveContact;
    //===============================
    @FindBy(xpath = "//div[@class='contact-item-detailed_card__50dTS']/h2")
    WebElement contactCardNameLastName;
    @FindBy(xpath = "//div[@class='contact-item-detailed_card__50dTS']")
    WebElement contactCardPhone;
    @FindBy(xpath = "//div[@class='contact-item-detailed_card__50dTS']/br")
    WebElement contactCardEmail;
    @FindBy(xpath = "//div[@class='contact-item-detailed_card__50dTS']/br[last()]")
    WebElement contactCardAddress;
    @FindBy(xpath = "//div[@class='contact-item-detailed_card__50dTS']/h3")
    WebElement contactCardDescription;

    public boolean isElementContactPresent() {
        return btnContact.isDisplayed();
    }

    public boolean isLastPhoneEquals(String phone) {
        return lastPhoneInList.getText().equals(phone);
    }

    public boolean urlContainsAdd() {
        return urlContains("add", 3);
    }

    public boolean isAlertPresent(int time) {
        try {
            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(time))
                    .until(ExpectedConditions.alertIsPresent());
            System.out.println(alert.getText());
            alert.accept();
            return true;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void clickFirstElementOfContactsList() {
        firstContactOnList.click();
    }

    public void clickBtnRemoveContact() {
        btnRemoveContact.click();
    }

    public int getContactNumber() {
        pause(2);
        return driver.findElements(By.xpath("//div[@class='contact-item_card__2SOIM']")).size();
    }

    public void fillEditForm(ContactDtoLombok newContact) {
        btnEditContact.click();
        inputName.clear();
        inputName.sendKeys(newContact.getName());
        inputLastName.clear();
        inputLastName.sendKeys(newContact.getLastName());
        inputPhone.clear();
        inputPhone.sendKeys(newContact.getPhone());
        inputEmail.clear();
        inputEmail.sendKeys(newContact.getEmail());
        inputAddress.clear();
        inputAddress.sendKeys(newContact.getAddress());
        //? description
    }
    public void clickBtnSaveContact(){
        btnSaveContact.click();
    }

    public ContactDtoLombok getContactFromDetailedCard() {

    }
}
