package model;

import java.util.HashMap;
import java.util.List;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
@Table(name="cards")
public class Card {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCard;
	
	@NotNull
	@NotBlank
	@NotEmpty
	@Length(min=1, max=45)
	private String name;
	
	@Length(max=500)
	private String description;
	
	@URL
	@Length(max=1000)
	private String image;
	
	@ElementCollection
	@CollectionTable(name = "card_categories",
		joinColumns = {@JoinColumn(name="idCard", referencedColumnName = "idCard", nullable=false)})
	@MapKeyColumn(name = "category")
	@Column(name="value")
	Map<String,Double> categories;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idDeck",
    referencedColumnName = "idDeck", nullable=false)
	Deck deck;
	
	public Card(List<String> names,List<Double> values) {
		this.categories = buildMap(names,values);
		
	}
	
	
	// Método empleado para completar el los pares categoria-valor.
	private static Map<String, Double> buildMap(List<String> names, List<Double> values2) {
		Map<String,Double> result=new HashMap<String,Double>();
		for(int i=0;i<names.size();i++) {
			result.put(names.get(i), values2.get(i));
		}
		return result;
	}

}
