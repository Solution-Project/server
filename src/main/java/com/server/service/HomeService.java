package com.server.service;

import com.server.mapper.BoardMapper;
import com.server.mapper.HomeMapper;
import com.server.model.BoardDTO;
import com.server.model.NoticeDTO;
import com.server.repository.NoticeDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.JDBCType;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeService {

    private final HomeMapper homeMapper;
    private final NoticeDAO noticeDAO;

    /* 공지사항 작성 */
    public int noticeWrite(NoticeDTO dto) {
        log.info("dto : {}", dto);

        int flag = 1;
        int result = homeMapper.write(dto);

        if(result >= 1) {
            flag = 0;
        } else {
            flag = 1;
        }
        return flag;
    }

    /* 공지사항 리스트 서비스 */
    public List<NoticeDTO> getNotice(int page, int pageSize) {
        return noticeDAO.getNotice(page, pageSize);
    }

    /* 공지사항 리스트 카운트 */
    public int getNoticeCount() {
        return homeMapper.getNoticeCount();
    }

    /* 요청받은 id로 공지사항 조회 */
    public NoticeDTO getNoticeById(int id) {
        return homeMapper.getNoticeById(id);
    }

}
