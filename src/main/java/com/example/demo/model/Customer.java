package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Customer")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String forename;
	private String surname;
	private LocalDate dateOfBirth;

	private Customer(Builder builder) {
		id = builder.id;
		forename = builder.forename;
		surname = builder.surname;
		dateOfBirth = builder.dateOfBirth;
	}

	public Customer() {}

	public Long getId() {
		return id;
	}

	public String getForename() {
		return forename;
	}

	public String getSurname() {
		return surname;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public static final class Builder {
		private Long id;
		private String forename;
		private String surname;
		private LocalDate dateOfBirth;
		private List<Account> accountList;

		public Builder() {
		}

		public Builder id(Long val) {
			id = val;
			return this;
		}

		public Builder forename(String val) {
			forename = val;
			return this;
		}

		public Builder surname(String val) {
			surname = val;
			return this;
		}

		public Builder dateOfBirth(LocalDate val) {
			dateOfBirth = val;
			return this;
		}

		public Customer build() {
			return new Customer(this);
		}
	}
}
