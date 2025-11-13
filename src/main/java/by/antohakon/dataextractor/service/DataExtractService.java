package by.antohakon.dataextractor.service;

import by.antohakon.dataextractor.dto.DataResponseDto;
import by.antohakon.dataextractor.dto.ExtractDataDto;

import java.io.FileNotFoundException;

public interface DataExtractService {

      DataResponseDto extractData(ExtractDataDto extractDataDto);
      boolean fileExists(String path);
      int[] extractDataFromFile(ExtractDataDto extractDataDto) throws FileNotFoundException;
      int[] sortArray(int[] array);
      DataResponseDto findNumber(int numberPosition);

}
