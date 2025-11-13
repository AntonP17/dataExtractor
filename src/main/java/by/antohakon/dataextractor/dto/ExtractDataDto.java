package by.antohakon.dataextractor.dto;

import lombok.Builder;

@Builder
public record ExtractDataDto(String path, int numberPosition) {
}
