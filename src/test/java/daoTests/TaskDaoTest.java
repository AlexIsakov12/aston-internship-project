package daoTests;

import org.junit.Before;
import org.junit.Test;
import org.project.dao.implementation.TaskDaoImpl;
import org.project.entity.Task;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TaskDaoTest extends AbstractDaoTest {
    private TaskDaoImpl taskDao;

    @Before
    public void setUp() {
        super.setUp();
        taskDao = new TaskDaoImpl();
    }

    @Test
    public void testFindById() {
        Long taskId = 200L;
        Task task = taskDao.findById(taskId);

        assertNotNull(task);
        assertEquals(taskId, task.getId());
        assertEquals("do_homework", task.getDescription());
    }

    @Test
    public void testFindAll() {
        List<Task> tasks = taskDao.findAll();

        assertNotNull(tasks);
        assertEquals(4, tasks.size());
    }

    @Test
    public void testSave() {
        Task task = Task.builder().id(204L).description("do_reading").build();
        taskDao.save(task);

        Task retrievedTask = taskDao.findById(204L);
        assertNotNull(retrievedTask);
        assertEquals("do_reading", retrievedTask.getDescription());
    }

    @Test
    public void testUpdate() {
        Long taskId = 200L;
        Task task = taskDao.findById(taskId);
        task.setDescription("New task");
        taskDao.update(task);

        Task updatedTask = taskDao.findById(taskId);
        assertNotNull(updatedTask);
        assertEquals("New task", updatedTask.getDescription());
    }
}
