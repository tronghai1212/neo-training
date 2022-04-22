package vn.plusplus.lms.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.services.interfaces.IFileService;
import vn.plusplus.lms.utils.FileUtils;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@Service
public class FileService implements IFileService {
    private static Logger logger = LogManager.getLogger(FileService.class);

    @Autowired
    private FileUtils fileUtils;

    private static final String PATHIMG = "/lms/images/";
    private static final String PATHPDF = "/lms/pdf/";
    private static final String PATHVIDEO = "/lms/video/";

    @Override
    public List<String> saveFiles(MultipartFile[] files) {
        List<String> lstFiles = new ArrayList<>();
        String fileName = "";
        try{
            if (files != null) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        logger.info("Uploading file [{}]", file.getOriginalFilename());
                        String nameFileServer = fileUtils.generateFileName(file.getOriginalFilename());
                        if (nameFileServer.matches(".+(pdf|txt|csv)")) {
                            fileName = fileUtils.save(file, PATHPDF, nameFileServer);
                        }else if (nameFileServer.matches(".+(jpg|png|jpeg|PNG|JPG|JPEG)")){
                            fileName = fileUtils.save(file, PATHIMG, nameFileServer);
                        } else if(nameFileServer.matches(".+(mp4|MP4)")){
                            fileName = fileUtils.save(file, PATHVIDEO, nameFileServer);
                        } else {
                            throw new AppException(ErrorCode.UNSUPPORT_FILE_EXTENSION);
                        }
                        lstFiles.add(fileUtils.genFilePath(fileName));
                    }
                }
            }
        }catch (URISyntaxException e) {
            e.getMessage();
        }
        return lstFiles;
    }

}
