package com.codurance.IO;

import java.util.Scanner;

public class Console {

	Scanner scanner = new Scanner(System.in);

	public void println(String messageToPrint) {
		System.out.println(messageToPrint);
	}

	public void print(String messageToPrint) {
		System.out.print(messageToPrint);
	}

	public String nextLine() {
		return scanner.nextLine();
	}
}
