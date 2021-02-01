package com.balasanthosh.dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Standalone dice game implementation.
 * 
 * @author kandi
 *
 */
public class DiceGame {

	private static final Random generator = new Random();

	public static void main(String[] args) {
		// number of players variable
		int numOfPlayers = 0;
		// finalpoints variable
		int finalPoints = 0;
		// store the players in array
		String[] arrayOfPlayers = null;
		// display welcome Message for the game.
		welcomeMessage();
		// Take the input of number of players
		Scanner in = new Scanner(System.in);
		System.out.println("Kindly enter number of Players");
		System.out.println("----------------------------------------------------------------");

		numOfPlayers = in.nextInt();

		System.out.println("Kindly enter points to accumulate");
		System.out.println("----------------------------------------------------------------");
		finalPoints = in.nextInt();
		// System.out.println("Points to accumulate is:"+finalPoints);

		arrayOfPlayers = new String[numOfPlayers];

		// Creation of Players name and storing them in array
		for (int i = 0; i < numOfPlayers; i++) {
			arrayOfPlayers[i] = "Player-" + (1 + i);
		}
		// Display the player names
		printPlayerList(arrayOfPlayers, numOfPlayers);

		// Random the game of players for starting
		shuffleArray(arrayOfPlayers);
		// Display the order of players in the game.
		randomPrintPlayerList(arrayOfPlayers, numOfPlayers);

		// Table to maintain the points.Key is playname and value is points
		HashMap<String, Integer> pointsTable = new HashMap<String, Integer>();

		// Temporary list which will change once points reached.
		List<String> playersPlayingList = new ArrayList<String>(Arrays.asList(arrayOfPlayers));
		List<String> finalWinningList = new ArrayList<String>();
		// Playing the game
		gameLogic(playersPlayingList, in, pointsTable, finalPoints, finalWinningList);
		// Display the points table in sorted order desc
		displayPointsTable(entriesSortedByValues(pointsTable));
		System.out.println("Rank List");
		System.out.println(finalWinningList);
		System.out.println("*******************Game End*********************");

	}

	/**
	 * Method to shuffle Array
	 * 
	 * @param array
	 */
	public static void shuffleArray(String[] array) {
		List<String> list = new ArrayList<>();
		for (String i : array) {
			list.add(i);
		}

		Collections.shuffle(list);

		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
	}

	/**
	 * Generating the Random Number between min and maximum
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getRandomInteger(int start, int end) {
		return start + generator.nextInt(end - start + 1);

	}

	/**
	 * Read the character
	 * 
	 * @param c
	 * @return
	 */
	public static boolean readCharacter(char c) {

		if (c == 'r') {
			return false;
		} else {
			System.out.println("Wrong key Pressed.Please enter 'r' only ");
			return true;
		}

	}

	/**
	 * Rolling the Dice using playername and input key
	 * 
	 * @param playerName
	 * @param in
	 * @return
	 */
	public static int rollTheDice(String playerName, Scanner in) {
		int gotPoint = 0;

		while (true) {
			if (!readCharacter(in.next().charAt(0))) {
				break;
			}

		}
		gotPoint = getRandomInteger(1, 6);
		System.out.println(playerName + " got the following points in this roll :" + gotPoint);
		System.out.println("----------------------------------------------------------------");

		return gotPoint;

	}

	/**
	 * Sorting the points Table
	 * 
	 * @param pointsMap
	 * @return
	 */
	public static HashMap<String, Integer> sortPointsTable(HashMap<String, Integer> pointsMap) {
		Set<Entry<String, Integer>> set = pointsMap.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		return pointsMap;

	}

	/**
	 * Sorting the points Table
	 * 
	 * @param <String>
	 * @param <Integer>
	 * @param map
	 * @return
	 */
	public static <String, Integer extends Comparable<? super Integer>> List<Entry<String, Integer>> entriesSortedByValues(
			Map<String, Integer> map) {

		List<Entry<String, Integer>> sortedEntries = new ArrayList<Entry<String, Integer>>(map.entrySet());

		Collections.sort(sortedEntries, new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});

