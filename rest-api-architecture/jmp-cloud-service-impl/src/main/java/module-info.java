module com.epam.training.backend_services.rest.jmp_service_impl {

    requires com.epam.training.backend_services.rest.jmp_dto;
    requires com.epam.training.backend_services.rest.jmp_service;

    requires spring.boot;
    requires spring.beans;
    requires spring.context;
    requires spring.aop;
    requires spring.core;
    requires spring.boot.autoconfigure;
    requires spring.data.commons;
    requires spring.data.jpa;
    requires com.zaxxer.hikari;
    requires com.h2database;
    requires java.sql;
    requires spring.orm;
    requires spring.boot.starter.data.jpa;
    requires spring.boot.starter.jdbc;
    requires jakarta.persistence;
    requires static lombok;
    requires org.slf4j;
    requires jakarta.activation;
    requires org.hibernate.orm.core;
    requires org.mapstruct;
    requires java.xml;
    requires jakarta.validation;

    exports com.epam.training.backend_services.rest.service.impl.heap;
    exports com.epam.training.backend_services.rest.service.impl.sql;

}