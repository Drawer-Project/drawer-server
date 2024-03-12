package drawer.server.lib;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    public void uploadFile(String key, MultipartFile file) throws IOException;

    public String getFileUrl(String key) throws IOException;

    public void deleteFile(String key);
}
