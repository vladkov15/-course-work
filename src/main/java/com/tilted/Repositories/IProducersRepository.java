package com.tilted.Repositories;

import com.tilted.Models.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProducersRepository extends JpaRepository<Producer, Integer> {
}
