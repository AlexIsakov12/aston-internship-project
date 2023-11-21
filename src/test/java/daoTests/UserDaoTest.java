package daoTests;

import org.junit.Before;
import org.junit.Test;
import org.project.dao.implementation.UserDaoImpl;
import org.project.entity.User;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDaoTest extends AbstractDaoTest {
    private UserDaoImpl userDao;

    @Before
    public void setUp() {
        super.setUp();
        userDao = new UserDaoImpl();
    }

    @Test
    public void testFindById() {
        Long userId = 100L;
        User user = userDao.findById(userId);

        assertNotNull(user);
        assertEquals(userId, user.getId());
        assertEquals("first_nick", user.getNickname());
    }

    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();

        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    public void testSave() {
        User user = User.builder().id(102L).nickname("third_user").build();
        userDao.save(user);

        User retrievedUser = userDao.findById(102L);
        assertNotNull(retrievedUser);
        assertEquals("third_user", retrievedUser.getNickname());
    }

    @Test
    public void testUpdate() {
        Long userId = 100L;
        User user = userDao.findById(userId);
        user.setNickname("New nickname");
        userDao.update(user);

        User updatedUser = userDao.findById(100L);
        assertNotNull(updatedUser);
        assertEquals("New nickname", updatedUser.getNickname());
    }
}
