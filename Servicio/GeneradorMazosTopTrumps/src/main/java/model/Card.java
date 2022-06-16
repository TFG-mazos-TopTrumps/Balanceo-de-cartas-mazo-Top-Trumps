package model;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="cards")
public class Card {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCard;
	
	private String name;
	private String description;
	private String image;
	
	@ElementCollection
	@CollectionTable(name = "card_categories",
		joinColumns = {@JoinColumn(name="idCard", referencedColumnName = "idCard")})
	@MapKeyColumn(name = "category")
	@Column(name="value")
	Map<String,Double> categories;
	
	@ManyToOne
	@JoinColumn(name="idDeck",
    referencedColumnName = "idDeck")
	Deck deck;

}
