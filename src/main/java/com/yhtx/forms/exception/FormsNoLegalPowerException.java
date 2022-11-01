package com.yhtx.forms.exception;

import lombok.extern.slf4j.Slf4j;

/**
 *Permission exception
 */
@Slf4j
public class FormsNoLegalPowerException extends RuntimeException {

    private static final String NO_LEGAL_POWER = "权限不足，该操作将被记录!";

    public FormsNoLegalPowerException() {
        this(NO_LEGAL_POWER);
    }

    public FormsNoLegalPowerException(String message) {
        if (null == message) {
            throw new FormsWebApiRuntimeException(NO_LEGAL_POWER);
        } else {
            throw new FormsWebApiRuntimeException(message);
        }
    }
}
