package si.iitech.lib.parser;

import org.junit.Test;

import si.iitech.lib.AbstractJpaTest;

public class EtSourceTest extends AbstractJpaTest {

	@Test
	public void testCreateExampleSource() {
		EtExampleSource exampleSource = new EtExampleSource();
		exampleSource.setTitle("Google");
		exampleSource.setUrl("www.google.com");
		exampleSource.setParserClass(ExampleParser.class.getName());
		getEntityManager().persist(exampleSource);
	}
}
