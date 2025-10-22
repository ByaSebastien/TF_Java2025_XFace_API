package be.bstorm.tf_java2025_xface_api.dl.entities;

import be.bstorm.tf_java2025_xface_api.dl.entities.msc.BaseEntityNotGenerated;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, of = {"users"})
public class Conversation extends BaseEntityNotGenerated<UUID> {

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Message> messages = new HashSet<>();

    public Set<User> getUsers() {
        return Set.copyOf(users);
    }

    public void addUser(User u){
        users.add(u);
    }

    public void removeUser(User u){
        users.remove(u);
    }

    public Set<Message> getMessages() {
        return Set.copyOf(messages);
    }

    public void addMessage(Message m){
        messages.add(m);
        m.setConversation(this);
    }

    public void removeMessage(Message m){
        messages.remove(m);
    }

    public boolean isDirectBetween(User a, User b){
        return users.size() == 2 && users.contains(a) && users.contains(b);
    }
}
