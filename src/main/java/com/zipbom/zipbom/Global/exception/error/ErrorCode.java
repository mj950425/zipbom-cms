package com.zipbom.zipbom.Global.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
@AllArgsConstructor
public enum ErrorCode {
    HANDLE_ACCESS_DENIED(403, "C0006", "Access denied");
    private final int status;
    private final String code;
    private final String message;
}
