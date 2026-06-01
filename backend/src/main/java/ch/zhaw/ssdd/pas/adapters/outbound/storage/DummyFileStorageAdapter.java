package ch.zhaw.ssdd.pas.adapters.outbound.storage;

import ch.zhaw.ssdd.pas.ports.outbound.FileStoragePort;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Profile("prod")
public class DummyFileStorageAdapter implements FileStoragePort {

    @Override
    public String saveFile(byte[] fileData, String originalFilename) throws IOException {
        return "./dummy";
    }
}
