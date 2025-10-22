package be.bstorm.tf_java2025_xface_api.dal.repositories;

import be.bstorm.tf_java2025_xface_api.dl.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, UUID> {

    @Query("""
        SELECT c
        FROM Conversation c
        JOIN c.users u
        WHERE u.id IN (:connectedUserId, :userId)
        GROUP BY c
        HAVING COUNT(u) = 2
    """)
    Optional<Conversation> findConversation(UUID connectedUserId,UUID userId);
}
