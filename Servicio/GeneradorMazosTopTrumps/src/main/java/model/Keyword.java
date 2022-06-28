package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name="keywords")
public class Keyword {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKeyword;
	
	private String word;
	
	@JsonIgnore
	@ManyToMany()
	@JoinTable(name="deck_keywords",
		joinColumns=
			@JoinColumn(name="idKeyword", 
					referencedColumnName="idKeyword"),
		inverseJoinColumns =
			@JoinColumn(name="idDeck",
						referencedColumnName="idDeck"))
	private List<Deck> decks;
	

}
