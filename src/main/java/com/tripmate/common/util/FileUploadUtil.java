package com.tripmate.common.util;

import com.tripmate.common.exception.FileUploadException;
import com.tripmate.domain.ReviewImageDTO;
import com.tripmate.entity.Const;
import com.tripmate.entity.FileUploadEnum;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class FileUploadUtil {
    public static boolean fileExtensionValidCheck(MultipartFile file) throws FileUploadException{
        if (file.getSize() > Const.MULTIPART_MAX_UPLOAD_SIZE) {
            throw new FileUploadException(FileUploadEnum.FILE_SIZE_EXCEPTION.getCode(), FileUploadEnum.FILE_SIZE_EXCEPTION.getMessage());
        }

        final String[] fileTypeList = {"jpg", "jpeg", "pjpeg", "png", "gif", "bmp"};
        for (String fileType : fileTypeList) {
            if (FilenameUtils.getExtension(file.getOriginalFilename()).equals(fileType)) {
                return true;
            }
        }

        return false;
    }

    public static ReviewImageDTO fileUpload(List<String> saveFiles, MultipartFile file) throws FileUploadException {
        String originalName = file.getOriginalFilename();

        String saveFileName = UUID.randomUUID() + "_" + originalName.substring(originalName.lastIndexOf("\\") + 1);
        saveFiles.add(saveFileName);

        ReviewImageDTO reviewImageDTO = ReviewImageDTO.builder()
                .reviewImageName(saveFileName)
                .reviewImagePath(File.separator + saveFileName)
                .reviewImageVolume(String.valueOf(file.getSize()))
                .build();

        try {
            file.transferTo(new File(Const.FILE_DIR_PATH, saveFileName));
        } catch (IOException e) {
            throw new FileUploadException(FileUploadEnum.FILE_UPLOAD_EXCEPTION.getCode(), FileUploadEnum.FILE_UPLOAD_EXCEPTION.getMessage());
        }

        return reviewImageDTO;
    }

    public static void deleteFile(List<String> saveFiles) {
        for (String file : saveFiles) {
            File existFile = new File(Const.FILE_DIR_PATH, file);
            if (existFile.exists()) {
                existFile.delete();
            }
        }
    }
}
