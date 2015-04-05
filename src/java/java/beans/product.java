/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Stateful;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author Sandeep
 */
@Stateful
public class product {

    private int ProductId;
    private String name;
    private String Description;
    private int Quantity;

    public product() {

    }

    product(JsonObject ob) {
        this.setDescription(ob.getString("description"));
      this.setProductId(ob.getInt("id"));
      this.setName(ob.getString("name"));
      this.setQuantity(ob.getInt("quantity"));
      
    }
    
    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int ProductId) {
        this.ProductId = ProductId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getQuantity() {
        return Quantity;
    }
    public Object toJSON(){
        JsonObjectBuilder obj = Json.createObjectBuilder()
                .add("productId", ProductId)
                .add("name", name)
                .add("description", Description)
                .add("quantity", Quantity);
        return obj.build();
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

}
