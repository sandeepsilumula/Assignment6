package services;
import beans.product;
import beans.Productlist;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Sandeep
 */
@Path("/products")
@RequestScoped
public class Servlet {

    @Inject
    Productlist productList;

    @GET
    @Produces("application/json")
    public Response doGet() {
        //String result = getResults("SELECT * FROM products");
        //return result;
        return Response.ok(productList.toJSON()).build();

    }
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response doGet(@PathParam("id") String id) {

        return Response.ok(productList.get(Integer.parseInt(id)).toJSON()).build();
    }
    @POST
    @Consumes("application/json")
    public Response doPost(@Context UriInfo uri, String str) {
        product product = new product();
        JsonParser parser = Json.createParser(new StringReader(str));
        Map<String, String> map = new HashMap<>();
        String name = "", value;
        while (parser.hasNext()) {
            JsonParser.Event evt = parser.next();
            switch (evt) {
                case KEY_NAME:
                    name = parser.getString();
                    break;
                case VALUE_STRING:
                    System.out.println("inside map string value");
                    value = parser.getString();
                    map.put(name, value);
                    break;
                case VALUE_NUMBER:
                    System.out.println("inside map");
                    value = Integer.toString(parser.getInt());
                    map.put(name, value);
                    break;
            }
        }
        System.out.println("before product set");
        product.setProductId(Integer.parseInt(map.get("productId")));
        product.setName(map.get("name"));
        product.setDescription(map.get("description"));
        product.setQuantity(Integer.parseInt(map.get("quantity")));
        try {
            productList.add(product);
            //return Response.ok().build();
            return Response.ok(uri.getAbsolutePath().toString() + "/" + map.get("productId")).build();
        } catch (Exception ex) {
            return Response.status(500).build();
        }

    }
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response doPut(@Context UriInfo uri, @PathParam("id") String id, String str) {
        product product = new product();
        int changes = 0;
        JsonParser parser = Json.createParser(new StringReader(str));
        Map<String, String> map = new HashMap<>();
        String name = "", value;
        while (parser.hasNext()) {
            JsonParser.Event evt = parser.next();
            switch (evt) {
                case KEY_NAME:
                    name = parser.getString();
                    break;
                case VALUE_STRING:
                    value = parser.getString();
                    map.put(name, value);
                    break;
                case VALUE_NUMBER:
                    value = Integer.toString(parser.getInt());
                    map.put(name, value);
                    break;
            }
        }
        product.setProductId(Integer.parseInt(id));
        product.setName(map.get("name"));
        product.setDescription(map.get("description"));
        product.setQuantity(Integer.parseInt(map.get("quantity")));
        try {
            productList.set(Integer.parseInt(id), product);
            //return Response.ok().build();
            return Response.ok(uri.getAbsolutePath().toString()).build();
        } catch (Exception ex) {
            return Response.status(500).build();
        }

    }
    @DELETE
    @Path("{id}")
    public Response doDelete(@Context UriInfo uri, @PathParam("id") String id) {

        try {
            productList.remove(Integer.parseInt(id));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(500).build();
        }

    }

}
