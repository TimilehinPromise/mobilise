package com.code.mobilise.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMessage {
    private String message;
}
