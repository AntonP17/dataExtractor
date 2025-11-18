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
    private final QuickSelectService quickSelectService;

    @Override
    public DataResponseDto extractData(ExtractDataDto extractDataDto) {

        log.info("Extracting data... method extractData begin");

        int[] numbersFromFile = xslxExtract.extractDataFromFile(extractDataDto);

        return findNumber(numbersFromFile, extractDataDto.numberPosition());

    }

    @Override
    public int[] sortArray(int[] array) {
        log.info("method sortArray begin");

        mergeSortService.mergeSort(array, 0, array.length - 1);

        log.info("sortArray = " + Arrays.toString(array));
        return array;
    }


    @Override
    @SneakyThrows
    public DataResponseDto findNumber(int[] array, int numberPosition) {

        log.info("method findNumber begin");

        int nthElement = quickSelectService.kthSmallest(
                array,
                0,
                array.length - 1,
                numberPosition);

        log.info("nthElement = " + nthElement);

        DataResponseDto dataResponseDto = DataResponseDto.builder()
                .text("найденное число по запросу")
                .findNumber(nthElement)
                .build();

        return dataResponseDto;
    }
}
