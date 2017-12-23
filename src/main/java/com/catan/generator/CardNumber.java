package com.catan.generator;

public class CardNumber implements Comparable<CardNumber> {

	private String card;
	private String number;

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((card == null) ? 0 : card.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardNumber other = (CardNumber) obj;
		if (card == null) {
			if (other.card != null)
				return false;
		} else if (!card.equals(other.card))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CardNumber [card=" + card + ", number=" + number + "]";
	}

	public int compareTo(CardNumber o) {

		return this.compareTo(o);
	}

	public CardNumber() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CardNumber(String card, String number) {
		super();
		this.card = card;
		this.number = number;
	}
	
	public String toPresentString() {
		return "["+ card + number +"]";
	}
	

}
