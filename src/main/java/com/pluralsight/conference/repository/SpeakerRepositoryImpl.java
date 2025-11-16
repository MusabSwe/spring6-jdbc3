package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Speaker;
import com.pluralsight.conference.repository.util.SpeakerRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Repository("speakerRepository")
public class SpeakerRepositoryImpl implements SpeakerRepository {

    private JdbcTemplate jdbcTemplate;

    public SpeakerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Speaker> findAll() {

        List<Speaker> speakers = jdbcTemplate.query("select * from speaker", new SpeakerRowMapper());
        return speakers;
    }

    @Override
    public Speaker create(Speaker speaker) {
//        method 1 of creating a record in the DB, using jdbcTemplate
//        jdbcTemplate.update("INSERT INTO speaker (name) values (?)", speaker.getName());

//        method 2 of creating a record in the DB, using SimpleJdbcInsert
//        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
//        insert.setTableName("speaker");
//        List<String> cols = new ArrayList<>();
//        cols.add("name");
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("name", speaker.getName());
//
//        insert.setGeneratedKeyName("id");
//
//        Number key = insert.executeAndReturnKey(data);
//
//        System.out.println(key);

//        method 3 to create a recode with JDBCTemplate and return in the response the created object
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                PreparedStatement ps = con.prepareStatement("INSERT INTO speaker (name) values (?)", new String[]{"id"});
//                ps.setString(1, speaker.getName());
//                return ps;
//            }
//
//
//        }, keyHolder);
//        Number id = keyHolder.getKey();

//        method4 simpleJDBCInsert with returned created object
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.setTableName("speaker");
        List<String> cols = new ArrayList<>();
        cols.add("name");

        Map<String, Object> data = new HashMap<>();
        data.put("name", speaker.getName());

        insert.setGeneratedKeyName("id");

        Number id = insert.executeAndReturnKey(data);

        System.out.println(id);
        return getSpeaker(id.intValue());
    }

    private Speaker getSpeaker(int id) {
        return jdbcTemplate.queryForObject("select * from speaker where id = ?", new SpeakerRowMapper(), id);
    }
}
