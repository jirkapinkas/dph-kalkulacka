package cz.jiripinkas.vatcalc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.jiripinkas.vatcalc.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
