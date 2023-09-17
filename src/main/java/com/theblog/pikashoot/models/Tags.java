package com.theblog.pikashoot.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tags")
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagId;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private Set<BlogPost> blogPosts = new HashSet<>();

}
