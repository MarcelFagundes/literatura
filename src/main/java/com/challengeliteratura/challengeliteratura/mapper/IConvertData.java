package com.challengeliteratura.challengeliteratura.mapper;

public interface IConvertData {

    <T> T obterInformacao(String json, Class<T> clase);

}