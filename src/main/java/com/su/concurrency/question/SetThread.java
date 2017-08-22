package com.su.concurrency.question;

public class SetThread extends Thread {
	
	private Person p;
	
	public SetThread(Person p) {
		this.p = p;
	}
	
	@Override
	public void run() {
		p.setAge(10);
	}

	
	
}
