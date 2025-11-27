package de.fhueter.demo.ibanvalidator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bank extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String bankCode;

	@Column(nullable = false, length = 11)
	private String bic;

	@Column(nullable = false)
	private String name;
}
