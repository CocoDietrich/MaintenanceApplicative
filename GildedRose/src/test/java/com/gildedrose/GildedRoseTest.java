package com.gildedrose;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private GildedRose createAppWithItem(String name, int sellIn, int quality) {
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        return new GildedRose(items);
    }

    @Test
    void normalItemDecreasesQualityAndSellIn() {
        GildedRose app = createAppWithItem("normal item", 10, 20);
        app.updateQuality();
        Item item = app.getItems()[0];
        assertEquals(9, item.sellIn);
        assertEquals(19, item.quality);
    }

    @Test
    void normalItemQualityNeverNegative() {
        GildedRose app = createAppWithItem("normal item", 10, 0);
        app.updateQuality();
        Item item = app.getItems()[0];
        assertEquals(9, item.sellIn);
        assertEquals(0, item.quality);
    }

    @Test
    void agedBrieIncreasesQuality() {
        GildedRose app = createAppWithItem("Aged Brie", 10, 40);
        app.updateQuality();
        Item item = app.getItems()[0];
        assertEquals(9, item.sellIn);
        assertEquals(41, item.quality);
    }

    @Test
    void agedBrieQualityNeverAbove50() {
        GildedRose app = createAppWithItem("Aged Brie", 10, 50);
        app.updateQuality();
        Item item = app.getItems()[0];
        assertEquals(9, item.sellIn);
        assertEquals(50, item.quality);
    }

    @Test
    void backstagePassesIncreaseQuality() {
        GildedRose app = createAppWithItem("Backstage passes to a TAFKAL80ETC concert", 11, 20);
        app.updateQuality();
        Item item = app.getItems()[0];
        assertEquals(10, item.sellIn);
        assertEquals(21, item.quality);
    }

    @Test
    void backstagePassesIncreaseQualityBy2When10DaysOrLess() {
        GildedRose app = createAppWithItem("Backstage passes to a TAFKAL80ETC concert", 10, 20);
        app.updateQuality();
        Item item = app.getItems()[0];
        assertEquals(9, item.sellIn);
        assertEquals(22, item.quality);
    }

    @Test
    void backstagePassesIncreaseQualityBy3When5DaysOrLess() {
        GildedRose app = createAppWithItem("Backstage passes to a TAFKAL80ETC concert", 5, 20);
        app.updateQuality();
        Item item = app.getItems()[0];
        assertEquals(4, item.sellIn);
        assertEquals(23, item.quality);
    }

    @Test
    void backstagePassesQualityDropsToZeroAfterConcert() {
        GildedRose app = createAppWithItem("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        app.updateQuality();
        Item item = app.getItems()[0];
        assertEquals(-1, item.sellIn);
        assertEquals(0, item.quality);
    }

    @Test
    void sulfurasNeverChanges() {
        GildedRose app = createAppWithItem("Sulfuras, Hand of Ragnaros", 10, 80);
        app.updateQuality();
        Item item = app.getItems()[0];
        assertEquals(10, item.sellIn);
        assertEquals(80, item.quality);
    }

    @Test
    void conjuredItemDegradesTwiceAsFast() {
        GildedRose app = createAppWithItem("Conjured", 5, 10);
        app.updateQuality();
        Item item = app.getItems()[0];
        assertEquals(4, item.sellIn);
        assertEquals(8, item.quality);
    }

    @Test
    void conjuredItemDegradesTwiceAsFastAfterSellIn() {
        GildedRose app = createAppWithItem("Conjured", 0, 10);
        app.updateQuality();
        Item item = app.getItems()[0];
        assertEquals(-1, item.sellIn);
        assertEquals(6, item.quality);
    }
}
