package com.isi.maven.services.service.imp;

import com.isi.maven.services.dto.AppRoles;
import com.isi.maven.services.exception.RequestException;
import com.isi.maven.services.mapping.AppRolesMapper;
import com.isi.maven.services.repository.IAppRolesRepository;
import com.isi.maven.services.service.IAppRolesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@Getter
@Setter
@CacheConfig(cacheNames = "roles")
public class AppRolesServiceImpl implements IAppRolesService {

    private final IAppRolesRepository iAppRolesRepository;
    private final AppRolesMapper appRolesMapper;
    private final MessageSource messageSource;

    @Transactional(readOnly = true)
    @Override
    public List<AppRoles> getAppRoles() {
        return StreamSupport.stream(iAppRolesRepository.findAll().spliterator(), false)
                .map(appRolesMapper::toAppRoles)
                .collect(Collectors.toList());
    }

    @Cacheable(key = "#id")
    @Transactional(readOnly = true)
    @Override
    public AppRoles getAppRole(int id) {
        return appRolesMapper.toAppRoles(iAppRolesRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("role.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Transactional
    @Override
    public AppRoles createAppRoles(AppRoles appRoles) {
        try {
            return appRolesMapper.toAppRoles(iAppRolesRepository.save(appRolesMapper.fromAppRoles(appRoles)));
        } catch (Exception e) {
            throw new RequestException("Erreur lors de la création du rôle : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @CachePut(key = "#id")
    @Transactional
    @Override
    public AppRoles updateAppRoles(int id, AppRoles appRoles) {
        try {
            return iAppRolesRepository.findById(id)
                    .map(entity -> {
                        appRoles.setId(id);
                        return appRolesMapper.toAppRoles(iAppRolesRepository.save(appRolesMapper.fromAppRoles(appRoles)));
                    }).orElseThrow(() -> new EntityNotFoundException(
                            messageSource.getMessage("role.notfound", new Object[]{id}, Locale.getDefault())
                    ));
        } catch (Exception e) {
            throw new RequestException("Erreur lors de la mise à jour du rôle : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @CacheEvict(key = "#id")
    @Transactional
    @Override
    public void deleteAppRoles(int id) {
        if (!iAppRolesRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    messageSource.getMessage("role.notfound", new Object[]{id}, Locale.getDefault()));
        }
        iAppRolesRepository.deleteById(id);
    }
}
