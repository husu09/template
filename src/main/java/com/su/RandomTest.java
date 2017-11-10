package com.su;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class RandomTest {
	/**
	 * 按概率随机
	 * */
	@Test
	public void t1() {
		List<Item> list = new ArrayList<>();
		// 合计100的概率
		list.add(new Item(1, 10));
		list.add(new Item(2, 20));
		list.add(new Item(3, 30));
		list.add(new Item(4, 25));
		list.add(new Item(5, 15));
		int min = 1;
		int max = 100;
		
		for (int i = 0; i < 1000000000; i++) {
			// 随机概率
			int rate = getRandom2(min, max);
			int value = 0;
			for (Item item : list) {
				value += item.getRate();
				if (rate <= value) {
					item.addResult();
					break;
				}
			}
		}
		System.out.println(list);
	}
	
	public static int getRandom(int minNumber, int maxNumber) {
		if (minNumber > maxNumber) {
			int temp = minNumber;
			minNumber = maxNumber;
			maxNumber = temp;
		}
		return (int) (minNumber + Math.random() * (maxNumber - minNumber + 1));
	}
	
	public static int getRandom2(int minNumber, int maxNumber) {
		Random random = new Random();
		return random.nextInt(maxNumber) % (maxNumber  - minNumber + 1) + minNumber;
 	}
	
	private class Item {
		private int id;
		private int rate;
		private int result;
		
		public Item(int id, int rate) {
			this.id = id;
			this.rate = rate;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getRate() {
			return rate;
		}

		public void setRate(int rate) {
			this.rate = rate;
		}

		public String getResult() {
			return result / 1000000000.0 * 100 + "%";
		}

		public void addResult() {
			this.result += 1;
		}

		@Override
		public String toString() {
			return "Item [id=" + id + ", rate=" + rate + ", result=" + getResult() + "]";
		}
		
		
		
	}
}
