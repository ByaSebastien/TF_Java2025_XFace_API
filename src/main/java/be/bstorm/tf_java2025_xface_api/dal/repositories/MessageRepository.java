package be.bstorm.tf_java2025_xface_api.dal.repositories;

import be.bstorm.tf_java2025_xface_api.dl.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select m from Message m join m.conversation c where c.id = :conversationId order by m.createdAt")
    List<Message> findAllByConversationId(UUID conversationId);
}

