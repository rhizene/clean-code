package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RefactoredGildedRoseCBackstagePassesTest {

	public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
	public static final int SELLIN_ABOVE_10 = 15;
	public static final int QUALITY_GOOD = 3;
	private static final int SELLIN_6_TO_10 = 10;
	public static final int SELLIN_BELOW_6 = 4;
	public static final int SELL_IN_EXPIRED = -1;
	public static final int QUALITY_ZERO = 0;
	public static final int QUALITY_MAX = 50;

	private Item createBackstagePassItem(int sellin, int quality) {
		return new Item(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, sellin, quality);
	}

	private GildedRose createGildedRoseWithBackstagePass(int sellIn, int quality) {
		Item item = createBackstagePassItem(sellIn, quality);
		Item[] items = new Item[] { item };
		return new GildedRose(items);
	}

	private void assertItem(Item expected, Item actual) {
		assertEquals(expected.name, actual.name, "Name not the same");
		assertEquals(expected.sellIn, actual.sellIn, "Sellin not same");
		assertEquals(expected.quality, actual.quality, "Quality not same");
	}

	@Test
	public void updateQuality_backstageSellinAbove10_qualityPlus1() {
		final int SELLIN = SELLIN_ABOVE_10;
		GildedRose app = createGildedRoseWithBackstagePass(SELLIN, QUALITY_GOOD);

		app.updateQuality();
		final Item expected = createBackstagePassItem(SELLIN -1, QUALITY_GOOD +1);
		final Item actual = app.items[0];

		assertItem(expected, actual);
	}

	@Test
	public void updateQuality_backstageSellinFrom10_qualityPlus2() {
		GildedRose app =  createGildedRoseWithBackstagePass(SELLIN_6_TO_10, QUALITY_GOOD);

		app.updateQuality();

		final Item expected = createBackstagePassItem(SELLIN_6_TO_10 - 1, QUALITY_GOOD + 2);
		final Item actual = app.items[0];
		assertItem(expected, actual);
	}

	@Test
	public void updateQuality_backstageSellinBelow6_qualityPlus3() {
		GildedRose app = createGildedRoseWithBackstagePass(SELLIN_BELOW_6, QUALITY_GOOD);

		app.updateQuality();

		final Item expected = createBackstagePassItem(SELLIN_BELOW_6 - 1, QUALITY_GOOD + 3);
		final Item actual = app.items[0];
		assertItem(expected, actual);
	}

	@Test
	public void updateQuality_backstageExpired_qualityZero() {
		GildedRose app = createGildedRoseWithBackstagePass(SELL_IN_EXPIRED, QUALITY_MAX);

		app.updateQuality();

		final Item expected = createBackstagePassItem(SELL_IN_EXPIRED - 1, QUALITY_ZERO);
		final Item actual = app.items[0];
		assertItem(expected, actual);
	}

}