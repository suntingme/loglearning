import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

public class LogUtil {

//    public static void logInit(){
//        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
//
//        LayoutComponentBuilder layoutBuilder = builder.newLayout("PatternLayout").addAttribute("pattern", "[%-5p] %d{yyyy-MM-dd HH:mm:ss,SSS} %t method:%l%n%m%n");
//
//        ComponentBuilder triggeringPolicy = builder.newComponent("Policies")
//                .addComponent(builder.newComponent("CronTriggeringPolicy").addAttribute("schedule", "0 0 0 * * ?"))
//                .addComponent(builder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", "100M"));
//
//
//        FilterComponentBuilder threadFilter = builder.newFilter("RegexFilter", Filter.Result.ACCEPT, Filter.Result.DENY);
//        threadFilter.addAttribute("regex", "INFO");
//
//        AppenderComponentBuilder fileAppenderBuilder = builder.newAppender("rolling", "RollingFile");
//        fileAppenderBuilder.addAttribute("fileName", "target/rolling.log");
//        fileAppenderBuilder.addAttribute("filePattern", "target/archive/rolling-%d{MM-dd-yy}.log.gz");
//        fileAppenderBuilder.add(layoutBuilder);
//        fileAppenderBuilder.add(threadFilter);
//        fileAppenderBuilder.addComponent(triggeringPolicy);
//        builder.add(fileAppenderBuilder);
//
//        FilterComponentBuilder errorFilter = builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.DENY);
//        threadFilter.addAttribute("level", "ERROR");
//        AppenderComponentBuilder errorAppenderBuilder = builder.newAppender("error", "RollingFile");
//        errorAppenderBuilder.addAttribute("fileName", "target/error.log");
//        errorAppenderBuilder.addAttribute("filePattern", "target/archive/error-%d{MM-dd-yy}.log.gz");
//        errorAppenderBuilder.add(layoutBuilder);
//        errorAppenderBuilder.add(errorFilter);
//        errorAppenderBuilder.addComponent(triggeringPolicy);
//
//        builder.add(errorAppenderBuilder);
//
//        builder.add( builder.newRootLogger( Level.DEBUG )
//                .add( builder.newAppenderRef( "rolling" ) )
//                .add( builder.newAppenderRef( "error" ) )
//        );
//
//        Configurator.initialize(builder.build());
//    }

    public static Logger agentLogger = null;

    static {
        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();

        LayoutComponentBuilder layoutBuilder = builder.newLayout("PatternLayout").addAttribute("pattern", "%d [%t] %-5level: %msg%n");

        ComponentBuilder triggeringPolicy = builder.newComponent("Policies")
                .addComponent(builder.newComponent("CronTriggeringPolicy").addAttribute("schedule", "0 0 0 * * ?"))
                .addComponent(builder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", "100M"));


        FilterComponentBuilder threadFilter = builder.newFilter("RegexFilter", Filter.Result.ACCEPT, Filter.Result.DENY);
//        threadFilter.addAttribute("regex", ".*INFO.*");
        threadFilter.addAttribute("level", "INFO");


        AppenderComponentBuilder fileAppenderBuilder = builder.newAppender("rolling", "RollingFile");
        fileAppenderBuilder.addAttribute("fileName", "target/rolling.log");
        fileAppenderBuilder.addAttribute("filePattern", "target/archive/rolling-%d{MM-dd-yy}.log.gz");
        fileAppenderBuilder.add(layoutBuilder);
        fileAppenderBuilder.add(threadFilter);
        fileAppenderBuilder.addComponent(triggeringPolicy);
        builder.add(fileAppenderBuilder);

        FilterComponentBuilder errorFilter = builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.DENY);
        errorFilter.addAttribute("level", "ERROR");
        AppenderComponentBuilder errorAppenderBuilder = builder.newAppender("error", "RollingFile");
        errorAppenderBuilder.addAttribute("fileName", "target/error.log");
        errorAppenderBuilder.addAttribute("filePattern", "target/archive/error-%d{MM-dd-yy}.log.gz");
        errorAppenderBuilder.add(layoutBuilder);
        errorAppenderBuilder.add(errorFilter);
        errorAppenderBuilder.addComponent(triggeringPolicy);

        builder.add(errorAppenderBuilder);

        builder.add( builder.newRootLogger( Level.INFO )
                .add( builder.newAppenderRef( "rolling" ) )
                .add( builder.newAppenderRef( "error" ) )
        );

        Configurator.initialize(builder.build());
        agentLogger = LogManager.getLogger(LogUtil.class);
        agentLogger.info("test  agentLogger");
        agentLogger.error("test error agentLogger");
    }

    public static Logger getLogger(Class c){
        return LogManager.getLogger(c);
    }

}