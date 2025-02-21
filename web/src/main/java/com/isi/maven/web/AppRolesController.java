package com.isi.maven.web;

import com.isi.maven.services.dto.AppRoles;
import com.isi.maven.services.service.IAppRolesService;
import com.isi.maven.services.service.imp.AppRolesServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roles")
@AllArgsConstructor
public class AppRolesController {
    private final IAppRolesService appRolesService;

    @GetMapping
    public List<AppRoles> getAppRoles() {
        return appRolesService.getAppRoles();
    }

    @GetMapping("/{id}")
    public AppRoles getAppRoles(@PathVariable("id") int id) {
        return appRolesService.getAppRole(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AppRoles createAppRoles(@Valid @RequestBody AppRoles appRoles) {
        return appRolesService.createAppRoles(appRoles);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppRoles> updateAppRoles(@PathVariable("id") int id, @Valid @RequestBody AppRoles appRoles) {
        return ResponseEntity.ok(appRolesService.updateAppRoles(id, appRoles));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppRoles(@PathVariable("id") int id) {
        appRolesService.deleteAppRoles(id);
        return ResponseEntity.noContent().build();
    }


}
