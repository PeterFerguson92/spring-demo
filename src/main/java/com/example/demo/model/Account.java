package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "Account")
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Integer accountNumber;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	protected Account() {}

	private Account(Builder builder) {
		id = builder.id;
		accountNumber = builder.accountNumber;
		customer = builder.customer;
	}


	public Long getId() {
		return id;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public Customer getCustomer() {
		return customer;
	}


	public static final class Builder {
		private Long id;
		private Integer accountNumber;
		private Customer customer;

		public Builder() {
		}

		public Builder id(Long val) {
			id = val;
			return this;
		}

		public Builder accountNumber(Integer val) {
			accountNumber = val;
			return this;
		}

		public Builder customer(Customer val) {
			customer = val;
			return this;
		}

		public Account build() {
			return new Account(this);
		}
	}
}
