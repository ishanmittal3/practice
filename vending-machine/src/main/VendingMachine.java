package main;

import java.util.HashMap;

import static main.Utils.*;

public class VendingMachine {
	private double userBalance;
	private HashMap<String, Integer> stock;
	private HashMap<String, Double> prices;
	
	public VendingMachine() {
		this.userBalance = 0;
		this.stock  = new HashMap<String, Integer>();
		this.prices = new HashMap<String, Double >();
	}
	
	public double getUserBalance() {
		return userBalance;
	}
	public void insertMoney(double money) {
		if (getCents(money) %5 == 0) {
			this.userBalance += money;
		}
		else {
			System.out.println("Inserted money should be a multiple of 5 cents");
		}
	}
	
	public Integer getInventory(String product) {
		return this.stock.get(product);
	}
	public void addNewProduct(String product, Integer count, Double price) {
		if (this.stock.containsKey(product)) {
			System.out.println("Vending machine already contains " + product);
		}
		else {
			this.stock.put(product, count);
			this.prices.put(product, price);
		}
	}
	
	public void stockExistingProduct(String product, Integer count) {
		Integer currCount = this.stock.get(product);
		if (currCount == null) {
			System.out.println(product + "doesn't exist in vending machine");
		}
		else {
			this.stock.put(product, currCount + count);
		}
	}
	
}
