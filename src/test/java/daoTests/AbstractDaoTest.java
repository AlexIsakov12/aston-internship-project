package daoTests;

import init.DatabaseInitializer;
import org.junit.After;
import org.junit.Before;

public abstract class AbstractDaoTest {
    private DatabaseInitializer databaseInitializer;

    @Before
    public void setUp() {
        databaseInitializer = new DatabaseInitializer();
        databaseInitializer.init();
    }

    @After
    public void tearDown() {
        databaseInitializer.drop();
    }
}
