package com.server.repository;

import com.server.mapper.HomeMapper;
import com.server.model.NoticeDTO;
import com.server.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Slf4j
public class NoticeDAO {

    private final HomeMapper homeMapper;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NoticeDAO(HomeMapper homeMapper, JdbcTemplate jdbcTemplate) {
        this.homeMapper = homeMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<NoticeDTO> getNotice(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        log.info("offset : {}", offset);

        String sql = "SELECT * FROM NOTICE LIMIT "+offset+", "+pageSize;
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> mapRowToYourEntity(resultSet));
    }

    private NoticeDTO mapRowToYourEntity(ResultSet resultSet) throws SQLException {
        NoticeDTO entity = new NoticeDTO();
        entity.setNoticeSeq(resultSet.getInt("NOTICE_SEQ"));
        entity.setStrNoticeTitle(resultSet.getString("NOTICE_TITLE"));
        entity.setStrNoticeDate(resultSet.getString("NOTICE_DATE"));
        entity.setStrNoticeContent(resultSet.getString("NOTICE_CONTENT"));
        // Set other properties as needed
        log.info("entity : {}", entity);

        return entity;
    }
}