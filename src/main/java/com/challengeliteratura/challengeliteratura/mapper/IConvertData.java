package com.challengeliteratura.challengeliteratura.mapper;

public interface IConvertData {
    <T> T getInformationAPI(String json, Class<T> classed);
}