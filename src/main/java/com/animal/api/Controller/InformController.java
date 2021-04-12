package com.animal.api.Controller;

import com.animal.api.Exception.InformNotFoundException;
import com.animal.api.Entity.Inform;
import com.animal.api.Repository.InformRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
public class InformController {

    private final InformRepository repository;

    public InformController(InformRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/informs")
    List<Inform> all() {
        return repository.findAll();
    }

    @PostMapping("/informs")
    Inform newInform(@RequestBody Inform newInform, @RequestParam(value = "file") MultipartFile multipartFile) throws IOException{

        String file_name = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        newInform.setImg_url(file_name);
        Inform savedInform = repository.save(newInform);
        String uploadDir = "/animal_imgs/"+savedInform.getId();
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try(InputStream inputstream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(file_name);
            Files.copy(inputstream,filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new IOException("Could not upload file "+file_name);
        }

        return savedInform;
    }

    @GetMapping("/informs/{id}")
    Inform one(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new InformNotFoundException(id));

    }

    @PutMapping("/informs/{id}")
    Inform replaceInform(@RequestBody Inform newInform, @PathVariable int id) {

        return repository.findById(id)
                .map(inform -> {
                    inform.setNow_status(newInform.getNow_status());
                    inform.setHandler_id(newInform.getHandler_id());
                    inform.setProgress_time(newInform.getProgress_time());
                    return repository.save(inform);
                })
                .orElseGet(() -> {
                    newInform.setId(id);
                    return repository.save(newInform);
                });
    }

    @DeleteMapping("/informs/{id}")
    void deleteInform(@PathVariable int id) {
        repository.deleteById(id);
    }
}
