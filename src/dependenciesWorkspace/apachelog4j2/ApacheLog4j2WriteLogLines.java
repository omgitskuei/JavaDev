package dependenciesWorkspace.apachelog4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.Plugin;

@Plugin(name = "apacheLog4jTesterConfig", category = "ConfigurationFactory")
public class ApacheLog4j2WriteLogLines {
    
    private static final Logger logger = LogManager.getLogger(ApacheLog4j2WriteLogLines.class);
    

    
    public static void main(String[] args) {
        
        
        
        //LoggerContext.getContext().setConfiguration("./src/dependenciesWorkspace/apachelog4j2/apacheLog4jTesterConfig.xml");
        
        // priority = Trace < Debug < Info < Warn < Error < Fatal
        logger.trace("> > ApacheLog4j2WriteLogLines START < <");
        logger.trace("entered a trace log line");
        logger.debug("entered a debug log line");
        logger.info("entered a info log line");
        logger.warn("entered a warn log line");
        logger.error("entered a error log line");
        logger.trace("> > ApacheLog4j2WriteLogLines END < <");
    }

}
