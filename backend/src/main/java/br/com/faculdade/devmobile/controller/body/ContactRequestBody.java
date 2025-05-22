package br.com.faculdade.devmobile.controller.body;

public record ContactRequestBody(
        String name,
        String email,
        String message) {
}
