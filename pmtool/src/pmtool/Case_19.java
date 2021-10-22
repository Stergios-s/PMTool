package pmtool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Case_19 {

	public static void main(String[] args) {
		// Step 0: Invoke chromedriver.exe file from your drive needed from Chrome
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\stergios\\Downloads\\Java_and_Selenium_course_files\\browser_drivers\\chromedriver.exe");
						
		// Step 1: Create driver object for Chrome browser
		WebDriver driver = new ChromeDriver();
					
		// Hit URL in browser
		driver.get("https://node-fs-app.herokuapp.com/");
		// Add a small delay before giving inputs
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		    System.out.println(e);
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
		LocalDateTime current_date_time = LocalDateTime.now();  
		String formateddatetime = dtf.format(current_date_time);
		
		// Register a new account
		driver.findElement(By.id("signup")).click();
				
		driver.findElement(By.id("fullName")).sendKeys("John Doe"+ formateddatetime);
		// Sign up form accepts upper case in email but requires lower case in login
		driver.findElement(By.id("email")).sendKeys("John_D" + formateddatetime + "@in.gr");
		driver.findElement(By.id("address")).sendKeys("Konstantinou Karamanli " + formateddatetime);
		driver.findElement(By.id("password")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button[name='action']")).click();
		
		try {
		    Thread.sleep(1500);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
		String expected_text = "Successfull registration, login to start using PPMTool";
		String sunny_day_text = driver.findElement(By.xpath("//*[@class='row']/div/div/div/p")).getText();
		//System.out.println(sunny_day_text);
		
		if (sunny_day_text.equals(expected_text)) {
			System.out.println("Registration was successful");
		} else {
			System.out.println("Registration failed");
		}
		
		
		// Login to account
		driver.findElement(By.id("login")).click();				

		// Form requires email to be lower case
		driver.findElement(By.id("email")).sendKeys("john_d" + formateddatetime + "@in.gr");
		driver.findElement(By.id("password")).sendKeys("123456");
		
		try {
		    Thread.sleep(200);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		driver.findElement(By.cssSelector("button[name='action']")).click();
		
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}	
		
		//Create and view first project 
		driver.findElement(By.xpath("//*[text()='Create']")).click();
		driver.findElement(By.id("name")).sendKeys("Project 1");
		driver.findElement(By.id("description")).sendKeys("This is project number 1");
		driver.findElement(By.cssSelector("button[name='action']")).click();
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}		

		expected_text = "Project 1";
		String extracted_text = driver.findElement(By.xpath("//*[@class='card-title']")).getText();
		
		if (extracted_text.equals(expected_text)) {
			System.out.println("Creation of project 1 was successful");
		} else {
			System.out.println("Project 1 creation failed");
		}
		
		try {
		    Thread.sleep(400);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}	
		
		//Create and view second project 
		driver.findElement(By.xpath("//*[text()='Create']")).click();
		driver.findElement(By.id("name")).sendKeys("Project 2");
		driver.findElement(By.id("description")).sendKeys("This is project number 2");
		driver.findElement(By.cssSelector("button[name='action']")).click();
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}		

		expected_text = "Project 2";
		extracted_text = driver.findElement(By.xpath("//*[text()='" + expected_text + "']")).getText();
		
		if (extracted_text.equals(expected_text)) {
			System.out.println("Creation of project 2 was successful");
		} else {
			System.out.println("Project 2 creation failed");
		}
		
		try {
		    Thread.sleep(400);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
		
		//Local variable initialization
		String in_prog_task_summ = "";
		String in_prog_task_desc = "";
		String upload_file_name = "";
		String label_tags = "";
		String current_field_value = "";
		expected_text = "This field is required";
				
		
		//Add Task 1 to Project 1 
		driver.findElement(By.id("btn_add_task")).click();
		try {
		    Thread.sleep(400);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
	
		driver.findElement(By.id("description")).sendKeys("This is task #1");
		//Set status "In progress"
		driver.findElement(By.className("select-wrapper")).click();
		driver.findElement(By.xpath("(//*[@class='select-wrapper']/ul/li)[2]")).click();
		
		//In labels set "Backend", "CI", "Testing"
		driver.findElement(By.id("multiselectContainerReact")).click();
		driver.findElement(By.xpath(("((//*[@id='multiselectContainerReact']/div)[2]/ul/li)[1]"))).click();
		driver.findElement(By.xpath(("((//*[@id='multiselectContainerReact']/div)[2]/ul/li)[4]"))).click();
		driver.findElement(By.xpath(("((//*[@id='multiselectContainerReact']/div)[2]/ul/li)[6]"))).click();
		
		//Upload file
		WebElement uploadElement = driver.findElement(By.id("attachments"));

        //Enter the file path
        uploadElement.sendKeys("C:\\Users\\stergios\\Desktop\\Workable_Assigment\\dummy_text.txt");
        
        
		//Attempt #1 (Summary is missing)
        driver.findElement(By.cssSelector("button[name='action']")).click();
		
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
		current_field_value = driver.findElement(By.xpath("//*[@id='summary']/following-sibling::p")).getText();
		
		if (current_field_value.equals(expected_text)) {
			System.out.println("Succesfull scenario when summary of task is missing");
		} else {
			System.out.println("Scenario failed! Mandatory field message is not printed when summary of project is missing");
		}
		
		//Attempt #2 (Description is missing)
		driver.findElement(By.id("summary")).sendKeys("Task #1");
		driver.findElement(By.id("description")).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
        driver.findElement(By.cssSelector("button[name='action']")).click();
		
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
		current_field_value = driver.findElement(By.xpath("//*[@id='description']/following-sibling::p")).getText();
				
		if (current_field_value.equals(expected_text)) {
			System.out.println("Succesfull scenario when description of task is missing");
		} else {
			System.out.println("Scenario failed! Mandatory field message is not printed when description of project is missing");
		}
		
        
		//Attempt #3 (Both Summary and Description are missing)
		driver.findElement(By.id("summary")).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		driver.findElement(By.id("description")).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        driver.findElement(By.cssSelector("button[name='action']")).click();
		
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
		//Check error message for summary
		current_field_value = driver.findElement(By.xpath("//*[@id='summary']/following-sibling::p")).getText();
				
		if (current_field_value.equals(expected_text)) {
			System.out.println("Succesfull scenario when summary of task is missing");
		} else {
			System.out.println("Scenario failed! Mandatory field message is not printed when summary of project is missing");
		}
		
		
		//Check error message for description
		current_field_value = driver.findElement(By.xpath("//*[@id='description']/following-sibling::p")).getText();
		
		if (current_field_value.equals(expected_text)) {
			System.out.println("Succesfull scenario when description of task is missing");
		} else {
			System.out.println("Scenario failed! Mandatory field message is not printed when description of project is missing");
		}
		
		// Teardown
		driver.close(); 
		driver.quit();
	}

}
