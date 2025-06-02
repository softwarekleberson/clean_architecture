package com.br.clean.arch.domain.entitie.card;

import com.br.clean.arch.domain.entitie.card.exeptions.CardNotFoundException;

public class AlgoritmoLumn {

	public static void validate(String numberCard) {
		
		 int sum = 0;
	     boolean toggle = false;

	        for (int i = numberCard.length() - 1; i >= 0; i--) {
	            int n = Integer.parseInt(numberCard.substring(i, i + 1));
	            if (toggle) {
	                n *= 2;
	                if (n > 9) {
	                    n -= 9;
	                }
	            }
	            sum += n;
	            toggle = !toggle;
	        }

	        if (sum % 10 != 0) {
	            throw new CardNotFoundException("Your number card not be validated");
	        }
	}
		
}
