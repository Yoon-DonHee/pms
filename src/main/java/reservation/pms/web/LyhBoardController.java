package reservation.pms.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reservation.pms.domain.lyhBoard.Board;
import reservation.pms.dto.LyhBoardDto;
import reservation.pms.service.LyhBoardService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class LyhBoardController {

    @Autowired
    private LyhBoardService lyhBoardService;

    @GetMapping("/lyhBoard")
    public @ResponseBody List<Board> getAllBoards() {
        return lyhBoardService.getAllBoard();
    }

    @PostMapping("/lyhBoard")
    public LyhBoardDto.info createBoard(
            @RequestBody @Validated LyhBoardDto.save saveDto) throws Exception {

    	LyhBoardDto.info infoDto = null;

        if(StringUtils.isEmpty(saveDto.getId())) {
            infoDto = lyhBoardService.addBoard(saveDto);
        } else {
            infoDto = lyhBoardService.modifyBoard(saveDto);
        }

        return infoDto;
    }

    /*@GetMapping("/board/{id}")
    public ResponseEntity<BoardDto.info> getBoardByNo(
            @PathVariable Integer id) {

        BoardDto.info infoDto = new BoardDto.info();

        infoDto = boardService.findbyId(id);

        return ResponseEntity.ok(infoDto);
    }*/

    @GetMapping("/lyhBoard/{id}")
    public ResponseEntity<LyhBoardDto.info> getBoardByNo(
            @PathVariable Integer id, @RequestParam(required = false, name = "title") String title) {

        System.out.println(id + "-" + title);

        LyhBoardDto.info infoDto = new LyhBoardDto.info();

        infoDto = lyhBoardService.findbyId(id);

        return ResponseEntity.ok(infoDto);
    }
}
