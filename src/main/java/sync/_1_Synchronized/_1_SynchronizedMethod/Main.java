package sync._1_Synchronized._1_SynchronizedMethod;

public class Main {

	public static void main(String[] args) {
		A a = new A();
		Thread thread1 = new Thread(() -> {
			a.syncRun("thread1");
		});
		Thread thread2 = new Thread(() -> {
			a.syncRun("thread2");
		});
		thread1.start();
		thread2.start();
	}
}

class A {
	public void run(String name) {
		System.out.println(name + " lock");
		System.out.println(name + " unlock");
	}

	public synchronized void syncRun(String name) {
		System.out.println(name + " lock");
		System.out.println(name + " unlock");
	}
}