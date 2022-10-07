package dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Keyword;

public interface KeywordsDao extends JpaRepository<Keyword, Integer> {
	
	Optional<Keyword> findKeywordByWord(String word);
	
	@Query("select k.word from Keyword k")
	List<String> findAllWord();
	
	@Query("select count(*) from Keyword k where k.word=?1")
	Integer countKeywordByWord(String word);
	

}
