package cz.jiripinkas.vatcalc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.jiripinkas.vatcalc.entity.Configuration;

public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {

}
