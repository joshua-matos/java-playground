class Product {


	Product(String name, int valueInCents) {
		this.name = name;
		this.valueInCents = valueInCents;
	}

	private String name;
	private int valueInCents;

	public String getName() {
		return name;
	}

	public int getValueInCents() {
		return valueInCents;
	}

	public Currency getCurrency(){
		return new Currency(this);
	}


	private static class Currency {
		private Product product;

		public Currency(Product product){
			this.product = product;
		}

		public String getUSD() {
//			long returnVal = (long) product.getValueInCents()/100;
//			long getcents2 = (long) product.getValueInCents() % 100;
//			String getcents = String.format("%.2f" , getcents2);
//
//			return "$"+ returnVal + "." + getcents;
			return String.format("$%.2f",(float)product.getValueInCents()/100);
		}

	}
}