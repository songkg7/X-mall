package com.xmall.xmall.file;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
    private MultipartFile multipartFile;
    private String fileUploadDir;
    private String newFileName;
    private String delFileName;
    private File file;

    public FileUpload() {

    }

    public FileUpload(MultipartFile multipartFile, String fileUploadDir, String delFileName) throws Exception {
        this.multipartFile = multipartFile;
        this.fileUploadDir = fileUploadDir;
        this.delFileName = delFileName;

        if (delFileName != null && !delFileName.isEmpty()) {
            if (!new File(fileUploadDir + delFileName).isFile()) {
                throw new Exception();
            }
        }
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String ori_file_name = multipartFile.getOriginalFilename();
            String file_extension = ori_file_name.substring(ori_file_name.lastIndexOf(".") + 1);
            newFileName = UUID.randomUUID() + "." + file_extension;
        }
    }

    public void upLoadFile() throws Exception {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            file = new File(fileUploadDir + newFileName);
            multipartFile.transferTo(file);

            if (delFileName != null && !delFileName.isEmpty()) {
                delete(fileUploadDir + delFileName);
            }
        }
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void delete() {
        if (file != null) {
            file.delete();
        }
    }

    public void delete(String file_name) {
        if (file_name == null || file_name.isEmpty()) {
            return;
        }

        File file2 = new File(file_name);

        if (file2.isFile()) {
            file2.delete();
        }
    }
}
