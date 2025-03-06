package com.gildedrose;

class AgedBrieItem extends UpdatableItem {
    public AgedBrieItem(Item item) {
        super(item);
    }

    @Override
    public void update() {
        increaseQuality(item.sellIn <= 0 ? 2 : 1);
        decreaseSellIn();
    }
}