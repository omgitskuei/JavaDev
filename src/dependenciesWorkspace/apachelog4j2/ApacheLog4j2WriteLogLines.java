package dependenciesWorkspace.apachelog4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;



//@Plugin(name = "log4j2-test.xml", category = "ConfigurationFactory")
public class ApacheLog4j2WriteLogLines {
    
    private static final Logger logger = LogManager.getLogger(ApacheLog4j2WriteLogLines.class);
    
    //String[] args = {"-Dlog4j.configurationFile=./dependenciesWorkspace/apachelog4j2/log4j2.xml"};
    
    public static void main(String[] args) {
        
        // -Dlog4j.configurationFile=./dependenciesWorkspace/apachelog4j2/log4j2.xml
    	
        ThreadContext.put("filterLevel", "TRACE");
        
    	// System.setProperty("log4j.configurationFile", new File("./dependenciesWorkspace/apachelog4j2", File.separatorChar+"log4j.xml").toURI().toString());
    	 
        // priority = Trace < Debug < Info < Warn < Error < Fatal
        logger.trace("> > ApacheLog4j2WriteLogLines START < <");
        logger.trace("entered a trace log line");
        logger.debug("entered a debug log line");
        logger.debug("entered a SECOND debug log line");
        logger.info("entered a info log line");
        logger.warn("entered a warn log line");
        logger.error("entered a error log line");
        logger.warn("entered a SECOND warn log line");
        logger.error("entered a SECOND error log line");
        logger.trace("> > ApacheLog4j2WriteLogLines END < <");
    }

}
