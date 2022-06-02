package threadlocal;

public class ThreadLocalService {

	private ThreadLocal<String> nameStorage = new ThreadLocal<>();

	public void logic(String name) {
		final String threadName = Thread.currentThread().getName();
		System.out.println("[" + threadName + "] " + "name : " + name + ", nameStorage : " + nameStorage.get());
		nameStorage.set(name);
		sleep(1000);
		System.out.println("[" + threadName + "] " + "nameStorage : " + nameStorage.get());
	}

	private void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
