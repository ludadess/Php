package tests;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.

public class ExtentManager {

  private static ExtentReports extent;

  public synchronized static ExtentReports getReporter(){
      if(extent == null){
          //Set HTML reporting file location
          String workingDir = System.getProperty("user.dir");
          String TimeDateStamp = new SimpleDateFormat("MM_dd-HH_mm_ss").format(Calendar.getInstance().getTime());
          extent = new ExtentReports(workingDir+"\\ExtentReports\\ExtentReportResults_"+TimeDateStamp+".html", true);
       }
      return extent;
  }
}