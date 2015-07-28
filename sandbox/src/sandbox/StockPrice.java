package sandbox;

public class StockPrice {
	/**
	 * Given a chronological array of stock prices,
	 * at what prices should we buy and sell 
	 * (the same stock that we bought) to get the 
	 * maximum profit 
	 * @param prices
	 * @return
	 */
	Prices findPrices(int[] prices) {
		int max = prices[prices.length-1];
		int minPrice = prices[prices.length-1];
		int maxPrice = minPrice;
	    for(int pos = prices.length-1; pos >= 0; pos--) {
	        if(prices[pos] > max) {
	            max = prices[pos];
	        } else {
	            if(max - prices[pos] > maxPrice - minPrice) {
	                minPrice = prices[pos];
	                maxPrice = max;
	            }
	        }
	    }
	    return new Prices(minPrice, maxPrice);
	}
	
	static class Prices {
		int min;
		int max;
		
		Prices(int min, int max) {
			this.min = min;
			this.max = max;
		}
	}
}
