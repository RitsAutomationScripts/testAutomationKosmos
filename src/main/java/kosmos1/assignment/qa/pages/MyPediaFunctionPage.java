package kosmos1.assignment.qa.pages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kosmos1.assignment.qa.base.TestBase;
import kosmos1.assignment.qa.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MyPediaFunctionPage extends TestBase{
	
	//Page Factory 
	
	@FindBy(xpath ="//*[@id='spanDone']")
	public WebElement done;
	
	@FindBy(xpath ="//*[@class='accountDetailsLangDropDown']//div[starts-with(@style,'color')]")
	public WebElement dropDown;
	
	@FindBy(xpath ="//span[@role='menuitem']/div/div/div")
	public List<WebElement> lang;
	
	@FindBy(xpath ="//button[@id='SI_0005']/div/div")
	public WebElement cont;
	
	@FindBy(xpath ="//div[@class='childSupportLink']/a")
	public WebElement setUp;
	
	@FindBy(xpath ="//*[text()='CREATE A NEW ACCOUNT']")
	public WebElement createAcc;
	
	@FindBy(xpath ="//input")
	public List<WebElement> inputBox;
	
	@FindBy(xpath ="(//div[starts-with(@style,'color')])[3]")
	public WebElement locationDD;
	
	@FindBy(xpath ="//span[@role='menuitem']")
	public List<WebElement> menuItem;
	
	@FindBy(xpath ="(//span[starts-with(@style,'position')])[3]")
	public WebElement createButton;
	
	//Initializing the Page Objects:
	public MyPediaFunctionPage(){
		PageFactory.initElements(driver, this);
	}
	Actions move = new Actions(driver);
	//Actions:
		
	public void popClose(){
		//WebElement done = driver.findElement(By.id("spanDone"));
		driver.switchTo().frame(driver.findElement(By.id("contentIframe")));
		waitForElement(5);
		done.click();
		driver.switchTo().defaultContent();
	}

	public void verifyDD() {
		waitForWebElementVisible(dropDown ,TestUtil.EXPLICIT_WAIT );
		dropDown.click();
		
		if (lang.size() > 0) {
			if (lang.get(0).getText().contains("English")) {
				System.out.println("English Language option is present");
			} else {
				Assert.fail("English Language option is not preent");
			}
			if (lang.get(1).getText().contains("हिंदी")) {
				System.out.println("हिंदी Language option is present");
			} else {
				Assert.fail("Hindi Language option is not preent");

			}
			if (lang.get(2).getText().contains("Español")) {
				System.out.println("Español Language option is present");
			} else {
				Assert.fail("Español Language option is not preent");
			}
		} else {
			Assert.fail("Language option is not present =" + lang.size());
			System.out.println("Language option is not present =" + lang.size());
		}

	}

	public void langChnage() throws InterruptedException {
		
		lang.get(1).click();

		waitForElement(5);
		if (cont.getText().equalsIgnoreCase("अग्रसर रहें")) {
			System.out.println("Hindi lang for DD is validated");
		} else {
			Assert.fail("Hindi Language not change for Continue button.");
		}
	}

	public void setupParent() {
		
		waitForWebElementVisible(setUp, 10);
		setUp.click();

	}

	public void createAccount() throws InterruptedException {

		
		waitForWebElementVisible(createAcc, 10);
		createAcc.click();

	}

	public void fillRegistration() throws InterruptedException, IOException {
		waitForElement(5);
		waitForWebElementVisible(inputBox.get(0), 10);
		List<String> value = readExcel();
		if (inputBox.size() > 0) {
			inputBox.get(0).sendKeys(value.get(0));
			inputBox.get(1).sendKeys(value.get(1));
			inputBox.get(2).sendKeys(value.get(2));
			inputBox.get(4).sendKeys(value.get(3));
			inputBox.get(5).sendKeys(value.get(4));
			inputBox.get(6).sendKeys(value.get(5));

		}
		
		waitForWebElementVisible(locationDD, 10);
		move.moveToElement(locationDD);
		locationDD.click();
		menuItem.get(0).click();

		
		if (createButton.getAttribute("style").contains("opacity: 0.3")) {
			System.out
					.println("Creation button is desabled as Capcha is not filled!");
		} else {
			Assert.fail("Creation button is enabled!");
		}

	}


	public List<String> readExcel() throws IOException {
		String FILE_PATH = System.getProperty("user.dir") + "/src/main/java/kosmos1/assignment/qa/config/data.txt";
		FileReader FR = new FileReader(FILE_PATH);
		BufferedReader BR = new BufferedReader(FR);
		String Content = "";
		String[] values = null;
		List<String> records = new ArrayList<String>();
		// Loop to read all lines one by one from file and print It.
		while ((Content = BR.readLine()) != null) {
			values = Content.split(",");
		}
		records.addAll(Arrays.asList(values));

		return records;

	}
	public void waitForElement(int iSeconds) {
		try {
			Thread.sleep(iSeconds * TestUtil.SLEEP_WAIT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean waitForWebElementVisible(WebElement element,long timeout){
		try{
		(new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOf(element));
		}catch(Exception e){
			System.out.println("----- Element is not Visible -------");
			return false;
		}
		return true;
	}
}