		return sortedEntries;
	}

	/**
	 * method to display points table
	 * 
	 * @param pointsTable
	 */
	public static void displayPointsTable(HashMap<String, Integer> pointsTable) {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Points Table by Ranking");
		System.out.println("-----------------------------------------------------------------");
		for (Entry<String, Integer> entry : pointsTable.entrySet()) {
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}
		System.out.println("\n");
		System.out.println("-----------------------------------------------------------------");

	}

	/**
	 * Method to display points table
	 * 
	 * @param list
	 */
	public static void displayPointsTable(List<Entry<String, Integer>> list) {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Points Table by Ranking");
		System.out.println("-----------------------------------------------------------------");
		System.out.print(list);
		System.out.println("\n");
		System.out.println("-----------------------------------------------------------------");
	}

	/**
	 * Welcome message method
	 */
	public static void welcomeMessage() {
		System.out.println("***************************************************************");
		System.out.println("*****************Welcome to Game of Dice.**********************");
		System.out.println("***************************************************************");
		System.out.println("----------------------------------------------------------------");
		System.out.println("                   Rules of the Game                            ");
		System.out.println("----------------------------------------------------------------");
		System.out.println(
				"1) If a player rolls the value \"6\" then they immediately get another chance to roll again and\r\n"
						+ "move ahead in the game.");
		System.out.println(
				"2) If a player rolls the value \"1\" two consecutive times then they are forced to skip their next\r\n"
						+ "turn as a penalty.");
		System.out.println("***************************************************************");
		System.out.println("----------------------------------------------------------------");
		System.out.println("Lets play the game");
		System.out.println("----------------------------------------------------------------");

	}

	/**
	 * Method to print name of players
	 * 
	 * @param arrayOfPlayers
	 * @param numOfPlayers
	 */
	public static void printPlayerList(String[] arrayOfPlayers, int numOfPlayers) {
		System.out.println("----------------------------------------------------------------");
		System.out.println("--------------------Name of Players-----------------------------");
		for (int j = 0; j < numOfPlayers; j++) {
			System.out.println(1 + j + ")" + arrayOfPlayers[j]);
			System.out.println("\n");
		}
		System.out.println("----------------------------------------------------------------");
	}

	/**
	 * Method to Print the players in random fashion
	 * 
	 * @param arrayOfPlayers
	 * @param numOfPlayers
	 */
	public static void randomPrintPlayerList(String[] arrayOfPlayers, int numOfPlayers) {
		System.out.println("----------------------------------------------------------------");
		System.out.println("----------------------Order of Players--------------------------");
		for (int j = 0; j < numOfPlayers; j++) {
			System.out.println(1 + j + ")" + arrayOfPlayers[j]);
			System.out.println("\n");
		}
		System.out.println("----------------------------------------------------------------");
	}

	public static void gameLogic(List<String> playersPlayingList, Scanner in, HashMap<String, Integer> pointsTable,
			int finalPoints, List<String> finalWinningList) {
		// List to maintain the players to be missed next rounds
		List<String> missTheRound = new ArrayList<String>();
		// Temporary map to maintain previous points registered
		HashMap<String, Integer> previousRoundPoints = new HashMap<String, Integer>();
		int rank = 1;
		while (playersPlayingList.size() != 0) {
			// Rolling the Dice

			// Iterator to iterate the list of players playing
			Iterator itr = playersPlayingList.iterator();
			while (itr.hasNext()) {
				String nowPlaying = itr.next().toString();
				if (!missTheRound.contains(nowPlaying)) {
					System.out.println(nowPlaying + " its your turn to roll the dice.Press 'r'");
					int pointScored = rollTheDice(nowPlaying, in);

					if (pointScored == 6) {

						System.out.println(nowPlaying + " you got another turn to roll the dice.Press 'r'");
						int newPointScored = rollTheDice(nowPlaying, in);
						pointScored = pointScored + newPointScored;

					}
					if (pointsTable.get(nowPlaying) == null) {

						pointsTable.put(nowPlaying, pointScored);

					} else {
						int previousPoint = pointsTable.get(nowPlaying).intValue();

						pointsTable.put(nowPlaying, pointScored + previousPoint);

					}

					if (pointsTable.get(nowPlaying).intValue() >= finalPoints) {
						System.out.println(
								"*********!!!!" + nowPlaying + " Congrats you attained the maximum points.Your rank is "
										+ rank + ".Thanks for Playing******");
						finalWinningList.add(nowPlaying);

						itr.remove();
						rank++;
					}
					if (previousRoundPoints.get(nowPlaying) != null
							&& previousRoundPoints.get(nowPlaying).intValue() == 1 && pointScored == 1) {
						System.out.println("***As per rules you will miss next iteration****");
						missTheRound.add(nowPlaying);
					}
					previousRoundPoints.put(nowPlaying, pointScored);
					pointScored = 0;// Making it 0 again
				} else {
					missTheRound.remove(nowPlaying);
				}

			} // while loop closure
				// Display the points table in sorted order desc
			displayPointsTable(entriesSortedByValues(pointsTable));

		} // first while loop closure
	}

}
