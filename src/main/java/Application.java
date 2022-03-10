import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.Timer;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;
import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.BorderFactory.createTitledBorder;


public class Application extends JFrame {

	//instance vars
	boolean playAgainPressed = false;
	boolean thinkCondition = false;
	//Create GUI fields, labels, and buttons

	private final JTextField textFieldUserName = new JTextField(12);

	private final JTextField textFieldUserNumber = new JTextField(12);

	private final JButton guessTheNumberButton = new JButton("Check if you got it!");

	private final JLabel statusPanel = new JLabel("           ");

	private final JButton bPlayAgain = new JButton("Play Again");

	private final JButton bClearFields = new JButton("New Game");

	private final JButton bDisplayReport = new JButton("Display Report");

	//Create an array list of users in case you want to use later
	private final List<User> allUsersOfGame = new ArrayList<>();

	//create a random number when Application is called
	private final int randomNumber = new Random().nextInt(1);

	//store the last user
	public LastUserTracker lastUser = new LastUserTracker();

	//Create user database
	Map<String, Integer> userDatabase = new HashMap<>();

	//one way to access users across mem
	User user;

	boolean whileCondition = false;

	public Application() {
		//Create the first user to play the game
		user = new User();

		//game starts these buttons should not work
		bPlayAgain.setEnabled(false);
		bClearFields.setEnabled(false);

		//Create the main frame for the program
		JFrame frame = new JFrame("The Guessing Game: Can you beat the odds?");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.white);
		frame.setVisible(true);
		frame.setMinimumSize(new Dimension(700, 50));
		frame.setResizable(false);


		//Panel to store fields
		JPanel panelTop = new JPanel(new GridLayout(2, 2, 12, 12));
		JLabel userNameLabel = new JLabel("Enter your name:");
		panelTop.add(userNameLabel);
		panelTop.add(textFieldUserName);
		JLabel userNumberEnteredLabel = new JLabel("Enter a number: (0-100)");
		panelTop.add(userNumberEnteredLabel);
		panelTop.add(textFieldUserNumber);


		//create middle panel
		JPanel panelMiddle = new JPanel(new GridLayout(1, 1, 15, 10));
		panelMiddle.setPreferredSize(new Dimension(740, 180));
		panelMiddle.setBorder(createTitledBorder("Here are your results:"));
		panelMiddle.add(statusPanel);

		//create bottom panel
		JPanel panelBottom = new JPanel(new GridLayout(2, 2, 10, 10));
		panelBottom.add(guessTheNumberButton);


		panelBottom.add(bClearFields);

		panelBottom.add(bDisplayReport);

		panelBottom.add(bPlayAgain);

		//create inner title
		statusPanel.setBorder(createTitledBorder(""));
		statusPanel.setHorizontalAlignment(JLabel.CENTER);

		//animate Text
		//statusPanel.addAncestorListener( );

		//new addActionListener
		guessTheNumberButton.addActionListener(e -> checkIfNumberMatches(user));

		bClearFields.addActionListener(e -> {
			//Create a database of users
			updateUserDatabase(user);
			//start a new game
			newGame(user);
		});

		//new addActionListener
		bDisplayReport.addActionListener(e -> displayLeaderBoard());

		//play action
		bPlayAgain.addActionListener(e -> playAgainMethod(user));

		//create a panel to hold other panels
		JPanel panelContainer = new JPanel(new BorderLayout());
		panelContainer.setVisible(true);
		panelContainer.setBorder(createEmptyBorder(15, 15, 15, 15));

		//Wrap the panels within another panel - this prevents the inner panels from spreading across the entire JFrame
		JPanel wrapper1 = new JPanel();
		wrapper1.add(panelTop);
		JPanel wrapper2 = new JPanel();
		wrapper2.add(panelMiddle);
		JPanel wrapper3 = new JPanel();
		wrapper3.add(panelBottom);

		panelContainer.add(wrapper1, BorderLayout.NORTH);
		panelContainer.add(wrapper2, BorderLayout.CENTER);
		panelContainer.add(wrapper3, BorderLayout.SOUTH);

		//set colors of panels
		panelContainer.setBackground(Color.white);
		wrapper1.setBackground(Color.white);
		wrapper2.setBackground(Color.white);
		wrapper3.setBackground(Color.white);
		panelTop.setBackground(Color.white);
		panelMiddle.setBackground(Color.white);
		panelBottom.setBackground(Color.white);


