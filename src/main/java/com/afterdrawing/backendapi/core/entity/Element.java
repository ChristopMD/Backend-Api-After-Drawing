package com.afterdrawing.backendapi.core.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
@Table(name = "element")
@Data
@NoArgsConstructor
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "name", nullable = false)
    private String ClassName;

    //@Size(max = 15)
    @Column(name = "positionX1", nullable = false)
    private Float positionX1;

    //@Size(max = 15)
    @Column(name = "positionY1", nullable = false)
    private Float positionY1;

    @Column(name = "positionX2", nullable = false)
    private Float positionX2;

    @Column(name = "positionY2", nullable = false)
    private Float positionY2;

    @Column(name = "element_type", nullable = false)
    @Size(max = 25)
    private Float elementType;





    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "interface_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Interface anInterface;


}
