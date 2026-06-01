package ch.zhaw.ssdd.pas.adapters.outbound.storage;

import ch.zhaw.ssdd.pas.config.StaticResourceConfiguration;
import ch.zhaw.ssdd.pas.ports.outbound.FileStoragePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * An adapter that implements the FileStoragePort by saving files to the local file system.
 */
@Service
@Profile("!prod")
public class LocalFileStorageAdapter implements FileStoragePort {

    private final Path storageDirectory;

    public LocalFileStorageAdapter(@Value("${app.storage.path:./uploads}") String storagePath) {
        this.storageDirectory = Paths.get(storagePath).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.storageDirectory);
        } catch (IOException e) {
            throw new RuntimeException("Could not create storage directory", e);
        }
    }

    @Override
    public String saveFile(byte[] fileData, String originalFilename) throws IOException {
        String extension = getExtension(originalFilename);
        String uniqueFilename = UUID.randomUUID().toString() + extension;
        Path targetLocation = this.storageDirectory.resolve(uniqueFilename);
        
        Files.write(targetLocation, fileData);

        return StaticResourceConfiguration.IMAGES_URL_PATH + "/" + uniqueFilename;
    }

    // TODO check magic bytes
    private String getExtension(String filename) {
        if (filename == null || filename.lastIndexOf(".") == -1) {
            return "";
        }
        return filename.substring(filename.lastIndexOf("."));
    }
}