		//add panels to frame and pack into one location on the in the frame
		frame.getContentPane().add(panelContainer);
		frame.pack();

	}

	//Main program

	public static void main(String[] args) {
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(Application::new);

	}

	//leaderboards like a BOSS
	public void displayLeaderBoard() {
		//test good for hash map storage
		StringBuilder leaderReport = new StringBuilder();

		Map<String, Integer> sorted =
				userDatabase.entrySet().stream()
						.sorted(comparingByValue())
						.collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

		for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
			// if the getTheUser is equal to the entry print a message
			if(!entry.getKey().equals(""))
			leaderReport.append("User: <b>").append(entry.getKey()).append("</b> has a score of ").append(entry.getValue()).append(".<br />");
		}


		String wrapReport = "<html>" + leaderReport + "</html>";
		JOptionPane.showMessageDialog(this,
				wrapReport,
				"Leaderboards", JOptionPane.WARNING_MESSAGE);
	}

	//play again method
	public void playAgainMethod(User user) {

		updateUserDatabase(user);
		newGame(user);
		textFieldUserName.setText(user.getUserName());
		this.playAgainPressed = true;
	}

	//Main logic for user entered number
	public void checkIfNumberMatches(User user) {
		//reserve memory - input text fields will populate these
		int usersInputtedNumber;
		String userName;
		bPlayAgain.setEnabled(true);
		bClearFields.setEnabled(true);
		try {
			//Set values, based on input - error handled
			usersInputtedNumber = Integer.parseInt(textFieldUserNumber.getText());
			userName = textFieldUserName.getText();

			//If there is no last user, set current user to last user
			if (lastUser.getLastUserStoredInMem() == null || lastUser.getLastUserStoredInMem() == "") {
				lastUser.setLastUser(userName);
			}

			//Set the username instance
			user.setUserName(userName);


			if (usersInputtedNumber == this.randomNumber && Integer.parseInt(user.getNumberOfTries()) == 1) {
				statusPanel.setText("<html>Congrats " + user.getUserName() + " You guessed " +
						this.randomNumber + "! It took you " + user.getNumberOfTries() + " guess(es)!</html>");

				//disable the button
				guessTheNumberButton.setEnabled(false);

				//print message for previous
				printPreviousUserMessage();

				//Animate text for a single person
				animateTextBox();


				//time delay
				autoStartNewGame(user);

			} else if (usersInputtedNumber == this.randomNumber) {
				statusPanel.setText("<html>Congrats " + user.getUserName() + " You guessed correctly, the number was " +
						this.randomNumber + " It took you " + user.getNumberOfTries() + " guess(es)! Previous guess(es): " + user.getRecordedTriesList() + "</html>");

				//print message for previous
				printPreviousUserMessage();

				//disable the button
				guessTheNumberButton.setEnabled(false);


				autoStartNewGame(user);

			} else if (usersInputtedNumber < this.randomNumber) {
				statusPanel.setText("<html><center>Sorry " + user.getUserName() + " that was not correct, try guessing a higher number!</center></html>");
				user.incrementNumberOfTries();
				user.setUserTriedNumbers(usersInputtedNumber);
			} else {

//				if(usersInputtedNumber - this.randomNumber <= 2)

				statusPanel.setText("<html><center>Sorry " + user.getUserName() + " that was not correct, try guessing a lower number!</center></html>");

				user.incrementNumberOfTries();
				user.setUserTriedNumbers(usersInputtedNumber);
			}

		} catch (Exception e) { //catch all - may miss a null input somewhere else
			errorInput();
		}
	}

	public void letTheUserThinkBeforeDoingAnything() {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				//think
				thinkCondition = true;
			}
		}, 1000);
	}

	public void autoStartNewGame(User user) {


		while(!whileCondition){
			letTheUserThinkBeforeDoingAnything();
		if (!this.playAgainPressed) {
			//time delay
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					statusPanel.setText("Starting new game in 5 seconds....");
					bPlayAgain.setEnabled(false);
					bClearFields.setEnabled(false);
					bDisplayReport.setEnabled(false);

				}
			}, 4000);
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					//Create a database of users
					updateUserDatabase(user);
					//new game
					newGame(user);

				}
			}, 8000);
			this.playAgainPressed = false;
			this.thinkCondition = false;
			whileCondition = true;
		}
	}

}


	private void animateTextBox() {

	}

	//display custom error message
public void errorInput() {
	JOptionPane.showMessageDialog(this,
			"Guess what you did wrong.",
			"Leader report", JOptionPane.INFORMATION_MESSAGE);
}

//Print this message if there was a last user
	public void printPreviousUserMessage() {

		//make sure last user and this user are not the same
//		if (!lastUser.getLastUserStoredInMem().equals(user.getUserName())) {

			//Get the last user from memory
			String getLastUser = this.lastUser.getLastUserStoredInMem();
			//current user number of tries
			int checkNumberOfTries = Integer.parseInt(user.getNumberOfTries());
			//Create an entry map to find the user in the hashMap
			for (Map.Entry<String, Integer> entry : userDatabase.entrySet()) {
				// if the getTheUser is equal to the entry print a message
				if (getLastUser == entry.getKey() && checkNumberOfTries < entry.getValue())
					statusPanel.setText("<html>Congrats " + user.getUserName() + ", you guessed correctly, the number was " +
							this.randomNumber + " It took you " + user.getNumberOfTries() + " guess(es)! Previous guess(es): " + user.getRecordedTriesList()
							+ " \n You beat " + entry.getKey() + " they took " + entry.getValue() + " tries!</html>");
			}

	}

	//Method to start a new game
	public void newGame(User user) {

		//enable the button
		guessTheNumberButton.setEnabled(true);
		bPlayAgain.setEnabled(true);
		bClearFields.setEnabled(true);
		bDisplayReport.setEnabled(true);

		//Store the  current user for later
		allUsersOfGame.add(user);

		lastUser.setLastUser(user.getUserName());
		//create new user
		this.user = new User();

		//clear the fields
		textFieldUserName.setText("");
		textFieldUserNumber.setText("");
		statusPanel.setText("");

		//reset whileCondition on new game
//		whileCondition = false;
		//displayReport();
		//the arrray list also has all the users
	}

	//create/update database
	public void updateUserDatabase(User user) {

		//Put in <HashMap> database new user || no database should contain an Empty key
		if(!Objects.equals(user.getUserName(), "") || user.getUserName() != null)
		userDatabase.putIfAbsent(user.getUserName(), Integer.parseInt(user.getNumberOfTries()));

		//Get the users total guesses
		int userUsersTotalGuesses = Integer.parseInt(this.user.getNumberOfTries());

		//Update the database with the best number of tries
		if (userUsersTotalGuesses < userDatabase.get(user.getUserName())) {
			userDatabase.put(user.getUserName(), Integer.parseInt(user.getNumberOfTries()));
		}

		//store the last user
		lastUser.setLastUser(this.user.getUserName());


	}

	//Print report of arraylist contents
	public void displayReport() {
		for (User value : allUsersOfGame) {
			System.out.println(value.getUserName() + "\n");
		}
	}
}