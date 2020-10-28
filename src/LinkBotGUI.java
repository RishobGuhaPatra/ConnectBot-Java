package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// ****
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

// ****

public class LinkBotGUI implements ActionListener {
	static Boolean execute = false;
	static JLabel connectLabel;
	static String connections = "Number of connections: ";
	static JLabel logoLabel;
	static JLabel option1, option2;
	static JComboBox dropdown;
	static JTextField pagelimit;
	JFrame frame;
	JPanel panel;
	static JButton runConnectAlgo;
	static JButton runFilterAlgo;
	JButton stop;
	static JTextField unameEntry;
	static JPasswordField passEntry;
	static JTextField targetEntry;
	static JTextField pagesEntry;
	static JLabel title;
	static JLabel connectionLabel;
	static JLabel Note1;
	static JLabel Note2;
	static JLabel Note3;
	static JLabel StartPage;
	static JButton runDtmAlgo;
	static JTextField dtmNum;

	public LinkBotGUI() {
		JFrame frame = new JFrame();
		runConnectAlgo = new JButton("Run ConnectBot");
		runFilterAlgo = new JButton("Run FilterBot");
		runDtmAlgo = new JButton("Run DTMBot");
		dtmNum = new JTextField("Number of messages from top");
		JButton stop = new JButton("Stop");
		JPanel panel = new JPanel();
		JLabel unameLabel = new JLabel("LinkedIn Username:");
		JLabel passLabel = new JLabel("LinkedIn Password:");
		JLabel Message = new JLabel("FilterBot 2020");
		unameEntry = new JTextField("rguhapatra@gmail.com");
		passEntry = new JPasswordField("Eagledtlcruise2020!");
		JLabel targetLabel = new JLabel("Search target:");
		JLabel pagesLabel = new JLabel("Pages to process:");
		targetEntry = new JTextField("Business student Atlanta");
		pagesEntry = new JTextField("5");
		connectionLabel = new JLabel("ConnectBot 2020");
		String options[] = { "Select FilterBot Setting", "Older than 2 Weeks", "Older than 1 Month" };
		dropdown = new JComboBox(options);
		title = new JLabel("LinkBot 2.0");
		title.setFont(new Font("Geneva", Font.PLAIN, 20));
		connectionLabel.setFont(new Font("Geneva", Font.PLAIN, 14));
		Message.setFont(new Font("Geneva", Font.PLAIN, 14));
		pagelimit = new JTextField("10");
		Note1 = new JLabel("See ConnectLog.txt for details");
		Note2 = new JLabel("See LinkedIn: no. of withdraws");
		Note3 = new JLabel("EXIT browser to TERMINATE");
		StartPage = new JLabel("Start page:");

		runConnectAlgo.addActionListener(this);
		runFilterAlgo.addActionListener(this);
		runDtmAlgo.addActionListener(this);
		dropdown.addActionListener(this);

		// ****** Selections : "More than 2 Weeks Old" & "More than 1 Month Old"

		Color fg1 = new Color(240, 200, 0);
		Color fg2 = new Color(168, 168, 168);
		Color bg = new Color(30, 30, 30);

		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		panel.setBackground(new Color(50, 50, 50));

		title.setForeground(fg1);
		runConnectAlgo.setBackground(fg1);
		runFilterAlgo.setBackground(fg1);
		runDtmAlgo.setBackground(fg1);
		dropdown.setBackground(fg1);
		title.setBackground(bg);
		dtmNum.setForeground(fg2);
		dtmNum.setBackground(bg);
		unameLabel.setForeground(fg1);
		pagelimit.setForeground(fg2);
		passLabel.setForeground(fg1);
		targetLabel.setForeground(fg1);
		pagesLabel.setForeground(fg1);
		unameEntry.setBackground(bg);
		pagelimit.setBackground(bg);
		passEntry.setBackground(bg);
		targetEntry.setBackground(bg);
		pagesEntry.setBackground(bg);
		unameEntry.setForeground(fg2);
		passEntry.setForeground(fg2);
		targetEntry.setForeground(fg2);
		Message.setForeground(new Color(250, 100, 0));
		pagesEntry.setForeground(fg2);
		pagesLabel.setForeground(fg1);
		connectionLabel.setForeground(new Color(250, 100, 0));
		connectionLabel.setBackground(bg);
		Note1.setForeground(new Color(0, 100, 200));
		Note2.setForeground(new Color(0, 100, 200));
		Note3.setForeground(new Color(250, 0, 0));
		StartPage.setForeground(fg1);

		panel.setLayout(new GridLayout(0, 1));
		panel.add(title);
		panel.add(connectionLabel);
//		panel.add(logoLabel);
		panel.add(unameLabel);
		panel.add(unameEntry);
		panel.add(passLabel);
		panel.add(passEntry);
		panel.add(targetLabel);
		panel.add(targetEntry);
		panel.add(pagesLabel);
		panel.add(pagesEntry);
		panel.add(runConnectAlgo);
		panel.add(Message);
		panel.add(StartPage);
//		panel.add(dropdown);
		panel.add(pagelimit);
		panel.add(runFilterAlgo);
		panel.add(dtmNum);
		panel.add(runDtmAlgo);
		panel.add(Note1);
		panel.add(Note2);
		panel.add(Note3);

		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("LinkBot 2.0");
		frame.pack();
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		new LinkBotGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == runConnectAlgo) {
			String uname = unameEntry.getText();
			String pass = String.valueOf(passEntry.getPassword());
			String targ = targetEntry.getText();
			String processPages = pagesEntry.getText();
			ConnectAlgo(uname, pass, targ, processPages);

		} else if (e.getSource() == runFilterAlgo) {
			String uname = unameEntry.getText();
			String pass = String.valueOf(passEntry.getPassword());
			String limit = pagelimit.getText();
			String processPages = pagesEntry.getText();
			int selection = dropdown.getSelectedIndex();
			try {
				FilterAlgo(uname, pass, limit, selection);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == runDtmAlgo) {
			String uname = unameEntry.getText();
			String pass = String.valueOf(passEntry.getPassword());
			String numm = dtmNum.getText();
			System.out.println(numm);
			int num = Integer.parseInt(numm);
			try {
				dtmAlgo(uname, pass, num);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

// *********************************** FILTER BOT *******************************************************************

	static WebDriver driver;
	static String filterCurrentUrl;
	static String filterBaseurl = "https://www.linkedin.com";
	static String filterLoginUrl = filterBaseurl + "/login";
	static String filterFeedUrl = filterBaseurl + "/feed";
	static int filterPgcount = 2;
	static int filterCount = 0;
	static int filterLimiter = 0;

	public static void filterNavigate(String url) {
		driver.get(url);
	}

	public static void filterLogin(String username, String password) {
		filterNavigate(filterLoginUrl);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();
		System.out.println("Login Successful..");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public static String filterInput(String text) {
		Scanner sc = new Scanner(System.in); // Create a Scanner object
		System.out.println(text);
		String input = null;
		input = sc.nextLine();
		// sc.close();
		return input;
	}

	public static void filterNextPage() {
		String nextUrl;
		nextUrl = "https://www.linkedin.com/mynetwork/invitation-manager/sent/?invitationType=&page=" + filterPgcount;
		filterNavigate(nextUrl);
		System.out.println("Redirecting to " + nextUrl);
		System.out.println("Navigating to Pending Connection Page " + filterPgcount);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public void filterRun(String uname, String pass, int pages, int selection) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "ChromeDriver/chromedriver");
		driver = new ChromeDriver();
		filterLogin(uname, pass);
		filterNavigate("https://www.linkedin.com/mynetwork/invitation-manager/sent/?invitationType=&page=" + pages);
		filterWithdraw(selection);

	}

	public void filterWithdraw(int selection) throws InterruptedException {
		while (true) {
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(., 'Withdraw')]")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//button[contains(., 'Withdraw')]")).click();
			filterCount++;
			System.out.println(filterCount + "withdrawals");
		}

	}

