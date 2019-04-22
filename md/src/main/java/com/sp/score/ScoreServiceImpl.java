package com.sp.score;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("score.scoreService")
public class ScoreServiceImpl implements ScoreService {
	@Autowired
	private ScoreMongoOperations scoreMongo;
	
	@Override
	public void insertScore(Score dto) {
		try {
			dto.setTot(dto.getKor()+dto.getEng()+dto.getMat());
			dto.setAve(dto.getTot()/3);
			
			scoreMongo.insertScore(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Score> listScore() {
		List<Score> list=null;
		
		try {
			list=scoreMongo.listScore();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Score readScore(String hak) {
		Score score=null;
		
		try {
			score=scoreMongo.readScore(hak);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return score;
	}

	@Override
	public void updateScore(Score dto) {
		try {
			dto.setTot(dto.getKor()+dto.getEng()+dto.getMat());
			dto.setAve(dto.getTot()/3);
			
			scoreMongo.updateScore(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteScore(String hak) {
		try {
			scoreMongo.deleteScore(hak);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
