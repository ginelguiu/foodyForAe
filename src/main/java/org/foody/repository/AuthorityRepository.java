package org.foody.repository;

import org.foody.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
