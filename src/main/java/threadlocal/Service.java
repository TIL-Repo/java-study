package threadlocal;

public class Service {

	private String nameStorage;

	public void logic(String name) {
		final String threadName = Thread.currentThread().getName();
		System.out.println("[" + threadName + "] " + "name : " + name + ", nameStorage : " + nameStorage);
		nameStorage = name;
		sleep(1000);
		System.out.println("[" + threadName + "] " + "nameStorage : " + nameStorage);
	}

	private void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
