package ConnectBot;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FilterAlgo extends BotGUI {
	static WebDriver driver;
	static String currentUrl;
	static String baseurl = "https://www.linkedin.com";
	static String loginUrl = baseurl + "/login";
	static String feedUrl = baseurl + "/feed";
	static int pgcount = 2;
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
		Scanner sc = new Scanner(System.in); // Create a Scanner object
		System.out.println(text);
		String input = null;
		input = sc.nextLine();
		// sc.close();
		return input;
	}

	public static void nextPage() {
		String nextUrl;
		nextUrl = "https://www.linkedin.com/mynetwork/invitation-manager/sent/?invitationType=&page=" + pgcount;
		navigate(nextUrl);
		System.out.println("Redirecting to " + nextUrl);
		System.out.println("Navigating to Pending Connection Page " + pgcount);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public void run(String uname, String pass, int pages, int selection) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "ChromeDriver/chromedriver");
		driver = new ChromeDriver();
		login(uname, pass);
		navigate("https://www.linkedin.com/mynetwork/invitation-manager/sent/?invitationType=&page=" + pages);
		withdraw(selection);

	}

	public void withdraw(int selection) throws InterruptedException {
		while (true) {
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(., 'Withdraw')]")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//button[contains(., 'Withdraw')]")).click();
			count++;
			System.out.println(count + "withdrawals");
		}

	}

	public FilterAlgo(String uname, String pass, String pages, int selection) throws InterruptedException {
		String username = uname;
		String password = pass;
//		int maxNum = Integer.parseInt(max);
		int pagesNum = Integer.parseInt(pages);
		run(uname, pass, pagesNum, selection);

		driver.close();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		try {
			FileWriter log = new FileWriter("ConnectLog.txt", true);
			log.write("************************************* \n");
			log.write("\n");
			log.write(formatter.format(date) + "\n");
			log.write("Number of Withdrawals: ");
			log.write(count + "\n");
			log.close();
			System.out.println("Successfully wrote to the file.");

		} catch (IOException e) {
			System.out.println("File log error occurred.");
			e.printStackTrace();
		}
	}

}
