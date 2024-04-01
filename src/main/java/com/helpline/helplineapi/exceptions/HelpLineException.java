package com.helpline.helplineapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Representa uma exceção base dentro da api da HelpLine.
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class HelpLineException extends RuntimeException {

    /**
     * A mensagem de erro.
     */
    private String message;

    /**
     * O código de erro.
     */
    private int errorCode;

    /**
     * Data e hora do momento da exceção.
     */
    private LocalDateTime timestamp;
}
