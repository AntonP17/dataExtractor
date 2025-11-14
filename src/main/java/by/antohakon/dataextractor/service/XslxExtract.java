package by.antohakon.dataextractor.service;

import by.antohakon.dataextractor.dto.ExtractDataDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Component
@Slf4j
public class XslxExtract {

    @SneakyThrows
    public int[] extractDataFromFile(ExtractDataDto extractDataDto) {

        log.info("method extractDataFromFile begin");
        if (!Files.exists(Path.of(extractDataDto.path()))) {
            log.error("файл по данному адресу не найден");
            throw new FileNotFoundException("файл по данному адресу не найден");
        }

        log.info("file exists :)");
        int[] numbers = null;

        try (FileInputStream fis = new FileInputStream(extractDataDto.path())) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getPhysicalNumberOfRows();
            numbers = new int[rowCount];

            for (int i = 0; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(0);

                if (cell.getCellType() == CellType.NUMERIC) {
                    numbers[i] = (int) cell.getNumericCellValue();
                } else {
                    numbers[i] = 0;
                }
            }

            log.info("прочитаны даные из файла = " + Arrays.toString(numbers));
        } catch (IOException e) {
            log.error("файл по данному пути не найден :(");
        }

        return numbers;

    }

}
