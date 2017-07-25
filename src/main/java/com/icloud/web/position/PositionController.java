package com.icloud.web.position;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PositionController {

	
	@RequestMapping("/admin/to_position")
	public String toPosition(){
		return "position/position_input";
	}
}
