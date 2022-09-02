package model;

import java.util.List;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.lang.NonNull;

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
	private String name;
	
	private String description;
	
	@NotNull
	@Positive
	private int nCards;
	
	@NonNull
	@Positive
	private int nCategories;
	
	private String image;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idUser",
	         referencedColumnName = "idUser")
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy="deck")
	private List<Card> cards;

	@JsonIgnore
	@ManyToMany(mappedBy="decks")
	private List<Keyword> keywords;
	

}
