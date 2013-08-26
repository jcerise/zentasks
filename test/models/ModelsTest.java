package models;

import models.*;
import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;

public class ModelsTest extends WithApplication{

    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }

    @Test
    public void createAndRetrieveUser() {
        new User("jeremy@readmythings.com", "Jeremy", "secret").save();
        User jeremy = User.find.where().eq("email", "jeremy@readmythings.com").findUnique();
        assertEquals("Jeremy2", jeremy.name);
    }

    @Test
    public void tryAuthenticateUser() {
        new User("jeremy@readmythings.com", "Jeremy", "secret").save();
        assertNotNull(User.authenticate("jeremy@readmythings.com", "secret"));
        assertNull(User.authenticate("jeremy@readmythings.com", "badpassword"));
        assertNull(User.authenticate("fake@fake.com", "secret"));
    }
}
