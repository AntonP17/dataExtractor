package by.antohakon.dataextractor.service;

import by.antohakon.dataextractor.dto.DataResponseDto;
import by.antohakon.dataextractor.dto.ExtractDataDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Slf4j
public class DataExtractServiceImpl implements DataExtractService {


    @Override
    public DataResponseDto extractData(ExtractDataDto extractDataDto) {

        log.info("Extracting data... method extractData begin");

        int[] numbersFromFile = extractDataFromFile(extractDataDto);
        int[] sortedNumbers = sortArray(numbersFromFile);
        DataResponseDto dataResponseDto = findNumber(sortedNumbers[0]);

        return dataResponseDto;


    }

    @Override
    public boolean fileExists(String path) {

        log.info("method fileExists begin");
        return Files.exists(Path.of(path));

    }

    @Override
    @SneakyThrows
    public int[] extractDataFromFile(ExtractDataDto extractDataDto) {

        log.info("method extractDataFromFile begin");
        if (!fileExists(extractDataDto.path())) {
            log.error("файл по данному адресу не найден");
            throw new FileNotFoundException("файл по данному адресу не найден");
        }

        log.info("file exists :)");

        int[] numbersFromFile = new int[]{1, 2, 3};
        return numbersFromFile;

    }

    @Override
    public int[] sortArray(int[] array) {
        log.info("method sortArray begin");
        return array;
    }

    @Override
    public DataResponseDto findNumber(int numberPosition) {

        log.info("method findNumber begin");
        DataResponseDto dataResponseDto = DataResponseDto.builder()
                .findNumber(3)
                .text("найденное число по запросу")
                .build();

        log.info("return dto = " + dataResponseDto);
        return dataResponseDto;
    }
}
