package com.study.spring.jpa;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="JpaMember")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // used for auto-increment
	private Long id; // we use Long so that id can store null values
	private String username;
	private LocalDate createDate;
	
	public void changeUsername(String username) {
		this.username = username;
	}
	public void changeCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
}
