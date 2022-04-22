package vn.plusplus.lms.controller.admin;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.services.interfaces.IFileService;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/file")
public class UploadFileController {

    private static Logger logger = LogManager.getLogger(UploadFileController.class);

    @Autowired
    private IFileService fileService;

    @Autowired
    ResponseFactory factory;


    @RequestMapping(value = "/upload", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity handleFileUpload(@RequestParam("files") MultipartFile[] files) throws URISyntaxException {
        List<String> listImg = fileService.saveFiles(files);
        if (listImg != null) {
            return factory.success(listImg);
        } else {
            throw new AppException(ErrorCode.UPLOAD_FILE_ERROR);
        }
    }
}
