package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*
MyBatis mapper for defining key Credential CRUD methods
 */
@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userID}")
    List<Credential> getCredentialsByUserID(Integer userid);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{url}, #{username}, #{key}, #{password}, #{userID})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialID")
    int insertCredentials(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialID = #{credentialID}")
    int deleteCredentials(int credentialID);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password} WHERE credentialID = #{credentialID}")
    int updateCredentials(Credential credential);
}
