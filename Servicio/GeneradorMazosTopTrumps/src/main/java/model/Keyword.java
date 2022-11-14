package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.validator.constraints.Length;

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
	
	@NotNull
	@NotBlank
	@NotEmpty
	@Unique
	@Length(min=1,max=45)
	private String word;

	@JsonIgnore
	@ManyToMany()
	@JoinTable(name="deck_keywords",
		joinColumns=
			@JoinColumn(name="idKeyword", 
					referencedColumnName="idKeyword", nullable=false),
		inverseJoinColumns =
			@JoinColumn(name="idDeck",
						referencedColumnName="idDeck", nullable=false))
	private List<Deck> decks;
	

	 public void addKeyword(Deck deck){
	        if(this.decks == null){
	            this.decks = new ArrayList<>();
	        }
	        
	        this.decks.add(deck);
	    }
}
