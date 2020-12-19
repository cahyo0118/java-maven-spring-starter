package com.dicicip.starter.controller;

import com.dicicip.starter.model.Module;
import com.dicicip.starter.repository.ModuleRepository;
import com.dicicip.starter.util.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

    @Autowired
    private ModuleRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public APIResponse<?> getAll(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return new APIResponse<>(repository.findAll());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/store")
    public APIResponse<?> store(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Module module
    ) {

        return new APIResponse<>(repository.save(module));
    }

}
