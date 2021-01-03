package com.cybertek.pages;

import com.cybertek.utils.ConfigurationReader;
import com.cybertek.utils.MobileUtils;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;


public class LoginPage extends  BasePage{

    @AndroidFindBy(id = "com.etsy.android:id/btn_link")
    private AndroidElement getStartedBtn;
    //private By getStartedBy = MobileBy.id("com.etsy.android:id/btn_link");
    // creating by objects and using them would be better for wait issues
    @AndroidFindBy(id = "com.etsy.android:id/edit_username")
    private AndroidElement emailInputInbox;

    @AndroidFindBy(id = "com.etsy.android:id/edit_password")
    private AndroidElement passwordElement;

    @AndroidFindBy(id = "com.etsy.android:id/button_signin")
    private AndroidElement signInButton;

    public void clickGetStarted(){
     MobileUtils.clickWithWait(getStartedBtn);
    }

    public void login(){
        String email = ConfigurationReader.getProperty("email");
        String password = ConfigurationReader.getProperty("password");
        login(email, password);
    }

    public void login(String email, String password){
        MobileUtils.waitFor(2000);
        emailInputInbox.sendKeys(email);
        passwordElement.sendKeys(password);
        signInButton.click();
    }
}
