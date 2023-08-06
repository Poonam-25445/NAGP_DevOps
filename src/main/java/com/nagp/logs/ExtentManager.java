package com.nagp.logs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.nagp.core.Config;

/**
 * The Class ExtentManager.
 */
public class ExtentManager {

    private static class LazyHolder {
        private static final ExtentReports INSTANCE = createInstance(Config.ExtentReportsPath);
    }

    /**
     * Gets the single instance of ExtentManager.
     *
     * @return ExtentReports    instance of Extent Report.
     */
    public static ExtentReports getExtentReportInstance() {
        return LazyHolder.INSTANCE;
    }
    
    private ExtentManager() {
    	// Keep this constructor private to prevent ExtentManager instance creation
	}

    /**
     * Creates the instance.
     *
     * @param fileName file name.
     * @return ExtentReports    instance of Extent Report.
     * @throws IOException 
     */
    private static ExtentReports createInstance(String fileName) {
    	File f = new File(Config.ExtentReportsPath);
		/*
		 * try { Files.createDirectories(Paths.get(Config.ScreenShotsPath));
		 * f.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
		 */
		
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle(Config.ReportTitle);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(Config.ReportName);
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);

        return extentReports;
    }
}