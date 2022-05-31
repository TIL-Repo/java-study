package sync._2_Volatile;

public class Main {

	volatile boolean isLoop = true;

	public static void main(String[] args) {
		new Main().startThread();
	}

	public void startThread() {
		final Thread thread1 = new Thread(() -> {
			int count = 0;
			while (isLoop) {
				count++;
			}
			System.out.println("쓰레드 끝 " + count);
		});

		final Thread thread2 = new Thread(() -> {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			isLoop = false;
		});
		thread1.start();
		thread2.start();
	}
}
