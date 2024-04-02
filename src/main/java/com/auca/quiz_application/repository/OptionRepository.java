package com.auca.quiz_application.repository;

import com.auca.quiz_application.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface OptionRepository extends JpaRepository<Option, UUID> {
}
