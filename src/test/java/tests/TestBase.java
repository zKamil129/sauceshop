package tests;

import appmanager.ApplicationManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {
    ApplicationManager app = new ApplicationManager();

    @BeforeClass
    public void setup() {
        app.init();
    }

    @AfterClass
    public void tearDown() {
        app.stop();
    }
}
