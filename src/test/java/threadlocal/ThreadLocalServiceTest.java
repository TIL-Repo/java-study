package threadlocal;

import org.junit.jupiter.api.Test;

class ThreadLocalServiceTest {

	private final Service service = new Service();
	private final ThreadLocalService threadLocalService = new ThreadLocalService();

	@Test
	void testWithoutThreadLocal() throws Exception {
	    // given
		final Thread thread = new Thread(() -> service.logic("userA"));
		final Thread thread2 = new Thread(() -> service.logic("userB"));

		thread.setName("Thread-A");
		thread2.setName("Thread-B");

		// when
		thread.start();
		thread2.start();

		thread.join();
		thread2.join();
	}

	@Test
	void testWithThreadLocal() throws Exception {
		// given
		final Thread thread = new Thread(() -> threadLocalService.logic("userA"));
		final Thread thread2 = new Thread(() -> threadLocalService.logic("userB"));

		thread.setName("Thread-A");
		thread2.setName("Thread-B");

		// when
		thread.start();
		Thread.sleep(2000);
		thread2.start();

		thread.join();
		thread2.join();
	}
}