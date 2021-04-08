package reservation.pms.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import reservation.pms.dto.LyhBoardDto;
import reservation.pms.service.LyhBoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/lyhBoard")
public class LyhBoardController {

	@Autowired
	private LyhBoardService boardService;

	@GetMapping("/getMockMvcTest")
	public @ResponseBody String getMockMvcTest(@RequestParam String name, @RequestParam String id) {
		return name+ "," + id;
	}

	@PostMapping("/postMockMvcTest")
	public @ResponseBody String postMockMvcTest(@RequestBody LyhBoardDto.info boardInfo) {
		return boardInfo.getTitle() + "," +boardInfo.getContent();
	}

	@GetMapping("/list/data")
	public @ResponseBody Page<LyhBoardDto.info> datas(
			Pageable pageable
			, LyhBoardDto.search searchDto
		) {
		log.debug("/board/list/data");
		Page<LyhBoardDto.info> pageBoardDto = boardService.findAllbyPageable(searchDto, pageable);
		return pageBoardDto;
	}

	@GetMapping(path = {"/form", "/form/{id}"})
	public String form(
			Model model
			, @PathVariable(required = false, name = "id") Integer id
			) throws Exception {
		log.debug("board/form");
		
		LyhBoardDto.info infoDto = new LyhBoardDto.info();
		if(! StringUtils.isEmpty(id)) {
			infoDto = boardService.findbyId(id);
		}
		
		model.addAttribute("infoDto", infoDto);
		
		return "board/form";
	}

	@PostMapping("/submit")
	public String submit(
			Model model
			, @Validated LyhBoardDto.save saveDto
			, BindingResult bindingResult
			) throws Exception {
		log.debug("board/submit");
		
		if(bindingResult.hasErrors()) {
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			StringBuffer errMsg = new StringBuffer();
			for (FieldError fieldError : fieldErrors) {
				errMsg.append(fieldError.getDefaultMessage() + ",");
			}
			throw new Exception(errMsg.toString());
		}
		
		LyhBoardDto.info infoDto = null;
		
		if(StringUtils.isEmpty(saveDto.getId())) {
			infoDto = boardService.addBoard(saveDto);
		} else {
			infoDto = boardService.modifyBoard(saveDto);
		}
		
		return "redirect:/board/list";
	}

	@GetMapping("/delete")
	public @ResponseBody Map<String, Object> delete(
			@RequestParam(required = true, name = "id") Integer[] ids) {
		log.debug("/board/delete");

		long deleteCnt = boardService.removeAllByIdIn(ids);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 200);
		result.put("delCnt", deleteCnt);

		return result;
	}

}