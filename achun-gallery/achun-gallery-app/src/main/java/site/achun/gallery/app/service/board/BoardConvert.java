package site.achun.gallery.app.service.board;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.gallery.app.generator.domain.Board;
import site.achun.gallery.app.generator.domain.BoardRecord;
import site.achun.gallery.app.generator.service.BoardRecordService;
import site.achun.gallery.app.service.board_record.BoardRecordQueryExecute;
import site.achun.gallery.app.service.gallery_group.MyGroupService;
import site.achun.gallery.app.utils.DateTimeUtil;
import site.achun.gallery.client.module.board.request.CreateOrUpdateBoard;
import site.achun.gallery.client.module.board.response.BoardResponse;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardConvert {

    private final BoardRecordService boardRecordService;
    private final MyGroupService myGroupService;
    private final BoardRecordQueryExecute boardRecordQueryExecute;
    private final MediaFileQueryClient fileQueryClient;

    public Board toBoard(CreateOrUpdateBoard createRequest) {
        return Board.builder()
                .boardCode(UUID.randomUUID().toString().replace("-",""))
                .description(createRequest.getDescription())
                .name(createRequest.getName())
                .userCode(createRequest.getUserCode())
                .ctime(LocalDateTime.now()).utime(LocalDateTime.now())
                .recordUtime(LocalDateTime.now())
                .build();
    }

    public BoardResponse toBoardResponse(Board board) {
        if(board == null) return null;
        BoardResponse boardResponse = BeanUtil.toBean(board, BoardResponse.class);
        // 填充预览图
        List<BoardRecord> records = boardRecordService.lambdaQuery()
                .eq(BoardRecord::getBoardCode, board.getBoardCode())
                .last("limit 3")
                .orderByDesc(BoardRecord::getCtime)
                .list();
        if(CollUtil.isNotEmpty(records)){
            List<String> previews = records.stream()
                    .map(BoardRecord::getFileCode)
                    .collect(Collectors.toList());
            boardResponse.setPreviews(previews);
        }else{
            boardResponse.setPreviews(new ArrayList<>());
        }
        // 填充分组名
        String groupNames = myGroupService.selectForNames(board.getBoardCode()).getGroupNames();
        boardResponse.setGroupNames(groupNames);



        return boardResponse;
    }

    public List<BoardResponse> toBoardResponseList(Collection<Board> boards){
        if(CollUtil.isEmpty(boards)){
            return new ArrayList<>();
        }
        List<BoardResponse> boardResponseList = BeanUtil.copyToList(boards, BoardResponse.class);

        // 封面图
        List<String> coverFileCodes = boardResponseList.stream()
                .map(BoardResponse::getCoverFileCodes)
                .toList();
        Map<String, MediaFileResponse> coverFileMap = fileQueryClient.queryFileMap(QueryByFileCodes.builder().fileCodes(coverFileCodes).build()).getData();

        // 预览图
        Set<String> boardCodes = boardResponseList.stream()
                .map(BoardResponse::getBoardCode)
                .collect(Collectors.toSet());
        Map<String, List<String>> previewMap = queryBoardsPreviews(boardCodes);

        boardResponseList.stream().forEach(boardResponse -> {
            // 预览图
            if(previewMap.containsKey(boardResponse.getBoardCode())){
                boardResponse.setPreviews(previewMap.get(boardResponse.getBoardCode()));
            }else{
                boardResponse.setPreviews(new ArrayList<>());
            }
            // 封面图
            if(StrUtil.isNotEmpty(boardResponse.getCoverFileCodes())&&coverFileMap.containsKey(boardResponse.getCoverFileCodes())){
                boardResponse.setCover(coverFileMap.get(boardResponse.getCoverFileCodes()).getCover());
            }
            // 填充分组名
            String groupNames = myGroupService.selectForNames(boardResponse.getBoardCode()).getGroupNames();
            boardResponse.setGroupNames(groupNames);
        });

        return boardResponseList;
    }

    private Map<String,List<String>> queryBoardsPreviews(Collection<String> boardCodes){
        List<BoardRecord> records = boardCodes.stream()
                .flatMap(boardCode -> boardRecordQueryExecute.randomQuery(boardCode, 13).stream())
                .toList();

        if(CollUtil.isEmpty(records)){
            return new HashMap<>();
        }
        Set<String> fileCodes = records.stream()
                .map(BoardRecord::getFileCode)
                .collect(Collectors.toSet());
        Map<String, MediaFileResponse> fileMap = fileQueryClient.queryFileMap(QueryByFileCodes.builder().fileCodes(fileCodes).build()).getData();

        Map<String,List<String>> result = new HashMap<>();
        for (BoardRecord record : records) {
            if(fileMap.containsKey(record.getFileCode())){
                if(result.containsKey(record.getBoardCode())){
                    result.get(record.getBoardCode()).add(fileMap.get(record.getFileCode()).getCover());
                }else{
                    List<String> files = new ArrayList<>();
                    files.add(fileMap.get(record.getFileCode()).getCover());
                    result.put(record.getBoardCode(),files);
                }
            }else{
                log.info("fileCode not found.fileCode:{}",record.getFileCode());
            }
        }
        return result;
    }
}
