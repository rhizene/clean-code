package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RefactoredGildedRoseADefaultItemTest {

	public static final int QUALITY_MIN = 0;
	final String DEFAULT_ITEM = "DEFAULT_ITEM";
	private static final int SELLIN_NOT_EXPIRED = 15;
	private static final int QUALITY_NOT_EXPIRED = 3;
	private static final int SELLIN_EXPIRED = -1;

	private GildedRose getGildedRoseWithOneDefaultItem(int sellin, int quality) {
		Item item = createDefaultItem(sellin, quality);
		Item[] items = new Item[]{item};
		return new GildedRose(items);
	}

	private Item createDefaultItem(int sellin, int quality) {
		return new Item(DEFAULT_ITEM, sellin, quality);
	}


	@Test
	public void testUpdateQuality_notExpired_decreaseQualityBy1() {
		GildedRose app = getGildedRoseWithOneDefaultItem(SELLIN_NOT_EXPIRED, QUALITY_NOT_EXPIRED);

		app.updateQuality();

		final Item expected = createDefaultItem(SELLIN_NOT_EXPIRED - 1, QUALITY_NOT_EXPIRED - 1);
		final Item actual = app.items[0];
		assertEqualItem(expected, actual);
	}

	@Test
	public void updateQuality_expiredItem_decreaseQualityBy2() {
		final int SELLIN = SELLIN_EXPIRED;
		GildedRose app = getGildedRoseWithOneDefaultItem(SELLIN, QUALITY_NOT_EXPIRED);

		app.updateQuality();

		final Item expected = createDefaultItem(SELLIN - 1, QUALITY_NOT_EXPIRED - 2);
		final Item actual = app.items[0];
		assertEqualItem(expected, actual);
	}

	private void assertEqualItem(Item expected, Item actual) {
		assertEquals(expected.name, actual.name, "Name not the same");
		assertEquals(expected.sellIn, actual.sellIn, "Sellin not same");
		assertEquals(expected.quality, actual.quality, "Quality not same");
	}

	@Test
	public void updateQuality_minimumQuality_cappedAtZero() {
		final int SELLIN = SELLIN_NOT_EXPIRED;
		GildedRose app = getGildedRoseWithOneDefaultItem(SELLIN, QUALITY_MIN);

		app.updateQuality();

		final Item expected = createDefaultItem(SELLIN - 1, QUALITY_MIN);
		final Item actual = app.items[0];
		assertEqualItem(expected, actual);
	}

}