import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileLogSub {

    private static Logger logger=LogUtil.getLogger(FileLogSub.class);

    public void test(){
        logger.info("sub INFO");
        logger.info("INFO");
    }
}