package com.example.osahaneat.service;

import com.example.osahaneat.service.imp.FileServiceImp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class FileService implements FileServiceImp {

    @Value("${fileUpload.rootPath}")
    String rootPath ;
    private  Path root;

    public void init() {
        try {
            root = Paths.get(rootPath);
            if (Files.notExists(root)) {
                Files.createDirectories(root);
            }
        } catch (Exception e){
            System.out.println("erro create Folder " + e.getMessage() );
        }
    }

    @Override
    public boolean save(MultipartFile file){
        init();
        try {

            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
    } catch (Exception e){
            System.out.println("erro save Folder " + e.getMessage() );
            return false;
    }
        return true;
    }

    @Override
    public Resource load(String filename) {
        init();
        try{
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        }catch (Exception e){
            System.out.println("erro save Folder " + e.getMessage() );
        }
        return null ;
    }
}
