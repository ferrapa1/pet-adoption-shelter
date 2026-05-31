package ch.zhaw.ssdd.pas.ports.outbound;

import java.io.IOException;

/**
 * Defines the contract for storing and retrieving file data.
 * This is an outbound port in the Hexagonal Architecture.
 */
public interface FileStoragePort {

    /**
     * Saves a file to the underlying storage.
     *
     * @param fileData The raw byte data of the file.
     * @param originalFilename The original name of the file, used to determine the extension.
     * @return The publicly accessible URL or path to the stored file.
     * @throws IOException If there is an error during the file save operation.
     */
    String saveFile(byte[] fileData, String originalFilename) throws IOException;
}
