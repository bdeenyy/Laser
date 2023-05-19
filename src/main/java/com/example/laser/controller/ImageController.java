package com.example.laser.controller;

import com.example.laser.model.ImageLength;
import com.example.laser.repository.ImageLengthRepository;
import com.example.laser.service.ImageLengthCalculator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ImageController {

    private final ImageLengthCalculator calculator;
    private final ImageLengthRepository repository;

    public ImageController(ImageLengthCalculator calculator, ImageLengthRepository repository) {
        this.calculator = calculator;
        this.repository = repository;
    }

    @PostMapping("/upload")
    @ResponseBody
    public Map<String, Object> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        // Рассчитать длину контура изображения
        double length = calculator.calculateLength(file);

        // Сохранить результат в базе данных
        ImageLength imageLength = new ImageLength(file.getOriginalFilename(), length);
        repository.save(imageLength);

        // Прочитать содержимое файла SVG
        String svg = new String(file.getBytes(), StandardCharsets.UTF_8);

        // Вернуть результат в виде карты
        Map<String, Object> result = new HashMap<>();
        result.put("length", length);
        result.put("svg", svg);
        return result;
    }
}