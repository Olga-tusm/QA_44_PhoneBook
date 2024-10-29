package tests;

import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddPage;
import pages.ContactPage;
import pages.HomePage;
import pages.LoginPage;
import utils.HeaderMenuItem;

import static pages.BasePage.clickButtonsOnHeader;

public class DeleteContactTests extends ApplicationManager {
    UserDto user = new UserDto("qa_mail@mail.com", "Qwerty123!");
    ContactPage contactPage;

    @BeforeMethod(alwaysRun = true)
    public void login() {
        new HomePage(getDriver());
        LoginPage loginPage = clickButtonsOnHeader(HeaderMenuItem.LOGIN);
        contactPage = loginPage.typeLoginForm(user).clickBtnLoginPositive();
    }

    @Test(groups = "smoke")
    public void deleteContactPositiveTest() {
        int quantityBeforeDelete = contactPage.getContactNumber();
        System.out.println("--> " + quantityBeforeDelete);
        contactPage.clickFirstElementOfContactsList();
        contactPage.clickBtnRemoveContact();
        int quantityAfterDelete = contactPage.getContactNumber();
        System.out.println("--> " + quantityAfterDelete);
        Assert.assertEquals(quantityBeforeDelete - 1, quantityAfterDelete);
    }
    @Test
    public void deleteContactPositiveTest_validateQuantityListContact_lambdaExp(){
        int quantityBeforeDelete = contactPage.getQuantityListContact();
        System.out.println("quantityBeforeDelete-->"+quantityBeforeDelete);
        contactPage.clickFirstElementOfContactsList();
        contactPage.clickBtnRemoveContact();
        //System.out.println("--> "+contactPage.getQuantityListContact());
        Assert.assertTrue(contactPage.isElementDeleteFromContactList(quantityBeforeDelete));
    }
}
