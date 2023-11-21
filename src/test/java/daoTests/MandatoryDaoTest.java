package daoTests;

import org.junit.Before;
import org.junit.Test;
import org.project.dao.implementation.MandatoryDaoImpl;
import org.project.entity.Mandatory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MandatoryDaoTest extends AbstractDaoTest {
    private MandatoryDaoImpl mandatoryDao;

    @Before
    public void setUp() {
        super.setUp();
        mandatoryDao = new MandatoryDaoImpl();
    }

    @Test
    public void testFindById() {
        Long mandatoryId = 300L;
        Mandatory mandatory = mandatoryDao.findById(300L);

        assertNotNull(mandatory);
        assertEquals(mandatoryId, mandatory.getId());
        assertEquals("Necessary", mandatory.getDescription());
    }

    @Test
    public void testFindAll() {
        List<Mandatory> mandatoryList = mandatoryDao.findAll();

        assertNotNull(mandatoryList);
        assertEquals(3, mandatoryList.size());
    }

    @Test
    public void testSave() {
        Mandatory mandatory = Mandatory.builder().id(303L).description("IDGAF").build();
        mandatoryDao.save(mandatory);

        Mandatory retrievedMandatory = mandatoryDao.findById(303L);
        assertNotNull(retrievedMandatory);
        assertEquals("IDGAF", retrievedMandatory.getDescription());
    }

    @Test
    public void testUpdate() {
        Long mandatoryId = 300L;
        Mandatory mandatory = mandatoryDao.findById(300L);
        mandatory.setDescription("New Mandatory");
        mandatoryDao.update(mandatory);

        Mandatory updatedMandatory = mandatoryDao.findById(mandatoryId);
        assertNotNull(updatedMandatory);
        assertEquals("New Mandatory", updatedMandatory.getDescription());
    }
}
