package si.iitech.web_parser;

import static org.junit.Assert.*;

import org.jsoup.nodes.Document;
import org.junit.Test;

public class WebParserTest {

	@Test
	public void testWebParser() {
		WebParser webParser = WebParser.createInstance();
		try {
			Document document = webParser.readWebSite("https://www.wikipedia.org/");
			assertNotNull(document);
		} catch (WebParserException e) {
			fail();
		}
	}
	
	@Test
	public void testWebParserFail() {
		WebParser webParser = WebParser.createInstance();
		try {
			webParser.readWebSite("aaa.ass.fs");
			fail();
		} catch (WebParserException e) {
		}
	}
}
