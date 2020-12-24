package com.dicicip.starter.controller;

import com.dicicip.starter.model.Permission;
import com.dicicip.starter.repository.PermissionRepository;
import com.dicicip.starter.util.APIResponse;
import com.dicicip.starter.util.query.DB;
import com.dicicip.starter.util.query.QueryHelpers;
import com.dicicip.starter.util.validator.Validator;
import com.dicicip.starter.util.validator.ValidatorItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionRepository repository;

    @Autowired
    DB db;

    @RequestMapping(method = RequestMethod.GET)
    public APIResponse<?> getAll(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return new APIResponse<>(QueryHelpers.getData(request.getParameterMap(), "permissions", db));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/detail")
    public APIResponse<?> getOne(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        Optional<Permission> permission = this.repository.findById(id);

        if (permission.isPresent()) {
            return new APIResponse<>(permission.get());
        } else {
            response.setStatus(400);
            return new APIResponse<>(
                    null,
                    false,
                    "Failed get data"
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/store")
    public APIResponse<?> store(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Permission requestBody
    ) {

        Validator<Permission> validator = new Validator<>(
                requestBody,
                new ValidatorItem("name", "required"),
                new ValidatorItem("module_id", "required")
        );

        if (validator.valid()) {
            return new APIResponse<>(repository.save(requestBody));
        } else {
            response.setStatus(400);
            return new APIResponse<>(
                    validator.getErrorsList(),
                    false,
                    "Failed save data"
            );
        }

    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}/update")
    public APIResponse<?> update(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Permission requestBody
    ) {

        Validator<Permission> validator = new Validator<>(
                requestBody,
                new ValidatorItem("name", "required"),
                new ValidatorItem("module_id", "required")
        );

        if (validator.valid()) {

            Optional<Permission> permission = this.repository.findById(id);

            if (permission.isPresent()) {

                requestBody.id = permission.get().id;

                return new APIResponse<>(this.repository.save(requestBody));

            } else {
                response.setStatus(400);
                return new APIResponse<>(
                        null,
                        false,
                        "Failed get data"
                );
            }

        } else {
            response.setStatus(400);
            return new APIResponse<>(
                    validator.getErrorsList(),
                    false,
                    "Failed save data"
            );
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}/delete")
    public APIResponse<?> delete(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        Optional<Permission> permission = this.repository.findById(id);

        if (permission.isPresent()) {

            this.repository.delete(permission.get());

            return new APIResponse<>(null);

        } else {
            response.setStatus(400);
            return new APIResponse<>(
                    null,
                    false,
                    "Failed delete data"
            );
        }
    }

}
