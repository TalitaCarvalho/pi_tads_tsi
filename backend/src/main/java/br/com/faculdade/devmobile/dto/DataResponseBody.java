package br.com.faculdade.devmobile.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DataResponseBody<T> {
    T data;
}
