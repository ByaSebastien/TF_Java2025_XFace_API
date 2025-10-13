package be.bstorm.tf_java2025_xface_api.dl.entities;

import be.bstorm.tf_java2025_xface_api.dl.entities.msc.BaseEntityGenerated;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, of = {"reaction"}) @ToString(callSuper=true, of = {"reaction"})
@Entity
public class CommentReaction extends BaseEntityGenerated<Long> {

    @Getter @Setter
    @Column(nullable = false, length = 10)
    private String reaction;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private User user;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Comment comment;
}
