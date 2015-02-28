package cz.jiripinkas.vatcalc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cz.jiripinkas.vatcalc.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	@Query("select i from Item i where i.visible = true")
	List<Item> findVisible();

	@Query("select i from Item i where i.visible = false")
	List<Item> findInvisible();

	@Transactional
	@Modifying
	@Query("update Item i set i.visible = true where i.id = ?1")
	void setVisible(int id);
}
