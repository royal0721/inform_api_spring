package com.animal.api.service;

import com.animal.api.entity.Inform;
import com.animal.api.exception.InformNotFoundException;
import com.animal.api.repository.InformRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class InformService {

    private final InformRepository repository;

    public InformService(InformRepository repository) {
        this.repository = repository;
    }

    public List<Inform> getAll() {
        return repository.findAll();
    }

    public Inform uploadInform(Inform newInform, MultipartFile multipartFile) throws IOException {

        String file_name = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        newInform.setImg_url(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        System.out.println(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        try{
            newInform.setImg_url(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        }catch (IOException e){
            throw new IOException("Could not upload file "+file_name);
        }
        Inform savedInform = repository.save(newInform);
        return savedInform;

    }

    public Inform getOne(Integer id){

        return repository.findById(id)
                .orElseThrow(() -> new InformNotFoundException(id));

    }

    public Inform updateInform(Inform newInform, Integer id) {

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

    public void deleteInform(Integer id) {

        repository.deleteById(id);

    }



}
