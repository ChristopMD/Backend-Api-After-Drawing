package com.afterdrawing.backendapi.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "interface")
@Data
public class Interface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "interfaceName", nullable = false)
    private String interfaceName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore //Evita que se muestre el atributo al realizar un request get
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "wireframe_id", updatable = false, nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Wireframe wireframe;

}
