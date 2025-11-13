package by.antohakon.dataextractor.controller;

import by.antohakon.dataextractor.dto.DataResponseDto;
import by.antohakon.dataextractor.dto.ExtractDataDto;
import by.antohakon.dataextractor.service.DataExtractService;
import by.antohakon.dataextractor.service.DataExtractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/dataExtract")
public class DataExtractController {

    private final DataExtractService dataExtractService;

    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PostMapping
    public DataResponseDto searchNumber(@RequestBody ExtractDataDto extractDataDto){
        return dataExtractService.extractData(extractDataDto);
    }

}
