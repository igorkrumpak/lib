package si.iitech.parser;

import org.junit.Test;

import si.iitech.AbstractTest;

public class EtSourceTest extends AbstractTest {

	@Test
	public void testCreateExampleSource() {
		EtExampleSource exampleSource = new EtExampleSource();
		exampleSource.setTitle("Google");
		exampleSource.setUrl("www.google.com");
		exampleSource.setParserClass(ExampleParser.class.getName());
		getEntityManager().persist(exampleSource);
	}
}
