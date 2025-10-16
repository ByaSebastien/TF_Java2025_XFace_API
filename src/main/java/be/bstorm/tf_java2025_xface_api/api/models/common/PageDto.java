package be.bstorm.tf_java2025_xface_api.api.models.common;

import java.util.List;

public record PageDto<T>(
        List<T> content,
        int currentPage,
        int totalPages,
        int total
) {
}
