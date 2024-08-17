package com.mavenstore.utilities;

import java.io.File;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListenerClass implements ITestListener {

	//WebDriver ldriver;

	/*
	 * public ExtentListenerClass(WebDriver rdriver) {
	 * 
	 * ldriver = rdriver;
	 * 
	 * PageFactory.initElements(rdriver, this); // create constructor of class
	 * HomePage }
	 */

	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;

	public void configureReport() {

		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		String reportName = "MyStoreTestReport-" + timestamp + ".html";
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//Reports//" + reportName);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);

		ReadConfig readConfig = new ReadConfig();

		reports.setSystemInfo("System Name: ", getSystemName());
		reports.setSystemInfo("Browser: ", readConfig.getBrowser());
		reports.setSystemInfo("OS Name: ", System.getProperty("os.name"));

		htmlReporter.config().setDocumentTitle("Test Report Maven Store");
		htmlReporter.config().setReportName("Test Report Maven Store");
		htmlReporter.config().setTheme(Theme.STANDARD);
	}

	@Override
	public void onTestStart(ITestResult result) {

		System.out.println("Test " + result.getName() + " started.");
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		System.out.println("Test " + result.getName() + " passed.");
		test = reports.createTest(result.getName());
		test.log(Status.PASS,
				MarkupHelper.createLabel("Name of the testcase passed is: " + result.getName(), ExtentColor.GREEN));
		
	}

	@Override
	public void onTestFailure(ITestResult result) {

		System.out.println("Test " + result.getName() + " failed.");
		test = reports.createTest(result.getName());
		test.log(Status.FAIL,
				MarkupHelper.createLabel("Name of the testcase failed is: " + result.getName(), ExtentColor.RED));
		test.log(Status.FAIL,result.getThrowable().toString());

		/*
		 * TakesScreenshot ss = (TakesScreenshot) ldriver;
		 * 
		 * File src = ss.getScreenshotAs(OutputType.FILE);
		 * 
		 * File dest = new File(System.getProperty("user.dir") +
		 * "\\Reports\\" + result.getName() + ".png");
		 * 
		 * try { FileUtils.copyFile(src, dest); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		String ssPath = System.getProperty("user.dir") + "\\screenshots\\" + result.getName() + ".png";
		File ssFile = new File(ssPath);
		if (ssFile.exists()) {
			test.fail("Captured screeshot is displayed: " + test.addScreenCaptureFromPath(ssPath));
		}
		// test.addScreenCaptureFromPath("");
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		System.out.println("Test " + result.getName() + " skipped.");
		test = reports.createTest(result.getName());
		test.log(Status.FAIL,
				MarkupHelper.createLabel("Name of the testcase skipped is: " + result.getName(), ExtentColor.YELLOW));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {

		configureReport();
		System.out.println("onStart method invoked...");
	}

	@Override
	public void onFinish(ITestContext context) {

		System.out.println("onFinish method invoked...");
		reports.flush();
	}

	public String getSystemName() {

		String systemName = null;

		try {
			systemName = InetAddress.getLocalHost().getHostName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return systemName;
	}

	/*
	 * public void captureScreenShotReport(WebDriver driver, String testName) throws
	 * IOException {
	 * 
	 * TakesScreenshot ss = (TakesScreenshot) driver;
	 * 
	 * File src = ss.getScreenshotAs(OutputType.FILE);
	 * 
	 * File dest = new File(System.getProperty("user.dir") +
	 * "\\Reports\\" + testName + ".png");
	 * 
	 * FileUtils.copyFile(src, dest); }
	 */

}
