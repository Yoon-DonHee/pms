package reservation.pms.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reservation.pms.common.enums.ExceptionEnum;
import reservation.pms.common.response.ApiNotiException;
import reservation.pms.dto.AttachFileDto;
import reservation.pms.dto.LyhBoardDto;
import reservation.pms.service.LyhBoardService;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

//@CrossOrigin(origins = "*")
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api/lyhBoard")
public class LyhBoardController {



    @Autowired
    private LyhBoardService lyhBoardService;



    @GetMapping("/getMockMvcTest")
    public @ResponseBody
    String getMockMvcTest(@RequestParam String name, @RequestParam String id) {
        return name + "," + id;
    }

    @PostMapping("/postMockMvcTest")
    public @ResponseBody
    String postMockMvcTest(@RequestBody LyhBoardDto.info boardInfo) {
        return boardInfo.getTitle() + "," + boardInfo.getContent();
    }

    @GetMapping("")
    public Page<LyhBoardDto.info> getAllBoards(Pageable pageable
            , LyhBoardDto.search searchDto) {

        Page<LyhBoardDto.info> pageBoardDto = lyhBoardService.findAllbyPageable(searchDto, pageable);
        return pageBoardDto;
    }

    @GetMapping("/{id}")
    public LyhBoardDto.info getBoard(
            @PathVariable Long id, @RequestParam(required = false, name = "title") String title) throws Exception {

        Map<String, Object> response = new HashMap<>();

        LyhBoardDto.info infoDto = null;
        //해당 게시글 존재여부 확인 필요
        try {
            infoDto = lyhBoardService.findbyId(id);
        } catch(EntityNotFoundException e) {
            throw new ApiNotiException(ExceptionEnum.NOTI_EXCEPTION, "조회오류. 해당 게시물이 없습니다.");
            //throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION, "조회오류. 해당 게시물이 없습니다.");
            //throw new Exception("조회오류. 해당 게시물이 없습니다.");
        }

        response.put("board", infoDto);

        return infoDto;
    }

    @GetMapping("/{id}/attachfiles")
    public List<AttachFileDto.info> getAllBoardAttachFiles(
            @PathVariable Long id) throws Exception {


        List<AttachFileDto.info> attachFileDtoList = null;

        try {
            attachFileDtoList = lyhBoardService.findbyAttachFileBoardId(id);
        } catch(EntityNotFoundException e) {
            throw new ApiNotiException(ExceptionEnum.NOTI_EXCEPTION, "조회오류. 해당 게시물이 없습니다.");
            //throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION, "조회오류. 해당 게시물이 없습니다.");
            //throw new Exception("조회오류. 해당 게시물이 없습니다.");
        }

        return attachFileDtoList;
    }

    @GetMapping("/downloadAttachFile/{id}")
    public ResponseEntity<Resource> downloadAttachFile(@PathVariable String id, HttpServletRequest request) throws MalformedURLException {


        Map<String, Object> downloadAttachFile = lyhBoardService.getBoardAttachFile(Long.parseLong(id));
        Resource resource = (Resource) downloadAttachFile.get("Resource");
        AttachFileDto.info infoDto = (AttachFileDto.info) downloadAttachFile.get("AttachFileDto.info");

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            throw new ApiNotiException(ExceptionEnum.NOTI_EXCEPTION, "파일 오류.");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + infoDto.getOriginalFilename() + "\"")
                .body(resource);
    }

    @PostMapping("")
    public LyhBoardDto.info createBoard(
            @Validated @RequestPart(value = "board", required = false) LyhBoardDto.save saveDto
            , BindingResult bindingResult
            , @RequestPart(value = "uploadFiles", required = false) List<MultipartFile> uploadFiles) throws Exception {

        LyhBoardDto.info infoDto = null;

        if(StringUtils.isEmpty(saveDto.getId())) {
            infoDto = lyhBoardService.addBoard(saveDto, uploadFiles);
        } else {
            infoDto = lyhBoardService.modifyBoard(saveDto, null, uploadFiles);
        }

        return infoDto;
    }

    @PutMapping("")
    public LyhBoardDto.info updateBoard(
            @Validated @RequestPart(value = "board", required = false) LyhBoardDto.save saveDto
            , @Validated @RequestPart(value = "deleteAttachfiles", required = false) List<Long> deleteAttachfiles
            , BindingResult bindingResult
            , @RequestPart(value = "uploadFiles", required = false) List<MultipartFile> files) throws Exception {

        LyhBoardDto.info infoDto = null;

        if(StringUtils.isEmpty(saveDto.getId())) {
            infoDto = lyhBoardService.addBoard(saveDto, files);
        } else {
            try {
                infoDto = lyhBoardService.modifyBoard(saveDto, deleteAttachfiles, files);
            } catch(EntityNotFoundException e) {
                throw new Exception("수정오류. 해당 게시물이 없습니다.");
            }
        }

        return infoDto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(
            @PathVariable Long id) throws Exception {

        System.out.println(id);

        Map<String, Boolean> response = new HashMap<>();

        //해당 게시글 존재여부 확인 필요
        try {
            LyhBoardDto.info infoDto = lyhBoardService.findbyId(id);
        } catch(EntityNotFoundException e) {
            throw new Exception("삭제오류. 해당 게시물이 없습니다.");
        }

        lyhBoardService.removeBoard(id);

        return ResponseEntity.ok(response);
    }
}
