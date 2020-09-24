package dependenciesWorkspace;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.LoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

public class ApacheLog4jTester {

    /**
     *  SOURCES:
     *  Quick guide
     *  https://www.baeldung.com/log4j2-programmatic-config
     *  List of Appenders
     *  https://logging.apache.org/log4j/2.x/manual/appenders.html
     *  Development history & context, Logging Library Compatibility
     *  https://www.marcobehler.com/guides/a-guide-to-logging-in-java
     */
    
    /**
     *  NOTE:
     *  Log4j2 can be used as a bridge (interface) with different logging implementations (SLF4J, JCL, log4j2, etc).
     *  This is why Log4j2 comes with TWO Maven dependencies (one core, and one implementation)
     *  Your code, however, will only reference the (bridge) Log4j2 classes
     *  THIS PROJECT MAVEN POM USES SLF4J IMPL
     */
    
    public static void main(String[] args) {
        
        // Create a ConfigurationBuilder to configure appenders, filters, layouts, and loggers.
        
        // Log4j 2 provides several ways to get a ConfigurationBuilder. One way is using factory;
        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
        
        
        /**
         * Appenders
         *  Tell the builder where to send each log line by instantiating and configuring Appenders: 
         *  Each Appender type corresponds to a possible place to send log lines
         *  Common ones:
         *  - Console sends to Terminal
         *  - File sends to file
         *  - HttpAppender sends via HTTP
         *  - JDBCAppender writes log events to a relational database table
         *  - SMTPAppender send log lines via e-mail
         *  - SyslogAppenders send events to a remote syslog daemon
         *  See https:logging.apache.org/log4j/2.x/manual/appenders.html for a full list of available appenders
         */
        // ConsoleAppender appender writes to either System.out (default) or System.err
        AppenderComponentBuilder consoleAppender = builder.newAppender("stdout", "Console"); // .newAppender(name, plugin)
        builder.add(consoleAppender);   // don't forget to call builder.add to append new appenders to the main configuration
        
        // FileAppender appender writes to the File named in the fileName parameter
        AppenderComponentBuilder fileAppender = builder.newAppender("log", "File");
        fileAppender.addAttribute("fileName", "JavaDevApacheLog4jTester.log");
        builder.add(fileAppender);  
        
        // Pattern Layout can be applied to any created appender to 'format' log lines
        LayoutComponentBuilder standard = builder.newLayout("PatternLayout");
        standard.addAttribute("pattern", "%d{yyyy-MM-dd} [%t] %-5level: %msg%n%throwable");
       
        // Add Layout to our two appenders
        consoleAppender.add(standard);
        fileAppender.add(standard);
        
        
        // Configuring the Root Logger to configure which logs will go to each destination (File or Console)
        // root logger is the highest logger, kind of like Object in Java
        
        // use a root logger to set the default logging level to ALL 
        RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.ALL);
        rootLogger.add(builder.newAppenderRef("stdout"));
        // set the default appender to our stdout appender
        builder.add(rootLogger);
        
        
        // Child loggers can be used to target specific packages or logger names.
        
        // add a logger, level ALL, for the dependenciesWorkspace package in our application ...
        LoggerComponentBuilder childLogger = builder.newLogger("dependenciesWorkspace", Level.ALL);
        // ... and having those log lines go to our FileAppender named 'log'
        childLogger.add(builder.newAppenderRef("log"));
        // additivity indicates whether this logger should inherit properties (eg. logging level and appender types) from its ancestors.
        childLogger.addAttribute("additivity", false);
        builder.add(childLogger);
        
        
        // NOTE: Not all components have a dedicated new method on ConfigurationBuilder
        
        // Eg. There isn't a TriggeringPolicyComponentBuilder, so we need to use newComponent for specifying our triggering policy
        @SuppressWarnings(value = "rawtypes")
        ComponentBuilder triggeringPolicies = builder.newComponent("Policies")
                                                .addComponent(builder.newComponent("SizeTriggeringPolicy")
                                                    .addAttribute("size", "100M"));
        fileAppender.addComponent(triggeringPolicies);
        
        
        // ConfigurationBuilder can print out the equivalent XML
        
        // We can double-check our configuration, or to persist our configuration, say, to the file system.
        try {
            builder.writeXmlConfiguration(System.out);
        } catch (IOException e) {
            System.out.println("Failed to Print XML");
            // e.printStackTrace();
        }
        
        
        // Now that we are fully configured, let's tell Log4j2 to use our configuration:
        
        // NOTE: After this is invoked, ALL FUTURE CALLS (to Log4j2) WILL USE THIS CONFIG.
        
        // This means that we need to invoke Configurator.initialize before we make any calls to LogManager.getLogger.
        //Configurator.initialize(builder.build());


    }

}
