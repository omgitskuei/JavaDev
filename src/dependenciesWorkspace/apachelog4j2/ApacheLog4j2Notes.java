package dependenciesWorkspace.apachelog4j2;

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

@SuppressWarnings("unused")
public class ApacheLog4j2Notes {

    /**
     *  SOURCES:
     *  
     *  Quick guide
     *  https://www.baeldung.com/log4j2-programmatic-config
     *  
     *  List of Appenders
     *  https://logging.apache.org/log4j/2.x/manual/appenders.html
     *  
     *  Development history & context, Logging Library Compatibility
     *  https://www.marcobehler.com/guides/a-guide-to-logging-in-java
     *  
     *  Comparing Log4j (1, not 2) to Logback
     *  http://logback.qos.ch/reasonsToSwitch.html
     *  
     *  Further reading on JBoss Logging, another logging bridge, famous for its internationalization
     *  https://docs.jboss.org/hibernate/orm/4.3/topical/html/logging/Logging
     *  
     *  Configuration
     *  http://logging.apache.org/log4j/2.x/manual/configuration.html
     *  
     *  Log4j2 Configuration XML examples
     *  https://howtodoinjava.com/log4j2/log4j-2-xml-configuration-example/
     *  
     *  Advanced:
     *  *********
     *  Masking sensitive data
     *  https://objectpartners.com/2017/09/26/masking-sensitive-data-in-log4j-2/
     *  https://stackoverflow.com/questions/16775253/how-masking-of-sensitive-data-is-achieved-using-slf4j-framework
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
        
        /**
         * Log Files Conventions
         * An application will normally log to more than one log file, having separate files for separate use cases.
         * Maybe you have both an error.log (with ERROR and WARN statements, consumed by monitoring and alerting
         * system and by operations staff) and an info.log files (with details about application progress or user activity)
         */
        // FileAppender appender writes to the File named in the fileName parameter
        AppenderComponentBuilder fileAppender = builder.newAppender("log", "File");
        fileAppender.addAttribute("fileName", "JavaDevApacheLog4jTester.log");
        builder.add(fileAppender);  
        
        /**
         * Meaningful Log lines - Proactive Help
         * WHAT exactly to write inside your log statements? 
         * it makes sense to not only log out an error message wherever you can, but also hint at possible
         * fixes/calls for action, if you can.
         */
        
        // Pattern Layout can be applied to any created appender to 'format' log lines
        LayoutComponentBuilder standard = builder.newLayout("PatternLayout");
        standard.addAttribute("pattern", "%d{yyyy-MM-dd} [%t] %-5level: %msg%n%throwable");
       
        // Add Layout to our two appenders
        consoleAppender.add(standard);
        fileAppender.add(standard);
        
        
        // Configuring the Root Logger to configure which logs will go to each destination (File or Console)
        // root logger is the highest logger, kind of like Object in Java
        
        /**
         * Log Levels
         * To which log level should you actually log to?
         * You have the choice of
         * - TRACE,
         * - DEBUG,
         * - INFO,
         * - WARN,
         * - ERROR,
         * - FATAL,
         * - ALL
         * 
         * Priority, and Frequency of log lines
         * [ALL <] Trace < Debug < Info < Warn < Error < Fatal [< OFF]
         * 
         * FATAL
         * Anything at this level means your Java process cannot continue and will now terminate.
         * You are very unlikely to use it in your application and API’s such as SLF4J don’t even support it directly.
         * 
         * ERROR
         * A request was aborted and the underlying reason requires human intervention ASAP.
         * 
         * WARN
         * A request was not serviced satisfactorily, intervention is required soon, but not necessarily immediately.
         * 
         * INFO
         * A request was not serviced satisfactorily but resolution details have been passed to the requestor
         * and no proactive support is required. Eg. "user login failed, incorrect username or password".
         * 
         * ERROR vs WARN
         * The main reasoning behind keeping ERROR and WARN tags clean is that it makes monitoring and hence reacting
         * to those events much simpler. Make sure to wake-up your operations guy at 3am in the morning for the right
         * (kind of) error.
         * 
         * INFO vs DEBUG
         * Process flow details should rather be logged with the debug level, instead of completely replicating a
         * user’s journey through your application in an info.log.
         * Historically, the main reason for logging out almost everything as INFO was that it has been difficult
         * to change log levels on the fly for applications without having to restart said application.
         * 
         * TRACE vs DEBUG
         * You could treat TRACE as a more detailed DEBUG, or for specific DEV/TEST environments only and not PROD
         * An example of a framework that uses the TRACE logging framework diligently is Spring Framework;
         * Spring’s transaction management will display database transaction boundaries when you enable the TRACE logging level
         * EG.
         * 2018-08-01 05:05:00,031 TRACE - Getting transaction for [com.marcobehler.BitcoinApp.mine]
         * ... etc.
         * 2018-08-01 05:05:00,142 TRACE - Completing transaction for [com.marcobehler.BitcoinApp.mine]
         */
        
        // use a root logger to set the default logging level to DEBUG 
        RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.DEBUG);
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
        
        
        /**
         * Rolling File Appenders
         * While log files often convey useful information, they naturally grow bigger over time, and if
         * allowed to grow indefinitely, their size could become a problem.
         * Logging libraries address this problem using rolling file appenders, which automatically “roll”
         * or archive the current log file and resume logging in a new file when certain predefined 
         * conditions occur, thereby preventing unwanted downtime.
         * 
         * https://www.baeldung.com/java-logging-rolling-file-appenders
         */
        
        
        
        
        /**
         * Mapped Diagnostic Context (MDC) or Thread Context
         * Imagine you have a user-request coming in, that is routed to multiple, different microservices.
         * When something goes wrong, a request fails, how do you know which log lines from which microservices
         * correspond to that very request?
         * You need a generated request-id that you want to log out with every log message.
         * Somewhere in your code, in an HTTP servlet-filter, you’ll have something like this:
         *   in an HTTP servlet-filter;
         *     MDC.put("requestId", "lknwelqk-12093alks-123nlkasn-5t234-lnakmwen");
         *   logging;
         *     logger.info("Hi, my name is: Slim Shady!");
         *   log messages;
         *     [lknwelqk-12093alks-123nlkasn-5t234-lnakmwen] - Hi, my name is: Slim Shady!
         *  Then it’s very easy to correlate all corresponding log messages, you simply have to specify or search
         *  for the same request-id across all your log files or in your centralized logging server.
         */
        //
        
        
        /**
         * Masking Sensitive Information
         * In the case of Log4j2 for example this means writing a custom LogEventPatternConverter,
         * that masks log events according to your regulations.
         */
        //
        
        
        
        
        // Now that we are fully configured, let's tell Log4j2 to use our configuration:
        
        // NOTE: After this is invoked, ALL FUTURE CALLS (to Log4j2) WILL USE THIS CONFIG.
        
        // This means that we need to invoke Configurator.initialize before we make any calls to LogManager.getLogger.
        //Configurator.initialize(builder.build());


    }

}
