package cz.jiripinkas.vatcalc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import cz.jiripinkas.vatcalc.entity.Item;
import cz.jiripinkas.vatcalc.repository.ItemRepository;

@Service
public class ItemService {

	private Logger log = LoggerFactory.getLogger(ItemService.class);

	@Autowired
	private ItemRepository itemRepository;

	public List<Item> findVisible() {
		return itemRepository.findVisible();
	}

	protected void validateInput(Item item) {
		if (item.isCenaSDphDisabled() && item.isCenaBezDphDisabled()) {
			log.error("Err. input: " + item.toString());
			throw new UnsupportedOperationException("input error, see log");
		} else if (!item.isCenaSDphDisabled() && !item.isCenaBezDphDisabled()) {
			log.error("Err. input: " + item.toString());
			throw new UnsupportedOperationException("input error, see log");
		} else if (item.isCenaSDphDisabled() && !item.isCenaBezDphDisabled()) {
			// uzivatel zadal cenu bez dph
			double dphSpravne = (double)Math.round((item.getCenaBezDph() * ((double)item.getDph() / 100)) * 100) / 100;
			double cenaSDphSpravne = (double)Math.round((item.getCenaBezDph() + dphSpravne) * 100) / 100;
			if (item.getCastkaDph() != dphSpravne) {
				log.error("Err. input: " + item.toString() + ", spravne dph: " + dphSpravne);
				throw new UnsupportedOperationException("input error, see log");
			}
			if (item.getCenaSDph() != cenaSDphSpravne) {
				log.error("Err. input: " + item.toString() + ", spravna cena s dph: " + cenaSDphSpravne);
				throw new UnsupportedOperationException("input error, see log");
			}
		} else if (!item.isCenaSDphDisabled() && item.isCenaBezDphDisabled()) {
			// uzivatel zadal cenu s dph
			double koeficient = (double)Math.round(((double)item.getDph() / ((double)item.getDph() + 100)) * 10000) / 10000;
			double dphSpravne = (double)Math.round((item.getCenaSDph() * koeficient) * 100) / 100;
			double cenaBezDphSpravne = (double)Math.round((item.getCenaSDph() - dphSpravne) * 100) / 100;
			if (item.getCastkaDph() != dphSpravne) {
				log.error("Err. input: " + item.toString() + ", spravne dph: " + dphSpravne);
				throw new UnsupportedOperationException("input error, see log");
			}
			if (item.getCenaBezDph() != cenaBezDphSpravne) {
				log.error("Err. input: " + item.toString() + ", spravna cena bez dph: " + cenaBezDphSpravne);
				throw new UnsupportedOperationException("input error, see log");
			}
		} else {
			throw new UnsupportedOperationException("this should never happen");
		}
	}

	public void save(Item item) {
		validateInput(item);
		itemRepository.save(item);
	}

	public Item findOne(int id) {
		return itemRepository.findOne(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(int id) {
		itemRepository.delete(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Item> findInvisible() {
		return itemRepository.findInvisible();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void setVisible(int id) {
		itemRepository.setVisible(id);
	}

}
