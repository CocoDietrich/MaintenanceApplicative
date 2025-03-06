package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    private final UpdatableItem[] items;

    public GildedRose(Item[] items) {
        this.items = Arrays.stream(items).map(GildedRose::wrapItem).toArray(UpdatableItem[]::new);
    }

    public void updateQuality() {
        for (UpdatableItem item : items) {
            item.update();
        }
    }

    private static UpdatableItem wrapItem(Item item) {
        switch (item.name) {
            case "Aged Brie":
                return new AgedBrieItem(item);
            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackstagePassItem(item);
            case "Sulfuras, Hand of Ragnaros":
                return new SulfurasItem(item);
            case "Conjured":
                return new ConjuredItem(item);
            default:
                return new NormalItem(item);
        }
    }

    public Item[] getItems() {
        return Arrays.stream(items).map(UpdatableItem::getItem).toArray(Item[]::new);
    }
}