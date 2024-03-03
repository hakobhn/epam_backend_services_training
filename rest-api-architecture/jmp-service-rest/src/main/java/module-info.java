open module jmp_rest {

    requires com.epam.training.backend_services.rest.jmp_dto;
    requires com.epam.training.backend_services.rest.jmp_service;

    requires spring.web;
    requires spring.boot;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.boot.starter;
    requires static lombok;
    requires org.slf4j;
    requires swagger.ui;
    requires io.swagger.v3.oas.annotations;
    requires io.swagger.v3.core;
    requires io.swagger.v3.oas.models;
    requires org.springdoc.openapi.webmvc.core;
    requires org.springdoc.openapi.ui;
    requires org.springdoc.openapi.common;
    requires spring.data.jpa;
    requires java.sql;
}