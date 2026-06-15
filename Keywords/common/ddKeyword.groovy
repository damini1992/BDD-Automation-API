package common 
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
 
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
 
class ddKeyword {
 
   @Keyword
def getAllDropdownOptions(TestObject optionsObject) {
 
    List<WebElement> options = WebUI.findWebElements(optionsObject, 10)
		
    String values = ""
 
    for(WebElement option : options) {
		println("step3")
        values.add(option.getText())
		
 
    }
 
    return values
}
 
  /*  @Keyword
    def selectDropdownByText(TestObject dropdownObject,
                             String value) {
 
        WebElement element = DriverFactory
                .getWebDriver()
                .findElement(
                    org.openqa.selenium.By.xpath(
                        dropdownObject.findPropertyValue("xpath")
 
        Select selt = new Select(element)
 
        selt.selectByVisibleText(value)
 
        println("Selected : " + value)
    }*/
 }