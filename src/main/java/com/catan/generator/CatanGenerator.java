package com.catan.generator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CatanGenerator {

	static String[] cards = { "WHEAT", "WHEAT", "WHEAT", "WHEAT", "WHEAT", "WHEAT", "ORE", "ORE", "ORE", "ORE", "ORE",
			"BRICK", "BRICK", "BRICK", "BRICK", "BRICK", "WOOD", "WOOD", "WOOD", "WOOD", "WOOD", "WOOD", "SHEEP", "SHEEP", "SHEEP", "SHEEP", "SHEEP", "SHEEP" };
	static List<String> cardList = Arrays.asList(cards);
	static String[] numbers = { "-2", "-2", "-3", "-3", "-3", "-4", "-4", "-4", "-5", "-5", "-5", "-6", "-6", "-6", "-8", "-8", "-8",
			"-9", "-9", "-9", "-10", "-10", "-10", "-11", "-11", "-11", "-12", "-12" };
	static List<String> numberList = Arrays.asList(numbers);
	static List<CardNumber> uniqueCardNumber = new ArrayList<CardNumber>();

	static int[] iteratorArray = { 3, 4, 5, 6, 5, 4, 3 };
	static CardNumber[][] cardNumberGraph = new CardNumber[7][6];

	public static void main(String[] args) {
		int min = 0;
		int max = cards.length;

		uniqueCardNumber.add(new CardNumber("DESERT", "-7"));
		uniqueCardNumber.add(new CardNumber("DESERT", "-7"));
		CardNumber randomCardNumber;
		int card;
		int number;
		while (uniqueCardNumber.size() != 30) {
			card = new Random().nextInt(max - min) + min;
			number = new Random().nextInt(max - min) + min;

			randomCardNumber = new CardNumber(cardList.get(card), numberList.get(number));
			if (cardList.size() != 1 && uniqueCardNumber.contains(randomCardNumber)) {
				continue;
			} else {
				uniqueCardNumber.add(randomCardNumber);
				cardList.set(card, null);
				numberList.set(number, null);
				cardList = removeNull(cardList);
				numberList = removeNull(numberList);
				max = max - 1;
			}
		}

		// for (CardNumber data : uniqueCardNumber) {
		// System.err.println(data);
		// }

		createGraph();
		printGraph();

	}

	private static void printGraph() {
		for (int i = 0; i < 7; i++) {
			for (int k = 0; k < 6; k++) {
				if ((i == 0 || i == 6) && k == 0) {
					System.out.print("           ");
				}
				if ((i == 1 || i == 5) && k == 0) {
					System.out.print("        ");
				}
				if ((i == 2 || i == 4) && k == 0) {
					System.out.print("    ");
				}
				System.out.print(cardNumberGraph[i][k] != null ? cardNumberGraph[i][k].toPresentString() : "");
			}
			System.out.println("");
		}
	}

	private static void createGraph() {
		int min = 0;
		int max = uniqueCardNumber.size();
		int random = new Random().nextInt(max - min) + min;
		for (int k = 0; k < 7; k++) {
			for (int j = 0; j < iteratorArray[k]; j++) {
				while (true) {
					random = new Random().nextInt(max - min) + min;

					if (checkWithPrevious(uniqueCardNumber.get(random), k, j)) {
						continue;
					}

					break;
				}

				cardNumberGraph[k][j] = uniqueCardNumber.get(random);
				uniqueCardNumber.set(random, null);
				uniqueCardNumber = removeNull(uniqueCardNumber);
				max = max - 1;
			}
		}
	}

	private static boolean checkWithPrevious(CardNumber cardNumber, int k, int j) {

		if (j > 0) {
			if (cardNumberGraph[k][j - 1].getCard().equals(cardNumber.getCard())
					|| cardNumberGraph[k][j - 1].getNumber().equals(cardNumber.getNumber())) {
				return true;
			}
		}

		if (k > 0) {
			int x = k - 1;
			int midpoint = ((j + 1) / 2) + 1;
			int endpoint = 6;
			int startind = 0;
			if (j <= 2) {
				for (int d = startind; d < midpoint; d++) {
					if (cardNumberGraph[x][d] != null && (cardNumberGraph[x][d].getCard().equals(cardNumber.getCard())
							|| cardNumberGraph[x][d].getNumber().equals(cardNumber.getNumber()))) {
						return true;
					}
				}
			} else if (j > 3) {
				for (int d = midpoint; d < endpoint; d++) {
					if (cardNumberGraph[x][d] != null && (cardNumberGraph[x][d].getCard().equals(cardNumber.getCard())
							|| cardNumberGraph[x][d].getNumber().equals(cardNumber.getNumber()))) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private static <T extends Object> List<T> removeNull(List<T> data) {
		List<T> dataList = new ArrayList<T>();
		for (T detum : data) {
			if (detum != null) {
				dataList.add(detum);
			}
		}
		return dataList;
	}

}
