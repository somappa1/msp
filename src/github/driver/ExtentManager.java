package github.driver;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
    private static ExtentReports extent;
    
    public synchronized static ExtentReports getReporter(String filePath) {
            extent = new ExtentReports(filePath);
            
            extent
                .addSystemInfo("Host Name", "Prathiksha")
                .addSystemInfo("Environment", "GitTests"); 
           
        return extent;
    }
}