	public void FilterAlgo(String uname, String pass, String pages, int selection) throws InterruptedException {
		String username = uname;
		String password = pass;
//			int maxNum = Integer.parseInt(max);
		int pagesNum = Integer.parseInt(pages);
		filterRun(uname, pass, pagesNum, selection);

		driver.close();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		try {
			FileWriter log = new FileWriter("ConnectLog.txt", true);
			log.write("************************************* \n");
			log.write("\n");
			log.write(formatter.format(date) + "\n");
			log.write("Number of Withdrawals: ");
			log.write(filterCount + "\n");
			log.close();
			System.out.println("Successfully wrote to the file.");

		} catch (IOException e) {
			System.out.println("File log error occurred.");
			e.printStackTrace();
		}
	}

// ************************************ CONNECTBOT ***********************************************************

	static String connectCurrentUrl;
	static String connectBaseurl = "https://www.linkedin.com";
	static String connectLoginUrl = connectBaseurl + "/login";
	static String connectFeedUrl = connectBaseurl + "/feed";
	static int connectPgcount = 2;
	static int connectCount = 0;
	static int connectLimiter = 0;

	public static void connectNavigate(String url) {
		driver.get(url);
	}

	public static void connectLogin(String username, String password) {
		connectNavigate(connectLoginUrl);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();
		System.out.println("Login Successful..");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public static String connectInput(String text) {
		Scanner sc = new Scanner(System.in); // Create a Scanner object
		System.out.println(text);
		String input = null;
		input = sc.nextLine();
		// sc.close();
		return input;
	}

	public static void connectNextPage() {
		String currentUrl = driver.getCurrentUrl();
		String nextUrl;
		if (currentUrl.contains("&page=") && connectPgcount < 10 && connectPgcount > 1) {
			nextUrl = currentUrl.substring(0, currentUrl.length() - 1);
			nextUrl = nextUrl + connectPgcount;
		} else if (connectPgcount > 10) {
			nextUrl = currentUrl.substring(0, currentUrl.length() - 2);
			nextUrl = nextUrl + connectPgcount;
		} else {
			nextUrl = currentUrl + "&page=" + connectPgcount;
			;
		}

		connectNavigate(nextUrl);
		System.out.println("Redirecting to " + nextUrl);
		System.out.println("Navigating to Pending Connection Page " + connectPgcount);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public static void connectSearchPeople(String target) {
		driver.findElement(By.className("search-global-typeahead__input")).sendKeys(target);
		driver.findElement(By.className("search-global-typeahead__input")).sendKeys(Keys.ENTER);
		System.out.println("Search Successful..");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[contains(string(), 'People')]")).click();
		connectCurrentUrl = driver.getCurrentUrl();
		System.out.println("People Tab selected..");

	}

	public static void connectFunction() throws InterruptedException {
		int pglimiter = 0;
		while (pglimiter < 11) {
			int i = 0;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
//					 jse.executeScript("window.scrollBy(0,250)", "");
			try {
				while (i < 3) {
					Thread.sleep(1500);
					driver.findElement(By.xpath("//button[contains(string(), 'Connect\n')]")).click();
					Thread.sleep(1500);
					driver.findElement(By.xpath("//button[contains(string(), 'Send\n')]")).click();
					System.out.println(connectCount++ + " Connections added");
					connections = "Number of connections: " + connectCount;
					i++;
				}
				WebElement element = driver.findElement(By.xpath("//button[contains(string(), 'Connect\n')]"));
				jse.executeScript("arguments[0].scrollIntoView(true);", element);
				pglimiter++;
			} finally {
				System.out.println("...");
				pglimiter++;
			}
		}
	}

	public static void connectRun(String username, String password, String target, int pageLimit) {
		System.setProperty("webdriver.chrome.driver", "ChromeDriver/chromedriver");
		driver = new ChromeDriver();
		connectLogin(username, password);
		connectSearchPeople(target);

		while (connectLimiter < pageLimit) {
			try {
				connectFunction();
				connectNextPage();
			}

			catch (WebDriverException e) {
				System.out.println("**ERROR**");
				System.out.println();
				e.printStackTrace();

				connectNextPage();
				continue;
			}

			catch (InterruptedException e) {
				System.out.println("**ERROR**");
				System.out.println();
				e.printStackTrace();

				connectNextPage();
				continue;
			}

			finally {
				System.out.println("ConnectBot refreshing ...");
				++connectPgcount;
				connectLimiter++;
			}
		}
	}

	public void ConnectAlgo(String uname, String pass, String targ, String pages) {
		// TODO Auto-generated constructor stub
		String username = uname;
		String password = pass;
		String target = targ;
		int pageLimit = Integer.parseInt(pages);
		connectRun(username, password, target, pageLimit);
		driver.close();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		try {
			FileWriter log = new FileWriter("ConnectLog.txt", true);
			log.write("************************************* \n");
			log.write("\n");
			log.write(formatter.format(date) + "\n");
			log.write("Search Parameter: ");
			log.write(targ + "\n");
			log.write("Number of Connections: ");
			log.write(connectCount + "\n");
			log.write("Number of pages: ");
			log.write(pages + "\n");
			log.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("File log error occurred.");
			e.printStackTrace();
		}
		System.out.println("ConnectBot TERMINATED ...");

		System.out.println("TOTAL CONNECTIONS: " + (connectCount - 1));
	}

// ************************************ DTM BOT ***********************************************************

	public void dtmAlgo(String uname, String pass, int dtmNum) throws InterruptedException {
		String username = uname;
		String password = pass;
		
		dtmRun(username, password,dtmNum);
//				driver.close();
	}

	public static void dtmRun(String username, String password, int num) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "ChromeDriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		connectLogin(username, password);
		driver.get("https://www.linkedin.com/mynetwork/invite-connect/connections/");
//		driver.findElement(By.xpath("/html/body/div[7]/aside/div[1]/header/section[2]/button[2]/li-icon/svg")).click();
		sendMessage(num);

	}
	
	public static String firstName(String fullname) {
		String firstName = "there";
		if(fullname.contains(" ")) {
			firstName = fullname.substring(0, fullname.indexOf(" "));
		}
		return firstName;
	}

	public static void sendMessage(int num) throws InterruptedException {
		List<WebElement> boxes = driver.findElements(By.tagName("li"));
		String listPath = "/html/body/div[7]/div[3]/div/div/div/div/div/div/div/div/section/ul/li[";
		WebElement namepath;
		WebElement messagepath;
		String name;
		for (int box = 1; box <= num; box++) {
			Thread.sleep(1000);
			namepath = driver.findElement(By.xpath(listPath + box + "]/div[1]/a/span[2]"));
			messagepath = driver.findElement(By.xpath(listPath + box + "]/div[2]/div[1]/button/span[1]"));
			name = namepath.getText();
			messagepath.click();
			Thread.sleep(1000);
			WebElement chat = driver.findElement(By.xpath("/html/body/div[7]/aside/div[2]/div[1]/form/div[2]/div/div[1]/div[1]"));
			chat.sendKeys("Hey " + firstName(name) + ", thanks for connecting! ");
			chat.sendKeys("I'm working on an ecommerce entrepreneurial venture with people from a wide variety of industries. I’m looking to connect and collaborate with students and professionals. Do you have a background or interest in Business/Leadership/E-commerce");
			System.out.println("Message Sent to " + name + " : " + box + "/"+ num);
			chat.sendKeys(Keys.ENTER);
			driver.findElement(By.xpath("/html/body/div[7]/aside/div[2]/header/section[2]/button[2]")).click();

		}
	}

}
