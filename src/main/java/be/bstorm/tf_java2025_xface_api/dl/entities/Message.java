package be.bstorm.tf_java2025_xface_api.dl.entities;

import be.bstorm.tf_java2025_xface_api.dl.entities.msc.BaseEntityGenerated;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, of = {"sender","content"})
public class Message extends BaseEntityGenerated<Long> {

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Conversation conversation;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User sender;

    @Getter @Setter
    @Column(nullable = false, length = 2000)
    private String content;

    public Message(User sender, String content) {
        this.sender = sender;
        this.content = content;
    }
}
