package si.iitech.lib.entity.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import si.iitech.lib.AbstractTest;
import si.iitech.lib.repository.ExampleRepository;


public class EtExampleTest extends AbstractTest {

	@Autowired
	private ExampleRepository exampleRepository;
	
	@Test
	public void testSaveAndLoad() {
		EtExample example = new EtExample();
		String text = "test";
		example.setText(text);
		getEntityManager().persist(example);
		
		EtExample foundExample = exampleRepository.findByText(text);
		assertNotNull(foundExample);
	}
	
}
