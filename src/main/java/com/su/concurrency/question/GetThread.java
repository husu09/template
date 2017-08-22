package com.su.concurrency.question;

public class GetThread extends Thread {
	
	private Person p;
	
	public GetThread(Person p) {
		this.p = p;
	}
	
	@Override
	public void run() {
		System.out.println(p.getAge());
	}

	
	
}
