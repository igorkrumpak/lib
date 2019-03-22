package si.iitech.lib;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Ignore
public class AbstractTest {

	@Autowired
	private TestEntityManager entityManager;
	
	protected TestEntityManager getEntityManager() {
		return entityManager;
	}
	
}
