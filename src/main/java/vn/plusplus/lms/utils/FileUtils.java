package vn.plusplus.lms.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUtils {
    @Value("${hostName}")
    private String HOST;

    @Value("${pathurl}")
    private String UPLOAD_DIR;

    public String save(MultipartFile file, String path, String fileName) {
        String currentDate = DateTimeUtils.getDateTimeNow("yyyyMMdd");
        String newPath = UPLOAD_DIR + path + currentDate;
        File pathSaveFile = new File(newPath);
        if (!pathSaveFile.exists()) {
            pathSaveFile.mkdirs();
        }
        Path rootCustomFile = Paths.get(newPath);
        try {
            Files.copy(file.getInputStream(), rootCustomFile.resolve(fileName));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
        return path + currentDate + "/" + fileName;
    }

    /**
     * genFilePath
     *
     * @param urlFile
     * @return
     * @throws URISyntaxException
     */
    public String genFilePath(String urlFile) throws URISyntaxException {
        return this.getDomainName() + urlFile;
    }

    /**
     * getDomainName
     *
     * @return String {java.lang.String}
     * @throws URISyntaxException
     */
    public String getDomainName() throws URISyntaxException {
        URI uri = new URI(HOST);
        String domain = uri.toString();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    /**
     * generateFileName : generate file name using save to dir
     *
     * @param fileNameClient : name file client request
     * @return String {java.lang.String}
     */
    public String generateFileName(String fileNameClient) {
        String dateTimeNow = DateTimeUtils.getDateTimeNow("HHmmssSSS");
        String[] splitString = fileNameClient.split("\\.");
        int tagFileSub = fileNameClient.lastIndexOf(".");
        fileNameClient = fileNameClient.substring(0, tagFileSub > 15 ? 15 : tagFileSub);
        return dateTimeNow + "_" + fileNameClient + "." + splitString[splitString.length - 1];
    }
}
