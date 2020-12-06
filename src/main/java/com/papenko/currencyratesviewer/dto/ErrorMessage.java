package com.papenko.currencyratesviewer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ErrorMessage implements ResponseEntityBody {
    boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String error;

    public ErrorMessage(String error) {
        this.success = false;
        this.error = error;
    }

    public ErrorMessage() {
        this.success = true;
        this.error = null;
    }
}
