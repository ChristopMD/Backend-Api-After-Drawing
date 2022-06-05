package com.afterdrawing.backendapi.resource;

import com.afterdrawing.backendapi.core.entity.Project;
import com.afterdrawing.backendapi.core.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class SaveWireframeResource {
    @Size(max = 50)
    @Column(name = "name", nullable = false, unique = false)
    private String name;
    //wireFrameName
    //columns for image

    @Column(name = "type")
    private String type;

    @Column(name = "image", unique = false, nullable = false, length = 100000)
    @JsonIgnore
    private byte[] image;

    //classes and posiciones

    @ElementCollection
    @CollectionTable(name ="wireframe_classes" , joinColumns=@JoinColumn(name="wireframe_id"))
    @Column(name = "classes")
    private List<String> classes;

    //@ElementCollection
    @ElementCollection
    @CollectionTable(name ="wireframe_classes_pos_x1" , joinColumns=@JoinColumn(name="wireframe_id"))
    @Column(name = "x1")
    @JsonIgnore
    private List<Float> X1;

    @ElementCollection
    @CollectionTable(name ="wireframe_classes_pos_y1" , joinColumns=@JoinColumn(name="wireframe_id"))
    @Column(name = "y1")
    @JsonIgnore
    private List<Float> Y1;

    @ElementCollection
    @CollectionTable(name ="wireframe_classes_pos_x2" , joinColumns=@JoinColumn(name="wireframe_id"))
    @Column(name = "x2")
    @JsonIgnore
    private List<Float> X2;

    @ElementCollection
    @CollectionTable(name ="wireframe_classes_pos_y2" , joinColumns=@JoinColumn(name="wireframe_id"))
    @Column(name = "y2")
    @JsonIgnore
    private List<Float> Y2;

    @ElementCollection
    @CollectionTable(name ="wireframe_code_classes" , joinColumns=@JoinColumn(name="wireframe_id"))
    @Column(name = "code")
    private List<String> code;

    /*
    @ManyToOne
    @JoinColumn(columnDefinition="integer", name = "project_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

    @ManyToOne
    @JoinColumn(columnDefinition="integer", name = "user_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    */

    //siguiente desarrollador trabaje la implementación de ruta
    /*
    @Size(max = 80)
    @Column(name = "route", nullable = false, unique = true)
    private String route;

     */

}
