package be.bstorm.tf_java2025_xface_api.dl.entities;

import be.bstorm.tf_java2025_xface_api.dl.entities.msc.BaseEntityGenerated;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, of = {"content"}) @ToString(callSuper = true, of = "content")
@Entity
public class Comment extends BaseEntityGenerated<Long> {

    @Getter @Setter
    @Column(nullable = false,length = 500)
    private String content;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private User user;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Post post;
}
