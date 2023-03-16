package com.example.organizationservice.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "parent")
    private List<Organization> childs;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Organization parent;

    @Column(name = "is_department")
    private Boolean isDepartment=false;

    @Column(name = "admin_name")
    private String adminName;

    @Column(name = "admin_lastname")
    private String adminLastname;
}
