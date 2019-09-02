package com.toonext.localization.validation;

import com.toonext.dto.Outcome;
import io.dropwizard.jersey.validation.ConstraintMessage;
import io.dropwizard.jersey.validation.JerseyViolationException;
import org.glassfish.jersey.server.model.Invocable;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.Set;
import java.util.stream.Collectors;

public class NBViolationExceptionMapper implements ExceptionMapper<JerseyViolationException> {

    @Override
    public Response toResponse(final JerseyViolationException exception) {
        final Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        final Invocable invocable = exception.getInvocable();
        final int status = ConstraintMessage.determineStatus(violations, invocable);

        Outcome outcome = new Outcome();
        outcome.setTitle("error " + status);
        outcome.addPayload("validation_errors", violations.stream().map((s) -> s.getMessage()).collect(Collectors.toList()));
        return Response.status(status)
                .type(MediaType.APPLICATION_JSON)
                .entity(status >= 500 ? outcome : outcome)
                .build();
    }
}