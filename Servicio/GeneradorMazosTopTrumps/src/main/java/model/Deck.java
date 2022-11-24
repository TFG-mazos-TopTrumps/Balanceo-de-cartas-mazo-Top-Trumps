package model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="decks")
public class Deck {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDeck;
	
	@NotNull
	@NotBlank
	@NotEmpty
	@Unique
	@Length(min=1, max=30)
	private String name;
	
	@Length(max=500)
	private String description;
	
	@NotNull
	@Positive
	@Min(2)
	@Max(30)
	private int nCards;
	
	@NotNull
	@Positive
	@Min(2)
	@Max(6)
	private int nCategories;
	
	@URL
	@Length(max=4000)
	private String image;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String borde;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String fondo;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String panel;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String fuente;
	
	
	private Boolean published = false;
	
	@NotNull
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idUser",
	         referencedColumnName = "idUser", nullable=false)
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy="deck")
	private List<Card> cards;

	@JsonIgnore
	@ManyToMany(mappedBy="decks")
	private List<Keyword> keywords;
	

}
