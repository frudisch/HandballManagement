package de.frudisch.service;

import de.frudisch.controller.AccountController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Frudisch on 09.04.2017.
 */
@RestController
public class AccountService {

    @Autowired
    AccountController accountController;
}
