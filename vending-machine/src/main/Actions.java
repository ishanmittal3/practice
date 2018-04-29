package main;

public class Actions {
	
	public static void stockProduct(VendingMachine vm, 
									String product, 
									Integer count, 
									Double... price) {
		Integer currCount = vm.getInventory(product);
		if (currCount == null) {
			if (price.length == 0) {
				System.out.println("Price needed to add new product");
				return;
			}
			vm.addNewProduct(product, count, price[0]);
		}
	}
	
	public void purchase(String product) {
		
	}
}
