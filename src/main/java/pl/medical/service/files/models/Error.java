package pl.medical.service.files.models;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
public class Error {
    private int code;
    private String message;
}
