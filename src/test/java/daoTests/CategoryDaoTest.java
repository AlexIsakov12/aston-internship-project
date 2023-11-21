package daoTests;

import org.junit.Before;
import org.junit.Test;
import org.project.dao.implementation.CategoryDaoImpl;
import org.project.entity.Category;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CategoryDaoTest extends AbstractDaoTest {
    private CategoryDaoImpl categoryDao;

    @Before
    public void setUp() {
        super.setUp();
        categoryDao = new CategoryDaoImpl();
    }

    @Test
    public void testFindById() {
        Long categoryId = 400L;
        Category category = categoryDao.findById(categoryId);

        assertNotNull(category);
        assertEquals(categoryId, category.getId());
        assertEquals("Home", category.getDescription());
    }

    @Test
    public void testFindAll() {
        List<Category> categories = categoryDao.findAll();

        assertNotNull(categories);
        assertEquals(3, categories.size());
    }

    @Test
    public void testSave() {
        Category newCategory = new Category(403L, "Work");
        categoryDao.save(newCategory);

        Category retrievedCategory = categoryDao.findById(403L);
        assertNotNull(retrievedCategory);
        assertEquals("Work", retrievedCategory.getDescription());
    }

    @Test
    public void testUpdate() {
        Long categoryId = 400L;
        Category category = categoryDao.findById(categoryId);
        category.setDescription("New Home");
        categoryDao.update(category);

        Category updatedCategory = categoryDao.findById(categoryId);
        assertNotNull(updatedCategory);
        assertEquals("New Home", updatedCategory.getDescription());
    }
}
