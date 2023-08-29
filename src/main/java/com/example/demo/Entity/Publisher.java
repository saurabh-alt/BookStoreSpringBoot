package com.example.demo.Entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

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
@Component
public class Publisher{
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int publisherId;
	 	@NotEmpty(message="Publisher Name can't be Empty.")
		@Size(min=3,max=20,message="Minimum 3 and maximum 20 characters allowed.")
		@Pattern(regexp="^[a-zA-Z\\s]*$",message="Only Alphabets Allowed")
	 	private String name;
	 	
	 	@NotEmpty(message="Publisher's Address Name can't be Empty.")
		@Size(min=10,max=50,message="Minimum 10 and maximum 50 characters allowed.")
		@Pattern(regexp="^[a-zA-Z0-9\\s]*$",message="Only Alphabets Allowed")
	 	private String address;
	 	
	 	@NotEmpty(message = "please enter contactno ")
	 	@Size(min=10,max=10,message="Please Enter proper Contact number")
		@Pattern(regexp="^\\d{10}$",message="Only 10 Numbers Allowed")
	    private String contactNumber;
	    
	    @OneToMany(mappedBy = "publisher")
	    @JsonIgnore
		private List<Book> books;
}
