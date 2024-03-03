module com.epam.training.backend_services.rest.jmp_dto {

    requires static lombok;
    requires spring.context;
    requires jakarta.validation;

    opens com.epam.training.backend_services.rest.dto;
    exports com.epam.training.backend_services.rest.dto;
    exports com.epam.training.backend_services.rest.exception;
}