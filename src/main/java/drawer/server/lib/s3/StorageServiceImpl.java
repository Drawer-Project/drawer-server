package drawer.server.lib.s3;

import drawer.server.lib.StorageService;
import io.awspring.cloud.s3.S3Template;
import java.io.IOException;
import java.net.URL;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final S3Template s3Template;

    @Value("${application.bucket.name}")
    private String bucketName;

    @Override
    public void uploadFile(String key, MultipartFile file) throws IOException {
        s3Template.upload(bucketName, key + ".png", file.getInputStream());
    }

    @Override
    public String getFileUrl(String key) throws IOException {
        URL url = s3Template.download(bucketName, key + ".png").getURL();
        return url.toString();
    }

    @Override
    public void deleteFile(String key) {
        s3Template.deleteObject(bucketName, key + ".png");
    }
}
