package be.bstorm.tf_java2025_xface_api.dl.entities;

import be.bstorm.tf_java2025_xface_api.dl.entities.msc.BaseEntityGenerated;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@EqualsAndHashCode(callSuper=true, of = "title") @ToString(callSuper = true, of = {"title"})
@Entity @Table(name = "post_")
public class Post extends BaseEntityGenerated<Long> {

    @Getter @Setter
    @Column(nullable = false, length = 100)
    private String title;

    @Getter @Setter
    @Column(nullable = false, length = 500)
    private String content;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private User owner;

    @Getter @Setter
    @CollectionTable
    private Set<String> images = new HashSet<>();

    @Getter @Setter
    @CollectionTable
    private Set<String> tags = new HashSet<>();

    public Post(String title, String content, Set<String> tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
    }
}
