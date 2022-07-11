package model;

import java.util.List;

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
	private String name;
	private String description;
	private int nCards;
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
