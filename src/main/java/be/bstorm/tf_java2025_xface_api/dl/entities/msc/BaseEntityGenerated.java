package be.bstorm.tf_java2025_xface_api.dl.entities.msc;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false) @ToString
@MappedSuperclass
public abstract class BaseEntityGenerated<T> extends  BaseEntity {

    @Id
    @GeneratedValue
    private T id;
}
