package com.example.osahaneat.service.imp;




import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileServiceImp {

    boolean save(MultipartFile file);
    Resource load(String filename);


}
