package vn.plusplus.lms.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.util.List;

public interface IFileService {
    List<String> saveFiles(MultipartFile[] files);
}
