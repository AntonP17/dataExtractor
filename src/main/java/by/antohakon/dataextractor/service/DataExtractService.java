package by.antohakon.dataextractor.service;

import by.antohakon.dataextractor.dto.DataResponseDto;
import by.antohakon.dataextractor.dto.ExtractDataDto;

import java.io.FileNotFoundException;

public interface DataExtractService {

      DataResponseDto extractData(ExtractDataDto extractDataDto);
      int[] sortArray(int[] array);
      DataResponseDto findNumber(int[] sortesArray, int numberPosition);

}
