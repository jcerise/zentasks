package models;

import models.*;
import org.junit.*;
import java.util.List;
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

    @Test
    public void findProjectsInvolving() {
        new User("bob@gmail.com", "Bob", "secret").save();
        new User("jane@gmail.com", "Jane", "secret").save();

        Project.create("Play 2", "play", "bob@gmail.com");
        Project.create("Play 1", "play", "jane@gmail.com");

        List<Project> results = Project.findInvolving("bob@gmail.com");
        assertEquals(1, results.size());
        assertEquals("Play 2", results.get(0).name);
    }
}
