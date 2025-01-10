package com.br.clean.arch.domain.entitie.card;

import java.time.LocalDate;
import java.util.Objects;

import com.br.clean.arch.domain.entitie.card.exeptions.IncorrectCardExpetion;
import com.br.clean.arch.domain.entitie.card.valueObject.Flag;

public class Card {

	private boolean main;
	private String printedName;
	private String code;
	private String numberCard;
	private Flag flag;
	private LocalDate expirationDate;
	
	public Card(boolean main, String printedName, String code, String numberCard, Flag flag, LocalDate expirationDate) {
		
		algoritmoLumn(numberCard);
		
		this.main = main;
		this.printedName = printedName;
		setCode(code);
		setNumberCard(numberCard);
		this.flag = flag;
		setExpirationDate(expirationDate);
	}
	
	public void algoritmoLumn(String numberCard) {
		AlgoritmoLumn.validate(numberCard);
	}

	public boolean isMain() {
		return main;
	}

	public void setMain(boolean main) {
		this.main = main;
	}

	public String getPrintedName() {
		return printedName;
	}

	public void setPrintedName(String printedName) {
		this.printedName = printedName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		String patterCode = "^[0-9]{3}$";
		if(!code.matches(patterCode)) {
			throw new IncorrectCardExpetion("Number card requered 3 numbers");
		}
		this.code = code;
	}

	public String getNumberCard() {
		return numberCard;
	}

	public void setNumberCard(String numberCard) {
		String patterNumberCard = "^[0-9]{16}$";
		if(!numberCard.matches(patterNumberCard)) {
			throw new IncorrectCardExpetion("Your card must have 16 numbers");
		}
		this.numberCard = numberCard;
	}

	public Flag getFlag() {
		return flag;
	}

	public void setFlag(Flag flag) {
		this.flag = flag;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		LocalDate currentDate = LocalDate.now();
		if(!expirationDate.isAfter(currentDate)) {
			throw new IncorrectCardExpetion("Your card need expiration date bigger current date");
		}
		this.expirationDate = expirationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numberCard, printedName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return Objects.equals(numberCard, other.numberCard) && Objects.equals(printedName, other.printedName);
	}

	@Override
	public String toString() {
		return "Card [main=" + main + ", printedName=" + printedName + ", code=" + code + ", numberCard=" + numberCard
				+ ", flag=" + flag + ", expirationDate=" + expirationDate + "]";
	}
}
