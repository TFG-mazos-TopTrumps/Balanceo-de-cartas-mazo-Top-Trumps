package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Keyword;

public interface KeywordsDao extends JpaRepository<Keyword, Integer> {
	
	Keyword findKeywordByWord(String word);
	
	@Query("select k.word from Keyword k")
	List<String> findAllWord();
	
	
}
