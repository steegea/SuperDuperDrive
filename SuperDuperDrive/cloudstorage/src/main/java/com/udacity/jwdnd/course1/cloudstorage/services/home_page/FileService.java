package com.udacity.jwdnd.course1.cloudstorage.services.home_page;


import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

/*
Service class that implements File mapper methods
 */
@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<File> getAllFiles(Integer userID){

        List<File> files = fileMapper.findByUserId(userID);

        files.forEach(
                file -> {
                    String base64 = Base64.getEncoder().encodeToString(file.getFiledata());
                    String dataURL = "data:" + file.getContenttype() + ";base64," + base64;

                    file.setDataURL(dataURL);
                }
        );

        return files;
    }

    public void uploadNewFile(MultipartFile fileUpload, Integer userid) throws IOException {
        File file = new File();
        try {
            file.setContenttype(fileUpload.getContentType());
            file.setFiledata(fileUpload.getBytes());
            file.setFilename(fileUpload.getOriginalFilename());
            file.setFilesize(Long.toString(fileUpload.getSize()));
        } catch (IOException e) {
            throw e;
        }
        fileMapper.insertFile(file, userid);
    }

    public void deleteFileByID(int fileid) {
        fileMapper.deleteFileByID(fileid);
    }

    public boolean isFilenameAvailable(String filename){
        return fileMapper.getFileByFilename(filename) == null;
    }

    public File getByFileId(int fileId) {
        return fileMapper.getFileByFileID(fileId);
    }


}
