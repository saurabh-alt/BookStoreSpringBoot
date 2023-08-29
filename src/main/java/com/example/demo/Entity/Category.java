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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;
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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;

    @NotEmpty(message="Category Name can't be Empty.")
	@Size(min=3,max=20,message="Minimum 3 and maximum 20 characters allowed.")
	@Pattern(regexp="^[a-zA-Z\\s]*$",message="Only Alphabets Allowed")
    private String name;

    @NotEmpty(message="Language of the Book can't be Empty.") 
	@Size(min=3,max=20,message="Minimum 3 and maximum 20 characters allowed.")
	@Pattern(regexp="^[a-zA-Z\\s]*$",message="Only Alphabets Allowed")
    private String language;

    @NotNull(message="Mention the Age-Group of the Book.") 
	@Size(min=3,max=20,message="Minimum 3 and maximum 20 characters allowed.")
    @Pattern(regexp="^[a-zA-z0-9\\s]+$",message="Only Alphabets and Numeric values are allowed.")
    private String agegroup;
    
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "category",fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Book> books;
}
