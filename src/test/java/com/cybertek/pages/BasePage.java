package com.cybertek.pages;

import com.cybertek.utils.Driver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    //make sure Logger imported from here: import org.apache.log4j.Logger;
    private final static Logger logger = Logger.getLogger(BasePage.class);

    public BasePage() {
        //AppiumFieldDecorator required for Appium
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getDriver()), this);
    }

    // either we declare a logger per class
    //import org.apache.logging.log4j.LogManager;
    //import org.apache.logging.log4j.Logger;
    /*
    <dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.14.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.14.0</version>
     */
}
