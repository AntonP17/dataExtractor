package by.antohakon.dataextractor.service;

import by.antohakon.dataextractor.dto.DataResponseDto;
import by.antohakon.dataextractor.dto.ExtractDataDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

@Service
@Slf4j
public class DataExtractServiceImpl implements DataExtractService {


    @Override
    public DataResponseDto extractData(ExtractDataDto extractDataDto) {

        log.info("Extracting data... method extractData begin");

        int[] numbersFromFile = extractDataFromFile(extractDataDto);
        int[] sortedNumbers = sortArray(numbersFromFile);
        DataResponseDto dataResponseDto = findNumber(extractDataDto.numberPosition());

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
        int[] numbers = null;

        try (FileInputStream fis = new FileInputStream(extractDataDto.path())) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0); // первый лист

            // Определяем количество строк
            int rowCount = sheet.getPhysicalNumberOfRows();
            numbers = new int[rowCount];

            // Читаем данные из столбца
            for (int i = 0; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(0); // первый столбец

                if (cell.getCellType() == CellType.NUMERIC) {
                    numbers[i] = (int) cell.getNumericCellValue();
                } else {
                    numbers[i] = 0;
                }
            }

            // Выводим числа в консоль
            for (int number : numbers) {
                System.out.println("Число: " + number);
            }
        } catch (IOException e) {
            log.error("файл по данному пути не найден :(");
        }

        return numbers;

    }

    // TO DO !!!!!!!!!!!
    @Override
    public int[] sortArray(int[] array) {
        log.info("method sortArray begin");
        return array;
    }

    // TO DO !!!!!!!!!!!
    @Override
    public DataResponseDto findNumber(int numberPosition) {

        log.info("method findNumber begin");
        int random = new Random().nextInt(20);
        DataResponseDto dataResponseDto = DataResponseDto.builder()
                .findNumber(random)
                .text("найденное число по запросу")
                .build();

        log.info("return dto = " + dataResponseDto);
        return dataResponseDto;
    }
}
