package com.sp.score;

import java.util.List;

public interface ScoreService {
	public void insertScore(Score dto);
	public List<Score> listScore();
	public Score readScore(String hak);
	public void updateScore(Score dto);
	public void deleteScore(String hak);
}
