package br.com.alexadrianodev.myapidemo.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class StandardError {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;
}
