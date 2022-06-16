package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Keyword;

public interface KeywordDao extends JpaRepository<Keyword, Integer> {
	
	Keyword findKeywordByWord(String word);

}
