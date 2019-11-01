package si.iitech.lib.test;

import java.util.concurrent.atomic.AtomicInteger;

public class AbstractTest {

	private static AtomicInteger atomicInteger = new AtomicInteger();

	protected String getNextCountString() {
		return String.valueOf(getNextCount());
	}

	protected int getNextCount() {
		return atomicInteger.getAndIncrement();
	}
}
