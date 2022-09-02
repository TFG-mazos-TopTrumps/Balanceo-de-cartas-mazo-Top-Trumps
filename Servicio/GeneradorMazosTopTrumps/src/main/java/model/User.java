package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUser;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String username;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String password;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String name;
	
	@OneToMany(mappedBy="user")
	private List<Deck> decks;
}
