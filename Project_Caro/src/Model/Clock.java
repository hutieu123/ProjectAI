package Model;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {
	public static void main(String[] args) throws InterruptedException {
		int start = 10;
		while (start > 0) {
			Thread.sleep(1000);
			System.out.println(start--);
		}
	}
}
