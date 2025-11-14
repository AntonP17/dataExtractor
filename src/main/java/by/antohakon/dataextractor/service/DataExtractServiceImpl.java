package by.antohakon.dataextractor.service;

import by.antohakon.dataextractor.dto.DataResponseDto;
import by.antohakon.dataextractor.dto.ExtractDataDto;
import by.antohakon.dataextractor.exception.BadPositionException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataExtractServiceImpl implements DataExtractService {

    private final XslxExtract xslxExtract;
    private final MergeSortService mergeSortService;

    @Override
    public DataResponseDto extractData(ExtractDataDto extractDataDto) {

        log.info("Extracting data... method extractData begin");

        int[] numbersFromFile = xslxExtract.extractDataFromFile(extractDataDto);
        int[] sortedNumbers = sortArray(numbersFromFile);

        DataResponseDto dataResponseDto = findNumber(
                sortedNumbers,
                extractDataDto.numberPosition());

        return dataResponseDto;

    }

    @Override
    public int[] sortArray(int[] array) {
        log.info("method sortArray begin");

        mergeSortService.mergeSort(array,0, array.length - 1);

        log.info("sortArray = " + Arrays.toString(array));
        return array;
    }


    @Override
    @SneakyThrows
    public DataResponseDto findNumber(int[] sortedArray, int position) {

        log.info("method findNumber begin");

        if (position < 1 || position > sortedArray.length) {
            log.error("Недопустимая позиция. Позиция должна быть от 1 до " + sortedArray.length);
            throw new BadPositionException("Недопустимая позиция. Позиция должна быть от 1 до " + sortedArray.length);
        }

        int findMinNumber = sortedArray[position - 1];
        log.warn("N-ое минимальное число: " + findMinNumber);

        DataResponseDto dataResponseDto = DataResponseDto.builder()
                .findNumber(findMinNumber)
                .text("найденное число по запросу")
                .build();

        log.info("return dto = " + dataResponseDto);
        return dataResponseDto;
    }
}
