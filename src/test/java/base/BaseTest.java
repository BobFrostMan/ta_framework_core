package base;

import com.foggger.core.config.Config;
import com.foggger.core.config.ConfigProvider;
import com.foggger.core.config.PropertyFileReader;
import com.foggger.core.helper.IDataGenerator;
import com.foggger.domain.steps.holders.SiteStepsHolder;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;

public class BaseTest implements IDataGenerator {

    protected Config config;
    protected SiteStepsHolder site;

    protected String user;
    protected String password;

    @BeforeSuite
    public void beforeTestSuite() {
        String env = System.getProperty("env", "demo");
        String url = "env" + File.separator + env + ".properties";
        ConfigProvider.init(new PropertyFileReader(), url);
        config = ConfigProvider.provide();
        site = new SiteStepsHolder(config);
        user = config.get("data.user");
        password = config.get("data.pass");
    }

    @AfterSuite
    public void cleanUp() {

    }
}
