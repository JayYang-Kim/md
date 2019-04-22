package com.sp.score;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("score.scoreController")
public class ScoreController {
	@Autowired
	private ScoreService scoreService;
	
	@RequestMapping(value="/score/list")
	public String list(Model model) throws Exception {
		List<Score> list=scoreService.listScore();
		model.addAttribute("list", list);
		return "score/list";
	}
	
	@RequestMapping(value="/score/created", method=RequestMethod.GET)
	public String createdForm(Model model) throws Exception {
		model.addAttribute("mode", "created");
		return "score/write";
	}
	
	@RequestMapping(value="/score/created", method=RequestMethod.POST)
	public String createdSubmit(Score score) throws Exception {
		scoreService.insertScore(score);
		return "redirect:/score/list";
	}

	@RequestMapping(value="/score/update", method=RequestMethod.GET)
	public String updateForm(@RequestParam(value="hak") String hak, Model model) throws Exception {
		Score dto=scoreService.readScore(hak);
		if(dto==null)
			return "redirect:/score/list"; 

		model.addAttribute("dto", dto);
		model.addAttribute("mode", "update");
		return "score/write";
	}
	
	@RequestMapping(value="/score/update", method=RequestMethod.POST)
	public String updateSubmit(Score dto) throws Exception {
		scoreService.updateScore(dto);
		return "redirect:/score/list";
	}
	
	@RequestMapping(value="/score/delete")
	public String delete(@RequestParam(value="hak") String hak) throws Exception {
		scoreService.deleteScore(hak);
		return "redirect:/score/list"; 
	}
}
