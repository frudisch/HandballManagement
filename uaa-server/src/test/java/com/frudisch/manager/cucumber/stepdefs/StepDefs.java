package com.frudisch.manager.cucumber.stepdefs;

import com.frudisch.manager.UaaServerApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = UaaServerApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
