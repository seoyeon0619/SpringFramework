package com.kosa.mycompany.score;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScoreController {
	@Resource(name="scoreService")
	ScoreService service;
	
	@RequestMapping(value="/score/list")
	public String getList(ScoreDto dto, Model model)
	{
		
		List<ScoreDto> list = service.getList(dto);
		for(int i=0; i<list.size(); i++)
		{
			System.out.println(list.get(i).getName());
		}
		
		model.addAttribute("list", service.getList(dto));
		return "score/list";
	}
}
