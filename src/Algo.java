import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Algo extends ConnectBot{
	static WebDriver driver;
	static String currentUrl;
	static String baseurl = "https://www.linkedin.com";
	static String loginUrl =  baseurl + "/login";
	static String feedUrl =  baseurl + "/feed";
	static int pgcount = 1;
	static int count = 0;
	static int limiter = 0;
	
	public static void navigate(String url) {
			driver.get(url);
	}
	
	public static void login(String username, String password) {
		navigate(loginUrl);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();
		System.out.println("Login Successful..");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	 }
	
	public static String input(String text) {
		 Scanner sc = new Scanner(System.in);  // Create a Scanner object
		 System.out.println(text);
		 String input = null;
		 input = sc.nextLine();	 
		 //sc.close();
		 return input;
	 }
	
	public static void nextPage() {
		 String currentUrl = driver.getCurrentUrl();
		 String nextUrl;
		 if(currentUrl.contains("&page=")) {
			 nextUrl = currentUrl.substring(0, currentUrl.length() - 1);
			 nextUrl = nextUrl + pgcount;
		 } else {
			 nextUrl = currentUrl + "&page="+ pgcount;

		 }
		 navigate(nextUrl);
		 System.out.println("Redirecting to " + nextUrl);
		 System.out.println("Navigating to Pending Connection Page " + pgcount);
		 driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	 }
	 
	 public static void searchPeople(String target) {
		 driver.findElement(By.className("search-global-typeahead__input")).sendKeys(target);
		 driver.findElement(By.className("search-global-typeahead__input")).sendKeys(Keys.ENTER);
		 System.out.println("Search Successful..");
		 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		 driver.findElement(By.xpath("//button[contains(string(), 'People')]")).click();
		 currentUrl = driver.getCurrentUrl();
		 System.out.println("People Tab selected..");

	 }
	 
	 public static void connect() throws InterruptedException {
		 int pglimiter = 0;
		 while(pglimiter < 11) {
			 int i = 0;
			 JavascriptExecutor jse = (JavascriptExecutor)driver;
//			 jse.executeScript("window.scrollBy(0,250)", "");
			 try{
				while(i < 3) {
					Thread.sleep(1500);
					driver.findElement(By.xpath("//button[contains(string(), 'Connect\n')]")).click();
					Thread.sleep(1500);
					driver.findElement(By.xpath("//button[contains(string(), 'Done\n')]")).click();
					System.out.println(count++ + " Connections added");
					connections = "Number of connections: " + count;
					i++;
				}
				WebElement element = driver.findElement(By.xpath("//button[contains(string(), 'Connect\n')]"));
				jse.executeScript("arguments[0].scrollIntoView(true);", element);
				pglimiter++;
			 } 
			 finally {
				 System.out.println("...");
				 pglimiter++;
			 }
		 }
	 }
	 
	 
	 public static void run(String username, String password, String target, int pageLimit) {
			System.setProperty("webdriver.chrome.driver", "ChromeDriver/chromedriver");
			driver = new ChromeDriver();
			login(username,password);
			searchPeople(target);
		
			while(limiter < pageLimit) {
				try {
					connect();
					nextPage();
				} 
				
				catch(WebDriverException e) {
					System.out.println("**ERROR**");
					System.out.println();
					e.printStackTrace();
					
					nextPage();
					continue;
				} 
				
				catch (InterruptedException e) {
					System.out.println("**ERROR**");
					System.out.println();
					e.printStackTrace();
					
					nextPage();
					continue;
				} 
				
				finally {
					System.out.println("ConnectBot refreshing ...");
					pgcount++;
					limiter ++;
				}
			}
	 }
	

	public Algo(String uname, String pass, String targ, String pages) {
		// TODO Auto-generated constructor stub
		String username = uname;
		String password = pass;
		String target = targ;
		int pageLimit = Integer.parseInt(pages);
		run(username,password,target, pageLimit);
		
		System.out.println("ConnectBot TERMINATED ...");
	}
}
