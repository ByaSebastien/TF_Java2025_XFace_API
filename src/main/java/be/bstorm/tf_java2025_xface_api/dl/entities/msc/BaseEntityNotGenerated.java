package be.bstorm.tf_java2025_xface_api.dl.entities.msc;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false) @ToString
@MappedSuperclass
public abstract class BaseEntityNotGenerated<T> {

    @Id
    private T id;
}
