public class Application {

      public static void main(String[] args) {
        System.out.println(DeerTractor.tractorsSold());
    }
 }


 class DeerTractor {
     int fuel = 0;
     static int  numberSold = 0;
    public int addFuel() {
	        fuel += 1;
	        return fuel;
		    }
   public static int tractorsSold() {
		        return numberSold;
	     }
 }