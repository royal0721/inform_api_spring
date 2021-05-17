package com.animal.api.controller;

import com.animal.api.exception.InformNotFoundException;
import com.animal.api.entity.Inform;
import com.animal.api.repository.InformRepository;
import com.animal.api.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
public class InformController {

    @Autowired
    private InformService InformService;

    @GetMapping("/informs")
    List<Inform> all() {
        return InformService.getAll();
    }

    @PostMapping("/informs")
//    @RequestBody Inform newInform
    Inform newInform(@RequestPart("user") Inform newInform, @RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        return InformService.uploadInform(newInform,multipartFile);
    }

    @GetMapping("/informs/{id}")
    Inform one(@PathVariable Integer id) {
        return InformService.getOne(id);

    }

    @PutMapping("/informs/{id}")
    Inform replaceInform(@RequestBody Inform newInform, @PathVariable int id) {

        return InformService.updateInform(newInform,id);
    }

    @DeleteMapping("/informs/{id}")
    void deleteInform(@PathVariable Integer id) {
        InformService.deleteInform(id);
    }
}
