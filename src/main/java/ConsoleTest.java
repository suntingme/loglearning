import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class ConsoleTest {

    public static void main(String[] args){
        LoggerContext context= (LoggerContext) LogManager.getContext();
        Configuration config= context.getConfiguration();

        Layout layout = PatternLayout.newBuilder()
                .withConfiguration(config).withPattern("%msg%n").build();
//        PatternLayout layout= PatternLayout.createLayout("%m%n", null, null, Charset.defaultCharset(),false,false,null,null);
        Appender appender=ConsoleAppender.createAppender(layout, null, null, "CONSOLE_APPENDER", null, null);
        appender.start();
        AppenderRef ref= AppenderRef.createAppenderRef("CONSOLE_APPENDER",null,null);
        AppenderRef[] refs = new AppenderRef[] {ref};
        LoggerConfig loggerConfig= LoggerConfig.createLogger("false", Level.INFO,"CONSOLE_LOGGER","com",refs,null,config,null);
        loggerConfig.addAppender(appender,null,null);

        config.addAppender(appender);
        config.addLogger("com", loggerConfig);
        context.updateLoggers(config);

        Logger logger=LogManager.getContext().getLogger(ConsoleTest.class.getName());
        logger.error("HELLO_WORLD");


    }
}