package com.example.demo.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int feedbackid;
	
	 @Min(value = 1, message = "Rating value must be at least 1")
	 @Max(value = 5, message = "Rating value must not exceed 5")
	    private double ratings;
	
	@NotNull(message = "Please enter Book Id for providing Feedback.")
	private Integer Bookid;

	@NotNull(message = "Please enter your Thoughts and Feedback of the Book.")
	@Size(min=3,message="Minimum 3 characters allowed.")
	private String review;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
    private Book book;
}
