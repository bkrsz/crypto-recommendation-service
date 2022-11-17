package com.xm.exercise.cryptorecommendationservice.application.service;

import com.xm.exercise.cryptorecommendationservice.application.exception.FileReadException;
import com.xm.exercise.cryptorecommendationservice.application.model.Crypto;
import com.xm.exercise.cryptorecommendationservice.application.model.CryptoRecord;
import com.xm.exercise.cryptorecommendationservice.application.utils.Utils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileReaderService {

    /**
     * Reads all the csv files present in the specified folder, the converts them into models
     *
     * @return List of cryptos
     */
    public List<Crypto> readFiles() {
        List<Resource> resources = findSourceFiles();
        return resources.stream().map(this::convertFile).toList();
    }

    /**
     * Converts a resource (file) into a model
     *
     * @param resource Resource representing the .csv file
     * @return Crypto model
     */
    private Crypto convertFile(Resource resource) {
        Crypto crypto = new Crypto();
        crypto.setSymbol(Utils.validateResource(resource.getFilename()));

        List<CryptoRecord> cryptoValueList = readFile(resource);
        crypto.setCryptoValues(cryptoValueList);

        return crypto;
    }

    /**
     * Reads the file then converts each line into the proper model
     * Skips the first line as that is the header
     *
     * @param resource The resource representing the .csv file
     * @return List of the models (for each line)
     */
    private List<CryptoRecord> readFile(Resource resource) {
        try {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
                List<CryptoRecord> result = new ArrayList<>();

                for (long i = 0; ; i++) {
                    String line = reader.readLine();

                    if (i == 0) {
                        continue;
                    }

                    if (line == null) {
                        break;
                    }

                    CryptoRecord cryptoRecord = Utils.convertLine(line);
                    result.add(cryptoRecord);
                }

                return result;
            }
        } catch (IOException exp) {
            throw new FileReadException();
        }
    }

    /**
     * Searches for valid .csv files in the specified folder
     *
     * @return List of valid resources (each representing a .csv file)
     */
    private List<Resource> findSourceFiles() {
        String scannedPackage = "csv/*";
        PathMatchingResourcePatternResolver scanner = new PathMatchingResourcePatternResolver();
        Resource[] resources;

        try {
            resources = scanner.getResources(scannedPackage);
        } catch (IOException exp) {
            throw new FileReadException();
        }

        return Stream.of(resources)
                .filter(resource -> resource.isFile() && resource.getFilename() != null && Utils.validateResource(resource.getFilename()) != null)
                .toList();
    }
}
