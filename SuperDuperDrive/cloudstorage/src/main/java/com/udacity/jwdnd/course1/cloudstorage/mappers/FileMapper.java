package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*
MyBatis mapper for defining key File CRUD methods
 */
@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
    File getFileByFileID(int fileid);

    @Select("SELECT * FROM FILES where filename = #{filename}")
    File getFileByFilename(String filename);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<File> findByUserId(int userid);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) VALUES (#{file.filename}, #{file.contenttype}, #{file.filesize}, #{file.filedata}, #{userid})")
    int insertFile(File file, int userid);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
    int deleteFileByID(int fileid);

}
