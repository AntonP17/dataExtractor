package by.antohakon.dataextractor.dto;

import lombok.Builder;

@Builder
public record DataResponseDto(String text, int findNumber) {
}
