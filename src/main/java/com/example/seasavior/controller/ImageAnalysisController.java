package com.example.seasavior.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.seasavior.model.MarineSpecies;
import com.example.seasavior.service.ImageAnalysisService;
@RestController
public class ImageAnalysisController {

    @Autowired
    private ImageAnalysisService analysisService;

    @PostMapping("/api/analyze")
    public ResponseEntity<MarineSpecies> analyzeImage(@RequestParam("file") MultipartFile file) {
        try {
            // Converta o MultipartFile para Mat (imagem OpenCV)
            Mat image = convertMultipartFileToMat(file);

            // Realize a análise de imagem e obtenha informações sobre o animal
            MarineSpecies species = analysisService.analyzeImageAndGetSpeciesInfo(image);

            return new ResponseEntity<>(species, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Mat convertMultipartFileToMat(MultipartFile file) throws IOException {
        // Converte o MultipartFile em bytes
        byte[] bytes = file.getBytes();
        
        // Converte bytes para um ByteArrayInputStream
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        
        // Lê a imagem do ByteArrayInputStream usando o OpenCV
        Mat image = Imgcodecs.imdecode(new MatOfByte(bytes), Imgcodecs.IMREAD_UNCHANGED);
        
        // Verifica se a imagem foi carregada corretamente
        if (image.empty()) {
            throw new IOException("Não foi possível ler a imagem");
        }
        
        return image;
    }
}