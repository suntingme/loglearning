import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class FileLogTest {

    //参考 https://www.baeldung.com/log4j2-programmatic-config

    public static void main(String[] args){

        FileLogSub fileLogSub = new FileLogSub();
        fileLogSub.test();

        Logger logger=LogUtil.getLogger(FileLogTest.class);
        logger.error("HELLO_WORLD");
        logger.info("INFO");
        logger.debug("testdebug");
    }
}