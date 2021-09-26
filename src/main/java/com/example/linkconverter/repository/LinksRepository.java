package com.example.linkconverter.repository;

import com.example.linkconverter.model.Links;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinksRepository extends JpaRepository<Links, Integer> {
}
