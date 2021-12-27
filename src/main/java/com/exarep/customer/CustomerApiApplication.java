package com.exarep.customer;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        tags = {
                @Tag(name="CustomerIndividual", description="CustomerIndividual Operations.")
        },
        info = @Info(
                title="Customer API",
                version = "0.0.1",
                contact = @Contact(
                        name = "Customer API Support",
                        url = "http://exampleurl.com/contact",
                        email = "techsupport@example.com")
        )
)
public class CustomerApiApplication extends Application {
}
