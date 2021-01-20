package kosmos1.assignment.qa.testcases;

import kosmos1.assignment.qa.base.TestBase;
import kosmos1.assignment.qa.pages.MyPediaFunctionPage;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyPediaUITest extends TestBase{
	MyPediaFunctionPage allWeatherStategy;
	
	public MyPediaUITest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		allWeatherStategy = new MyPediaFunctionPage();
		allWeatherStategy.popClose();
		
	}
	
	/*	Confirm that language dropdown has at least 3 languages 
	 *	Auto-Select different languages and validate that the label of the [CONTINUE] button changes to selected language.
	 **/
	 @Test(priority=0)
		public void dropDownCheck() throws InterruptedException
		{
		 allWeatherStategy.verifyDD();
		 allWeatherStategy.langChnage();
		 
			
		}
	    
		 	
	 	/*Click on "setup parent support" -> Create a new account. Fill all the details to create an account. Make this data driven so it could be executed multiple times. 
	 	*Assert that "create account" button is disabled till all fields are filled.
	 	**/
	 	@Test(priority = 1)
	 	public void createAccount() throws Exception{
	 		allWeatherStategy.setupParent();
	 		allWeatherStategy.createAccount();
	 		allWeatherStategy.fillRegistration();
	 	}
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	
	

}
