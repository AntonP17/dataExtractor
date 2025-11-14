package by.antohakon.dataextractor.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record ExtractDataDto(

        @NotBlank(message = "Путь к файлу не может быть пустым")
        @Pattern(
                regexp = "^(?:[a-zA-Z]:)?[\\\\/][^:*?\"<>|\\n]*$",
                message = "Путь должен быть в формате: Windows - C:\\\\folder\\\\file.xlsx, Linux/Mac - /home/user/file.xlsx"
        )
        String path,

        @Min(value = 1, message = "Позиция должна быть положительным числом")
        int numberPosition
) {
}
