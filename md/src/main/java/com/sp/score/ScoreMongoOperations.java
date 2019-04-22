package com.sp.score;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service("score.scoreMongoOperations")
public class ScoreMongoOperations {
	@Autowired
	private MongoOperations mongo; // 인터페이스 구현 클래스는 MongoTemplate
	
	public void insertScore(Score dto) {
		try {
			dto.setCreated(new Date());
			
			mongo.insert(dto);
			//mongo.save(dto);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public List<Score> listScore() {
		List<Score> list=null;
		
		try {
			list = mongo.findAll(Score.class);
			
			Query query = new Query();
			query.with(new Sort(Sort.Direction.DESC, "tot"));
			
			list = mongo.find(query, Score.class);
			
			/*final Pageable pageRequest = new PageRequest(0, 2); // 페이지(index : 0부터), 개수
			Query query = new Query();
			query.with(pageRequest);
			
			list = mongo.find(query, Score.class);*/
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Score readScore(String hak) {
		Score dto=null;
		
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("hak").is(hak));
			dto = mongo.findOne(query, Score.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	public void updateScore(Score dto) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("hak").is(dto.getHak()));
			
			Update update = new Update();
			update.set("name", dto.getName());
			update.set("birth", dto.getBirth());
			update.set("kor", dto.getKor());
			update.set("eng", dto.getEng());
			update.set("mat", dto.getMat());
			update.set("tot", dto.getTot());
			update.set("ave", dto.getAve());
			
			mongo.updateFirst(query, update, Score.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteScore(String hak) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("hak").is(hak));
			
			Score dto = mongo.findOne(query, Score.class);
			mongo.remove(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
