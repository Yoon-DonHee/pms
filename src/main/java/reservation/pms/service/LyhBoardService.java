package reservation.pms.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.multipart.MultipartFile;
import reservation.pms.domain.lyhBoard.AttachFile;
import reservation.pms.domain.lyhBoard.AttachFileRepository;
import reservation.pms.domain.lyhBoard.Board;
import reservation.pms.domain.lyhBoard.BoardRepository;
import reservation.pms.dto.AttachFileDto;
import reservation.pms.dto.LyhBoardDto;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Transactional
public class LyhBoardService {

	@Value("${board.attach-file.storage}") private String filePath;
	@Value("${board.attach-file.url}") private String fileUrl;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private AttachFileRepository attachFileRepository;

	public Page<LyhBoardDto.info> findAllbyPageable(LyhBoardDto.search searchDto, Pageable pageable){
		Page<LyhBoardDto.info> pageSearchBoard = boardRepository.findAllBySearch(searchDto, pageable);
		return pageSearchBoard;
	}

	public LyhBoardDto.info findbyId(Long id) {
		Board board = boardRepository.getOne(id);

		board.increaseCounts();

		LyhBoardDto.info infoDto = new LyhBoardDto.info(board);
		return infoDto;
	}

	public Map<String, Object> getBoardAttachFile(Long id) throws MalformedURLException {
		AttachFile attachFile = attachFileRepository.getOne(id);
		AttachFileDto.info infoDto = new AttachFileDto.info(attachFile);

		Path fileLocation = Paths.get(filePath).toAbsolutePath().normalize();

		Path filePath = fileLocation.resolve(infoDto.getFileName()).normalize();

		Resource resource = new UrlResource(filePath.toUri());

		Map<String, Object> downloadAttachFile = new HashMap<String, Object>();
		downloadAttachFile.put("Resource", resource);
		downloadAttachFile.put("AttachFileDto.info", infoDto);

		return downloadAttachFile;
	}

	public List<AttachFileDto.info> findbyAttachFileBoardId(Long id) {
		List<AttachFileDto.info> attachFileDtoList = new ArrayList<>();
		List<AttachFile> attachFiles = attachFileRepository.findAllByBoardId(id);
		for(AttachFile attachFile : attachFiles) {
			attachFileDtoList.add(new AttachFileDto.info(attachFile));
		}
		return attachFileDtoList;
	}

	public List<Board> getAllBoard() {
		return boardRepository.findAll();
	}

	public LyhBoardDto.info addBoard(LyhBoardDto.save saveDto, List<MultipartFile> uploadFiles) throws IOException {
		Board board = boardRepository.save(saveDto.toEntity());

		addBoardAttachFile(board, null, uploadFiles);

		LyhBoardDto.info infoDto = new LyhBoardDto.info(board);
		return infoDto;
	}

	public LyhBoardDto.info modifyBoard(LyhBoardDto.save saveDto, List<Long> deleteAttachFiles, List<MultipartFile> uploadFiles) throws IOException {
		Board board = boardRepository.getOne(saveDto.getId());

		addBoardAttachFile(board, deleteAttachFiles, uploadFiles);

		Board saveBoard = saveDto.toEntity();
		board.update(saveBoard.getType(), saveBoard.getTitle(), saveBoard.getContent(), saveBoard.getMemberNo(), saveBoard.getCheckCol1(), saveBoard.getRadioCol1());

		LyhBoardDto.info infoDto = new LyhBoardDto.info(board);
		return infoDto;
	}

	public void addBoardAttachFile(Board board, List<Long> deleteAttachFiles, List<MultipartFile> uploadFiles) throws IOException {

		if(deleteAttachFiles != null && deleteAttachFiles.size() > 0) {
			for (Long attachFileId : deleteAttachFiles) {
				attachFileRepository.deleteById(attachFileId);
			}
		}

		if(uploadFiles != null && uploadFiles.size() > 0) {
			for (MultipartFile mf : uploadFiles) {
				String newFileName = System.currentTimeMillis() + UUID.randomUUID().toString().replaceAll("-","") +  "." + FilenameUtils.getExtension(mf.getOriginalFilename());
				File file = new File(filePath + File.separator + newFileName);
				File dir = new File(filePath);
				if(!dir.exists()) {
					dir.mkdirs();
				}
				mf.transferTo(file);

				AttachFile attachFile = AttachFile.builder()
						.originalFilename(mf.getOriginalFilename())
						.fileName(newFileName)
						.fileSize(mf.getSize())
						.filePath(filePath)
						.fileUrl(fileUrl)
						.build();

				attachFile.setBoard(board);

				attachFileRepository.save(attachFile);
			}
		}
	}

	public void removeBoard(Long id) {
		boardRepository.deleteById(id);
	}

}