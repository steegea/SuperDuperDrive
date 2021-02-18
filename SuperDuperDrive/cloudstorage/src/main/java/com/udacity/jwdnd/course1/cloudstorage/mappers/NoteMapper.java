package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*
MyBatis mapper for defining key Note CRUD methods
 */
@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteID}")
    Note getNoteById(Integer noteID);

    @Select("SELECT * FROM NOTES WHERE userid = #{userID}")
    List<Note> getNotesByUserID(Integer userID);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) " +
            "VALUES (#{noteTitle}, #{noteDescription}, #{userID})")
    @Options(useGeneratedKeys = true, keyProperty = "noteID")
    int insertNote(Note note);

    @Select("SELECT MAX(noteid) from NOTES")
    Integer getLatestNoteID();

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteID}")
    int deleteNote(int noteid);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteID}")
    int updateNote(Note note);

}
