package com.kosa.mycompany.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	@Resource(name="testService")
	TestService testService;
	
	// http://localhost:9000/mycompany/test?test=abcd
	@ResponseBody
	@RequestMapping(value="test")
	public void test(TestDto dto)
	{
		testService.insertData(dto);
	}
}
