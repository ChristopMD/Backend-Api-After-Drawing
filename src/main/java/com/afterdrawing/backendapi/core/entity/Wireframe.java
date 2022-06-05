package com.afterdrawing.backendapi.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wireframe")
@Data
//anotations for image
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wireframe {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "name", nullable = false, unique = false)
    private String name;
    //wireFrameName
    //columns for image

    @Column(name = "type")
    @JsonIgnore
    private String type;

    @Column(name = "image", unique = false, nullable = false, length = 100000)
    @JsonIgnore
    private byte[] image;

    //classes and posiciones

    @ElementCollection
    @CollectionTable(name ="wireframe_classes" , joinColumns=@JoinColumn(name="wireframe_id"))
    @Column(name = "classes")
    @JoinColumn(name = "wireframe_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<String> classes;



    //@ElementCollection
    @ElementCollection
    @CollectionTable(name ="wireframe_classes_pos_x1" , joinColumns=@JoinColumn(name="wireframe_id"))
    @Column(name = "x1")
    @JoinColumn(name = "wireframe_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Float> X1;

    @ElementCollection
    @CollectionTable(name ="wireframe_classes_pos_y1" , joinColumns=@JoinColumn(name="wireframe_id"))
    @Column(name = "y1")
    @JoinColumn(name = "wireframe_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Float> Y1;

    @ElementCollection
    @CollectionTable(name ="wireframe_classes_pos_x2" , joinColumns=@JoinColumn(name="wireframe_id"))
    @Column(name = "x2")
    @JoinColumn(name = "wireframe_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Float> X2;

    @ElementCollection
    @CollectionTable(name ="wireframe_classes_pos_y2" , joinColumns=@JoinColumn(name="wireframe_id"))
    @Column(name = "y2")
    @JoinColumn(name = "wireframe_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Float> Y2;

    @ElementCollection
    @CollectionTable(name ="wireframe_code_classes" , joinColumns=@JoinColumn(name="wireframe_id"))
    @Column(name = "code")
    @JoinColumn(name = "wireframe_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<String> code;


    //siguiente desarrollador trabaje la implementación de ruta
    /*
    @Size(max = 80)
    @Column(name = "route", nullable = false, unique = true)
    private String route;

    @OneToOne
    @JoinColumn(name = "interface_id", updatable = false, nullable = false)
    private Interface anInterface;
    */





}
