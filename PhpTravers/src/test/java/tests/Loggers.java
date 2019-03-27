package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.relevantcodes.extentreports.LogStatus;

public class Loggers {
    private static Logger log = LogManager.getLogger(Loggers.class.getName());

    public static void info(String message) {
        log.info(message);
        ExtentTestManager.getTest().log(LogStatus.INFO, "", message);
    }
    public static void error(String message) {
        log.error(message);
        ExtentTestManager.getTest().log(LogStatus.FAIL, message, "error");
    }
    public static void warn(String message) {
        log.warn(message);
        ExtentTestManager.getTest().log(LogStatus.WARNING,message, "warning");
    }
    public static void fatal(String message) {
        log.fatal(message);
        
        ExtentTestManager.getTest().log(LogStatus.FATAL,message, "error");
    }

}