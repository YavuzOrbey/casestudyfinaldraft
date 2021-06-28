package com.casestudydraft.model;

/*


Base Model that every POJO extends. I wanted to get created_on and updated_on in each entity so this was the best way of doing that
For right now
 */

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
@MappedSuperclass
public abstract class BaseModel {

    @Column(name = "created_on",  updatable = false)
    final private Timestamp created_on;

    @Column(name = "updated_on")
    private Timestamp updated_on;


    public BaseModel() {
        this.created_on = new Timestamp(System.currentTimeMillis());
        this.updated_on = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getCreated_on() {
        return created_on;
    }

    public Timestamp getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Timestamp updated_on) {
        this.updated_on = updated_on;
    }

}
