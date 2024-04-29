package com.dd.api.restapi.controllers.utility;

import com.dd.api.restapi.services.MaintenanceProvider;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is STRICTLY for internal maintenance.
 * It will not be exposed to a production environment.
 */
@RestController
@RequestMapping("/diamond-data/api/xyz/abc/maintenance")
public class MaintenanceController {

    private final String username = "*@dd-devs";
    private final String encryptedPassword = Base64.encodeBase64String("dd-devs_CLEAR123".getBytes());

    @Autowired
    private final MaintenanceProvider maintenanceProvider;

    @Autowired
    public MaintenanceController(MaintenanceProvider maintenanceProvider) {
        this.maintenanceProvider = maintenanceProvider;
    }

    @DeleteMapping
    @RequestMapping("/clear-deleted-records")
    public boolean deleteRecords(@RequestParam String userId, @RequestParam String password) {

        if(!encryptedPassword.equals(Base64.encodeBase64String(password.getBytes())) || userId.equals(username)) {
            return false;
        }

        return maintenanceProvider.clearDeletedRecords();

    }

}
