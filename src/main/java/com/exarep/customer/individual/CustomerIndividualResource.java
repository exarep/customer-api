package com.exarep.customer.individual;

import com.exarep.customer.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;
import java.util.Optional;

@Path("/api/customers/individuals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
@Slf4j
@Tag(name = "CustomerIndividual")
public class CustomerIndividualResource {

    private CustomerIndividualService customerIndividualService;

    @GET
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "Get All Individual Customers",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = SchemaType.ARRAY, implementation = CustomerIndividual.class)))
            }
    )
    public Response get() {
        return Response.ok(customerIndividualService.findAll()).build();
    }

    @GET
    @Path("/{customerIndividualId}")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "Get Customer Individual by customerIndividualId",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = SchemaType.OBJECT, implementation = CustomerIndividual.class))),
                    @APIResponse(
                            responseCode = "404",
                            description = "No Customer Individual found for customerIndividualId provided",
                            content = @Content(mediaType = "application/json")),
            }
    )
    public Response getById(@PathParam("customerIndividualId") Integer customerIndividualId) {
        Optional<CustomerIndividual> optional = customerIndividualService.findById(customerIndividualId);
        return !optional.isEmpty() ? Response.ok(optional.get()).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "201",
                            description = "Customer Individual Created",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = SchemaType.OBJECT, implementation = CustomerIndividual.class)))
            }
    )
    public Response post(@Valid CustomerIndividual customerIndividual) {
        customerIndividualService.save(customerIndividual);
        return Response.status(Response.Status.CREATED).entity(customerIndividual).build();
    }

    @PUT
    @Path("/{customerIndividualId}")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "Customer updated",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = SchemaType.OBJECT, implementation = CustomerIndividual.class))),
                    @APIResponse(
                            responseCode = "404",
                            description = "No Customer found for customerId provided",
                            content = @Content(mediaType = "application/json")),
            }
    )
    public Response put(@PathParam("customerIndividualId") Integer customerIndividualId, @Valid CustomerIndividual customerIndividual) {
        if (!Objects.equals(customerIndividualId, customerIndividual.getCustomerIndividualId())) {
            throw new ServiceException("PUT failed. PathParam.customerIndividualId does not equal " +
                    "CustomerIndividual.customerIndividualId. customerIndividualId[%s] != CustomerIndividual.customerIndividualId[%s]",
                    customerIndividualId, customerIndividual.getCustomerIndividualId());
        }
        customerIndividualService.update(customerIndividual);
        return Response.ok(customerIndividual).build();
    }

}