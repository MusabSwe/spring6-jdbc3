package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Speaker;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("speakerRepository")
public class SpeakerRepositoryImpl implements SpeakerRepository {

    private JdbcTemplate jdbcTemplate;

    public SpeakerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Speaker> findAll() {
        Speaker speaker = new Speaker();
        speaker.setName("Bryan Hansen");
        speaker.setSkill("Java");
        List<Speaker> speakers = new ArrayList<>();
        speakers.add(speaker);
        return speakers;
    }

    @Override
    public Speaker create(Speaker speaker) {
//        method 1 of creating a record in the DB, using jdbcTemplate
        jdbcTemplate.update("INSERT INTO speaker (name) values (?)", speaker.getName());

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

        return null;
    }
}
