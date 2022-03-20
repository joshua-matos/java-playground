package busdriver;

public class Driver {

	//store routes provided by bus driver
	private final int[][] routes;

	//store gossip from bus driver;
	private int [][] gossip;

	public Driver(int[][] routes) {
		this.routes = routes;
		//generate gossip for each driver
		generateGossip();
	}

	//track gossip between drivers
	public void generateGossip(){
		//store the gossip - size of the routes
		gossip = new int[routes.length][routes.length];

		for (int i = 0; i < gossip.length; i++) {
			//set gossip diagonally
			gossip[i][i] = 1;
		}
	}

	// Share gossip
	public void shareGossip(int[] gossipOne, int[] gossipTwo){
		for (int i = 0; i < gossipOne.length; i++) {
			//see if gossip array contains gossip
			if(gossipOne[i] == 1 || gossipTwo[i] == 1){
				gossipOne[i] = 1;
				gossipTwo[i] = 1;
			}
		}
	}

	public boolean checkGossip() {
		//checks if each array value in gossip array contains a 1
		//if it does it is equal to the entire size of the array
		int solutionNumber = gossip.length * gossip.length;
		int sum =0;
		for (int[] currentGossip : gossip) {
			for (int i : currentGossip) {
				sum = i;
			}
		}
		return sum == solutionNumber;
	}

	//what stops are the drivers at
	public int getStop(int minute, int[] route) {
		//based on the current minute what stop on the route am i
//		if(minute % route.length) { //0, 3 = 0; 1,3=1; 2,3 = 2; 3,3=0; runs through the index of array and restarts

//		}
		int index = minute % route.length;
		return route[index];
	}

	public int  sendTheDrivers() {
		//track minutes
		int count = 0;

		//this starts the drivers... attempts
		while(count < 480) {
			//check to see if any drivers meet
			//go through the routes
			for (int i = 0; i < routes.length; i++) {
				for (int j = i + 1; j < routes.length; j++) {
					//check the stop
					if(getStop(count, routes[i]) == getStop(count, routes[j])){
						//share gossip
						shareGossip(gossip[i], gossip[j]);
					}
				}
			}

			if(checkGossip()) {
				return count + 1;
			} else {
				count++;
			}
		}
			return 0;
	}
}

