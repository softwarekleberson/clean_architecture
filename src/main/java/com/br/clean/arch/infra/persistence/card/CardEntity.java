package com.br.clean.arch.infra.persistence.card;

import java.time.LocalDate;

import com.br.clean.arch.infra.persistence.customer.CustomerEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")
public class CardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean main;
	private String printedName;
	private String code;
	private String numberCard;
	
	@Enumerated(EnumType.STRING)
	private FlagEntity flag;
	
	private LocalDate expirationDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_entity_id") 
	private CustomerEntity customerEntity;
	
	public CardEntity() {

	}

	public CardEntity(boolean main, String printedName, String code, String numberCard, FlagEntity flag,
			LocalDate expirationDate) {
		this.main = main;
		this.printedName = printedName;
		this.code = code;
		this.numberCard = numberCard;
		this.flag = flag;
		this.expirationDate = expirationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		this.code = code;
	}

	public String getNumberCard() {
		return numberCard;
	}

	public void setNumberCard(String numberCard) {
		this.numberCard = numberCard;
	}

	public FlagEntity getFlag() {
		return flag;
	}

	public void setFlagEntity(FlagEntity flag) {
		this.flag = flag;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}
	
	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}
	
}
