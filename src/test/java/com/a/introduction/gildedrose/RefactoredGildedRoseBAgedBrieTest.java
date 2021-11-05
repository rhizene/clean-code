package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RefactoredGildedRoseBAgedBrieTest {

	public static final String AGRED_BRIE = "Aged Brie";
	public static final int SELLIN_NOT_EXPIRED = 4;
	public static final int QUALITY_GOOD = 3;
	public static final int SELLIN_EXPIRED = -1;
	public static final int MAX_QUALITY = 50;

	private GildedRose createGildedRoseWithAgredBrie(int sellin, int quality) {
		Item item = createAgedBrieItem(sellin, quality);
		Item[] items = new Item[]{item};
		return new GildedRose(items);
	}

	private Item createAgedBrieItem(int sellin, int quality) {
		return new Item(AGRED_BRIE, sellin, quality);
	}

	private void assertBrie(Item expected, Item actual) {
		assertEquals(expected.name, actual.name, "Name not the same");
		assertEquals(expected.sellIn, actual.sellIn, "Sellin not same");
		assertEquals(expected.quality, actual.quality, "Quality not same");
	}

	@Test
	public void updateQuality_unexpiredBrie_qualityPlus1() {
		GildedRose app = createGildedRoseWithAgredBrie(SELLIN_NOT_EXPIRED, QUALITY_GOOD);

		app.updateQuality();

		Item actual = app.items[0];
		Item expected = createAgedBrieItem(SELLIN_NOT_EXPIRED - 1, QUALITY_GOOD + 1);
		assertBrie(expected, actual);
	}

	@Test
	public void updateQuality_expiredSellinBrie_qualityPlus2() {
		GildedRose app = createGildedRoseWithAgredBrie(SELLIN_EXPIRED, QUALITY_GOOD);

		app.updateQuality();

		final Item actual = app.items[0];
		final Item expected = createAgedBrieItem(SELLIN_EXPIRED - 1, QUALITY_GOOD + 2);
		assertBrie(expected, actual);
	}

	@Test
	public void updateQuality_maxBrieQuality_sameQuality() {
		GildedRose app = createGildedRoseWithAgredBrie(SELLIN_NOT_EXPIRED, MAX_QUALITY);

		app.updateQuality();

		Item expected = createAgedBrieItem(SELLIN_NOT_EXPIRED - 1, MAX_QUALITY);
		Item actual = app.items[0];
		assertBrie(expected, actual);
	}
}
